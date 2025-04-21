package kr.or.ddit.emprecordcards.vo;

import java.io.Serializable;

import kr.or.ddit.attendance.vo.AttendanceVO;
import kr.or.ddit.contract.vo.ContractVO;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.empappointments.vo.AppointmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.empresignations.vo.ResignationVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.salary.vo.PayAccountVO;
import kr.or.ddit.salary.vo.SalaryVO;
import kr.or.ddit.team.vo.TeamVO;
import lombok.Data;

@Data
public class EmpCardVO implements Serializable{
   
   private String cardId;
   private String empId;
   private String certificateId;
   private String paId;
   private String resignationDate;
   private String resignationReason;
   private String passbook;
   private String rewardPenalty;
   private String workStatus;
   private String tenure;
   private String emergencyNumber;
   private String health;
   private String hobby;
   private String specialty;
   private String maritalstatus;
   private String children;
   private String familyinfo;
   private String household;
   private String school;
   private String major;
   private String graduation;
   private String degree;
   private String languagescore;
   private String employmenttype;
   private String yearsofservice;
   private String workexperience;
   private String promotionhistory;
   private String performence;
   private String evaluation;
   private String kpi;
   private String feedback;
   private String selfassessment;
   private String bonusincentives;
   private String performancebonus;
   private String benefit;
   private String internaltraining;
   private String externaltraining;
   private String safetytraining;
   private String healthcheck;
   private String workinjuries;
   private String vaccination;
   
   EmployeeVO employee;
   AttendanceVO Attendance;
   DepartmentVO department;
   TeamVO team;
   RankVO rank;
   JobVO job;
   PositionVO position;
   PayAccountVO payAccount;
   ContractVO contract;
   AppointmentVO appointment;
//   LeaveEmployeeVO leaveEmployee;
//   EvaluationVO valuation;
   SalaryVO salary;
   
   
	
}
