package kr.or.ddit.approval.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.approval.vo.DraftTemplateVO;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.team.vo.TeamVO;

public interface ApprovalService {
	
	
	/**
	 * 결재 양식 리스트 
	 * @return
	 */
	public List<DraftTemplateVO> draftTemplateList();
	
	
	
	/**
	 * 결재 양식 리스트(일반 사원)
	 * @return
	 */
	public List<DraftTemplateVO> draftTemplateListEmp();
	
	
	/**
	 * 카테고리 목록 불러오기 
	 * @return
	 */
	public List<String> getAllTemplateCategories(); 
	
	
	/**
     * 결재 양식 동적 불러오기 
     * @param templateId
     * @return
     */
    public FileDetailVO getTemplateFileByTemplateId(Long templateId);
	
	/**
	 * 결재 양식 정보 
	 * @param templateId
	 * @return
	 */
	public DraftTemplateVO draftTemplateDetail(Long templateId);
	
	
	/**
	 * 결재 양식 수정 
	 * @param draftTemplateVO
	 * @param fileIds 
	 */
	public void updateDraftTemplate(DraftTemplateVO draftTemplateVO, List<Long> fileIds);
	
	
	
	/**
	 * 결재 양식 등록 할 때 팀 목록 선택
	 * @return
	 */
	public List<TeamVO> getTeamList();
	
	/**
	 * 결재 양식 등록
	 * @param draftTemplate 
	 * @return
	 */
	public void insertDraftTemplate(DraftTemplateVO draftTemplate, List<Long> fileIds);
	
	
	

	/**
	 * 결재 양식 파일 정보 저장
	 * @param paramMap
	 */
	public void insertDraftTemplateFile(Map<String, Object> paramMap);
	
	/**
	 * 결재 양식 삭제 
	 * @param templateIds
	 */
	public void deleteDraftTemplates(List<Long> templateIds);

	

}
