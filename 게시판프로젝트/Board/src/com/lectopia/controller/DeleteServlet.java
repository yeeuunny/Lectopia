package com.lectopia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lectopia.model.BoardService;

/**
 * Servlet implementation class DeleteServlet
 * 게시글 삭제 서블릿 
 * @author yeeun
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String registerNo = request.getParameter("registerNo");
		System.out.println(registerNo);
		
		/*
		 * 받아온 등록번호로 디비가서 지움 
		 */
		
		
		BoardService service = new BoardService();
		boolean res = service.remove(Integer.parseInt(registerNo));
		System.out.println(res);
		response.sendRedirect("/Board/search.do");
	}

}
