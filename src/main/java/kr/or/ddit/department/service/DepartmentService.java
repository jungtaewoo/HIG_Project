package kr.or.ddit.department.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.department.vo.DepartmentVO;

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


public interface DepartmentService {
    // 부서의 소속 직원 수 가져오기
    public List<DepartmentVO> getEmployeeCountByDepartment();

	// 조직도 조회
	public List<DepartmentVO> getOrg();

	// 부서 목록 조회
    public List<DepartmentVO> getDepartmentList();

    // 부서 추가
    public void insertDepartment(DepartmentVO department);

    // 부서 업데이트 (선택/비선택)
    public boolean updateDepartment(DepartmentVO departmentId);

    // 단일 부서 조회
    public DepartmentVO getDepartmentById(Long departmentId);

    // 부서 삭제
    public void deleteDepartment(String departmentCode);

    // 부서 여러 개 삭제
    public void deleteDepartments(List<String> departmentIds);

    // 오늘 날짜의 출근한 사람들의 인원수 조회
 	public List<DepartmentVO> TodayAlive();

 	// 받은 날짜의 출근한 사람들의 인원수 조회
 	public List<DepartmentVO> TodayAlive(String date);
}
