package kr.or.ddit.recruitment.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mybatis.mappers.recruitment.RecruitmentMapper;
import kr.or.ddit.recruitment.vo.RecruitmentVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
	
	@Inject
	private final RecruitmentMapper dao;

	// 채용공고 목록 조회
	@Override
	public List<RecruitmentVO> readRecruitBoardList(String date) {
		return dao.selectRecruitBoardList(date);
	}

	// 채용공고 상세 조회
	@Override
	public RecruitmentVO readRecruitBoardDetail(Long recruitId) {
		return dao.selectRecruitBoardDetail(recruitId);
	}

	// 채용공고 등록
	@Override
	public boolean createRecruitBoard(RecruitmentVO recruitment) {
		return dao.insertRecruitBoard(recruitment) > 0;
	}

	// 채용공고 수정
	@Override
	public boolean modifyRecruitBoard(RecruitmentVO recruitment) {
		return dao.updateRecruitBoard(recruitment) > 0;
	}


	

}
