package com.lectopia.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.lectopia.model.BoardService;

/**
 * Servlet implementation class CheckPwdServlet
 * 삭제/수정 시 비밀번호 확인 서블릿  
 * @author yeeun
 *
 */
public class CheckPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String registerNo = request.getParameter("registerNo");
		String inputPwd = request.getParameter("password");
		
		JSONObject obj = new JSONObject();
		
		/*
		 * 해당 등록번호의 비밀번호 디비에서 가져옴
		 */
		
		BoardService service = new BoardService();
		String password = service.getPassword(Integer.parseInt(registerNo));
		
		if((password).equals(inputPwd))
			obj.put("result", "SUCCESS");
		
		else
			obj.put("result", "FAIL");

			PrintWriter writer = response.getWriter();
			writer.println(obj);
			writer.flush();
			writer.close();			
	}

}
