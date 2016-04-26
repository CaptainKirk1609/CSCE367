package dbUtilities;

import java.sql.*;

public class Utilities {
	
	private Connection conn = null; // Connection object
	
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
			/**
			sql = "SELECT sid FROM student " +
				  "WHERE s_first='Ryan'";
			rset = stmt.executeQuery(sql);
			sid = rset.getString(1);
			*/
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
		
		/**
		
		try {
			// create a Statement and an SQL string for the statement
			Statement stmt = conn.createStatement();
			sql = "INSERT INTO schedule (sch_num, sid, year_plan, type) " +
				  "VALUES ('"+sNum+"%', '"+sid+"%', '"+yrPlan+"%', '"+type+"%') ";
			rset = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("createStatement " + e.getMessage() + sql);
		}
		*/

		return rset;
		
	}
}
