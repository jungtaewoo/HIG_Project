package kr.or.ddit.qr.service;

import java.time.LocalDateTime;

import kr.or.ddit.qr.vo.QrVO;

public interface QrService {
	public void saveQR(String token, String empId,  String expiresAt);

    public QrVO getQRInfo(String token);

    public void deleteQR(String token);
}
