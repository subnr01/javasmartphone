/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package  project1unit6.driver;

import project1unit6.adapter.*;
import project1unit6.util.DatabaseIO;


public class Driver {
	public static void main(String[] args) {
		
		/* Create the database */
		new DatabaseIO().createDataBase();
		
		
		BuildAuto automobiles = new BuildAuto();
		
		/* Add the automobiles to the linkedhashmap and the database */
		System.out.println("---------------------------------------------");
		System.out.println("Adding Automobiles To Database");
		System.out.println();
		automobiles.buildDBAuto("Audi.txt");
		automobiles.buildDBAuto("Focus.txt");
		automobiles.buildDBAuto("ModelS.Properties");
		automobiles.buildDBAuto("Musang.Properties");
		System.out.println("---------------------------------------------");
		new PrintDatabaseDetails().printTable();
		System.out.println();
		
		
		/*
		 * Delete the automobiles from database 
		 * and the linkedhashmap
		 */
		System.out.println("---------------------------------------------");
		System.out.println("Processing Delete Car in Database");
		System.out.println();
		automobiles.deleteDBAuto("Ford Mustang");
		automobiles.deleteDBAuto("Focus Wagon ZTW");
		automobiles.deleteDBAuto("Tesla ModelS");
		
		/* Check whether the automobile is deleted */
		System.out.println();
		System.out.println("Testing Delete of a non-existing automobile");
		automobiles.deleteDBAuto("Ford Mustang");
		
		System.out.println("---------------------------------------------");
		System.out.println("Printing automobile information again");
		new PrintDatabaseDetails().printTable();
		System.out.println();
		
		/*
		 * Update the automobiles in the database
		 * and the hashmap
		 */
		System.out.println("---------------------------------------------");
		System.out.println("Update Automobile in Database");
		System.out.println();
		
		/*
		 * Update price of
		 * the automobile
		 */
		automobiles.updateDBAutoBasePrice("Audi A3", 99999);
		
		/*
		 * Update price
		 * of option color
		 */
		automobiles.updateDBAutoOptionBasePrice("Audi A3", "Color", "Liquid Grey", 9999);
		
		
		/*
		 * Attempting to
		 * update the price
		 * of a deleted automobile
		 */
		System.out.println();
		System.out.println("Attempting to update price of a deleted automobile");
		automobiles.updateDBAutoBasePrice("Ford Mustang", 99999);
		
		/*
		 * Attempting to update
		 * the price of an option
		 * of a deleted automobile
		 */
		System.out.println();
		System.out.println("Attempting to update price of option of a deleted automobile");
		automobiles.updateDBAutoOptionBasePrice("Ford Mustang", "Color", "Liquid Grey", 9999);
		
		
		System.out.println("---------------------------------------------");
		new PrintDatabaseDetails().printTable();

		
	}
}
