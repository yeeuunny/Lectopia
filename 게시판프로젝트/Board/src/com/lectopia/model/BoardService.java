package com.lectopia.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Servlet과 DAO 사이의 Service 클래스
 * @author yeeun
 *
 */
public class BoardService 
{
	/**
	 * DAO
	 */
	private BoardDAO dao;
	
	/**
	 * 디폴트 생성자
	 */
	public BoardService()
	{
		this(new BoardDAO());
	}
	/**
	 * 오버로디드 생성자
	 * @param dao
	 */
	public BoardService(BoardDAO dao)
	{
		this.dao = dao;
	}
	/**
	 * DB에 해당 DTO 저장
	 * @param dto 저장할 DTO 
	 * @return 저장 성공 여부
	 */
	public boolean register(BoardDTO dto)
	{
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		
		dto.setWriteDate(df.format(cal.getTime()));
		dto.setRegisterNo(dao.selectLastNo() + 1);
		
		return dao.insert(dto);
	}
	/**
	 * DB에 해당 등록번호의 정보를 수정
	 * @param registerNo 등록번호
	 * @param name 수정할 카테고리
	 * @param data 수정할 내용
	 * @return
	 */
	public boolean modify(int registerNo, String name, String data)
	{
		return dao.update(registerNo, name, data);
	}
	/* 전체 업데이트한다면 */
	/**
	 * DB에서 기존 데이터를 새로운 DTO로 수정
	 * @param dto 새로운 DTO
	 * @return 수정 성공 여부
	 */
	public boolean modify(BoardDTO dto)
	{
		return dao.update(dto);
	}
	/**
	 * DB에서 해당 등록번호의 기존 데이터를 새로운 DTO로 수정
	 * @param registerNo 등록번호
	 * @param newDTO 새로운 DTO 
	 * @return 수정 성공 여부
	 */
	public boolean modify(int registerNo, BoardDTO newDTO)
	{
		BoardDTO oldDTO = dao.selectByRegisterNo(registerNo);
		newDTO.setCount(oldDTO.getCount());
		
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		
		newDTO.setWriteDate(df.format(cal.getTime()));
		return dao.update(newDTO);
	}
	/* 서블릿에서 전의 내용과 새로운 내용을 가지고있을경우 => 전의 내용 가지게하려면..? */
	/**
	 * DB에서 해당 등록번호의 기존 DTO를 새로운 DTO와 비교하여 부분 수정
	 * @param registerNo 등록번호
	 * @param oldDTO 기존 DTO
	 * @param newDTO 새로운 DTO
	 * @return
	 */
	public boolean modify(int registerNo, BoardDTO oldDTO, BoardDTO newDTO)
	{
		boolean res = false;
		return res;
	}
	/**
	 * DB에서 해당 등록번호의 데이터 삭제
	 * @param registerNo 등록번호
	 * @return 삭제 성공 여부
	 */
	public boolean remove(int registerNo)
	{
		return dao.delete(registerNo);
	}
	/**
	 * DB에서 해당 등록번호의 DTO 반환
	 * @param registerNo 등록번호
	 * @return DTO
	 */
	public BoardDTO get(int registerNo)
	{
		return dao.selectByRegisterNo(registerNo);
	}
	/**
	 * DB의 해당 카테고리에서 부분 일치하는 데이터 목록 반환
	 * @param name 카테고리
	 * @param data 비교할 데이터
	 * @return DTO 목록
	 */
	public ArrayList<BoardDTO> get(String name, String data)
	{
		
		/*
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		*/
		ArrayList<BoardDTO> list = null;
		
		if("제목".equals(name))
			list = dao.select("title", data);
		else if("내용".equals(name))
			list = dao.select("content", data);
		
		else if("작성자".equals(name))
			list = dao.select("writer", data);
		
		else if("작성일".equals(name))
			list = dao.select("writeDate", data);
		
		else
		{
			list = new ArrayList<BoardDTO>();
			ArrayList<BoardDTO> all = dao.selectAll();
			list.add(all.get(Integer.parseInt(data)));
		}
		
		return list;
	}
	/**
	 * DB에서 모든 데이터 반환
	 * @return BoardDTO 목록
	 */
	public ArrayList<BoardDTO> getAll()
	{
		return dao.selectAll();
	}
	/**
	 * DB에서 해당 등록번호의 조회수 1 증가
	 * @param registerNo 등록번호
	 * @return 증가 성공 여부
	 */
	public boolean updateCount(int registerNo)
	{
		BoardDTO old = dao.selectByRegisterNo(registerNo);
		return dao.update(registerNo, "count", old.getCount() + 1);
	}
	/**
	 * DB에서 해당 등록번호의 비밀번호 찾아 반환
	 * @param registerNo 등록번호
	 * @return 비밀번호
	 */
	public String getPassword(int registerNo)
	{
		return dao.selectPassword(registerNo);
	}
	
	/**
	 * getter
	 * @return BoardDAO
	 */
	public BoardDAO getDao()		 { return dao; }
	/**
	 * setter
	 * @param dao BoardDAO
	 */
	public void setDao(BoardDAO dao) { this.dao = dao; }
	@Override
	public String toString() {
		return "BoardService [dao=" + dao + "]";
	}
	
}
