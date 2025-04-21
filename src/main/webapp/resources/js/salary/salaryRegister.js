/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 23.     	youngjun            최초 생성
 *
 * </pre>
 */

document.addEventListener("DOMContentLoaded", () => {
	console.log("미리보기모달");
	const myModal = document.getElementById("sampleSalaryModal");
	const modal = new bootstrap.Modal(myModal);
	const saveBtn = document.getElementById("saveBtn");
	//console.log("찍히나",saveBtn);
	
	$("#saveBtn").on("click", function(){
		console.log("급여등록미리보기 모달");
		const year = $("input[name='payYear']").val();
		const month = $("input[name='payMonth']").val();
		
		console.log("year",year);
		console.log("month",month);
		
		$.ajax({
			url:`/salary/insert/ex/${year}/${month}`,
			type:"GET",
			success:function(data){
				$(".modal-body").html(data);
				modal.show();
			},
			error:function(){
				alert("급여 미리보기 로드 실패");
			}
		});
	});
});

function registerSalary(obj){
	console.log("급여등록버튼");
	
	const year = $("input[name='payYear']").val();
	const month = $("input[name='payMonth']").val();
	
	console.log("year",year,month);
	
	$.ajax({
		url:"/salary/insert/register",
		type:"POST",
		data: {
				payYear: year,
				payMonth: month
			},
		success(res){
			console.log("넘어온값",res);
			alert("급여 등록 완료");
			location.href = "/salary/list";
		},
		
	error(xhr) {
				console.error("에러 상태:", xhr.status, xhr.responseText);
				alert("급여 등록 중 오류 발생!");
			}
		});
	}