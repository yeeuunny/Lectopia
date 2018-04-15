package com.lectopia.bean;
/**
 * Bean
 * @author yeeun
 *
 */
public class BoardBean {
	/**
	 * 등록번호
	 */
	private String registerNo;
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
	 * 작성일
	 */
	private String writeDate;
	/**
	 * 조회수
	 */
	private String count;
	
	/**
	 * 디폴트 생성자
	 */
	public BoardBean() {
		super();
	}
	/**
	 * 오버로디드 생성자
	 * @param registerNo 등록번호 
	 * @param title 제목
	 * @param content 내용
	 * @param writer 작성자
	 * @param writeDate 작성일
	 * @param count 조회수
	 */
	public BoardBean(String registerNo, String title, String content,
			String writer, String writeDate, String count) {
		super();
		this.registerNo = registerNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.count = count;
	}
	/**
	 * getter
	 * @return 등록번호 
	 */
	public String getRegisterNo() {
		return registerNo;
	}
	/**
	 * setter
	 * @param registerNo 등록번호
	 */
	public void setRegisterNo(String registerNo) {
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
	public String getCount() {
		return count;
	}
	/**
	 * setter
	 * @param count 조회수
	 */
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "BoardBean [registerNo=" + registerNo + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", password="
				+ ", writeDate=" + writeDate + ", count=" + count
				+ "]";
	}
	
}
