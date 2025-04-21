//var dpList = window.departmentList;
//var dac = window.departmentAliveCount;
//console.log("js에서 찌긍ㄴ dac",dac)
//console.log("js에서 찌긍ㄴ dpList",dpList)
//var optionsProfileVisit = {
//  annotations: {
//    position: "back",
//  },
//  dataLabels: {
//    enabled: true, 
//    formatter: function (val, opt) {
//		var dac = window.departmentAliveCount;
//      // 각 부서의 인원수에 맞게 값을 반환하도록 수정
//      const department = dac[opt.dataPointIndex]; // 각 데이터 포인트에 맞는 부서 정보
//      return `출근 인원: ${department.AliveemployeeCount}명`; // "출근 인원"으로 텍스트 변경
//	  
//    },
//    style: {
//      fontSize: '12px',
//      fontWeight: 'bold',
//      colors: ['#000000'],
//    },
//    offsetY: -10, // 글자가 막대 위로 올라오도록 설정
//  },
//  chart: {
//    type: "bar",
//    height: 300,
//  },
//  fill: {
//    opacity: 1,
//  },
//  series: [
//    {
//      name: "출근",
//      data: dac.map(function(todayAlive) {
//		console.log("js찍은 todayAlive",todayAlive)
//        return todayAlive.AliveemployeeCount+"명"; // 각 부서의 출근 인원 (이 예시에서는 부서별 인원수로 설정)
//      }),
//    },
//  ],
//  colors: ["#435ebe"],
//  xaxis: {
//    categories: dpList.map(function(department) {
//      return `${department.departmentName} / 총 ${department.employeeCount}명`; 
//      // 부서 이름 옆에 총 인원수를 추가
//    }),
//  },
//  yaxis: {
//    min: 0,
//    max: 60, // 총 인원보다 큰 최대값 설정
//  },
//};
//
//// ApexCharts 차트 생성
//var chartProfileVisit = new ApexCharts(
//  document.querySelector("#chart-profile-visit"),
//  optionsProfileVisit
//);
//
//// 차트 렌더링
//chartProfileVisit.render();
