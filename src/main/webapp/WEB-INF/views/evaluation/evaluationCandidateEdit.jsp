<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>평가 대상자 등록</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #f9f9f9; }
        select { width: 100px; }
        button { margin-top: 10px; }
    </style>
</head>
<body>
<h2>평가 대상자 등록</h2>

<form:form modelAttribute="wrapper" method="post"
           action="${pageContext.request.contextPath}/evaluation/evaluationCandidateUpdate">
<button ></button>
    <table>
        <thead>
        <tr>
            <th>사번</th>
            <th>이름</th>
            <th>평가년도</th>
            <th>구분</th>
            <th>대상</th>
            <th>평가여부</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="x" items="${wrapper.candidateList}" varStatus="status">
            <tr>
                <td>
                    <form:input path="candidateList[${status.index}].empId" />
                </td>
                <td>
                    <form:input path="candidateList[${status.index}].employee.name" />
                </td>

                <td>
                    <form:select path="candidateList[${status.index}].evaluationYear">
                        <form:option value="">선택</form:option>
                        <c:forEach var="i" begin="2020" end="2030">
                            <form:option value="${i}">${i}</form:option>
                        </c:forEach>
                    </form:select>
                </td>

                <td>
                    <form:select path="candidateList[${status.index}].halfYear">
                        <form:option value="">선택</form:option>
                        <form:option value="상반기">상반기</form:option>
                        <form:option value="하반기">하반기</form:option>
                    </form:select>
                </td>

                <td>
                    <form:select path="candidateList[${status.index}].isTarget">
                        <form:option value="">선택</form:option>
                        <form:option value="Y">Y</form:option>
                        <form:option value="N">N</form:option>
                    </form:select>
                </td>

                <td>
                    <form:select path="candidateList[${status.index}].evaluationStatus">
                        <form:option value="">선택</form:option>
                        <form:option value="Y">Y</form:option>
                        <form:option value="N">N</form:option>
                    </form:select>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button type="submit">대상자 등록</button>
</form:form>

</body>
</html>
