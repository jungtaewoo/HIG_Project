package kr.or.ddit.paging;

import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 처리와 관련된 프로퍼티를 갖는 객체.
 */
@Getter
public class PaginationInfo<T> {
	//그냥 호출하면 10,5 그대로 사용 
	public PaginationInfo() {
		this(10, 5);
	}
	public PaginationInfo(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
		this.currentPage = 1;
	}
	
	// 검색 지원 프로퍼티 : 단순 키워드 검색
	@Setter
	private SimpleCondition simpleCondition;
	
	//제네릭 타입으로 vo 동적 생성 -> 
	//관리 대상에 부합하는 상세 검색 조건을 지원하는 프로퍼티
	@Setter
	private T detailCondition;
	
	@Setter
	// 전체 레코드 수 : DB 조회
	private int totalRecord;
	// 한 페이지에서 보여줄 레코드 수 
	private int screenSize;
	// 한 페이지에서 제공할 페이지 링크 수 
	private int blockSize;
	@Setter
	// 사용자의 요구 페이지 : 요청 파라미터 
	private int currentPage;
	
//	private int totalPage;
	public int getTotalPage() {
		return (totalRecord + (currentPage -1)) / screenSize ;
	}
//	private int startRow
	public int getStartRow() {
		return getEndRow() - (screenSize - 1);
	}
//	private int endRow
	public int getEndRow() {
		return currentPage * screenSize;
	}
//	private int startPage
	public int getStartPage() {
		return getEndPage() - (blockSize - 1);
	}
//	private int endPage
	public int getEndPage() {
		return ((currentPage+ (blockSize - 1)) / blockSize) * blockSize;
	}
}
