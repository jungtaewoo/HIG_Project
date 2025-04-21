package kr.or.ddit.empresignations.service;

import java.util.List;

import kr.or.ddit.empresignations.vo.ResignationVO;

public interface ResignationService {
	
	public List<ResignationVO> readResignationList();
	public ResignationVO readResignation(String resignId);
	public boolean createResignation(ResignationVO resign);
	
}
