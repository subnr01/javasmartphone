/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit6.util;

import java.io.*;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Properties;
import project1unit6.model.Automobile;


public class DatabaseIO {
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";

	/*
	 * createDatabase()
	 * 
	 * create the database in 
	 * mysql
	 * 
	 * 
	 */

	public void createDataBase() {
		Statement statement = null;
		BufferedReader bufferedReader = null;
		try {
			
			Connection connection = null;
			/* connect to database */
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connection to Database successful");
			}
			statement = connection.createStatement();
			String command = null;
			
			/*
			 * Check whether the database already exists.
			 * If yes, then delete it.
			 */
			try {
				Properties deletelist = new Properties();
				FileInputStream in = new FileInputStream("deletedatabase.Properties");
				deletelist.load(in);
				command = deletelist.getProperty("DeleteDataBase");
				statement.executeUpdate(command);
				in.close();
			} catch (IOException ex ) {
				System.out.println("ERROR: " + ex.toString());
			}
			
			
			/* Create the database */
			bufferedReader = new BufferedReader(new FileReader(new File("createdatabase.sql")));
			while ((command = bufferedReader.readLine()) != null) {
				statement.executeUpdate(command);
			}
			bufferedReader.close();
			connection.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/*
	 * DatabaseInsertAuto()
	 * 
	 * add an automobile into database
	 */
	public int[] DatabaseInsertAuto(Automobile auto, int autoID, int optSetIDStart, 
								int optionIDStart) {
	
		int[] idarray = new int[2]; 
		int optionSetID = optSetIDStart;
		int optionID = optionIDStart;
		LinkedHashMap<String, Float> options = null;

		String[] optionSetName = { "Color", "Transmission", "Brakes/Traction Control", 
									"Side Impact Airbags","Power Moonroof" };

		try {

			Connection connection = null;
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connection to Database Successful");
			}
			
			Properties insertList = new Properties();
			try {
				FileInputStream in = new FileInputStream("addDatabase.Properties");
				insertList.load(in);
				in.close();
			}  catch (IOException ex){
					System.out.println("Error: " + ex.toString());
			}

			/*
			 * Insert Automobile information
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(insertList.getProperty("AddAutomobile"));
			preparedStatement.setInt(1, autoID);
			preparedStatement.setString(2, auto.getName());
			preparedStatement.setFloat(3, auto.getBasePrice());
			preparedStatement.executeUpdate();

			
			/*
			 * Insert optionSet information into database.
			 */
			for (int i = 0; i < optionSetName.length; i++) {
				preparedStatement = connection.prepareStatement(insertList.getProperty("AddOptionSet"));
				preparedStatement.setInt(1, optionSetID);
				preparedStatement.setString(2, optionSetName[i]);
				preparedStatement.setInt(3, autoID);
				preparedStatement.executeUpdate();
				
				
				/*
				 * Insert Options information into database
				 */
				options = auto.getOptionSetMap(optionSetName[i]);
				for (String option : options.keySet()) {
					
					preparedStatement = connection.prepareStatement(insertList.getProperty("AddOption"));
					
					preparedStatement.setInt(1, optionID);
					preparedStatement.setString(2, option);
					preparedStatement.setFloat(3, options.get(option));
					preparedStatement.setInt(4, optionSetID);
					preparedStatement.setInt(5, autoID);
					preparedStatement.executeUpdate();
					
					optionID++;
				}

				optionSetID++;
			}
			
			idarray[0] = optionSetID;
			idarray[1] = optionID;

			System.out.println(auto.getName() + " added to Database Successfully!");
			
			preparedStatement.close();
			connection.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return idarray;
	}

	/*
	 * DatabaseDeleteAuto()
	 * 
	 * delete an automobile into database
	 * 
	 */
	public void DatabaseDeleteAuto(String AutoName) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connection to Database Successful");
			}
			
			
			Properties deleteList = new Properties();
			Properties selectList = new Properties();
			try {
				FileInputStream in1 = new FileInputStream("deleteDatabase.Properties");
				FileInputStream in2 = new FileInputStream("selectDatabase.Properties");
				deleteList.load(in1);
				selectList.load(in2);
				in1.close();
				in2.close();
			}  catch (IOException ex){
					System.out.println("Error: " + ex.toString());
			}

			
			/*
			 * Get the automobile ID of
			 * the automobile.
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(selectList.getProperty("SelectAutomobileByName"));
			preparedStatement.setString(1, AutoName);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int AutoID = Integer.parseInt(resultSet.getString("AutoID"));
			
			
			/*
			 * Delete all the options
			 * of the automobile
			 */
			preparedStatement=connection.prepareStatement(deleteList.getProperty("DeleteAutomobileOption"));
			preparedStatement.setInt(1, AutoID);
			preparedStatement.executeUpdate();
			
