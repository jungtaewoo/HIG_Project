package kr.or.ddit.mybatis.mappers.approval;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.approval.vo.DraftTemplateVO;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.team.vo.TeamVO;

@Mapper
public interface ApprovalMapper {
	
	
	/**
	 * 결재 양식 리스트(관리자)
	 * @return
	 */
	public List<DraftTemplateVO> DraftTemplateList();
	
	
	
	
	/**
	 * 결재 양식 리스트(일반 사원)
	 * @return
	 */
	public List<DraftTemplateVO> draftTemplateListEmp();
	
	/**
	 * 카테고리 리스트 
	 * @return
	 */
	public List<String> getAllTemplateCategories();
	
    /**
     * 결재 양식 동적 불러오기 
     * @param templateId
     * @return
     */
    public FileDetailVO getTemplateFileByTemplateId(@Param("templateId") Long templateId);
	
	
	/**
	 * 결재 양식 상세 정보 
	 * @param templateId
	 * @return
	 */
	public DraftTemplateVO draftTemplateDetail(@Param("templateId") Long templateId);
	
	
	/**
	 * 결재 양식 수정 -> callbyreference -> 리턴값 x 
	 * @param draftTemplateVO
	 */
	public void updateDraftTemplate(DraftTemplateVO draftTemplateVO);
	
	
	
	/**
	 * 결재 양식 등록 시 팀 리스트 
	 * @return
	 */
	public List<TeamVO> getTeamList();
	
	/**
	 * 결재 양식 등록
	 * @param draftTemplate 
	 * @return
	 */
	public int insertDraftTemplate(DraftTemplateVO draftTemplate);
	
	



    /**
     * 결재 양식 파일 정보 저장
     * @param templateId
     * @param fileId
     * @return
     */
	public void insertDraftTemplateFile(Map<String, Object> paramMap);

	/**
	 * 결재 양식 정보 삭제 
	 * @param draftTemplateVO
	 */
	public void deleteDraftTemplate(@Param("templateId") Long templateId);




}
