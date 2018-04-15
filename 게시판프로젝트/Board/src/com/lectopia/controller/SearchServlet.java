package com.lectopia.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lectopia.bean.BoardBean;
import com.lectopia.model.BoardDTO;
import com.lectopia.model.BoardService;

/**
 * Servlet implementation class SearchServlet
 * 게시글 검색, 전체 목록 조회 서블릿
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String select = request.getParameter("select");
		String inputSearch = request.getParameter("inputSearch");
		
		/*
		 * 파라미터 가지고 디비가서 목록 가져와 제이슨 어레이에 담음.
		 */
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		BoardService service = new BoardService();
		
		if("전체보기".equals(select) || select == null)
			list = service.getAll();
		else
			list = service.get(select, inputSearch);
		
		
		/*
		 * DTO -> bean 
		 */
		ArrayList<BoardBean> beans = new ArrayList<BoardBean>();
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		
		for(int i = 0; i < list.size(); i++)
		{
			BoardDTO dto = list.get(i);
			
			try {
				cal.setTime(df.parse(dto.getWriteDate()));
				
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = df.format(cal.getTime());
				
				System.out.println("date:" + date);
				beans.add(new BoardBean(Integer.toString(dto.getRegisterNo()), dto.getTitle(),
						dto.getContent(), dto.getWriter(), date, Integer.toString(dto.getCount())));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		if(select == null)
		{
			request.setAttribute("data", beans);
			RequestDispatcher dispatcher = request.getRequestDispatcher("postList.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			JSONArray data = new JSONArray();
			for(int i = 0; i < beans.size(); i++)
			{
				JSONObject obj= new JSONObject();
				obj.put("registerNo", beans.get(i).getRegisterNo());
				obj.put("no", i + 1);
				obj.put("title", beans.get(i).getTitle());
				obj.put("writer", beans.get(i).getWriter());
				obj.put("writeDate", beans.get(i).getWriteDate());
				obj.put("count", beans.get(i).getCount());
				
				data.add(obj);
			}

			PrintWriter writer = response.getWriter();
			writer.println(data);
			writer.flush();
			writer.close();		
		}
		
	}

}
