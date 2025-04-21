package kr.or.ddit.paging;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  단순 키워드 검색 조건을 담기 위한 객체 
 *  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCondition implements Serializable{
	private String searchType;
	private String searchWord;
}
