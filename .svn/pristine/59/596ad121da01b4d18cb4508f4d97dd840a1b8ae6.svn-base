/**
 * 
 */
document.addEventListener("DOMContentLoaded", function(){	
	const $searchForm = $("#search-form");
	const $searchUi = $("#search-ui");
	const $searchBtn = $("#search-btn");
	//: 전체 
	$searchForm.find(":input[data-init-value]").each(function(idx, input){
		let initValue = input.dataset.initValue;
		$(input).val(initValue);
	});
	$searchUi.find(":input[data-init-value]").each(function(idx, input){
		let initValue = input.dataset.initValue;
		$(input).val(initValue);
	});
	
	 fnPaging = function(page){
	//	location.href="?page="+page;
		$searchForm.find("[name='page']").val(page);
		$searchForm.submit();
	}
	
	$searchBtn.on("click", function(){
		let $searchUi = $(this).parents("#search-ui");
		$searchUi.find(":input[name]").each(function(idx, input){
			let name = input.name;
			let value = $(input).val();
			$searchForm.find(`[name='${name}']`).val(value);
		});
		$searchForm.submit();
	});
});
