package kr.or.ddit.mybatis.mappers.empresignations;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.empresignations.vo.ResignationVO;

@Mapper
public interface ResignationMapper {

	public List<ResignationVO> selectResignList();
	public ResignationVO selectResign(String resignId);
	public int insertResign(ResignationVO resign);
}
