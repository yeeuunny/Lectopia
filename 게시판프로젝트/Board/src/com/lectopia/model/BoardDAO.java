package com.lectopia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * DAO
 * @author yeeun
 *
 */
public class BoardDAO
{
	/**
	 * DB 연결 객체 
	 */
	private DBConnector connector;
	
	/**
	 * 디폴트 생성자
	 */
	public BoardDAO(){
		connector = new DBConnector();
	}
	
	/**
	 * DB에 전달인자의 레코드 저장
	 * @param dto 저장할 DTO
	 * @return 저장 성공 여부 
	 */
	public boolean insert(BoardDTO dto)
	{
		boolean res = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into Board_tb values(?, ?, ?, ?, ?, ?, ?)";

		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getRegisterNo());
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getWriter());
				pstmt.setString(5, dto.getPassword());
				pstmt.setString(6, dto.getWriteDate());
				pstmt.setInt(7, dto.getCount());
				pstmt.executeUpdate();
			
				res = true;
				
			}catch(Exception e){
				
				res = false;
				e.printStackTrace();
				
			}finally{
				if(conn != null) connector.disconnect();
			}
		}
		return res;
	}
	/**
	 * DB에서 전달인자의 레코드 수정 
	 * @param dto 수정할 DTO
	 * @return 수정 성공 여부
	 */
	public boolean update(BoardDTO dto)
	{
		Boolean res = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update Board_tb set title=?, content=?, writer=?, password=?, "
				+ "writeDate=?, count=? where registerNo=?";

		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getWriter());
				pstmt.setString(4, dto.getPassword());
				pstmt.setString(5, dto.getWriteDate());
				pstmt.setInt(6, dto.getCount());
				pstmt.setInt(7, dto.getRegisterNo());
				pstmt.executeUpdate();
			
				res = true;
				
			}catch(Exception e){
				
				res = false;
				e.printStackTrace();
				
			}finally{
				if(conn != null) connector.disconnect();
			}
		}
		return res;
	}
	/**
	 * DB에서 해당 등록번호의 레코드 수정
	 * @param registerNo 등록번호
	 * @param name 수정할 카테고리
	 * @param data 수정할 데이터
	 * @return 수정 성공 여부
	 */
	public boolean update(int registerNo, String name, String data)
	{
		boolean res = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update board_tb set " + name + "=? where registerNo=?";
		if(connector.connect())
		{
			conn = connector.getConn();
			
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, data);
				pstmt.setInt(2, registerNo);
				
				pstmt.executeUpdate();
				res = true;
				
			}catch(Exception e){
				e.printStackTrace();
				res = false;
			}finally{
				if(conn != null) connector.disconnect();
			}
		}
		return res;
	}
	/**
	 * DB에서 해당 등록번호의 레코드 수정
	 * @param registerNo 등록번호
	 * @param name 수정할 카테고리
	 * @param data 수정할 데이터
	 * @return 수정 성공 여부
	 */
	public boolean update(int registerNo, String name, int data)
	{
		boolean res = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update board_tb set " + name + "=? where registerNo=?";
		if(connector.connect())
		{
			conn = connector.getConn();
			
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, data);
				pstmt.setInt(2, registerNo);
				
				pstmt.executeUpdate();
				res = true;
			}catch(Exception e){
				e.printStackTrace();
				res = false;
			}finally{
				if(conn != null) connector.disconnect();
			}
		}
		return res;
	}
	/**
	 * DB에서 해당 등록번호의 레코드 삭제
	 * @param registerNo 등록번호
	 * @return 삭제 성공 여부
	 */
	public boolean delete(int registerNo)
	{
		Boolean res;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete Board_tb where registerNo=?";

		connector.connect();
		conn = connector.getConn();
		try{
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, registerNo);
			pstmt.executeUpdate();
		
			res = true;
			
		}catch(Exception e){
			
			res = false;
			e.printStackTrace();
			
		}finally{
			connector.disconnect();
		}
		return res;
	}
	/**
	 * DB에서 전체 레코드 반환
	 * @return 전체 레코드 목록
	 */
	public ArrayList<BoardDTO> selectAll()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "select * from Board_tb order by writeDate desc";
		
		if(connector.connect())
		{
			conn = connector.getConn();
			
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					
					list.add(new BoardDTO (rs.getInt("registerNo"), rs.getString("title"),
							rs.getString("content"),rs.getString("writer"), rs.getString("password"), 
							rs.getString("writeDate"), rs.getInt("count")));
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				connector.disconnect();
			}
		}
		return list;
	}
	/**
	 * DB에서 해당 등록번호의 레코드 반환
	 * @param registerNo 등록번호
	 * @return 등록번호의 레코드
	 */
	public BoardDTO selectByRegisterNo(int registerNo)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		String sql = "select * from board_tb where registerNo=?";
		
		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, registerNo);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					dto = new BoardDTO (rs.getInt("registerNo"), rs.getString("title"),
						rs.getString("content"),rs.getString("writer"), rs.getString("password"), 
						rs.getString("writeDate"), rs.getInt("count"));
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(conn != null)
					connector.disconnect();
			}
		}
		return dto;
	}
	/**
	 * DB에서 레코드의 컬럼 내용과 일치하는 데이터 반환
	 * @param name 컬럼 
	 * @param data 데이터
	 * @return 레코드 목록
	 */
	public ArrayList<BoardDTO> select(String name, int data)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select * from board_tb where " + name + "like ? order by writeDate desc";
		
		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, "%" + Integer.toString(data) + "%" );
				rs = pstmt.executeQuery();
				
				if(rs.next())
					list.add(new BoardDTO (rs.getInt("registerNo"), rs.getString("title"),
						rs.getString("content"),rs.getString("writer"), rs.getString("password"), 
						rs.getString("writeDate"), rs.getInt("count")));
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(conn != null)
					connector.disconnect();
			}
		}
		return list;
	}
	/**
	 * DB에서 레코드의 컬럼 내용과 일치하는 데이터 반환
	 * @param name 컬럼 
	 * @param data 데이터
	 * @return 레코드 목록
	 */
	public ArrayList<BoardDTO> select(String name, String data)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "select * from board_tb where " + name + " like ? order by writeDate desc";
		
		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, "%" + data + "%");
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					list.add(new BoardDTO (rs.getInt("registerNo"), rs.getString("title"),
						rs.getString("content"),rs.getString("writer"), rs.getString("password"), 
						rs.getString("writeDate"), rs.getInt("count")));
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(conn != null)
					connector.disconnect();
			}
		}
		return list;
	}
	/**
	 * DB에서 해당 등록번호의 비밀번호 가져옴
	 * @param registerNo 등록번호
	 * @return 비밀번호
	 */
	public String selectPassword(int registerNo)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String password = null;
		
		String sql = "select password from board_tb where registerNo=?";
		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, registerNo);
				
				rs = pstmt.executeQuery();
				if(rs.next())
					password = rs.getString("password");
			}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				if(conn != null) connector.disconnect();
			}
		}
		return password;
	}
	/**
	 * DB에서 마지막 레코드의 등록번호 반환
	 * @return 마지막 등록번호
	 */
	public int selectLastNo()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int registerNo = 0;
		
		String sql = "select registerNo from (select registerNo from board_tb order by registerNo desc) where rownum=1";
		if(connector.connect())
		{
			conn = connector.getConn();
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next())
					registerNo = rs.getInt("registerNo");
			}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				if(conn != null) connector.disconnect();
			}
		}
		return registerNo;
	}
	/**
	 * getter
	 * @return DBConnector
	 */
	public DBConnector getConnector() 				{ return connector; }
	/**
	 * setter
	 * @param connector DBConnector
	 */
	public void setConnector(DBConnector connector) { this.connector = connector; }
	
}
