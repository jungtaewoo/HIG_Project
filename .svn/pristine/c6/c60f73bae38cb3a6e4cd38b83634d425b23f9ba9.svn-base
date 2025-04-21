$(document).ready(function(){
    // 결재자 차트 데이터를 가져오는 AJAX 호출
    $.ajax({
        url: "/approver/chart",  // 컨트롤러에 매핑된 엔드포인트
        method: "GET",
        dataType: "json",
        success: function(response) {
            // 기본 상태별 건수를 0으로 초기화 (기타 제외)
            var statusCounts = {
                "대기": 0,
                "진행중": 0,
                "승인": 0,
                "반려": 0
            };

            // 응답 데이터를 순회하면서 값 채우기 (정의된 상태만 업데이트)
            $.each(response, function(i, item) {
                if (statusCounts.hasOwnProperty(item.userStatus)) {
                    statusCounts[item.userStatus] = item.cnt;
                }
                // 기타 상태는 무시
            });

            // 커스텀 UI(요약 카드)의 각 상태를 data-status 속성으로 업데이트
            $('[data-status="대기"]').text(statusCounts["대기"] + "건");
            $('[data-status="진행중"]').text(statusCounts["진행중"] + "건");
            $('[data-status="승인"]').text(statusCounts["승인"] + "건");
            $('[data-status="반려"]').text(statusCounts["반려"] + "건");

            // 차트 데이터 준비 (상태별 키와 값 배열)
            var labels = Object.keys(statusCounts);
            var dataValues = labels.map(function(key) {
                return statusCounts[key];
            });

            // Chart.js 차트 생성 (가로 막대형, 막대 내부에 데이터 라벨 표시)
            var ctx = document.getElementById('statusApprChart').getContext('2d');
            var statusChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '결재 건수',
                        data: dataValues,
                        backgroundColor: [
                            'rgba(128, 128, 128, 0.2)',  // 대기
                            'rgba(54, 162, 235, 0.2)',   // 진행중
                            'rgba(75, 192, 192, 0.2)',   // 승인
                            'rgba(255, 99, 132, 0.2)'    // 반려
                        ],
                        borderColor: [
                            'rgba(128, 128, 128, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(255, 99, 132, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    indexAxis: 'y', // 가로 막대형
                    responsive: true,
					onClick: function(e) {
					    // 차트를 클릭하면 이동
					   window.location.href = "/approval/approverDrafts";
					},
                    plugins: {
                        legend: {
                            display: false
                        },
                        datalabels: {
                            anchor: 'center', // 막대 내부 중앙
                            align: 'center',  // 막대 내부 중앙
                            formatter: function(value, context) {
                                return value + "건";
                            },
                            color: '#000',
                            font: {
                                weight: 'bold'
                            }
                        }
                    },
                    scales: {
                        x: {
                            beginAtZero: true,
                            ticks: {
                                stepSize: 1
                            }
                        }
                    }
                },
                plugins: [ChartDataLabels]  // chartjs-plugin-datalabels를 등록
            });
        },
        error: function(xhr, status, error) {
            console.error("결재자 차트 데이터 가져오기 실패:", error);
        }
    });
});
