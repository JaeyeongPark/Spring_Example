package com.ssafy.ws.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.SearchCondition;
import com.ssafy.ws.model.repo.BookRepo;
import com.ssafy.ws.util.PageNavigation;

/**
 * 빈으로 등록될 수 있도록 @Service를 선언한다.
 *
 */
@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	/**
	 * has a 관계로 사용할 BookRepo 타입의 repo를 선언한다.
	 */
	private BookRepo repo;
	/**
	 * 파일 업로드 경로를 설정하기 위해 ResourceLoader를 주입받는다.
	 */
	@Autowired
	ResourceLoader resLoader;

	/**
	 * setter를 통해 BookRepo를 주입받는다.
	 * 
	 * @Autowired를 통해 BookRepo 타입의 빈을 주입 받는다.
	 * @param repo
	 */
	@Autowired
	public void setBookRepo(BookRepo repo) {
		this.repo = repo;
	}

	public BookRepo getBookRepo() {
		return repo;
	}

	@Override
	@Transactional
	public int insert(Book book) throws IllegalStateException, IOException {
		// 파일을 저장할 폴더 지정
		Resource res = resLoader.getResource("resources/upload");
		MultipartFile file = book.getFile();
		if (file != null && file.getSize()>0) {
			// prefix를 포함한 전체 이름
			book.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			book.setOrgImg(file.getOriginalFilename());

			// 변경된 파일 이름이 적용된 Book을 BookService를 통해 DB에 저장한다.

			file.transferTo(new File(res.getFile().getCanonicalPath() + "/" + book.getImg()));
		}
		logger.debug("book: {}", book);
		return repo.insert(book);
	}

	@Override
	@Transactional
	public int update(Book book) {
		return repo.update(book);
	}

	@Override
	@Transactional
	public int delete(String isbn) {
		return repo.delete(isbn);
	}

	@Override
	public Book select(String isbn) {
		return repo.select(isbn);
	}

	@Override
	public List<Book> search(SearchCondition condition) {
		return repo.search(condition);
	}

	/**
	 * 리스트에 페이징을 적용하기 위한 메서드
	 * Map에 books를 키로 화면에 표시할 Book 목록을 저장하고
	 * navigation이라는 키로 PageNavigation 객체를 저장해서 반환한다.
	 * PageNavigation을 만들기 위한 정보로 currentPage는 SearchCondition에서 얻어오고
	 * totalCount는 BookRepo에 새롭게 추가한 메서드를 사용한다.
	 * 
	 * @param condition
	 * @return
	 */
	@Override
	public Map<String, Object> pagingSearch(SearchCondition condition) {
		logger.debug("condition: {}", condition);
		int totalCount = repo.getTotalSearchCount(condition);
		PageNavigation nav = new PageNavigation(condition.getCurrentPage(), totalCount);

		// 결과를 전달할 pagingResult를 만들고 books와 navigation을 키로 값을 저장한다.
		Map<String, Object> pagingResult = new HashMap<>();

		pagingResult.put("books", search(condition));
		pagingResult.put("navigation", nav);

		logger.debug("result map: {}", pagingResult);
		return pagingResult;
	}

}
