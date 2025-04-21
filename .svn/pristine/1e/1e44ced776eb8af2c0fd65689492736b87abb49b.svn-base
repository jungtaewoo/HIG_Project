package kr.or.ddit.signature.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.signature.vo.SignatureVO;

@Service
public interface SignatureService {
	//서명추가
	int insertSign(SignatureVO sign);
	//모든 서명 조회
	List<SignatureVO> selectSign(); 
	//특정 직원의 서명 조회
	SignatureVO getSignature(String empId);
	//서명 덮어쓰기
	int updateSign(SignatureVO sign);
}
