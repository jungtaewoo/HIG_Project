package kr.or.ddit.paging;

public class DefaultPaginationRenderer implements PaginationRenderer {

	@Override
	public String renderPagination(PaginationInfo paging, String fnName) {
		int startPage = paging.getStartPage();
		int endPage = paging.getEndPage(); // -> 8
		int totalPage = paging.getTotalPage();
		int blockSize = paging.getBlockSize();
		int currentPage = paging.getCurrentPage();
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		//반복되는 페이지의 변환 작업
		StringBuffer html = new StringBuffer();
		//javascript:void(0) -> 커서만 바뀌고 아무런 동작을 안함
		String pattern = "<a href='javascript:void(0)' onclick='"+fnName+"(%d);'>%s</a>\n";
		
		if(startPage > blockSize) {	
			html.append(
				String.format(pattern, startPage - blockSize, "이전")
			);
		}
		
		
		for(int page = startPage; page <= endPage; page++) {
			if(page == currentPage) {
				html.append(
					String.format("[%d]", page)
				);
			}else {
				html.append(
					String.format(pattern, page, page)
				);
				
			}
		}
		if(endPage < totalPage) {
			html.append(
				String.format(pattern, endPage + 1, "다음")
			);
		}
		
		return html.toString();
	}

}
