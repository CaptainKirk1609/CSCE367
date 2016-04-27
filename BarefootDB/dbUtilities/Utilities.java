package dbUtilities;

import java.sql.*;

public class Utilities {
	
	private Connection conn = null; // Connection object
	
	
	public void openDef() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException e) {
		System.out.println("Unable to load driver.");
		}  

		// Connect to the database
		String url = "jdbc:mysql://zoe.cs.plu.edu:3306/ba367_2016";
		String username = "ba367";
		String password = "LizIsQueen";
		 
		try {
		conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		System.out.println("Error connecting to database: " + e.toString());
		}
		 
		System.out.println("Accessing: ba367_2016");
	}// openDB

	
	public void openDB(String uname, String pass){
		// Load the MySQL JDBC driver
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException e) {
		System.out.println("Unable to load driver.");
		}  

		// Connect to the database
		String url = "jdbc:mysql://zoe.cs.plu.edu:3306/ba367_2016";
				 
		try {
		conn = DriverManager.getConnection(url, uname, pass);
		} catch (SQLException e) {
		System.out.println("Error connecting to database: " + e.toString());
		}
	}
	
	public void closeDB() {
		try {
			conn.close();
			conn = null;
			System.out.println("Connection to DB closed");
		} catch (SQLException e) {
			System.err.println("Failed to close database connection: " + e);
		}
	}// closeDB
	
	public ResultSet createSchedule(String sNum, int yrPlan, String type){
		ResultSet rset = null;
		String sql = null;
		String sid = null;
		
		try {
			// create a Statement and an SQL string for the statement
			Statement stmt = conn.createStatement();
			
			sid = "04040404";
			rset = null;
			
			//Debug 
			stmt = conn.createStatement();
			sql = "INSERT INTO schedule (sch_num, sid, year_plan, type) " +
				  "VALUES ('"+sNum+"', '"+sid+"', "+yrPlan+", '"+type+"') ";
			stmt.executeUpdate(sql);
			//EndDEBUG
			System.out.println("Success");
			
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}
		
		return rset;
	}
	
	public ResultSet newAdviser(String fid, String sid){
		ResultSet rset = null;
		String sql = null;
				
		try {
			Statement stmt = conn.createStatement();
			
			rset = null;
			
			stmt = conn.createStatement();
			sql = "UPDATE student SET fid = '"+fid+"' " +
				  "WHERE sid = '"+sid+"' ";
			stmt.executeUpdate(sql);
			//EndDEBUG
			Statement show = conn.createStatement();
			show = conn.createStatement();
			sql = null;
			sql = "SELECT sid, fid FROM student WHERE sid = '"+sid+"' ";
			rset = show.executeQuery(sql);
			
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}
		
		return rset;
	}
	
	public void deleteCourse(int sNum, String cNum, String dept){
		String sql = null;
				
		try {
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			sql = "DELETE FROM belongs_to "+
			"WHERE course_num = '"+cNum+" and sch_num = "+sNum+" and "+ 
			"dept = '"+dept+"' and sid = 01010101";
			stmt.executeUpdate(sql);
			//EndDEBUG
			System.out.print("Class "+dept+" "+cNum+" successfully deleted from schedule "+sNum);						
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}
	}
	
	public ResultSet scheduleEval(){
		return null;
	}
	
	public void updatePreReq(String oldPRNum,String oldPRDept,String newPRNum,String newPRDept,String cNum,String cDept){
		String sql = null;
		
		try {
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			sql = "delete from pre_req "+
				  "where dept='"+cDept+"' and course_num='"+cNum+"' and dept2='"+oldPRDept+"' and course_num2='"+oldPRNum+"'";
			stmt.executeUpdate(sql);
			sql = "insert into pre_req "+
				  "values ('"+cDept+"','"+cNum+"','"+newPRDept+"','"+newPRNum+"')";
			stmt.executeUpdate(sql);
			//EndDEBUG
			System.out.print(oldPRDept+" "+oldPRNum+" replaced with "+newPRDept+" "+newPRNum+"\n");						
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}
		
	}
	
}
