package edu.ssafy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{
	/*
	 * Servler 에서 Controller 중간에 위치
	 * preHandle : serlver  -> Controller
	 * return : true -> servler 에서  Controller 호출
	 *          false -> Controller를 호출하지 안음
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		/*
		 * true : 
		 */
//		String servletPath = request.getServletPath();
//		System.out.println(servletPath);
//		System.out.println("Common preHandle");
//		if (servletPath.equals("/listmember.do")) {
//			String id = (String) request.getSession().getAttribute("id");
//			if(id == null) {
//				response.sendRedirect("initpage.do");
//				return false;
//			}
//		}
		return true;
	}
	
	/*
	 *  Controller -> Servlet
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("Common postHandle");
	}
	
	/*
	 * View 처리가 끝나고 나서
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("Common afterCompletion");
	}
}
