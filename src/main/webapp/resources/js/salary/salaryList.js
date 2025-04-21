/** 
 * <pre>
 *   수정일         수정자         수정내용
 *  -----------      -------------    ---------------------------
 * 2025. 3. 25.    youngjun         최초 생성
 * </pre>
 */
function salaryStatus(button){
   const tr = button.closest("tr");
   const empId = tr.querySelector("td[data-salaryid]").textContent.trim();
   const salaryId = tr.querySelector("td[data-salaryid]").getAttribute("data-salaryid");
   
   console.log("salaryId",salaryId);
   
   //컨트롤러 비동기요청
   $.ajax({
      url:"/salary/final",
      type:"POST",
      data:{
         empId:empId,
         salaryId:salaryId,
      },
      //요청성공시
      success:function(res){
         console.log(res)
         console.log("final 함수에서",empId,salaryId)
         qweasdzxc(empId,salaryId)
         //window.location.href="/salary/list"
          // refreshConfirmCount();
      },
      error:function(){
         alert("급여 확정 처리 실패");
      }
   })
   
}
const fix = document.querySelector("#fix")
function qweasdzxc(empId,salaryId){
   console.log("qweqweqwewq함수에서 ",empId ,salaryId)
   //컨트롤러 비동기요청
$.ajax({
   url:"/salary/qweasdzxc",
   type:"get",
   data:{
      empId:empId,
      salaryId:salaryId,
   },
   dataType:"json",
   //요청성공시
   success:function(res){
      console.log(res)
      console.log("fix",fix)
      if(res.response == "확정"){
         fix.textContent = "확정 대기"
      }else{
         fix.textContent = "확정"
      }
      //window.location.href="/salary/list"
       // refreshConfirmCount();
   },
   error:function(){
      alert("급여 확정 처리 실패");
   }
})
   
}

//확정인원 카운트
function refreshConfirmCount(){
   $.ajax({
      url:"/salary/finalConfirmCount",
      type : "GET",
      success : function(count) {
 document.querySelector("#confirmCount").innerText = `${count.confirmedCount}명`;
      },
      error : function(){
         console.log("확정인원 카운트 실패");
      }
   })
}

document.addEventListener("DOMContentLoaded", () => {
    document.addEventListener("click", (e) => {
        if (e.target.classList.contains("open-detail-modal")) {
            const empId = e.target.dataset.empid;
            const payYear = e.target.dataset.payyear;
            const payMonth = e.target.dataset.paymonth;
            const payStatus = e.target.dataset.paystatus;

            if (payStatus === '확정') {
                const url = `/salary/detail/${empId}/${payYear}/${payMonth}`;

            const modalHtml = `
            <div class="modal fade" id="iframeModal" tabindex="-1">
              <div class="modal-dialog modal-xl modal-dialog-centered">
                <div class="modal-content">
                  <div class="modal-header bg-white text-white">
                    <h5 class="modal-title text-center w-100"></h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                  </div>
                  <div class="modal-body">
                 
                 
                 <iframe id="previewIframe"
                         src="${url}"
                         style="width:100%; height:900px; border:none;"
                         data-empid="${empId}"
                         data-payyear="${payYear}"
                         data-paymonth="${payMonth}"></iframe>
                    
                    <!-- 급여명세서 단건발송 -->
                    <div class="d-flex flex-column align-items-end mt-3">
                      <button type="button" class="btn btn-danger btn-sm mb-2" id="sendPaystubBtn">📧 급여명세서 발송</button>
                      <p class="text-danger fw-bold mb-0">※ 확정된 급여로 수정이 불가합니다.</p>
                    </div>

                    <div id="imagePreviewArea" style="display:none; margin-top:20px;">
                      <img id="imagePreview" style="width:100%; border:1px solid #ccc;" />
                    </div>
                  </div>
                </div>
              </div>
            </div>`;


                const wrapper = document.createElement("div");
                wrapper.innerHTML = modalHtml;
                document.body.appendChild(wrapper);

                const modal = new bootstrap.Modal(wrapper.querySelector("#iframeModal"));
                modal.show();

                wrapper.querySelector("#iframeModal").addEventListener("hidden.bs.modal", () => {
                    wrapper.remove();
                });

            } else {
                // 확정대기일 때: 수정 가능한 모달 열기
                $.ajax({
                    url: `/salary/detail/${empId}/${payYear}/${payMonth}`,
                    type: "GET",
                    success: function (html) {
                        document.querySelector("#exampleModal .modal-body").innerHTML = html;
                        const modal = new bootstrap.Modal(document.getElementById("exampleModal"));
                        modal.show();
                    },
                    error: function () {
                        alert("급여명세서 로딩 실패");
                    }
                });
            }
        }
    });
});

function captureIframe() {
    const iframe = document.getElementById("previewIframe");
    const iframeDoc = iframe.contentWindow.document;

    html2canvas(iframeDoc.body).then(canvas => {
        const imgData = canvas.toDataURL("image/png");
        document.getElementById("imagePreview").src = imgData;
        document.getElementById("imagePreviewArea").style.display = 'block';
    });
}
