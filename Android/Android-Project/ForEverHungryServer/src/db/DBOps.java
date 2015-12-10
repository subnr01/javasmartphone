package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOps {
	private static final String CONNECTION_URL_PRIMITIVE = "jdbc:mysql://localhost:3306/";
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/ForEverHungryServer";
	private static final String DB_NAME = "ForEverHungryServer";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static final String TABLE_NAME = "user";
	private Connection conn = null;
	
	public boolean connectPrimitive() {
		try {
			conn = DriverManager.getConnection(CONNECTION_URL_PRIMITIVE, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.err.println("Error: Unable to connect to mysql DB. Try again!");
			return false;
		}
		
		return true;
	}
	
	public boolean connect() {
		try {
			conn = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.err.println("Error: Unable to connect to mysql DB. Try again!");
			return false;
		}
		
		return true;
	}
	
	public boolean createDB() {
		String queryStr = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";";
		try {
			Statement st = conn.createStatement();
			st.execute(queryStr);
		} catch (SQLException e) {
			System.err.println("ERROR creating DB:" + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public boolean createTable() {
		String queryStr = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (email VARCHAR(255) PRIMARY KEY, username VARCHAR(255), password VARCHAR(255));";
		Statement st;
		try {
			st = conn.createStatement();
			st.execute(queryStr);
		} catch (SQLException e) {
			System.err.println("ERROR creating Table:" + e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean createUser(String email, String username, String password) {
		String queryStr = "INSERT INTO " + TABLE_NAME + " VALUES('" + email + "','" + username + "', '" + password + "');";
		try {
			Statement st = conn.createStatement();
			st.execute(queryStr);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean authenticate(String username, String password) {
		String queryStr = "SELECT * FROM " + TABLE_NAME + " WHERE username='" + username + "';";
		try {
			Statement st = conn.createStatement();
			if(!st.execute(queryStr)) {
				return false;
			}
			ResultSet result =  st.getResultSet();
			if(!result.next()) {
				return false;
			}
			
			if(!result.getString("password").equals(password)) {
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// Ignore
		}
	}

	public boolean getpassword(String email, StringBuilder oldpassword) {
		System.out.println(email);
		String queryStr = "SELECT * FROM " + TABLE_NAME + " WHERE email='" + email + "';";
		try {
			Statement st = conn.createStatement();
			if(!st.execute(queryStr)) {
				return false;
			}
			ResultSet result =  st.getResultSet();
			if(!result.next()) {
				return false;
			}
			
			oldpassword.append(result.getString("password"));
			System.out.println(oldpassword);
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
			return false;
		}
		
		return true;
	}
}