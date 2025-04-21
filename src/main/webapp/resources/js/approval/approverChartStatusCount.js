function renderChart() {
  // 1) 전체 상태 목록(서버 응답에 없는 상태도 미리 포함)
  // (내부적으로 "승인"을 최종 승인 상태로 사용)
  const ALL_STATUSES = ["대기", "회수", "승인", "반려"];

  fetch("/approval/approver/statusCount")
    .then(res => {
      if (!res.ok) throw new Error(res.status);
      return res.json();
    })
    .then(stats => {
      // 2) 서버 응답을 기반으로 상태별 건수 매핑 (없으면 0으로 초기화)
      const statsMap = {};
      ALL_STATUSES.forEach(status => {
        statsMap[status] = 0;
      });
      stats.forEach(({ APRSTATUS, CNT }) => {
        // 만약 서버에서 "완료"로 온다면 이를 "승인"으로 변환(예: 기존 맥락)
        const key = (APRSTATUS === "완료") ? "승인" : APRSTATUS;
        if (key in statsMap) {
          statsMap[key] = CNT;
        }
      });

      // 3) summary badge 업데이트
      ALL_STATUSES.forEach(status => {
        const cnt = statsMap[status];
        const badge = document.querySelector(`.stat-card .badge[data-status="${status}"]`);
        if (badge) badge.textContent = cnt + '건';
      });

      // 4) 차트 데이터 준비
      const labels = ALL_STATUSES; // 고정 순서 사용
      const data = labels.map(status => statsMap[status]);

      // 상태별 색상 정의 (회수 포함)
      const colorMap = { 
        대기: "#6c757d",   // 회색
        회수: "#0dcaf0",   // 하늘색
        승인: "#198754",   // 초록
        반려: "#dc3545"    // 빨강
      };

      // 라벨에 맞는 색상 배열 생성
      const bgColors = labels.map(l => colorMap[l] || "#888");

      const ctx = document.getElementById("statusChart").getContext("2d");

      // 만약 이전에 차트 인스턴스가 있다면 제거
      if (window.statusChartInstance) {
        window.statusChartInstance.destroy();
      }

      // 5) 차트 생성
      window.statusChartInstance = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: '문서 건수',
            data: data,
            backgroundColor: bgColors
          }]
        },
        options: {
          indexAxis: 'y', // 가로 막대형
          animation: { 
            duration: 0,
            onComplete: function() {
              const chart = this;
              const ctx = chart.ctx;
              const dataset = chart.data.datasets[0];

              ctx.font = 'bold 14px "Noto Sans KR", sans-serif';
              ctx.fillStyle = '#fff';
              ctx.textAlign = 'center';
              ctx.textBaseline = 'middle';

              dataset.data.forEach((value, index) => {
                const bar = chart.getDatasetMeta(0).data[index];
                const centerPoint = bar.getCenterPoint();
                ctx.fillText(value + "건", centerPoint.x, centerPoint.y);
              });
            }
          },
          scales: {
            x: { 
              beginAtZero: true, 
              grid: { display: false }, 
              ticks: { precision: 0 }
            },
            y: { grid: { display: false } }
          },
          plugins: {
            legend: { display: false },
            title: { display: true },
            tooltip: {
              callbacks: {
                label: ctx => `${ctx.label}: ${ctx.parsed.x}건`
              }
            }
          },
          // 막대 클릭 시 필터링을 위해 localStorage에 label 값을 그대로 저장
          onClick: (evt, elems) => {
            if (!elems.length) return;
            const idx = elems[0].index;
            const status = labels[idx] || 'all'; // 변환 없이 그대로 사용
            localStorage.setItem('draftStatusFilterValue', status);
            window.location.href = '/approval/mydrafts';
          }
        }
      });
    })
    .catch(err => console.error("차트/섬머리 로드 실패:", err));
}

// 페이지 로드되면 자동 실행
document.addEventListener("DOMContentLoaded", renderChart);
