package kr.or.ddit.recruitment.service;

import java.util.List;

import kr.or.ddit.recruitment.vo.RecruitmentVO;

public interface RecruitmentService {
	
	// 목록 조회
	public List<RecruitmentVO> readRecruitBoardList(String date);
	
	// 상세 조회
	public RecruitmentVO readRecruitBoardDetail(Long recruitId);
	
	// 등록
	public boolean createRecruitBoard(RecruitmentVO recruitment);
	
	// 수정
	public boolean modifyRecruitBoard(RecruitmentVO recruitment);
	

}
