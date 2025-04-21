package kr.or.ddit.mybatis.mappers.attendance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.CustomRootContextConfig;
import kr.or.ddit.attendance.vo.AttendanceVO;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.team.vo.TeamVO;
import kr.or.ddit.workstatus.vo.WorkstautsVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CustomRootContextConfig
class attendanceMapperTest {

    @Autowired
    private attendanceMapper mapper;

    @Test
    void testAttendanceList() {
        List<AttendanceVO> list = mapper.attendanceList();
        assertNotNull(list, "출퇴근 리스트가 null이면 안 됨");
    }

    @Test
    void testAttendanceListWithParams() {
        List<AttendanceVO> list = mapper.attendanceList("300", "400", "2025-03-30");
        assertNotNull(list, "출퇴근 검색 결과가 null이면 안 됨");
    }

    @Test
    void testAttendanceDetail() {
        List<AttendanceVO> list = mapper.attendanceDetail("2025031589", "2025-03-01", "2025-03-30");
        assertNotNull(list, "개인의 출퇴근 기록이 null이면 안 됨");
    }


    @Test
    void testAttendanceInsert() {
        AttendanceVO attendance = new AttendanceVO();
        attendance.setEmpId("2025031589");
        attendance.setWorkDate("2025-03-30");
        attendance.setWorkStartTime("09:00");
        mapper.attendanceInsert(attendance);
        log.info("출근 기록 삽입 완료");
    }

    @Test
    void testFindTodayAttendance() {
        AttendanceVO attendance = mapper.findTodayAttendance("2025031589");
        assertNotNull(attendance, "오늘 출근 기록이 null이면 안 됨");
    }

    @Test
    void testFindTodayAttendanceList() {
        List<AttendanceVO> list = mapper.findTodayAttendanceList();
        assertNotNull(list, "오늘 출근한 직원 목록이 null이면 안 됨");
    }

    @Test
    void testUpdateWorkEnd() {
        AttendanceVO attendance = new AttendanceVO();
        attendance.setEmpId("2025031589");
        attendance.setWorkDate("2025-03-30");
        attendance.setWorkEndTime("18:00");
        mapper.updateWorkEnd(attendance);
        log.info("퇴근 기록 업데이트 완료");
    }



    @Test
    void testWorkStatusList() {
        List<WorkstautsVO> list = mapper.workStatusList();
        assertNotNull(list, "업무 상태 목록이 null이면 안 됨");
    }

    @Test
    void testWorkStatusEmployee() {
        AttendanceVO status = mapper.workStatusEmployee("2025031589");
        assertNotNull(status, "직원의 업무 상태가 null이면 안 됨");
    }

    @Test
    void testEmployee() {
        EmployeeVO employee = mapper.Employee("2025031589");
        assertNotNull(employee, "직원 정보가 null이면 안 됨");
    }

    @Test
    void testUpdateAttendance() {
        AttendanceVO attendance = mapper.updateAttendance("2025031589");
        assertNotNull(attendance, "출퇴근 기록 업데이트 결과가 null이면 안 됨");
    }

    @Test
    void testInsertAbsentStatus() {
        List<String> absentList = Arrays.asList("2023050701", "2023050910");
//        mapper.insertAbsentStatus(absentList);
        log.info("결근 상태 업데이트 완료");
    }
}