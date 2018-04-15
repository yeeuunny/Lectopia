package com.lectopia.model;

public class TestMain {
	public static void main(String[] args) {
	
		BoardDAO dao = new BoardDAO();
		//System.out.println(dao.insert(new BoardDTO(20, "제목", "내용", "작성자", "비밀번호", "작성일", 1)));
		//System.out.println(dao.selectAll());
		//System.out.println(dao.update(new BoardDTO(33, "제목", "내용", "예은", "1234", "2016-12-22", 2)));
		//System.out.println(dao.selectAll());
		//System.out.println(dao.update(33, "title", "드디어"));
		
		//System.out.println(dao.update(33, "count", 22));
		
		//System.out.println(dao.delete(33));
		
		//System.out.println(dao.selectRegisterNo(1));
		
		//System.out.println(dao.selectPassword(1));
		
		System.out.println(dao.selectAll());
		
	}
}
