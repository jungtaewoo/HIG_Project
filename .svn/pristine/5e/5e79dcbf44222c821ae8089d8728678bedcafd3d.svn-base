document.addEventListener('DOMContentLoaded', function() {
    // Determine the current month (formatted as yyyy-mm).
    let today = new Date();
    let year = today.getFullYear();
    let month = (today.getMonth() + 1).toString().padStart(2, '0');
    let ym = year + '-' + month;

    // Retrieve the employee ID.
    let empId = document.querySelector("#empId").value;

    // AJAX call to fetch attendance data.
    $.ajax({
        url: '/myAttendance',
        type: 'GET',
        dataType: "json",
        data: { workDate: ym, empId: empId },
        success: function(data) {
            let myAttendance = data.myAttendance;
            let totalSeconds = 0;
            // Calculate total worked seconds by summing working, overtime and night work durations.
            myAttendance.forEach(item => {
                let workingHours = parseInt(item.workingHours) || 0;
                let workingMinutes = parseInt(item.workingMinutes) || 0;
                let overtimeHours = parseInt(item.overtimeHours) || 0;
                let overtimeMinutes = parseInt(item.overtimeMinutes) || 0;
                let nightWorkHours = parseInt(item.nightWorkHours) || 0;
                let nightWorkMinutes = parseInt(item.nightWorkMinutes) || 0;
                let totalMinutes = (workingHours + overtimeHours + nightWorkHours) * 60 +
                                   (workingMinutes + overtimeMinutes + nightWorkMinutes);
                totalSeconds += totalMinutes * 60;
            });
            // For demonstration, assume 200 hours as target work seconds.
            let targetSeconds = 160 * 3600;
            let remainSeconds = Math.max(targetSeconds - totalSeconds, 0);
            totalchart(totalSeconds, remainSeconds);
        },
        error: function(xhr, status, error) {
            console.error("데이터 로딩 실패:", error);
        }
    });
});

function formatTime(seconds) {
    let h = Math.floor(seconds / 3600);
    let m = Math.floor((seconds % 3600) / 60);
    if (m === 0) return h + "시간";
    return h + "시간 " + m + "분";
}

var totalChartInstance = null;

function totalchart(totalSeconds, remainSeconds) {
    let ctx = document.getElementById('totalchart').getContext('2d');
    if (totalChartInstance) {
        totalChartInstance.destroy();
    }
    totalChartInstance = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['이번 달 근무 시간', '남은 근무 시간'],
            datasets: [{
                data: [totalSeconds, remainSeconds],
                backgroundColor: ['#5c61b4', '#EEEEEE'],
                hoverOffset: 4
            }]
        },
        options: {
            responsive: true,
            cutout: '80%',
            plugins: {
                legend: { position: 'bottom' },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            let value = context.raw;
                            return context.label + ': ' + formatTime(value);
                        }
                    }
                }
            }
        },
        plugins: [{
            // Plugin to display the total time in the chart center.
            id: 'centerText',
            beforeDraw: function(chart) {
                let width = chart.width,
                    height = chart.height,
                    ctx = chart.ctx;
                ctx.restore();
                let fontSize = (height / 10).toFixed(2);
                ctx.font = fontSize + "px sans-serif";
                ctx.textBaseline = "middle";
                ctx.textAlign = "center";
                let text = formatTime(totalSeconds);
                let textX = Math.round(width / 2);
                let textY = Math.round(height / 2.2);
                ctx.fillText(text, textX, textY);
                ctx.save();
            }
        }]
    });
}


