/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      		수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 4. 3.     	youngjun            최초 생성
 *  2025. 4. 8.     	youngjun            일괄 발송 3명 단위 최적화
 * </pre>
 */

// 단건 발송
document.addEventListener('click', function(e) {
	if (e.target && e.target.id === "sendPaystubBtn") {
		const iframe = document.getElementById("previewIframe");
		const iframeDoc = iframe.contentWindow.document;

		const empId = iframe.getAttribute("data-empid");
		const payYear = iframe.getAttribute("data-payyear");
		const payMonth = iframe.getAttribute("data-paymonth");

		html2canvas(iframeDoc.body).then(canvas => {
			const base64Image = canvas.toDataURL("image/png");

			$.ajax({
				url: '/salary/send',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
					empId: empId,
					payYear: payYear,
					payMonth: payMonth,
					base64Image: base64Image
				}),
				success: function(res) {
					alert(res.message || '명세서 메일발송 성공');
				},
				error: function() {
					alert('명세서 발송에 실패했습니다.');
				}
			});
		});
	}
});

$("#sendAllPaystubsBtn").on("click", function () {
  console.log("일괄메일시작");

  const selectedDate = $("#payDate").val();
  if (!selectedDate) {
    alert("📅 연도와 월을 선택해주세요.");
    return;
  }

  const [payYear, payMonth] = selectedDate.split("-");
  $.ajax({
    url: "/salary/send/confirm",
    type: "GET",
    data: { payYear, payMonth },
    dataType: "json",
    success: async function (response) {
      const confirmList = response.confirmList;
      console.log("발송대상자:", confirmList);

      const iframes = ["previewIframe1", "previewIframe2", "previewIframe3"];

      async function sendPaystub(emp, iframeId) {
        return new Promise((resolve) => {
          const iframe = document.getElementById(iframeId);

          // 숨겨진 iframe 위치 설정
          iframe.style.position = "absolute";
          iframe.style.left = "-9999px";
          iframe.style.top = "0";
          iframe.style.width = "1200px";
          iframe.style.height = "2000px";
          iframe.style.visibility = "visible";

          iframe.src = `/salary/detail/${emp.empId}/${payYear}/${payMonth}`;

          iframe.onload = () => {
            const interval = setInterval(() => {
              try {
                const doc = iframe.contentWindow.document;
                const ready = doc.querySelector(".salary-detail-title") && iframe.contentWindow.renderReady;

                if (ready) {
                  clearInterval(interval);

                  html2canvas(doc.body).then(canvas => {
                    console.log("canvas width:", canvas.width, "height:", canvas.height);

                    canvas.toBlob(blob => {
                      if (!blob || blob.size === 0) {
                        console.error("캡처 blob null");
                        console.log("document 상태:", doc.body.innerHTML);
                        resolve();
                        return;
                      }

                      const formData = new FormData();
                      formData.append("empId", emp.empId);
                      formData.append("payYear", payYear);
                      formData.append("payMonth", payMonth);
                      formData.append("file", blob);

                      $.ajax({
                        url: "/salary/send/all",
                        type: "POST",
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function () {
                          console.log(`${emp.empId} 발송 완료`);
                          resolve();
                        },
                        error: function (err) {
                          console.error(`${emp.empId} 발송 실패`, err);
                          resolve();
                        }
                      });
                    }, "image/png");
                  });
                }
              } catch (err) {
                console.error("iframe오류", err);
                clearInterval(interval);
                resolve();
              }
            }, 300);
          };
        });
      }

      // 순차 발송 처리
      for (let i = 0; i < confirmList.length; i++) {
        const emp = confirmList[i];
        const iframeId = iframes[i % iframes.length];
        await sendPaystub(emp, iframeId);
      }

      alert("✅ 모든 명세서 발송 완료");
    },
    error: function () {
      alert("❌ 확정 사원 목록 조회 실패");
    }
  });
});
