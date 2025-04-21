<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 20.     	CHOI            최초 생성
 *
-->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <h2> 연차 신청서</h2>
    <form id="draftForm">
        <table border="1">
            <tr>
                <td>기안 ID:</td>
                <td><input type="text" id="draftId" name="draftId" ></td>
            </tr>
            <tr>
                <td>기안양식 ID:</td>
                <td><input type="text" id="templateId" name="templateId" ></td>
            </tr>
            <tr>
                <td>기안자 ID:</td>
                <td><input type="text" id="empId" name="empId" readonly></td>
            </tr>
            <tr>
                <td>문서 제목:</td>
                <td><input type="text" id="draftTitle" name="draftTitle"></td>
            </tr>
            <tr>
                <td>문서 내용:</td>
                <td><textarea id="draftContent" name="draftContent"></textarea></td>
            </tr>
            <tr>
                <td>첨부 파일:</td>
                <td><input type="file" id="draftFile" name="draftFile"></td>
            </tr>
            <tr>
                <td>긴급 여부:</td>
                <td><input type="checkbox" id="draftUrgent" name="draftUrgent"></td>
            </tr>
            <tr>
                <td>부서 ID:</td>
                <td><input type="text" id="departmentId" name="departmentId"></td>
            </tr>
        </table>

        <h3>결재자 목록</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>결재선순번ID</th>
                    <th>결재자 ID</th>
                    <th>결재 순서</th>
                    <th>승인 상태</th>
                    <th>결재 사유</th>
                </tr>
            </thead>
            <tbody id="approverTable">
                <!-- 동적으로 추가될 결재자 목록 -->
            </tbody>
        </table>
        <button type="button" onclick="addApprover()">결재자 추가</button>

        <br><br>
        <button id="submitApprovalBtn" type="button"> 결재 상신</button>
        
        <h3>결재 승인/반려</h3>
        <label>결재 의견:</label> <input type="text" id="aprReason">
        <br><br>
        <button id="approveDraftBtn" type="button">결재 승인</button>
        <button id="rejectDraftBtn" type="button">결재 반려</button>
    </form>

    <hr>

    <h2> 특정 기안자의 문서 조회</h2>
    <label>기안자 ID 검색:</label> <input type="text" id="searchEmpId">
    <button onclick="getDraftsByEmpId()">조회</button>

    <table border="1">
        <thead>
            <tr>
                <th>문서 ID</th>
                <th>제목</th>
                <th>기안일</th>
                <th>상태</th>
                <th>최종 결재자</th>
            </tr>
        </thead>
        <tbody id="draftTableBody"></tbody>
    </table>
    
    <script src="/resources/js/approval/approvalDraft.js" defer></script>
    <script src="/resources/js/approval/approveReject.js" defer></script>