			/*
			 * Delete all the optionSets
			 * of the automobile
			 */
			preparedStatement=connection.prepareStatement(deleteList.getProperty("DeleteOptionSet"));
			preparedStatement.setInt(1, AutoID);
			preparedStatement.executeUpdate();

			/*
			 * Finally delete the automobile.
			 */
			preparedStatement=connection.prepareStatement(deleteList.getProperty("DeleteAutomobile"));
			preparedStatement.setInt(1, AutoID);
			preparedStatement.executeUpdate();
			
			System.out.println("Deletion of " + AutoName + " from database is Successful!");
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}

	/*
	 * updateAutoBasePrice()
	 * 
	 * update an automobile's base price in database
	 * 
	 */

	public void DatabaseUpdateAuto(String AutoName, float newBasePrice) {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connection to Database Successful");
			}
			
			
			Properties updatelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("UpdateDatabase.Properties");
				updatelist.load(in);
				in.close();
			}  catch (IOException ex){
					System.out.println("Error: " + ex.toString());
			}

			
			/*
			 * Update the base price
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(updatelist.getProperty("UpdateAutoBasePrice"));
			preparedStatement.setFloat(1,newBasePrice);
			preparedStatement.setString(2, AutoName);
			preparedStatement.executeUpdate();
			
			System.out.println("Base Price of " + AutoName + " Updated Successfully");

			
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Automobile" + AutoName + " not in the database");
		} 
	}

	/*
	 * updateAutoOptionPrice()
	 * 
	 * update an automobile's option price in database
	 * 
	 */

	public void DatabaseUpdateAutoOptionPrice(String AutoName, String OptionSetName, String OptionName, float newOptionPrice) {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connection to Database Successful");
			}

			Properties updateList = new Properties();
			Properties selectList = new Properties();
			try {
				FileInputStream in1 = new FileInputStream("UpdateDatabase.Properties");
				FileInputStream in2 = new FileInputStream("selectDatabase.Properties");
				updateList.load(in1);
				selectList.load(in2);
				in1.close();
				in2.close();
			}  catch (IOException ex){
					System.out.println("Error: " + ex.toString());
			}
			
			/* 
			 * Get the OptionSet
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(selectList.getProperty("SelectAutomobileByName"));
			preparedStatement.setString(1, AutoName);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int AutoId = Integer.parseInt(resultSet.getString("AutoId"));

			
			/*
			 * Get the Option
			 */
			preparedStatement = connection.prepareStatement(selectList.getProperty("GetOptionSetID"));
			preparedStatement.setString(1, OptionSetName);
			preparedStatement.setInt(2, AutoId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int OptionSetId = Integer.parseInt(resultSet.getString("OptionSetId"));

			
			
			/*
			 * Update price of option
			 * belonging to Optionset
			 */
			
			preparedStatement = connection.prepareStatement(updateList.getProperty("UpdateOptionPrice"));
			preparedStatement.setFloat(1, newOptionPrice);
			preparedStatement.setInt(2, OptionSetId);
			preparedStatement.setInt(3, AutoId);
			preparedStatement.setString(4, OptionName);
			preparedStatement.executeUpdate();

			System.out.println("Price of Option " +OptionName+ " belonging to OptionSet "
									+OptionSetName+ " of Automobile "+AutoName+ " updated Successfully");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}

}
