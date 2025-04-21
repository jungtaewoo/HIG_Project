/**
 * 
 */
document.addEventListener("DOMContentLoaded",()=>{
	const myModalEl = document.getElementById('exampleModal')
	myModalEl.addEventListener('show.bs.modal', event => {
		let aTag = event.relatedTarget;
		let url = aTag.href;
		$.ajax({
			url: url,
			dataType:"html",
			success:function(resp){
				$(myModalEl).find(".modal-body").html(resp);
			}
		})
	})
});

