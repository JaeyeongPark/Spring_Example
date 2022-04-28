package com.ssafy.ws.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.SearchCondition;
import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.UserService;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	/**
	 * BookService를 주입받는다.
	 */
	@Autowired
	BookService bService;
	/**
	 * UserService를 주입받는다.
	 */
	@Autowired
	UserService uService;

	/**
	 * <pre>
	 *  / 또는 /index 요청이 get 방식으로 들어왔을 때 index 로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public String showRoot() {
		return "index";
	}

	/**
	 * <pre>
	 * /error/404 요청이 get 방식으로 들어왔을 때 error/404로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/error/404")
	public String showError404() {
		return "error/404";
	}

	/**
	 * <pre>
	 * /login 요청이 post 방식으로 왔을 때 login 처리한다.
	 * </pre>
	 * 
	 * 사용자의 요청에서 계정 정보를 추출해 로그인 처리한다.
	 * 로그인 성공 시 session에 정보를 담고 redirect로 index로 이동한다.
	 * 그렇지 않을 경우는 로그인 실패 메시지를 model에 담고 forward로 index로 이동한다.
	 * 
	 * @param user
	 *            전달된 파라미터는 ModelAttribute를 통해서 객체로 받을 수 있다.
	 * @param session
	 *            사용자 정보를 세션에 저장하기 위해 사용한다.
	 * @param model
	 *            Request scope에 정보를 저장하기 위해서 사용된다.
	 * @return
	 */
	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, HttpSession session, Model model) {
		// UserService를 통해 사용자 계정을 가져온다.
		User selected = uService.select(user.getId());
		// 계정이 존재하고 비밀번호가 일치하면 로그인 성공, 그렇지 않다면 실패이다.
		if (selected != null && user.getPass().equals(selected.getPass())) {
			session.setAttribute("loginUser", selected);
			return "redirect:/";
		} else {
			model.addAttribute("msg", "로그인 실패");
			return "index";
		}
	}

	/**
	 * <pre>
	 * /logout을 get 방식으로 요청했을 때 session을 만료 시키고 로그아웃 처리한다.
	 * </pre>
	 * 
	 * 다음 경로는 redirect 형태로 /index로 이동한다.
	 * 
	 * @param session
	 * @return
	 */

	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}

	/**
	 * <pre>
	 * /list를 get 방식으로 요청할 때 도서 정보를 보여준다.
	 * </pre>
	 * 
	 * Paging을 이용하는 pagingSearch를 이용하도록 변경한다.
	 * 
	 * @return
	 */
	@GetMapping("/list")
	public String showList(Model model, @ModelAttribute SearchCondition condition) {
		//List<Book> books = bService.search(condition);
//		logger.debug("전달받은 condition: {}", condition);
		//condition.setCurrentPage(condition.getCurrentPage()-1);
		Map<String, Object> pagingResult = bService.pagingSearch(condition);
		model.addAttribute("books", pagingResult.get("books"));
		model.addAttribute("navigation", pagingResult.get("navigation"));
		return "list";
	}

	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}

	/**
	 * 도서 정보를 저장한다.
	 * 
	 * @param book
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Book book) throws IllegalStateException, IOException {
		// 변경된 파일 이름이 적용된 Book을 BookService를 통해 DB에 저장한다.
		// T.X 처리를 위해 파일 저장에 대한 부분을 service로 이동시킨다.
		bService.insert(book);
		return "regist_result";
	}

	/**
	 * <pre>
	 * /error/400 요청이 get 방식으로 들어왔을 때 error/400로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/error/400")
	public String showError400() {
		return "error/400";
	}

	/**
	 * <pre>
	 * /error/500 요청이 get 방식으로 들어왔을 때 error/500로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/error/500")
	public String showError500() {
		return "error/500";
	}
}
