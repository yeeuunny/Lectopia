package com.lectopia.model;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnector {

	private static String url;
	private static String id;
	private static String pw;
	private Connection conn;
	
	static{
		url = "jdbc:oracle:thin:@192.168.0.5:1521:XE";
		 id = "hr";
		 pw = "1111";
	}
	
	/**
	 * getter
	 * @return url
	 */
	public static String getUrl()         { return url; }
	/**
	 * getter
	 * @return id
	 */
	public static String getId()          { return id;  }
	/**
	 * getter
	 * @return pw
	 */
	public static String getPw()  		  { return pw;  }
	/**
	 * setter
	 * @param url
	 */
	public static void setUrl(String url) { DBConnector.url = url; }
	/**
	 * setter
	 * @param id
	 */
	public static void setId(String id)   { DBConnector.id  = id;  }
	/**
	 * setter 
	 * @param pw
	 */
	public static void setPw(String pw)   { DBConnector.pw  = pw;  }
	/**
	 * getter
	 * @return Connection
	 */
	public Connection getConn() 		  { return conn; }
	/**
	 * setter 
	 * @param conn Connection
	 */
	public void setConn(Connection conn)  { this.conn = conn; }
	
	@Override
	public String toString() {
		return "DBConnector [url=" + url + ", id=" + id + ", pw=" + pw + "]";
	}

	/**
	 * DB와 연결
	 * @return 연결 성공 여부
	 */
	public boolean connect()
	{
		boolean res;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			res = true;
			
		}catch(Exception e){
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	/**
	 * DB와의 연결 차단
	 * @return 연결 차단 성공 여부
	 */
	public boolean disconnect()
	{	
		boolean res;
		try{
			if(conn != null) conn.close();
			res = true;
		}catch(Exception e){
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
}
