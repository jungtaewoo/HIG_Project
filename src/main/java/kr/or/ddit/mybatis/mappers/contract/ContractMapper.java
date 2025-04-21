package kr.or.ddit.mybatis.mappers.contract;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.contract.vo.ContractVO;

@Mapper
public interface ContractMapper {
	
	//모든 근로계약서 조회임
	List<ContractVO> contractList();
	//특정 사원의 근로계약서 조회임
	ContractVO selectContract(String empId);
	//근로계약서 등록임
	int insertContract(ContractVO contract);
	//근로계약서가 없는 사원 조회임
	List<ContractVO> unContractList();
	//근로계약서 조회시 서명 이미지가 포함댐
	List<ContractVO> selectContractSign();
	//근로계약서의 사원이 서명시 업데이트
	int updateContractSign(@Param("contractId") int contractId, @Param("signId") long signId);
	//내 근로계약서 조회
	ContractVO selectMyContract(@Param("empId") String empId);
	
	ContractVO selectAutoSalaryByEmpId(String empId);
	
	
}
