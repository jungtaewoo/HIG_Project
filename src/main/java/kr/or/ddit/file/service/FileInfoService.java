package kr.or.ddit.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.file.vo.FileInfoVO;
import kr.or.ddit.file.vo.FileVO;
import kr.or.ddit.mybatis.mappers.file.FileMapper;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileInfoService {

    @Autowired
    private FileMapper mapper;

    @Autowired
    private FileInfoVO fileInfoVO; // 파일 관련 설정 (예: fileUploads, fileImages, maxFileSize 등)

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 파일 그룹 생성
     */
    @Transactional
    public Long createFileGroup() {
        FileVO fileVO = new FileVO();
        fileVO.setFileDel("N");
        mapper.insertFile(fileVO);
        return fileVO.getFileId();
    }

    /**
     * 기존 파일 그룹 업데이트 (파일 교체)
     */
    @Transactional
    public List<Long> updateFileGroup(Long fileId, MultipartFile[] files) throws IOException {
        if (fileId == null) {
            throw new IllegalArgumentException("파일 그룹 ID가 필요합니다.");
        }
        List<Long> detailIds = new ArrayList<>();

        // 기존 파일 삭제
        List<FileDetailVO> existingFiles = mapper.getFileDetailsByFileId(fileId);
        for (FileDetailVO fileDetail : existingFiles) {
            if (fileDetail != null && fileDetail.getFilePath() != null) {
                // 직접 절대경로로 접근 (fileInfoVO.getFileUploads() 혹은 getFileImages()가 절대 경로라 가정)
                String basePath = fileDetail.getFilePath().startsWith(fileInfoVO.getFileImages()) 
                        ? fileInfoVO.getFileImages() 
                        : fileInfoVO.getFileUploads();
                File targetFile = new File(basePath, fileDetail.getFileSavename());
                if (targetFile.exists()) {
                    targetFile.delete();
                }
                mapper.deleteFile(fileDetail.getDetailId());
            }
        }

        long totalRequestSize = 0;
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;
            long fileSize = file.getSize();
            if (fileSize > fileInfoVO.getMaxFileSize() * 1024 * 1024) {
                throw new IllegalArgumentException("파일 크기가 너무 큽니다.");
            }
            totalRequestSize += fileSize;
            if (totalRequestSize > fileInfoVO.getMaxRequestSize() * 1024 * 1024) {
                throw new IllegalArgumentException("전체 요청 크기가 너무 큽니다.");
            }
            String contentType = file.getContentType();
            boolean isImage = contentType != null && contentType.startsWith("image");
            String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
            File uploadDir = new File(basePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String originalFileName = file.getOriginalFilename();
            String uuidFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            File savedFile = new File(uploadDir, uuidFileName);
            try {
                file.transferTo(savedFile);
            } catch (IOException e) {
                throw new IOException("파일 저장 실패: " + savedFile.getAbsolutePath(), e);
            }
            String relativeFilePath = basePath + uuidFileName;
            FileDetailVO fileDetail = new FileDetailVO();
            fileDetail.setFileId(fileId);
            fileDetail.setFileName(originalFileName);
            fileDetail.setFileSavename(uuidFileName);
            fileDetail.setFilePath(relativeFilePath);
            fileDetail.setFileSize(fileSize);
            fileDetail.setFileType(contentType);
            mapper.insertFileDetail(fileDetail);
            detailIds.add(fileDetail.getDetailId());
        }
        return detailIds;
    }

    /**
     * 파일 저장 (새 파일 그룹 생성)
     */
    @Transactional
    public List<Long> saveFiles(MultipartFile[] files) throws Exception {
        List<Long> detailIds = new ArrayList<>();
        FileVO fileVO = new FileVO();
        fileVO.setFileDel("N");
        mapper.insertFile(fileVO);
        Long fileId = fileVO.getFileId();
        long totalRequestSize = 0;
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;
            long fileSize = file.getSize();
            if (fileSize > fileInfoVO.getMaxFileSize() * 1024 * 1024) {
                throw new IllegalArgumentException("파일 크기가 너무 큽니다.");
            }
            totalRequestSize += fileSize;
            if (totalRequestSize > fileInfoVO.getMaxRequestSize() * 1024 * 1024) {
                throw new IllegalArgumentException("전체 요청 크기가 너무 큽니다.");
            }
            String contentType = file.getContentType();
            boolean isImage = contentType != null && contentType.startsWith("image");
            String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
            File uploadDir = new File(basePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String originalFileName = file.getOriginalFilename();
            String uuidFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            File savedFile = new File(uploadDir, uuidFileName);
            try {
                file.transferTo(savedFile);
            } catch (IOException e) {
                throw new IOException("파일 저장 실패: " + savedFile.getAbsolutePath(), e);
            }
            String relativeFilePath = basePath + uuidFileName;
            FileDetailVO fileDetail = new FileDetailVO();
            fileDetail.setFileId(fileId);
            fileDetail.setFileName(originalFileName);
            fileDetail.setFileSavename(uuidFileName);
            fileDetail.setFilePath(relativeFilePath);
            fileDetail.setFileSize(fileSize);
            fileDetail.setFileType(file.getContentType());
            mapper.insertFileDetail(fileDetail);
            detailIds.add(fileDetail.getDetailId());
        }
        return detailIds;
    }

    // 파일 ID로 파일 정보 조회
    public FileDetailVO getFileById(Long detailId) {
        return mapper.getFileById(detailId);
    }

    /**
     * 논리 삭제 (상태 변경)
     */
    @Transactional
    public void deleteFile(Long fileId) {
        FileDetailVO fileDetail = mapper.getFileById(fileId);
        if (fileDetail != null && fileDetail.getFilePath() != null) {
            // (논리 삭제: FILE_STATUS 변경)
            mapper.deleteFile(fileId);
        }
    }

    /**
     * 물리 삭제 (디스크 파일 + DB 레코드 삭제)
     * @param detailId - FILE_DETAIL 테이블의 DETAIL_ID
     */
    @Transactional
    public void deletePhyFile(Long detailId) {
        FileDetailVO fileDetail = mapper.getFileById(detailId);
        if (fileDetail == null) {
            throw new RuntimeException("삭제할 파일 정보를 찾을 수 없습니다. (DETAIL_ID=" + detailId + ")");
        }
        String relativeFilePath = fileDetail.getFilePath();
        if (relativeFilePath == null || relativeFilePath.isEmpty()) {
            throw new RuntimeException("파일 경로가 유효하지 않습니다.");
        }
        // basePath는 fileInfoVO.getFileUploads() 또는 fileInfoVO.getFileImages() (절대경로)
        boolean isImage = relativeFilePath.startsWith(fileInfoVO.getFileImages());
        String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
        // 실제 저장된 파일은 basePath와 fileSavename로 구성 (fileName은 원본)
        File targetFile = new File(basePath, fileDetail.getFileSavename());
        if (targetFile.exists()) {
            boolean deleted = targetFile.delete();
            if (!deleted) {
                throw new RuntimeException("디스크 파일 삭제 실패: " + targetFile.getAbsolutePath());
            }
        } else {
            System.out.println("디스크에 해당 파일이 존재하지 않습니다: " + targetFile.getAbsolutePath());
        }
        // DB에서 물리적 삭제 (레코드 완전 삭제)
        mapper.deletePhyFile(fileDetail.getDetailId());
    }

    /**
     * 모든 파일 목록 조회
     */
    public List<FileDetailVO> getAllFiles() {
        return mapper.getAllFiles();
    }

    /**
     * 파일 다운로드, 뷰어 등에 사용할 Resource 로딩
     */
    public Resource loadFileAsResource(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("파일 이름이 제공되지 않았습니다.");
        }
        boolean isImage = fileName.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp|webp)$");
        String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
        if (!basePath.endsWith("/") && !basePath.endsWith(File.separator)) {
            basePath += "/";
        }
        Resource resource = resourceLoader.getResource("file:" + basePath + fileName);
        if (!resource.exists() || !resource.isReadable()) {
            throw new IOException("파일을 찾을 수 없거나 읽을 수 없습니다: " + fileName);
        }
        return resource;
    }

    /**
     * 파일 저장 (FILE_DETAIL 레코드 생성 포함)
     */
    @Transactional
    public Long saveFileWithGroup(MultipartFile file, Long fileId) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드된 파일이 비어 있습니다.");
        }
        String contentType = file.getContentType();
        boolean isImage = contentType != null && contentType.startsWith("image");
        String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
        log.info("파일 저장 경로 basePath : {}", basePath);
        File uploadPath = new File(basePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String originalFileName = file.getOriginalFilename();
        String uuidFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        File savedFile = new File(uploadPath, uuidFileName);
        try {
            file.transferTo(savedFile);
        } catch (IOException e) {
            throw new IOException("파일 저장 실패: " + savedFile.getAbsolutePath(), e);
        }
        String relativeFilePath = basePath + uuidFileName;
        FileDetailVO fileDetail = new FileDetailVO();
        fileDetail.setFileId(fileId);
        fileDetail.setFileName(originalFileName);
        fileDetail.setFileSavename(uuidFileName);
        fileDetail.setFilePath(relativeFilePath);
        fileDetail.setFileSize(file.getSize());
        fileDetail.setFileType(contentType);
        mapper.insertFileDetail(fileDetail);
        return fileDetail.getDetailId();
    }

    /**
     * 파일 그룹 생성 및 여러 파일 저장
     */
    @Transactional
    public Long createFile(MultipartFile[] files) throws IOException {
        FileVO fileVO = new FileVO();
        fileVO.setFileDel("N");
        mapper.insertFile(fileVO);
        Long fileId = fileVO.getFileId();
        long totalRequestSize = 0;
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;
            long fileSize = file.getSize();
            if (fileSize > fileInfoVO.getMaxFileSize() * 1024 * 1024) {
                throw new IllegalArgumentException("파일 크기가 너무 큽니다.");
            }
            totalRequestSize += fileSize;
            if (totalRequestSize > fileInfoVO.getMaxRequestSize() * 1024 * 1024) {
                throw new IllegalArgumentException("전체 요청 크기가 너무 큽니다.");
            }
            String contentType = file.getContentType();
            boolean isImage = contentType != null && contentType.startsWith("image");
            String basePath = isImage ? fileInfoVO.getFileImages() : fileInfoVO.getFileUploads();
            File uploadDir = new File(basePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String originalFileName = file.getOriginalFilename();
            String uuidFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            File savedFile = new File(uploadDir, uuidFileName);
            try {
                file.transferTo(savedFile);
            } catch (IOException e) {
                throw new IOException("파일 저장 실패: " + savedFile.getAbsolutePath(), e);
            }
            String relativeFilePath = basePath + uuidFileName;
            FileDetailVO fileDetail = new FileDetailVO();
            fileDetail.setFileId(fileId);
            fileDetail.setFileName(originalFileName);
            fileDetail.setFileSavename(uuidFileName);
            fileDetail.setFilePath(relativeFilePath);
            fileDetail.setFileSize(fileSize);
            fileDetail.setFileType(file.getContentType());
            mapper.insertFileDetail(fileDetail);
        }
        return fileId;
    }
}
