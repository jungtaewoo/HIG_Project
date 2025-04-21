package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.file.vo.FileDetailVO.FileType;
import kr.or.ddit.file.vo.FileInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    private FileInfoService fileService;

    @Autowired
    private FileInfoVO fileInfoVO;
    
    /**
     * 파일 업로드 (다중 파일)
     */
    @PostMapping("upload")
    public ResponseEntity<List<Long>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<Long> fileIds = fileService.saveFiles(files);
            return ResponseEntity.ok(fileIds);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 파일 다운로드 (이미지도 다운로드 가능 - inline 또는 attachment)
     */
    @GetMapping("download/{detailId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("detailId") Long detailId) {
        try {
            // 1) DB에서 파일 상세 정보 조회
            FileDetailVO fileDetail = fileService.getFileById(detailId);
            
            // 2) 실제 파일(Resource) 로딩 (파일명은 uuid가 붙은 저장 파일명)
            Resource resource = fileService.loadFileAsResource(fileDetail.getFileSavename());

            // 3) 파일 전체 경로 구하기
            //    fileDetail.getFilePath()는 저장 시 설정된 (이미지/일반 파일 구분된) 경로입니다.
            String fileFullPath = fileDetail.getFilePath();
            File file = new File(fileFullPath);
            
            // 4) 파일 MIME 타입 자동 감지 (이미지이면 image/jpeg, image/png 등)
            String mimeType = Files.probeContentType(file.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            // 5) 파일명 UTF-8 인코딩 (한글 파일명 깨짐 방지)
            String encodedFileName = java.net.URLEncoder
                                        .encode(fileDetail.getFileName(), StandardCharsets.UTF_8)
                                        .replaceAll("\\+", "%20");

            // 6) Content-Disposition 결정
            //    fileDetail.getType() 값에 따라 inline (브라우저 내 표시) 또는 attachment (다운로드)로 처리
            //    만약 이미지도 무조건 다운로드 받게 하려면 "attachment"로 고정하면 됩니다.
            String contentDisposition = (fileDetail.getType() == FileType.INLINE)
                ? "inline"
                : "attachment";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                            contentDisposition + "; filename*=UTF-8''" + encodedFileName)
                    .body(resource);

        } catch (Exception e) {
            log.error("파일 다운로드 실패", e);
            return ResponseEntity.notFound().build();
        }
    }


    /**
     *  파일 삭제 (논리 삭제)
     */
    @DeleteMapping("delete/{detailId}")
    public ResponseEntity<String> deleteFile(@PathVariable("detailId") Long detailId) {
        try {
            fileService.deleteFile(detailId);
            return ResponseEntity.ok("파일 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("파일 삭제 실패");
        }
    }

    
    /**
     * 물리 삭제(디스크 파일 + DB 레코드 삭제)
     * @param detailId
     * @return
     */
    @DeleteMapping(
	    value = "physical/{detailId}",
	    produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> deletePhyFile(@PathVariable("detailId") Long detailId){
    	try {
    		log.info("파일 삭제 -> 단위 파일 {}",detailId);
    		fileService.deletePhyFile(detailId);
			return ResponseEntity.ok("파일 물리 삭제 완료!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("파일 물리 삭제 실패 "+ e.getMessage());
		}
    }
    
    /**
     * 파일 리스트 조회
     */
    @GetMapping("list")
    public ResponseEntity<List<FileDetailVO>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    
    
    /**
     * 이미지 뷰어 
     * @param detailId
     * @param response
     * @throws IOException
     */
    @GetMapping("view/detail/{detailId}")
    public void viewFileByDetailId(@PathVariable("detailId") Long detailId, HttpServletResponse response) throws IOException {
        // 1. DB에서 파일 상세정보를 조회
        FileDetailVO fileDetail = fileService.getFileById(detailId);
        if (fileDetail == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일 정보가 없습니다.");
            return;
        }
        
        // 2. 파일이 이미지 파일인지 확인 (필요하다면 FileType 등으로 체크)
        boolean isImage = fileDetail.getFileType() != null && fileDetail.getFileType().startsWith("image");
        
        // 3. basePath는 이미지인지 일반 파일인지에 따라 결정
        String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
        
        // 4. 실제 파일은 basePath와 저장된 파일명(fileSavename)으로 구성됨
        File file = new File(basePath, fileDetail.getFileSavename());
        
        log.info("파일 요청: {}", file.getAbsolutePath());
        
        if (!file.exists()) {
            log.error("파일을 찾을 수 없음: {}", file.getAbsolutePath());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        // 5. MIME 타입 자동 감지
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setContentLengthLong(file.length());
        
        try (var inputStream = Files.newInputStream(file.toPath());
             var outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        }
    }

    
    
    
    /**
     *  이미지 파일 다운로드 (Resource 활용)
     */
    @GetMapping("view/{fileName}")
    public void viewFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        // 디코딩 처리
        String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

        // 우선 파일 확장자 체크 (이미지인지 아닌지)
        boolean isImage = decodedFileName.matches("(?i).+\\.(jpg|jpeg|png|gif|bmp|webp)$");

        // 경로 분기 처리
        String fileDirectory = isImage 
            ? fileInfoVO.getFileImages()               // 이미지 경로
            : fileInfoVO.getFileUploads();             // 일반 파일 경로 (이 경로는 서버 설정에 맞게 조정 가능)

        String filePath = fileDirectory + decodedFileName;
        File file = new File(filePath);

        log.info("파일 요청: {}", filePath);

        if (!file.exists()) {
            log.error("파일을 찾을 수 없음: {}", filePath);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // MIME 타입 자동 감지
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLengthLong(file.length());

        try (var inputStream = Files.newInputStream(file.toPath());
             var outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        }
    }


}
