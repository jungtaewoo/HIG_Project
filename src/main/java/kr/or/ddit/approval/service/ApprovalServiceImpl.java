package kr.or.ddit.approval.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.approval.vo.DraftTemplateVO;
import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.mybatis.mappers.approval.ApprovalMapper;
import kr.or.ddit.team.vo.TeamVO;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
	@Autowired
	private ApprovalMapper mapper;
	
	@Inject
	private FileInfoService service; //파일 
	
	@Override
	public List<DraftTemplateVO> draftTemplateList(){
		return mapper.DraftTemplateList();
		
	}
	
	@Override
	public List<DraftTemplateVO> draftTemplateListEmp() {
		return mapper.draftTemplateListEmp();
	}
	
	/**
     * 결재 양식 동적 불러오기 
     * @param templateId
     * @return
     */
	@Override
    public FileDetailVO getTemplateFileByTemplateId(Long templateId) {
    	return mapper.getTemplateFileByTemplateId(templateId);
    }
	
	@Override
	public DraftTemplateVO draftTemplateDetail(Long templateId) {
		return mapper.draftTemplateDetail(templateId);	
	}


	
	/**
	 * 결재 양식 파일 정보 저장
	 * @param paramMap
	 */
	@Override
	public void insertDraftTemplateFile(Map<String, Object> paramMap) {
		mapper.insertDraftTemplateFile(paramMap);
	}

	
	@Override
	public void insertDraftTemplate(DraftTemplateVO draftTemplate, List<Long> fileIds) {
		mapper.insertDraftTemplate(draftTemplate);
		
	}

	@Override
	public void updateDraftTemplate(DraftTemplateVO draftTemplateVO, List<Long> fileIds) {
	    mapper.updateDraftTemplate(draftTemplateVO);
	    
	}

    // 여러 양식 삭제
    @Override
    public void deleteDraftTemplates(List<Long> templateIds) {
        for (Long templateId : templateIds) {
            mapper.deleteDraftTemplate(templateId);
        }
    }

	/**
	 * 팀 리스트 
	 */
	@Override
	public List<TeamVO> getTeamList() {
		return mapper.getTeamList();
	}

	/**
	 * 양식 리스트
	 */
	@Override
	public List<String> getAllTemplateCategories() {
		return mapper.getAllTemplateCategories();
	}

	
	
	
}
