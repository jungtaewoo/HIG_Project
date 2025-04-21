<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>종합 평가</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0; /* 바디 여백 제거 */
            display: flex;
            justify-content: center; /* 가운데 정렬 */
            align-items: center;
            min-height: 100vh; /* 화면 높이를 꽉 채움 */
            background-color: #f8f9fa;
        }
        .container {
            width: 90%;  /* 화면이 작을 때 여백 유지 */
		    max-width: 1500px;  /* 너무 넓어지지 않도록 제한 */
		    min-height: 100vh;
		    padding: 20px;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		    background: white;
		    box-sizing: border-box;
        }
        .section {
            padding: 20px;
            border: 1px solid #ddd; /* 내부 보더 추가 */
            border-radius: 5px; /* 모서리 둥글게 */
            background-color: #f9f9f9; /* 약간의 배경색 */
            margin-bottom: 10px;
        }
        .section-title {
            font-weight: bold;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th {
            background-color: #f4f4f4;
            padding: 10px;
            text-align: left;
        }
        td {
            padding: 10px;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            height: 80px;
        }
        .total-score {
            font-size: 1.2em;
            font-weight: bold;
            text-align: right;
            padding: 10px;
        }
        h2{
            margin-bottom : 40px;
        }
        
        html, body {
		    width: 100%;
		    overflow-x: hidden; /* 가로 스크롤 방지 */
		}
        
    </style>
</head>
<body>
<h2>평가 하기</h2>
    <div class="container">
        <div class="section">
            <div class="section-title">평가 기본 정보</div>
            <table>
                <tr>
                    <th>평가년도</th>
                    <th>상/하반기</th>
                    <th>평가일</th>
                    <th>평가 그룹</th>
                    <th>평가자</th>
                </tr>
                <tr>
                    <td><input type="text" name="evaluationYear"></td>
                    <td><input type="text" name="halfYear"></td>
                    <td><input type="date" name="evaluationDate"></td>
                    <td><input type="text" name="evaluationGroup"></td>
                    <td><input type="text" name="evaluatorEmpId"></td>
                </tr>
            </table>
        </div>
        
        <div class="section">
            <div class="section-title">평가 대상자 정보</div>
            <table>
                <tr>
                    <th>부서</th>
                    <th>팀</th>
                    <th>직급</th>
                    <th>이름</th>
                    <th>사번</th>
                </tr>
                <tr>
                    <td><input type="text" name="department"></td>
                    <td><input type="text" name="team"></td>
                    <td><input type="text" name="rank"></td>
                    <td><input type="text" name="employeeName"></td>
                    <td><input type="text" name="employeeId"></td>
                </tr>
            </table>
        </div>
        
        <div class="section">
            <div class="section-title">세부 평가 항목</div>
            <table>
                <tr>
                    <th>평가명</th>
                    <th>유형</th>
                    <th>항목</th>
                    <th>가중치</th>
                    <th>평가 점수</th>
                    <th>환산 점수</th>
                    <th>평가 의견</th>
                </tr>
                <tr>
                    <td><input type="text" name="evaluationName"></td>
                    <td><input type="text" name="evaluationType"></td>
                    <td><input type="text" name="evaluationCriteria"></td>
                    <td><input type="number" name="evaluationWeight"></td>
                    <td><input type="number" name="evaluationScore"></td>
                    <td><input type="number" name="convertedScore"></td>
                    <td><textarea name="evaluationComments"></textarea></td>
                </tr>
            </table>
        </div>
        
        <div class="section">
            <div class="section-title">최종 평가 의견</div>
            <textarea name="evaluationFinal"></textarea>
        </div>
        
        <div class="section">
            <div class="section-title">총점</div>
            <div class="total-score">총점: <span id="totalScore">0</span></div>
        </div>
    </div>
</body>
</html>
