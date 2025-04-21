/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 4. 1.     	정태우            최초 생성
 *
 * </pre>
 */
document.addEventListener('DOMContentLoaded', function() {
	const today = new Date();
	const year = today.getFullYear();
	const month = String(today.getMonth() + 1).padStart(2, '0');
	const defaultValue = `${year}-${month}`;
	const [payYear, payMonth] = defaultValue.split("-").map(Number);

	$.ajax({
		url: "/salary/summary",
		type: "GET",
		data: { payYear, payMonth },
		success: function(res) {
			renderCharts(res);		
		},
		error: function() {
			alert("요약 정보 조회 실패");
		}
	});

	$.ajax({
		url: "/recruit/board/list",
		type: "GET",
		data: { date: payYear },
		dataType: "json",
		success: function(res) {
			console.log("res채용",res)
			recruitChart(res.recruitBoardList);
		},
		error: function() {
			alert("요약 정보 조회 실패");
		}
	});
});

// 전역 차트 객체 선언
let deptChartInstance = null;
let rankChartInstance = null;

// ✅ ChartDataLabels 플러그인 등록 (한 번만 실행)
//Chart.register(ChartDataLabels);

function renderCharts(deptData) {
	const deptLabels = deptData.deptChartData.map(item => item.name);
	const deptSalaries = deptData.deptChartData.map(item => item.avgSalary);
	const deptCounts = deptData.employeeByDept.map(item => item.departmentEmployeeCount);
	const MinSalaryByDept = deptData.MinMaxSalaryByDept.map(item => item.MIN_NET_SALARY);
	const MaxSalaryByDept = deptData.MinMaxSalaryByDept.map(item => item.MAX_NET_SALARY);
	
	if (deptChartInstance !== null) {
		deptChartInstance.destroy();
	}

	deptChartInstance = new Chart(document.getElementById('deptSalaryChart'), {
		data: {
			labels: deptLabels,
			datasets: 						[
			  {
			    type: 'bar',
			    label: '최저 급여 (만원)',
			    data: MinSalaryByDept,
			    backgroundColor: '#A3C4DC',
			    yAxisID: 'y',
			    order: 3,
			    barThickness: 20,
			    grouped: false
			  },
			  {
			    type: 'bar',
			    label: '최고 급여 (만원)',
			    data: MaxSalaryByDept,
			    backgroundColor: '#EBD9B4',
			    yAxisID: 'y',
			    order: 3,
			    barThickness: 20,  // 조금 얇게 해서 안쪽에 보여지게
			    grouped: false
			  },
			  {
			    type: 'line',
			    label: '인원수',
			    data: deptCounts,
			    borderColor: '#BFD8B8',
			    backgroundColor: '#BFD8B8',
			    yAxisID: 'y1',
			    order: 1,
			    tension: 0,
			    borderWidth: 2,
			    pointRadius: 3
			  },
			  {
			    type: 'line',
			    label: '평균 급여 (만원)',
			    data: deptSalaries,
			    borderColor: '#C4B8CC',
			    backgroundColor: '#C4B8CC',
			    yAxisID: 'y',
			    tension: 0,
			    order: 0,
			    borderWidth: 2,
			    pointRadius: 3
			  }
			]
		},
		options: {
			responsive: true,
			maintainAspectRatio: true,
			scales: {
				y: {
					beginAtZero: true,
					title: {
						display: true,
						text: '급여 (만원)'
					}
				},
				y1: {
					beginAtZero: true,
					position: 'right',
					grid: { drawOnChartArea: false },
					title: {
						display: true,
						text: '인원수'
					}
				}
			},
			plugins: {
				datalabels: {
					formatter: function(value, context) {
						const datasetIndex = context.datasetIndex;
						if (datasetIndex === 3) {
							return value.toLocaleString() + "만원";
						}
						return null; // 평균 급여만 라벨 표시
					},
					anchor: 'start',
					align: 'center',
					color: '#000',
					font: {
						weight: 'bold'
					}
				}
			}
		},
		plugins: [ChartDataLabels]
	});
}

function recruitChart(deptData) {
	const applicantCount = deptData.map(item => item.applicantCount);   	    // 지원자 수
	const passCount = deptData.map(item => item.passCount);				    // 합격자 수 
	const LittleTitle = deptData.map(item => item.recruitTitle.split("팀")[0] + "(" + item.recruitHiretype + ")"); // 간소한 제목

	new Chart(document.getElementById('recruitChart'), {	
		type: 'bar',
		data: {
			labels: LittleTitle,
			datasets: [
				{
					label: '지원자 수',
					data: applicantCount,
					backgroundColor: 'rgba(255, 159, 64, 0.7)'
				},
				{
					label: '합격자 수',
					data: passCount,
					backgroundColor: 'rgba(75, 192, 192, 0.7)' // 다른 색상으로 구분
				}
			]
		},
		options: {
			responsive: true,
			maintainAspectRatio: true,
			indexAxis: 'y', // 가로형 바 차트
			scales: {
				x: {
					beginAtZero: true,
					ticks: {
						stepSize: 5,
						callback: function(value) {
							return value + "명";
						}
					}
				}
			},
			plugins: {
				datalabels: {
					anchor: 'center',
					align: 'right',
					formatter: function(value) {
						return value.toLocaleString() + "명";
					},
					color: 'black',
					font: {
						weight: 'bold'
					}
				}
			}
		},
		plugins: [ChartDataLabels]
	});
}
