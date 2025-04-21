package kr.or.ddit.mybatis.mappers.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.recruitment.vo.RecruitmentVO;

@Mapper
public interface RecruitmentMapper {

	//채용공고 목록 조회
	public List<RecruitmentVO> selectRecruitBoardList(String date);
	
	//채용공고 상세 조회
	public RecruitmentVO selectRecruitBoardDetail(@Param("recruitId") Long recruitId);
	
	//채용공고 등록
	public int insertRecruitBoard(RecruitmentVO recruitment);
	
	//채용공고 수정
	public int updateRecruitBoard(RecruitmentVO recruitment);
	
}
