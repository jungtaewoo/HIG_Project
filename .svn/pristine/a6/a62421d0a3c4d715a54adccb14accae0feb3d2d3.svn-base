package kr.or.ddit.qr.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mybatis.mappers.qr.qrMapper;
import kr.or.ddit.qr.vo.QrVO;

@Service
public class QrServiceImpl implements QrService {
	
	@Autowired
	private qrMapper dao;

	public void saveQR(String token, String empId, String expiresAt) {
		dao.saveQR(token, empId, expiresAt);
	}

	public QrVO getQRInfo(String token) {
		return dao.getQRInfo(token);
	}

	public void deleteQR(String token) {
		dao.deleteQR(token);
	}
}
