package com.lectopia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lectopia.model.BoardDTO;
import com.lectopia.model.BoardService;

/**
 * Servlet implementation class ModifyServlet
 * 게시글 수정 서블릿
 * @author yeeun
 */
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		int registerNo = Integer.parseInt(request.getParameter("registerNo"));
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		
		System.out.println("title:" + title);
		System.out.println("content:" + content);
		System.out.println("writer:" + writer);
		System.out.println("password:" + password);
		/*
		 * 수정한 내용 파라미터에서 꺼내 가지고 디비가서 저장
		 */
		
		BoardService service = new BoardService();
		boolean res = service.modify(registerNo, new BoardDTO(registerNo, request.getParameter("title"),request.getParameter("content"),
				request.getParameter("writer"),request.getParameter("password")));
		
		System.out.println("수정:" + res);
		response.sendRedirect("/Board/search.do");
		
	}

}
