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
	
	static void openDB() {
		String user;
		String pass;
		
		System.out.print("Please enter the username: ");
		user = keyboard.nextLine();
		System.out.print("Please enter the password: ");
		pass = keyboard.nextLine();
		testObj.openDB(user,pass);
	}
	
	static void callCreateSchedule() throws SQLException {
		ResultSet rs;
		
		System.out.print("Please enter the schedule number:  ");
		String sNum = keyboard.nextLine();
		
		System.out.print("Please enter your desired year plan:  ");
		int yrPlan = Integer.parseInt(keyboard.nextLine());
		
		System.out.print("Please enter the degree type (BA or BS):  ");
		String type = keyboard.nextLine().toUpperCase();
		/**
		while(!type.equalsIgnoreCase("BA") || !type.equalsIgnoreCase("BS")){
			System.out.print("Invalid input, please enter either BA or BS: ");
			type = keyboard.nextLine();
		}
		*/
		
		System.out.printf("\n%-12s\n", "Schedule");
		System.out.println("------------------------");
		System.out.printf("%s\t%s\t%s\t\n", "Schedule Number", "Year Plan", "Degree Type");
		rs = testObj.createSchedule(sNum, yrPlan, type);
		/**
		while(rs.next()){ 
			System.out.printf("    %-8s     %s\n", rs.getString(1), 
					rs.getString(2) + rs.getString(3));
		}
		*/
	}
	
	static void callNewAdviser() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
	}
	
	static void callDeleteCourse() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
	}
	
	static void callScheduleEval() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
	}
	
	static void callUpdatePreReq() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
	}
	
}
	
