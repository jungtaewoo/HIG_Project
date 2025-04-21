<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2> 인사 평가 기준 </H2>

<h6> 평가 항목 : 달성율1, 달성율2, 부서장 평가, 팀장 평가, 동료 평가. </h6>
<a href="${pageContext.request.contextPath }/evaluation/evaluationCriteria">평가기준 수정</a>
<table class="table-bordered">
<thead>
<tr>
	<th>직급 / 직책</th> 	
	<th>지표</th> 	
</tr>
<tr>
	<th>사원 / 사원이하 </th>
	<td>출근율(비교지표1), 연차 사용률(비교지표2), 부서장 평가, 팀장 평가. </td>
</tr>
<tr>	
	<th>대리 / 사원 </th>
	<td>출근율(비교지표2), 연차 사용률(비교지표2), 부서장 평가, 팀장 평가, 동료평가.</td>
</tr>
<tr>		
	<th>과장 / 선임 </th>
	<td>동일 직급 평균 급여(비교지표1) , 동일 직책 평균 급여(비교지표2), 부서장 평가 , 팀장 평가, 동료 평가.</td>
</tr>
<tr>		
	<th>차장 / 선임 이상</th>
	<td>동일 직급 평균 급여(비교지표1) , 동일 직책 평균 급여(비교지표2), 부서장 평가 , 팀장 평가. </td>
</tr>
<tr>		
	<th>부장 / 팀장 </th>
	<td>동일 직급 평균 급여(비교지표1) , 동일 직책 평균 급여(비교지표2), 부서장 평가. </td>
</tr>
<tr>	
	<th></th>
</tr>
</thead>

<tbody>
<tr>


</tr>
</tbody>

</table>

<table>
<thead>
<tr>
	<th>성과 기준</th>
	<th></th>
	<th></th>
</tr>
</thead>
</table>

	※ 성과 등급 
	1) 90~100	S	: 우수, 높은 성과
	2) 80~89	A	: 보통 이상의 성과
	3) 70~79	B	: 보통
	4) 60~69	C	: 개선 필요
	5) 60 미만	D	: 낮은 성과
	 - 정량 평가 (수치화 가능 항목): 60% 반영
	 - 정성 평가 (태도, 리더십 등 주관적 평가): 40% 반영
	
	예: 출근율 목표 95% 이상, 연차 사용율 80% 이하
	
	※ 평가 항목 지표 가중치
	1) 업무 성과	성과 점수 (평균) 40%
	2) 목표 달성률	KPI 달성률	25%
	3) 근태 점수	출석률, 초과근무	15%
	4) 조직 적응도	교육 참여, 승진 이력	15%
	5) 급여 및 보상	급여 인상률, 보너스 지급	10%
	
	※ 평가 결과	활용 방안
	1) 우수 직원 (S 등급)	승진, 보너스 지급, 핵심 인재 육성
	2) A~B 등급 직원	현재 직무 유지, 교육 지원
	3) C~D 등급 직원	피드백 제공, 개선 계획 수립

	
	정성 평가 
	
	평가 항목	설명	측정 방법
	1) 업무 성과	목표 대비 업무 수행 정도	KPI 달성률, 프로젝트 성과
	2) 근태	출근율, 지각, 결근	출석 데이터 (출퇴근 기록)
	3) 협업 태도	동료와의 협력, 팀워크	동료 피드백, 팀장 평가
	4) 문제 해결 능력	예상치 못한 문제 해결 능력	사례 분석, 평가자가 점수 부여
	5) 자기 계발 노력	교육, 자격증 취득, 학습 노력	교육 이수, 자격증 보유 여부
	
	
		업무 태도, 협업 능력 평가
	
		프로젝트 기획서, 보고서의 질적 평가
	
		창의성, 리더십 평가
	
		동료 또는 경쟁자와 비교하여 평가
	
		문제 해결 능력	상사 평가 점수
		
		
	자기 평가 점수 
		
	
	정성 평가 
	
	성과 피드백 및 평가 코멘트 활용
	
	리더십 및 교육 참여도  반영
	
	퇴직 사유 분석 	
	
		
	정량 평가 
		업무 성과 KPI(Key Performance Indicators) 성과 측정
	
		매출, 생산량, 판매 수량
	
		근태 기록(출석률, 지각 횟수)
	
		시험 점수, 평가 점수(리커트 척도 등)
	
	정량 평가 (Quantitative Evaluation)
	
	KPI 성과 수치 (EMP_CARD.KPI)를 기반으로 목표 달성률 측정
	
	출석률 (Attendance)과 초과근무 (EMP_CONTRACT.OVERTIME_PAY) 계산
	
	급여 인상 비율 (SALARY_MANAGEMENT.BASE_SALARY 변화량) 추적
	
	🔹 비교 평가 (Comparative Evaluation) → 동료 또는 경쟁자와 비교하여 평가
	🔹 자기 평가 (Self-Assessment) → 본인이 직접 자신의 성과를 평가
	🔹 360도 피드백 (360-Degree Feedback) → 상사, 동료, 부하 직원 등 여러 사람이 참여하는 다면 평가
	🔹 절대 평가 (Absolute Evaluation) → 정해진 기준에 따라 절대적인 성과를 평가 (ex: 100점 만점 기준)
	🔹 상대 평가 (Relative Evaluation) → 다른 사람들과 비교하여 등급을 매김 (ex: 상위 10%만 승진)

	
		➡ 종합 평가 점수 계산 예시 :
	SELECT EMP_ID, 
	       (AVG(EVALUATION_SCORE) * 0.4 +
	        (SUM(KPI) / COUNT(KPI)) * 0.2 +
	        ((TOTAL_ATTENDANCE - (LATE_COUNT + ABSENT_COUNT)) / TOTAL_ATTENDANCE * 100) * 0.15 +
	        (COUNT(INTERNALTRAINING) + COUNT(PROMOTIONHISTORY)) * 0.15 +
	        ((CURRENT_SALARY - PREV_SALARY) / PREV_SALARY * 100) * 0.1) AS FINAL_SCORE
	FROM (
	    -- 각 평가 항목의 서브쿼리 조인
	) AS PERFORMANCE_DATA
	GROUP BY EMP_ID;
	
