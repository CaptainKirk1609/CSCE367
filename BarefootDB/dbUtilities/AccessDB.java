package dbUtilities;

import java.sql.*;
import java.util.Scanner;

public class AccessDB {

	static Utilities testObj = new Utilities(); // Utilities object for testing
	static Scanner keyboard = new Scanner(System.in); // standard input
	
	public static void main(String[] args) throws SQLException{
		int choice;
		boolean done = false;

		while (!done) {
			System.out.println();
			displaymenu();
			choice = getChoice();
			switch (choice) {
			case 1: {
				openDB();
				break;
			}
			case 2: {
				callCreateSchedule();
				break;
			}
			case 3: {
				callNewAdviser();
				break;
			}
			case 4: {
				callDeleteCourse();
				break;
			}
			case 5: {
				callScheduleEval();
				break;
			}
			case 6:{
				callUpdatePreReq();
				break;
			}
			case 7: {
				testObj.closeDB(); //close the DB connection 
				break;
			}
			case 8: {
				done = true;
				System.out.println("Good bye");
				break;
			}
			case 9:{
				openDefault();
				break;
			}
			}
		}
	}
			
	static void displaymenu() {
		System.out.println("1)  Open the DB");
		System.out.println("2)  Create a new schedule");
		System.out.println("3)  Give student a new adviser");
		System.out.println("4)  Delete a course from a schedule");
		System.out.println("5)  Evaluate a schedule");
		System.out.println("6)  Update a prerequisite for a class");
		System.out.println("7)  Close the DB");
		System.out.println("8)  Quit");
	}
	
	static int getChoice() {
		String input;
		int i = 0;
		while (i < 1 || i > 10) {
			try {
				System.out.print("Please enter an integer between 1-10: ");
				input = keyboard.nextLine();
				i = Integer.parseInt(input);
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return i;
	}
	
	static void openDefault(){
		testObj.openDef();
	}
	
	static void openDB() {
		String user;
		String pass;
		
		System.out.print("Please enter the username: ");
		user = keyboard.nextLine();
		System.out.print("Please enter the password: ");
		pass = keyboard.nextLine();
		testObj.openDB(user,pass);
	}
	
	/**
	 * This medthod will allow student to create a new schedule
	 * Student need to enter the schedule number, the year plan they want to graduate ( 2 or 4 year plans),
	 * type of the degree they want (BA or BS)
	 */
	static void callCreateSchedule() throws SQLException {
		
		System.out.print("Please enter the schedule number:  ");
		String sNum = keyboard.nextLine();
		
		System.out.print("Please enter your desired year plan:  ");
		int yrPlan = Integer.parseInt(keyboard.nextLine());
		
		System.out.print("Please enter the degree type (BA or BS):  ");
		String type = keyboard.nextLine().toUpperCase();
				
		System.out.printf("\n%-12s\n", "Schedule");
		System.out.println("------------------------");
		System.out.printf("%s\t%s\t%s\t\n", "Schedule Number", "Year Plan", "Degree Type");
		testObj.createSchedule(sNum, yrPlan, type);
		
	}
	
	/**
	 * This method will allow student to add their new addvisor for their academic
	 * Student need to enter their sid and their faculty addvisor fid	 * 
	 */
	static void callNewAdviser() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter the Advisers ID number (8 numbers): ");
		String fid = keyboard.nextLine();
		System.out.print("Please enter the Students ID number (8 numbers): ");
		String sid = keyboard.nextLine();
		
		System.out.printf("\n%-12s\n", "Student and Adviser");
		System.out.println("------------------------");
		System.out.printf("%s\t\t%s\n", "SID", "FID");
		rs = testObj.newAdviser(fid, sid);
		
		while(rs.next()){
			System.out.printf("%s\t%s \n", rs.getString(1), rs.getString(2));
			
		}
	}
	
	static void callDeleteCourse() throws SQLException {
		System.out.print("Please enter the schedule number: ");
		String input = keyboard.nextLine();
		int sNum= Integer.parseInt(input);
		System.out.print("Please enter the course number: ");
		String cNum = keyboard.nextLine();
		System.out.print("Please enter the course department(CSCE): ");
		String dept = keyboard.nextLine();
		
		testObj.deleteCourse(sNum, cNum, dept);
	}
	
	static void callScheduleEval() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter your schedule number: ");
		int sch_num = Integer.parseInt(keyboard.nextLine());
		System.out.println("\nThere is class you should take to fulfill the degree: ");
		System.out.println("**********************************************************");
		System.out.printf("%s\t\t%s\t%s\n", "Course","Semester","Credit hours");
		rs = testObj.scheduleEval1(sch_num);
		while (rs.next()) {
			System.out.printf("%s\t%s\t\t%s\n", rs.getString(1),rs.getString(2),rs.getString(3));
		}
		
		rs = testObj.scheduleEval2(sch_num);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Please choose 1 course in this list: ");
		System.out.printf("%s\t\t%s\t%s\n", "Course","Semester","Credit hours");
		while (rs.next()) {
		System.out.printf("%s\t\t%s\t\t%s\n", rs.getString(1),rs.getString(2),rs.getString(3));
		}
		
		rs = testObj.scheduleEval3(sch_num);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Please choose 3 in this list: ");
		System.out.printf("%s\t\t%s\t%s\n", "Course","Semester","Credit hours");
		while (rs.next()) {
			System.out.printf("%s\t\t%s\t\t%s\n", rs.getString(1),rs.getString(2),rs.getString(3));
			}
	}
	
	static void callUpdatePreReq() throws SQLException {
		ResultSet rs;
		System.out.print("Enter the course number of the prerequisite to be changed: ");
		String oldPRnum = keyboard.nextLine();
		System.out.print("Enter the department of the prerequisite to be changed: ");
		String oldPRdept= keyboard.nextLine();
		System.out.print("Enter the course number of the new prerequisite: ");
		String newPRnum = keyboard.nextLine();
		System.out.print("Enter the department of the new prerequisite: ");
		String newPRdept = keyboard.nextLine();
		System.out.print("Enter the number of the course that the prerequisite is for: ");
		String cNum = keyboard.nextLine();
		System.out.print("Enter the department of the course that the prerequisite is for: ");
		String cDept = keyboard.nextLine();
				
		testObj.updatePreReq(oldPRnum, oldPRdept, newPRnum, newPRdept, cNum, cDept);
	}
	
}
	
