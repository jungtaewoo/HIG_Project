<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 20.     	LJW            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <title>인사 관리</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .category { background-color: #ccc; font-weight: bold; }
    </style>
</head>
<body>

<!-- 기본 정보 -->
<table>
    <tr><th colspan="2" class="category">기본 정보 (Basic Info)</th></tr>
    <tr><th>이름</th><td>${x.employee.Name}</td></tr>
    <tr><th>사진</th><td><img src="${x.employee.empImg}" alt="Profile Image" width="100"></td></tr>
    <tr><th>주민등록번호</th><td>${x.juminFront}</td></tr>
    <tr><th>국적</th><td>${x.nationality}</td></tr>
    <tr><th>성별</th><td>${x.gender}</td></tr>
    <tr><th>주소</th><td>${x.address}</td></tr>
    <tr><th>전화번호</th><td>${x.phoneNumber}</td></tr>
    <tr><th>이메일</th><td>${x.email}</td></tr>
</table>

<!-- 가족 및 인적 사항 -->
<table>
    <tr><th colspan="2" class="category">가족 및 인적 사항 (Family & Personal)</th></tr>
    <tr><th>배우자 유무</th><td>${x.maritalStatus}</td></tr>
    <tr><th>자녀 수 및 인적 사항</th><td>${x.children}</td></tr>
    <tr><th>부모 및 형제자매 정보</th><td>${x.familyInfo}</td></tr>
    <tr><th>가족 관계</th><td>${x.household}</td></tr>
</table>

<!-- 학력 및 자격 -->
<table>
    <tr><th colspan="2" class="category">학력 및 자격 (Education & Skills)</th></tr>
    <tr><th>최종 학력</th><td>${x.finalLevel}</td></tr>
    <tr><th>출신 학교 및 전공</th><td>${x.schoolMajor}</td></tr>
    <tr><th>졸업 여부</th><td>${x.graduation}</td></tr>
    <tr><th>학위 정보</th><td>${x.degree}</td></tr>
    <tr><th>자격증</th><td>${x.certificateId}</td></tr>
    <tr><th>어학 성적</th><td>${x.languageScore}</td></tr>
</table>

<!-- 고용 및 직무 -->
<table>
    <tr><th colspan="2" class="category">고용 및 직무 (Employment & Job)</th></tr>
    <tr><th>고용 형태</th><td>${x.employment.workType}</td></tr>
    <tr><th>부서명</th><td>${x.department.departmentName}</td></tr>
    <tr><th>팀명</th><td>${x.team.teamName}</td></tr>
    <tr><th>직급</th><td>${x.rank.rankName}</td></tr>
    <tr><th>임원</th><td>${x.job.jobName}</td></tr>
    <tr><th>직무</th><td>${x.position.positionName}</td></tr>
    <tr><th>근속 연수</th><td>${x.tenure}</td></tr>
</table>

<!-- 근태 및 보상 -->
<table>
    <tr><th colspan="2" class="category">근태 및 보상 (Attendance & Compensation)</th></tr>
    <tr><th>근태 기록</th><td>${x.attendance.record}</td></tr>
    <tr><th>연차 및 휴가 사용 내역</th><td>${x.attendance.leaveHistory}</td></tr>
    <tr><th>상벌 사항</th><td>${x.attendance.disciplinaryActions}</td></tr>
    <tr><th>급여</th><td>${x.salary.salaryAmount}</td></tr>
    <tr><th>상여금 및 인센티브</th><td>${x.salary.bonusIncentives}</td></tr>
    <tr><th>성과급 및 보상</th><td>${x.salary.performanceBonus}</td></tr>
</table>

<!-- 복리후생 및 보험 -->
<table>
    <tr><th colspan="2" class="category">복리후생 및 보험 (Welfare & Insurance)</th></tr>
    <tr><th>복리후생</th><td>${x.benefits}</td></tr>
    <tr><th>연금 및 보험 가입 내역</th><td>${x.pensionInsurance}</td></tr>
</table>

<!-- 교육 및 안전 -->
<table>
    <tr><th colspan="2" class="category">교육 및 안전 (Training & Safety)</th></tr>
    <tr><th>사내 교육 이수 내역</th><td>${x.internalTraining}</td></tr>
    <tr><th>외부 교육 및 연수 내역</th><td>${x.externalTraining}</td></tr>
    <tr><th>안전 교육 및 훈련 이수 내역</th><td>${x.safetyTraining}</td></tr>
</table>

<!-- 건강 및 사고 기록 -->
<table>
    <tr><th colspan="2" class="category">건강 및 사고 기록 (Health & Incidents)</th></tr>
    <tr><th>건강검진 내역</th><td>${x.healthCheck}</td></tr>
    <tr><th>산재, 업무상 부상 기록</th><td>${x.workInjuries}</td></tr>
    <tr><th>예방접종 기록</th><td>${x.vaccinations}</td></tr>
</table>

</body>
</html>