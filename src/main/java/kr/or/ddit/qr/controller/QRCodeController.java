package kr.or.ddit.qr.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.or.ddit.annual.service.AnnualHistoryService;
import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.attendance.service.AttendanceService;
import kr.or.ddit.attendance.vo.AttendanceVO;
import kr.or.ddit.qr.service.QrService;
import kr.or.ddit.qr.vo.QrVO;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * 
 * @author 정태우
 * @since 2025. 3. 14.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	정태우	          최초 생성
 *
 * </pre>
 */
@Controller
@Slf4j
public class QRCodeController {
	
	private SecretKey secretKey;
	
	@Autowired
	private AnnualHistoryService AHservice;
	
	@Autowired
	private AttendanceService service;
	
	@Autowired
	private QrService qrservice;
	
    @Autowired
    private QrWebSocket qrWebSocket;
    
    /**
     * QR스캔시 들어오는 메서드 
     * @param token
     * @param model
     * @return
     */
	@GetMapping("/qrqr")
	public ResponseEntity<String> locationQR(@RequestParam("token") String token) {
		try {
			// 1. QR 코드 유효성 검사
			QrVO qrInfo = qrservice.getQRInfo(token);
			if (qrInfo == null)
				throw new RuntimeException("유효하지 않은 QR 코드 입니다.");
			
			//token안에 claims에서 id와 유효시간 추출
			Claims claims = Jwts.parser()
				    .setSigningKey(secretKey.getEncoded())  // 반드시 같은 키로 복호화해야 함
				    .parseClaimsJws(token)                  // 토큰 문자열
				    .getBody();     
			String empId = claims.get("empId",String.class);
			String expiresAt = claims.get("formattedExpireAt", String.class);
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime expirationTime = LocalDateTime.parse(expiresAt, formatter);

			// 2. QR 코드 만료 여부 확인
			if (expirationTime.isBefore(now))
				throw new RuntimeException("QR 코드가 만료되었습니다.");

			// 3. 오늘의 출근 기록 조회
			AttendanceVO existingAttendance = service.findTodayAttendance(empId);
			
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
			String formattedDate = now.format(formatterDate);
			String formattedTime = now.format(formatterTime);

			String message;
			if (existingAttendance != null) {
				// 퇴근 처리
				existingAttendance.setWorkEndTime(formattedTime);
				existingAttendance.setStatusId("STAT_03");
				service.updateWorkEnd(existingAttendance);
				message = "퇴근이 정상적으로 처리되었습니다.";
			} else {
				// 출근 처리
				AttendanceVO attendance = new AttendanceVO();
				LocalDate today = LocalDate.now();
				String attendanceStatus = "정상"; // 기본값
				boolean isMorningLeave = false;

				// 한 직원의 연차 기록 목록
				List<AnnualHistoryVO> empHistoryList = AHservice.EmpHisrotyDetail(qrInfo.getEmpId());
				// 오늘자 연차가 있으면서 그게 오전반차일때 정상 및 오전반차 체크
				for (AnnualHistoryVO empHistory : empHistoryList) {
					DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime leaveDateTime = LocalDateTime.parse(empHistory.getLeaveDate(), formatterDateTime);
					LocalDate leaveDateL = leaveDateTime.toLocalDate();
					
					if (today.equals(leaveDateL) && "AC_10".equals(empHistory.getAnnualCode())) { 
						isMorningLeave = true;
						break;
					}
				}
				// 출근 상태 결정
				if (isMorningLeave) {
					attendanceStatus = now.toLocalTime().isBefore(LocalTime.of(13, 1)) ? "오전반차" : "지각";
				} else {
					attendanceStatus = now.toLocalTime().isBefore(LocalTime.of(9, 1)) ? "정상" : "지각";
				}
				// 공통 출근 처리
				attendance.setEmpId(empId);
				attendance.setWorkDate(formattedDate);
				attendance.setWorkStartTime(formattedTime);
				attendance.setAttendanceStatus(attendanceStatus);
				attendance.setStatusId("STAT_01");
				service.attendanceInsert(attendance);
				message = "출근이 정상적으로 처리되었습니다.";
			}
			// 공통 후처리
			qrservice.deleteQR(token);
			qrWebSocket.sendMessageToUser(empId, message);
			
			String script = "<script> window.close();</script>";
			return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(script);
		} catch (Exception e) {
			// 6. 예외 발생 시 에러 메시지 반환
			String errorScript = "<script>alert('오류 발생: " + e.getMessage() + "'); window.close();</script>";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_HTML).body(errorScript);
		}
	}

	
	/**
	 * QR을 만들어주고 그 안에 동적인 IP와 토큰을 만들어줌
	 * @param model
	 * @param session
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/generate-qr")
	public String generateQRCode(Model model, HttpSession session, HttpServletRequest req) throws Exception {
	    Authentication account = SecurityContextHolder.getContext().getAuthentication();
	    // 로그인된 사용자 ID
	    String empId = account.getName(); 
	    
	    LocalDate today = LocalDate.now();
	    
	    // 한 직원의 연차 기록 목록
		List<AnnualHistoryVO> empHistoryList = AHservice.EmpHisrotyDetail(empId);
		
		boolean isOnLeave = false; // 연차 여부 체크 변수
		
		// 오늘이 연차기록에 있다면 QR 생성 불가 return
		for (AnnualHistoryVO empHistory : empHistoryList) {
			DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime leaveDateTime = LocalDateTime.parse(empHistory.getLeaveDate(), formatterDateTime);
			LocalDateTime leaveEndDateTime = LocalDateTime.parse(empHistory.getLeaveEndDate(), formatterDateTime);
			
			LocalDate leaveDateL = leaveDateTime.toLocalDate();
			LocalDate leaveEndDateL = leaveEndDateTime.toLocalDate();
			
			// 오늘 날짜가 연차 시작일과 종료일 사이에 있고, 연차 코드가 "AC_10" 또는 "AC_11"이 아닌 경우
			if ((today.isEqual(leaveDateL) || today.isAfter(leaveDateL)) &&
			    (today.isEqual(leaveEndDateL) || today.isBefore(leaveEndDateL)) &&
			    !("AC_10".equals(empHistory.getAnnualCode()) || "AC_11".equals(empHistory.getAnnualCode()))) {
			    
			    isOnLeave = true;
			    break;
			}	
		}
		
		if (isOnLeave) {
			String message = "연차인 직원은 QR스캔 불가.";
//			qrWebSocket.sendMessageToUser(empId, message);
			 model.addAttribute("qrAlertMessage", message);
		    return "qr/NotQr";
		}
	    // QR 만료 시간 ( 5분 후 만료 )
	    LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedExpireAt = expiresAt.format(formatter);
	    
	    // empID와 만료기한을 맵에 등록
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("empId", empId);
	    claims.put("formattedExpireAt", formattedExpireAt);
	    
	    // HmacSHA256 알고리즘을 사용하여 비밀 키 생성
	    secretKey = createSecretKey();
	    String token = Jwts.builder()
	    		.setClaims(claims)
	    	    .setExpiration(new Date(System.currentTimeMillis() + (5 * 60 * 1000))) // 5분 후 만료
	    	    .signWith(SignatureAlgorithm.HS256, secretKey.getEncoded()) // byte[]로 변환된 비밀키 사용
	    	    .compact();
	    
	    // DB에 QR 정보 저장 
	    qrservice.saveQR(token);
	    
	    // 서버 IP 가져오기
	    String ip = getIp();
	    
	    // QR에 넣어줄 링크 데이터 
	    String qrData = "https://" + ip + req.getContextPath() + "/qrqr?token=" + token + "&autoClose=true";
	    try {
	        int size = 400; // QR 코드 사이즈 
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
	        hints.put(EncodeHintType.MARGIN, 1); // QR 코드 여백 설정

	        // QR 데이터로 QR 코드 생성
	        BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, size, size, hints);

	        // BitMatrix를 BufferedImage로 변환
	        BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < size; x++) {
	            for (int y = 0; y < size; y++) {
	                bufferedImage.setRGB(x, y, (bitMatrix.get(x, y)) ? 0x000000 : 0xFFFFFF); // 검정과 흰색으로 설정
	            }
	        }
	        
	        // 이미지를 ByteArray로 변환하여 Base64로 인코딩
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
	        byte[] qrImageBytes = byteArrayOutputStream.toByteArray();
	        String qrCodeImage = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(qrImageBytes);

	        // 모델에 QR 코드 이미지 데이터 전달
	        model.addAttribute("qrCode", qrCodeImage);
	        
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return "qr/QRcode"; // 모달로 QR코드를 랜더링 할 JSP
	}
	
	// 비밀 키 생성 메소드 (HmacSHA256 알고리즘 사용)
	private SecretKey createSecretKey() throws Exception {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
	    keyGenerator.init(256); // 키 크기 설정 (256비트)
	    return keyGenerator.generateKey();
	}
	private static final  String IP_PREFIX = "192.168.";  // 원하는 ip 대역 설정
	
	public String getIp() throws SocketException {
	    String wifiIp = null;

	    for (NetworkInterface netIf : Collections.list(NetworkInterface.getNetworkInterfaces())) {
	        String name = netIf.getName().toLowerCase();
	        String displayName = netIf.getDisplayName().toLowerCase();

	        // 🚫 가상 어댑터 필터링 (displayName에 기반)
	        if (displayName.contains("vethernet") || displayName.contains("wsl") ||
	            displayName.contains("hyper-v") || displayName.contains("virtual") ||
	            displayName.contains("docker") || displayName.contains("nat") ||
	            displayName.contains("vmnet") || displayName.contains("loopback") ||
	            displayName.contains("br-")) {
	            continue;
	        }

	        for (InetAddress addr : Collections.list(netIf.getInetAddresses())) {
	            if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
	                String ip = addr.getHostAddress();

	                // 🧠 유선 우선, IP 대역도 필터링
	                if (ip.startsWith(IP_PREFIX) || ip.startsWith("10.") || ip.matches("172\\.(1[6-9]|2[0-9]|3[0-1])\\..*")) {
	                    if (name.startsWith("eth") || name.startsWith("en") || name.startsWith("ens")) {
	                        return ip;
	                    }
	                    if (name.startsWith("wlan") || name.startsWith("wi") || name.contains("wifi")) {
	                        wifiIp = ip;
	                    }
	                }
	            }
	        }
	    }

	    return wifiIp; // 유선이 없으면 무선 반환
	}
}
