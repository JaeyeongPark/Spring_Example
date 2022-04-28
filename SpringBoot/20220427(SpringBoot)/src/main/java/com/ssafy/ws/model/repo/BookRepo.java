package com.ssafy.ws.model.repo;

import java.util.List;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.SearchCondition;

public interface BookRepo {

	int insert(Book book);

	int update(Book book);

	int delete(String isbn);
	
	Book select(String isbn);
	
	/**
	 * 조건에 맞는 데이터의 목록을 반환한다.
	 * @param condition
	 * @return
	 */
	List<Book> search(SearchCondition condition);
	
	/**
	 * 조건에 맞는 데이터의 건수를 반환한다.
	 * @param condition
	 * @return
	 */
	int getTotalSearchCount(SearchCondition condition);
}
