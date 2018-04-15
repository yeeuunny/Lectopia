package com.lectopia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lectopia.model.BoardDTO;
import com.lectopia.model.BoardService;

/**
 * Servlet implementation class RegisterServlet
 * 게시글 등록 서블릿
 * @author yeeun
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.sendRedirect("writePost.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		
		System.out.println("title:" + title);
		System.out.println("content:" + content);
		System.out.println("writer:" + writer);
		System.out.println("password:" + password);
		/*
		 * 파라미터 꺼내 가지고 디비로 가서 저장 
		 */
		
		BoardDTO dto = new BoardDTO(request.getParameter("title"), request.getParameter("content"), 
				request.getParameter("writer"), request.getParameter("password"), 0);
		
		BoardService service = new BoardService();
		System.out.println("등록:" + service.register(dto));
		
		response.sendRedirect("/Board/search.do");
	}

}
