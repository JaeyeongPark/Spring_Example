package com.ssafy.ws.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.SearchCondition;
import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RestBookController {
	private static final Logger logger = LoggerFactory.getLogger(RestBookController.class);
	@Autowired
	private BookService bookService;
	/**
	 * <pre>
	 * /list를 get 방식으로 요청할 때 도서 정보를 보여준다.
	 * </pre>
	 * 
	 * Paging을 이용하는 pagingSearch를 이용하도록 변경한다.
	 * 
	 * @return
	 */
//	@GetMapping("/list")
//	public String showList(Model model, @ModelAttribute SearchCondition condition) {
//		//List<Book> books = bService.search(condition);
//		logger.debug("전달받은 condition: {}", condition);
//		//condition.setCurrentPage(condition.getCurrentPage()-1);
//		Map<String, Object> pagingResult = bService.pagingSearch(condition);
//		model.addAttribute("books", pagingResult.get("books"));
//		model.addAttribute("navigation", pagingResult.get("navigation"));
//		return "list";
//	}
	
	@RequestMapping(value = "/book",method = RequestMethod.GET, headers = {"Content-type=application/json" })
	public List<Book> showList(Model model, @ModelAttribute SearchCondition condition) {
		return bookService.search(condition);
	}
	/**
	 * 도서 정보를 저장한다.
	 * 
	 * @param book
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
//	@PostMapping("/regist")
//	public String doRegist(@ModelAttribute Book book) throws IllegalStateException, IOException {
//		// 변경된 파일 이름이 적용된 Book을 BookService를 통해 DB에 저장한다.
//		// T.X 처리를 위해 파일 저장에 대한 부분을 service로 이동시킨다.
//		bService.insert(book);
//		return "regist_result";
//	}
	
	@RequestMapping(value = "/book",method = RequestMethod.POST, headers = {"Content-type=application/json" })
	public int doRegist(Model model, @ModelAttribute Book book) throws IllegalStateException, IOException {
		return bookService.insert(book);
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public int bookModify(Model model, @ModelAttribute Book book) throws Exception {
		return bookService.update(book);
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.DELETE, headers = { "Content-type=application/json" })
	public int bookDelete(Model model, @ModelAttribute String isbn) throws Exception {
		return bookService.delete(isbn);
	}


}
