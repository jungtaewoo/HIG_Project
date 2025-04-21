<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>부서 조직도</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.37.0/skin-win8/ui.fancytree.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.37.0/jquery.fancytree-all-deps.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <style>
        /* 레이아웃 스타일 */
        .container {
            display: flex;
            height: 100vh;
        }
        #orgTreeContainer {
            width: 30%;
            border-right: 2px solid #ccc;
            padding: 10px;
            overflow-y: auto;
        }
        #detailPane {
            width: 70%;
            padding: 20px;
            display: none;
        }
        /* 아이콘 스타일 */
        .fancytree-icon {
            font-size: 16px;
            margin-right: 5px;
        }
    </style>

    <script>
        $(document).ready(function() {
            console.log("DOM 로드 완료");

            // 부서 데이터 불러오기 (AJAX 요청)
            $.ajax({
                url: "${pageContext.request.contextPath}/employee/getDepartments",
                method: 'GET',
                dataType: 'json',
                success: function(response) {
                    console.log("받은 응답 데이터:", response);
                    
                    var departmentList = Array.isArray(response) ? response : (response.data ? response.data : []);
                    console.log("가공 전 부서 데이터:", departmentList);

                    var orgData = processOrgData(departmentList);
                    console.log("가공된 조직 데이터:", orgData);

                    // FancyTree 초기화
                    $("#orgTree").fancytree({
                        glyph: {
                            map: {
                                expanderClosed: "fas fa-plus-square",
                                expanderOpen: "fas fa-minus-square",
                                folder: "fas fa-folder",
                                folderOpen: "fas fa-folder-open",
                                doc: "fas fa-file"
                            }
                        },
                        source: orgData,
                        activate: function(event, data) {
                            console.log("클릭한 노드:", data.node);
                            console.log("노드 데이터:", data.node.data);

                            if (data.node.data && data.node.data.type === 'department') {
                                $("#detailTitle").text("부서 수정");
                                $("#detailContent").load("${pageContext.request.contextPath}/department/update?departmentId=" + data.node.data.departmentId);
                                $("#detailPane").show();
                            } else if (data.node.data && data.node.data.type === 'team') {
                                $("#detailTitle").text("팀 정보");
                                $("#detailContent").html(
                                    "<p><strong>팀명:</strong> " + (data.node.data.teamName || "") + "</p>" +
                                    "<p><strong>전화번호:</strong> " + (data.node.data.teamPhonenumber || "") + "</p>" +
                                    "<p><strong>팩스번호:</strong> " + (data.node.data.teamFaxnumber || "") + "</p>"
                                );
                                $("#detailPane").show();
                            } else if (data.node.data && data.node.data.type === 'employee') {
                                $("#detailTitle").text("직원 정보");
                                $("#detailContent").html(
                                    "<p><strong>이름:</strong> " + (data.node.title || "") + "</p>" +
                                    "<p><strong>부서:</strong> " + (data.node.data.dept || "") + "</p>" +
                                    "<p><strong>직위:</strong> " + (data.node.data.job || "") + "</p>" +
                                    "<p><strong>이메일:</strong> " + (data.node.data.email || "") + "</p>" +
                                    "<p><strong>전화번호:</strong> " + (data.node.data.phone || "") + "</p>" +
                                    "<p><strong>주소:</strong> " + (data.node.data.address || "") + "</p>"
                                );
                                $("#detailPane").show();
                            } else {
                                $("#detailPane").hide();
                            }
                        }
                    });
                },
                error: function(xhr, status, error) {
                    console.error("Ajax 요청 실패:", status, error);
                }
            });

            // 조직도 데이터 변환 함수
            function processOrgData(departmentList) {
                var orgData = [];
                if (!Array.isArray(departmentList)) {
                    console.error("departmentList 형식 오류:", departmentList);
                    return orgData;
                }
                departmentList.forEach(function(dept) {
                    var deptNode = {
                        title: `<i class="fas fa-building fancytree-icon"></i>` + dept.departmentName,
                        folder: true,
                        expanded: false,
                        data: {
                            type: 'department',
                            departmentId: dept.departmentId,
                            departmentName: dept.departmentName,
                            departmentLocation: dept.departmentLocation,
                            departmentPhonenumber: dept.departmentPhonenumber,
                            departmentFaxnumber: dept.departmentFaxnumber
                        },
                        children: []
                    };

                    if (dept.teams && Array.isArray(dept.teams)) {
                        dept.teams.forEach(function(team) {
                            var teamNode = {
                                title: `<i class="fas fa-users fancytree-icon"></i>` + team.teamName,
                                folder: true,
                                expanded: false,
                                data: {
                                    type: 'team',
                                    teamName: team.teamName,
                                    teamPhonenumber: team.teamPhonenumber,
                                    teamFaxnumber: team.teamFaxnumber
                                },
                                children: []
                            };
                            if (team.employees && Array.isArray(team.employees)) {
                                team.employees.forEach(function(emp) {
                                    teamNode.children.push({
                                        title: `<i class="fas fa-user fancytree-icon"></i>` + emp.name,
                                        data: {
                                            type: 'employee',
                                            dept: dept.departmentName,
                                            job: (emp.job && emp.job.jobName) ? emp.job.jobName : "",
                                            email: emp.email || "",
                                            phone: emp.phoneNumber || "",
                                            address: emp.address || ""
                                        }
                                    });
                                });
                            }
                            if (teamNode.children.length > 0) {
                                deptNode.children.push(teamNode);
                            }
                        });
                    }

                    if (dept.employees && Array.isArray(dept.employees)) {
                        dept.employees.forEach(function(emp) {
                            deptNode.children.push({
                                title: `<i class="fas fa-user fancytree-icon"></i>` + emp.name,
                                data: {
                                    type: 'employee',
                                    dept: dept.departmentName,
                                    job: (emp.job && emp.job.jobName) ? emp.job.jobName : "",
                                    email: emp.email || "",
                                    phone: emp.phoneNumber || "",
                                    address: emp.address || ""
                                }
                            });
                        });
                    }

                    if (deptNode.children.length > 0) {
                        orgData.push(deptNode);
                    }
                });
                return orgData;
            }
        });
    </script>
</head>
<body>
    <h1>부서 조직도</h1>
    <div class="container">
        <div id="orgTreeContainer">
            <div id="orgTree"></div>
        </div>
        <div id="detailPane">
            <h2 id="detailTitle"></h2>
            <div id="detailContent"></div>
        </div>
    </div>
</body>
</html>