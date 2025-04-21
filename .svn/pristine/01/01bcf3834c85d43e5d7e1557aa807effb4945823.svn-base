package kr.or.ddit.empresignations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.empresignations.exception.ResignNotFoundException;
import kr.or.ddit.empresignations.vo.ResignationVO;
import kr.or.ddit.mybatis.mappers.empresignations.ResignationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResignationServiceImpl implements ResignationService {

	private final ResignationMapper dao;
	
	@Override
	public List<ResignationVO> readResignationList() {
		return dao.selectResignList();
	}

	@Override
	public ResignationVO readResignation(String resignId) throws ResignNotFoundException {
		ResignationVO resign = dao.selectResign(resignId);
		if(resign==null) {
			throw new ResignNotFoundException(resignId);
		}
		return resign;
	}

	@Override
	public boolean createResignation(ResignationVO resign) {
		int rowcnt = dao.insertResign(resign);
		return rowcnt > 0;
	}

}
