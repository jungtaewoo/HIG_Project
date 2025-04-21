package kr.or.ddit.mybatis.mappers.qr;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.qr.vo.QrVO;

@Mapper
public interface qrMapper {
	public QrVO getQRInfo(@Param("token") String token);
	public void saveQR(@Param("token") String token, @Param("empId") String empId, @Param("expiresAt") String expiresAt);
	public void deleteQR(@Param("token") String token);
}
