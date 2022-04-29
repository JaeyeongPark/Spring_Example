package com.ssafy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{
	
	
	/*
	 * Servlet 에서 Controller 중간에 위치
	 * 
	 * preHandle : servlet -> Controller
	 *  - return : true -> servlet에서 Controller 호출
	 *             false -> Controller를 호출하지 않음
	 */
	
	//서블렛에서 컨트롤 갈 때
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		System.out.println("Common preHandle");
		if(servletPath.contentEquals("/listmember.do")){
			String id = (String) request.getSession().getAttribute("id");
			if(id == null) {
				response.sendRedirect("initpage.do");
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * Controller -> Servlet
	 */
	
	//컨트롤에서 서블렛으로 갈 때
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Common postHandle");
		
	}
	
	
	
	/*
	 *  View 처리가 끝나고나서
	 */
	
	//view까지 처리가 끝났을 때
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Common afterHandle");
		
	}
}
