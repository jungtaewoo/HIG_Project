package kr.or.ddit.contract.service;

import java.util.List;

import kr.or.ddit.contract.vo.ContractVO;

public interface Contractservice {
    List<ContractVO> ContractList();
    
    ContractVO selectContract(String empId);
    
    int insertContract(ContractVO contract);
    
    List<ContractVO> unContractList();
    
    List<ContractVO> selectContractSign();
    
    int updateContractSign(int contractId, long signId);
    
    ContractVO selectMyContract(String empId);
    
    ContractVO createContractWithAutoSalary(String empId);

}
