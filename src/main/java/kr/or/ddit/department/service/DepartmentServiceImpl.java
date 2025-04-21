package kr.or.ddit.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.mybatis.mappers.department.DepartmentMapper;

/**
*
* @author KHS
* @since 2025. 3. 11.
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      			수정자           수정내용
*  -----------   	-------------    ---------------------------
*  2025. 3. 11.     	KHS	          최초 생성
*
* </pre>
*/


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Override
	public List<DepartmentVO> getEmployeeCountByDepartment() {
		return mapper.getEmployeeCountByDepartment();
	}

	@Override
	public List<DepartmentVO> getOrg() {
		return mapper.getOrg();
	}

	// ✅ 부서 목록 조회
    @Override
    public List<DepartmentVO> getDepartmentList() {
        return mapper.getDepartmentList();
    }

    // ✅ 부서 추가
    @Override
    public void insertDepartment(DepartmentVO department) {
    	mapper.insertDepartment(department);
    }

    // ✅ 부서 상태 업데이트
    @Override
    public boolean updateDepartment(DepartmentVO departmentId) {
    	return mapper.updateDepartment(departmentId) > 0;
    }

    @Override
    public DepartmentVO getDepartmentById(Long departmentId) {
        return mapper.getDepartmentById(departmentId);
    }

    // ✅ 부서 삭제
    @Override
    public void deleteDepartment(String departmentCode) {
    	mapper.deleteDepartment(departmentCode);
    }

    // ✅ 여러 개의 부서 삭제 (추가한 부분)
    @Override
    public void deleteDepartments(List<String> departmentIds) {
        mapper.deleteDepartments(departmentIds);
    }

	@Override
	public List<DepartmentVO> TodayAlive() {
		return 	mapper.TodayAlive();
	}

	@Override
	public List<DepartmentVO> TodayAlive(String date) {
		return 	mapper.TodayAlive(date);
	}
}

