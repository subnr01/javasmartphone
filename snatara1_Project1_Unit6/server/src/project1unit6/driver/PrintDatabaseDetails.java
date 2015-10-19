/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit6.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class PrintDatabaseDetails {
	
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";

	
	/*
	 * printTable()
	 * 
	 * print the Automobile information.
	 * 
	 * */
	public void printTable() {
		
		System.out.println("print Automobile Information");
		System.out.println("**********************************************************************");
		printAutomobileInfo();
		System.out.println("**********************************************************************");
		
		System.out.println("print Option Set Information");
		System.out.println("**********************************************************************");
		printOptionSetInfo();
		System.out.println("**********************************************************************");
		
		System.out.println("print Option Information");
		System.out.println("**********************************************************************");
		printOptionInfo();
		System.out.println("**********************************************************************");
		
	}
	
	
	/*
	 * printAutomobileInfo()
	 * 
	 * print the Automobile table
	 * 
	 * */

	public void printAutomobileInfo() {
		Connection connection = null;
		Statement statement = null;
		String command = null;
		ResultSet rs;
		try {
			
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("printdatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
					System.out.println("Error: " + e.toString());
			}
			
			
			statement = connection.createStatement();
			command = executelist.getProperty("PrintAutomobile");
			rs = statement.executeQuery(command);
			
			System.out.format("%-5s%20s%21s\n", "AutoId", "AutoName", "BasePrice");
			
			while (rs.next()) {
				System.out.format("%-18s%-20s%9s\n", rs.getString("AutoId"), rs.getString("AutoName"),
						rs.getString("BasePrice"));
			}
			
			statement.close();
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * printOptionSetInfo() 
	 * 
	 * 
	 * print the OptionSet table
	 * 
	 * */


	public void printOptionSetInfo() {
		Connection connection = null;
		Statement statement = null;
		String command = null;

		ResultSet rs;
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();

			
			
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("printdatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
					System.out.println("Error: " + e.toString());
			}
			
			statement = connection.createStatement();
			command = executelist.getProperty("PrintOptionSet");
			rs = statement.executeQuery(command);
			
			System.out.format("%10s%20s%16s\n", "OptionSetId", "OptionSetName", "AutoId");
			
			while (rs.next()) {
				System.out.format("%-18s%-20s%9s\n", rs.getString("OptionSetId"), rs.getString("OptionSetName"),
						rs.getString("AutoId"));

			}
			
			statement.close();
			rs.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}
	
	
	/*
	 * printOptionInfo()
	 * 
	 * print the AutoOptionTable
	 * 
	 * */

	public void printOptionInfo() {
		Connection connection = null;
		Statement statement = null;
		String command = null;

		ResultSet rs;
		try {
			
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();
			
			
			
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("printdatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
					System.out.println("Error: " + e.toString());
			}
			
			statement = connection.createStatement();
			
			command = executelist.getProperty("PrintAutomobileOption");
			rs = statement.executeQuery(command);
			
			System.out.format("%-5s%20s%41s%15s%20s\n", "OptionId", "OptionName", "OptionPrice","OptionSetId","AutoID");
			
			while (rs.next()) {
				System.out.format("%-18s%-40s%-15s%-15s%11s\n", rs.getString("OptionId"), rs.getString("OptionName"),
						rs.getString("OptionPrice"), rs.getString("OptionSetId"), rs.getString("AutoID"));
			}
			
			statement.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}
}
