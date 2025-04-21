package kr.or.ddit.mybatis.mappers.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.file.vo.FileVO;

@Mapper
public interface FileMapper {

    /**
     * 파일 ID로 파일 정보 조회
     * @param detailId
     * @return
     */
    public FileDetailVO getFileById(@Param("detailId") Long detailId);

    /**
     * 파일 정보 저장 (INSERT)
     * 먼저 부모 테이블에 파일을 삽입하고, 그 후에 반환된 FILE_ID를 자식 테이블에 사용.
     * @param file
     * @return
     */
    public int insertFile(FileVO file);

    /**
     * 파일 상세 정보 저장 (FILE_DETAIL)
     * @param fileDetail
     * @return
     */
    public int insertFileDetail(FileDetailVO fileDetail);

    /**
     * 파일 삭제 (상태 변경)
     * @param fileId
     * @return
     */
    public int deleteFile(@Param("fileId") Long fileId);
    
    
    /**
     * 파일 삭제 (물리 삭제) -> 하나하나 제거 그룹단 x 
     * @param detailId
     * @return
     */
    public int deletePhyFile(@Param("detailId") Long detailId);
    
    /**
     * 모든 파일 목록 조회
     * @return
     */
    public List<FileDetailVO> getAllFiles();

	/**
	 * @param fileId
	 * @return
	 */
	public List<FileDetailVO> getFileDetailsByFileId(Long fileId);
}
