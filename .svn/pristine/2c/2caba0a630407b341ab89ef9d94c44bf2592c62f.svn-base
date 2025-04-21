package kr.or.ddit.contract.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.contract.vo.ContractVO;
import kr.or.ddit.mybatis.mappers.contract.ContractMapper;

@Service
public class ContractServiceImpl implements Contractservice{

	 @Autowired
	 private ContractMapper mapper;
	    @Override
	    public List<ContractVO> ContractList() {
	        return mapper.contractList();
	    }

	    @Override
	    public ContractVO selectContract(String empId) {
	        return mapper.selectContract(empId);
	    }

		@Override
		public int insertContract(ContractVO contract) {
			return mapper.insertContract(contract);
		}

		@Override
		public List<ContractVO> unContractList() {
			return mapper.unContractList();
		}

		@Override
		public List<ContractVO> selectContractSign() {
			return mapper.selectContractSign();
		}

		@Override
		public int updateContractSign(int contractId, long signId) {
			Map<String, Object> param = new HashMap<>();
			param.put("contractId", contractId);
			param.put("signId", signId);
			return mapper.updateContractSign(contractId, signId);
		}

		@Override
		public ContractVO selectMyContract(String empId) {
			return mapper.selectMyContract(empId);
		}
		
		@Override
		public ContractVO createContractWithAutoSalary(String empId) {
		    return mapper.selectAutoSalaryByEmpId(empId);
		}

}
