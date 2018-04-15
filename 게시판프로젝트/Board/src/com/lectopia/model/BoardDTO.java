package com.lectopia.model;

import java.io.Serializable;
/**
 * DTO
 * @author yeeun
 *
 */
public class BoardDTO implements Serializable
{
	/**
	 * 등록번호
	 */
	private int registerNo;
	/**
	 * 제목
	 */
	private String title;
	/**
	 * 내용
	 */
	private String content;
	/**
	 * 작성자
	 */
	private String writer;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 작성일
	 */
	private String writeDate;
	/**
	 * 조회수
	 */
	private int count;
	
	/**
	 * 디폴트 생성자
	 */
	public BoardDTO() {
		super();
	}
	/**
	 * 오버로디드 생성자 
	 * @param title 제목 
	 * @param content 내용 
	 * @param writer 작성
	 * @param password 비밀번호
	 * @param count 조회수
	 */
	public BoardDTO(String title, String content,
			String writer, String password, int count){
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.count = count;
	}
	/**
	 * 오버로디드 생성자 
	 * @param registerNo 등록번호
	 * @param title 제목
	 * @param content 내용
	 * @param writer 작성자
	 * @param password 비밀번호
	 */
	public BoardDTO(int registerNo, String title, String content,
			String writer, String password) {
		super();
		this.registerNo = registerNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
	}
	/**
	 * 오버로디드 생성자
	 * @param registerNo 등록번호
	 * @param title 제목
	 * @param content 내용
	 * @param writer 작성자
	 * @param password 비밀번호
	 * @param writeDate 작성일
	 * @param count 조회수
	 */
	public BoardDTO(int registerNo, String title, String content,
			String writer, String password, String writeDate, int count) {
		super();
		this.registerNo = registerNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.writeDate = writeDate;
		this.count = count;
	}
	/**
	 * getter 
	 * @return 등록번호 
	 */
	public int getRegisterNo() {
		return registerNo;
	}
	/**
	 * setter
	 * @param registerNo 등록번호 
	 */
	public void setRegisterNo(int registerNo) {
		this.registerNo = registerNo;
	}
	/**
	 * getter
	 * @return 제목
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * setter
	 * @param title 제목
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * getter
	 * @return 내용
	 */
	public String getContent() {
		return content;
	}
	/**
	 * setter
	 * @param content 내용
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * getter
	 * @return 작성자
	 */
	public String getWriter() {
		return writer;
	}
	/**
	 * setter
	 * @param writer 작성자
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}
	/**
	 * getter
	 * @return 비밀번호
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * setter
	 * @param password 비밀번호
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * getter
	 * @return 작성일
	 */
	public String getWriteDate() {
		return writeDate;
	}
	/**
	 * setter
	 * @param writeDate 작성일
	 */
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	/**
	 * getter
	 * @return 조회수
	 */
	public int getCount() {
		return count;
	}
	/**
	 * setter
	 * @param count 조회수
	 */
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "BoardDTO [registerNo=" + registerNo + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", password="
				+ password + ", writeDate=" + writeDate + ", count=" + count
				+ "]";
	}
}
