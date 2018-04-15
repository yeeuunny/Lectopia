package com.lectopia.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lectopia.bean.BoardBean;
import com.lectopia.model.BoardDTO;
import com.lectopia.model.BoardService;

/**
 * Servlet implementation class GetDetailServlet
 * 하나의 게시글 조회 서블릿
 * @author yeeun
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String registerNo = request.getParameter("num");
		/*
		 * 해당 등록 번호에 해당하는 내용 디비에서 가져와 빈으로 만들어 속성 저장
		 */
		
		BoardService service = new BoardService();
		boolean res = service.updateCount(Integer.parseInt(registerNo));
		System.out.println("조회수증가: " + res);
		BoardDTO dto = service.get(Integer.parseInt(registerNo));
		
		/*
		 * DTO -> bean 
		 */
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		
		try {
			cal.setTime(df.parse(dto.getWriteDate()));
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String date = df.format(cal.getTime());
		BoardBean bean = new BoardBean(Integer.toString(dto.getRegisterNo()), dto.getTitle(), dto.getContent(), 
				dto.getWriter(), date, Integer.toString(dto.getCount()));
		
		request.setAttribute("bean", bean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("readPost.jsp");
		dispatcher.forward(request, response);
	}

}
