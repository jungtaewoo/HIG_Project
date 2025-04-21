package kr.or.ddit.mybatis.mappers.department;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.department.vo.DepartmentVO;

@Mapper
public interface DepartmentMapper {
    // 부서의 소속 직원 수 가져오기
    public List<DepartmentVO> getEmployeeCountByDepartment();

	// 조직도 조회
	public List<DepartmentVO> getOrg();

    // 부서 목록 조회
    List<DepartmentVO> getDepartmentList();

    // 부서 추가
    public int insertDepartment(DepartmentVO department);

    // 부서 상태 변경 (선택/비선택)
    public int updateDepartment(DepartmentVO departmentId);

    // 부서 삭제
    public int deleteDepartment(String departmentCode);

    public void updateDepartmentStatus(String departmentCode, String departmentStatus);

	// 여러 개 삭제 메서드 추가
    public void deleteDepartments(@Param("departmentIds") List<String> departmentIds);

    // 	부서 상세조회
    public DepartmentVO getDepartmentById(Long departmentId);

    // 오늘 날짜의 출근한 사람들의 인원수 조회
    public List<DepartmentVO> TodayAlive();

    // 받은 날짜의 출근한 사람들의 인원수 조회
    public List<DepartmentVO> TodayAlive(String date);

}
