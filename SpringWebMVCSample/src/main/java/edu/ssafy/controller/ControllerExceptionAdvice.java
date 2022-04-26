package edu.ssafy.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionAdvice {
	
	@ExceptionHandler(SQLException.class)
	public String exceptionHandlerSQL(SQLException e, Model m) {
		m.addAttribute("message",e.getMessage());
		return "error/error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(SQLException e, Model m) {
		m.addAttribute("message",e.getMessage());
		return "error/error";
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String exceptionHandler404(SQLException e, Model m) {
		m.addAttribute("message",e.getMessage());
		return "error/404";
	}
	
}
