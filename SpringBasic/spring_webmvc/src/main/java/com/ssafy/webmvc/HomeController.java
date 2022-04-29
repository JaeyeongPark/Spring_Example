package com.ssafy.webmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.webmvc.model.HelloDto;
import com.ssafy.webmvc.model.ParameterDto;
import com.ssafy.webmvc.model.service.HelloService;


public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private HelloService helloService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		HelloDto helloDto = new HelloDto();
		helloDto.setMessage("Hello Spring Web MVC!!!");
		mav.addObject("hello", helloDto);
		mav.setViewName("step01/hello");
		return mav;
	}
	
	@RequestMapping("/hellomvc")
	public ModelAndView helloMvc() {
		ModelAndView mav = new ModelAndView();
		HelloDto helloDto = helloService.greeting();
		mav.addObject("hello", helloDto);
		mav.setViewName("step02/hello");
		return mav;
	}
	
	@RequestMapping("/parameter")
	public String parameter() {
		return "step03/form";
	}
	
//	@RequestMapping(value = "/sendparam", method = RequestMethod.GET)
//	public String parameterTest(String userid, String username, String area) {
//		System.out.println(" >>> " + userid + "  " + username + "  " + area);
//		return "step03/form";
//	}
	
	@RequestMapping(value = "/sendparam", method = RequestMethod.GET)
	public String parameterTest(@RequestParam("userid") String id, @RequestParam("username") String name, String area) {
		System.out.println(" >>> " + id + "  " + name + "  " + area);
//		String val[] = {id, name, area};
//		logger.debug("id : {}, name : {}, area : {}", val);
		return "step03/form";
	}
	
	@RequestMapping(value = "/sendparam", method = RequestMethod.POST)
	public String parameterTest(ParameterDto parameterDto, Model model) {
//		System.out.println(" array >>> " + parameterDto.getUserid() + "  " + parameterDto.getUsername() + "  " + parameterDto.getFruit()[0]);
//		System.out.println(" list >>> " + parameterDto.getUserid() + "  " + parameterDto.getUsername() + "  " + parameterDto.getFruit().get(0));
		model.addAttribute("data", parameterDto);
		return "step03/result";
	}
	
}
