package edu.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.dto.MemDTO;
import edu.ssafy.service.MemService;

@Controller
//@RestController
@RequestMapping("/mem")
public class MemberController {
	
	@Autowired
	private MemService memser;
	
	@RequestMapping(value="/idcheck", method = {RequestMethod.GET,RequestMethod.POST})
	private @ResponseBody Map<String,String> idCheck(/*@RequestBody MemDTO m,*/ String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//System.out.println(m);
		HashMap<String, String> map = new HashMap();
		if(memser.idCheck(id)) {
			map.put("res", "사용할 수 있는 아이디 입니다");
		}else {
			map.put("res", "사용할 없는 아이디 입니다");
		}
		
		System.out.println(map);
		return map; 
	}
	
	@RequestMapping(value="/login.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		boolean login = memser.login(id, pw);
		if(login) {
			request.getSession().setAttribute("id", id);
			return "redirect:initpage.do";
		}else {
			request.setAttribute("res", "로그인 실패");
			return "result";
		}
	}
	
	@RequestMapping(value="/logout.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return "redirect:initpage.do"; 
	}
	@RequestMapping(value="/viewmember.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String viewMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/viewmember"; 
	}
	
	@RequestMapping(value="/updatemember.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String updateMember(MemDTO m) throws Exception {
		
		memser.updateMember(m);
		
		return "redirect:listmember.do";
	}
	@RequestMapping(value="/delmember.do",method = {RequestMethod.GET,RequestMethod.POST})
	private String deleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		memser.deleteMember(new MemDTO(request.getParameter("id")
				, request.getParameter("pw")
				, request.getParameter("name")
				, request.getParameter("addr")
				, request.getParameter("age")
				));
		return "redirect:listmember.do";
	}
	@RequestMapping(value="/listmember.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String listMember(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException, ServletException {
		List<MemDTO> list = memser.listMember();
		request.setAttribute("list", list);
		return "member/listmember";
	}
	@RequestMapping(value="/regmember.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String regMember(@RequestParam("id")String id,
			@RequestParam("pw")String pw,
			@RequestParam("name")String name,
			@RequestParam("addr")String addr,
			@RequestParam("age")String age) throws Exception, IOException {
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String name = request.getParameter("name");
//		String addr = request.getParameter("addr");
//		String age = request.getParameter("age");
		System.out.printf("%s %s %s %s %s \n", id, pw, name, addr, age);
		memser.regMember(new MemDTO(id, pw, name, addr, age));
		
		return "redirect:listmember.do";
	}
	
	@RequestMapping(value="/initpage.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String init(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		return "index";
	}
	@RequestMapping(value="/post.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String postTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String[] values = request.getParameterValues("hobby");
		System.out.println(name + "," + age);
		System.out.println(Arrays.toString(values));

		request.setAttribute("res", "<h1>" + name + "," + age + "</h1> <h1>" + Arrays.toString(values) + "</h1>");
		return "result";
	}
	@RequestMapping(value="/get.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String getTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// 1 charset 설정
		// 2 파라미터 처리
		// 3 login 처리
		// 4 화면처리
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		request.setAttribute("res", "<h1>" + name + "," + age + "</h1>");
		return "result";
	}
	@RequestMapping(value="/regmemberform.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String loginRegPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "member/regmember";
	}
	@RequestMapping(value="/loginmemberform.do", method = {RequestMethod.GET,RequestMethod.POST})
	private String loginMemberform(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "member/login";
	}
	
}


