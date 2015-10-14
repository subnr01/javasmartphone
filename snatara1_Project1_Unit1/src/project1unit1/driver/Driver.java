/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit1.driver;

import project1unit1.model.*;
import project1unit1.util.*;



public class Driver {
	private static String FILENAME = "filename1.txt";
	public static void main(String[] args) {
		runSerializationTest();
		System.out.println("\n\n");
		runtestOptionSets();
		System.out.println("\n\n");
		runtestOptions();
		System.out.println("\n\n");
	}
	
	public static void runSerializationTest()
	{
		FileIO fio = new FileIO();
		System.out.println("=================================");
		System.out.println("Serialization test start");
		Automotive FordZTW = fio.buildAutomative(FILENAME);
		System.out.println("=================================");
		System.out.println("Car Details:-");
		FordZTW.print();
		System.out.println("=================================");
		System.out.println("Serialization in progress");
		/* serialize to auto.ser file*/
		fio.serializeOutput(FordZTW);
		System.out.println("Serialization Done");
		
		/* serialize from auto.ser file*/
		System.out.println("\nDeSerialization in progress");
		Automotive newFordZTW = fio.serializeInput("auto.ser");
		System.out.println("DeSerialization Done");
		System.out.println("=================================");
		System.out.println("Deserialized Car details:-");
		newFordZTW.print();
		System.out.println("Serialization test End");
		System.out.println("=================================\n\n");
		
	}
	
	/*
	 * This test basically runs 
	 * for code coverage
	 */
	public static void runtestOptions()
	{
		FileIO fio = new FileIO();
		System.out.println("=================================");
		System.out.println("Options test start");
		Automotive FordZTW = fio.buildAutomative(FILENAME);
		System.out.println("=================================");
		System.out.println("Car Details:");
		FordZTW.print();
		System.out.println("=================================");
		
		
		
		System.out.println("=================================");
		System.out.println("Test \"getOptionPrice()\"");
		System.out.println("Price of Option ABS is " + FordZTW.getOptionPrice("Brakes/Traction Control", "ABS"));
		System.out.println("Price of Option ABS is" + FordZTW.getOptionPrice("Brakes/Traction Control", 1));
		System.out.println("Price of Option ABS is " + FordZTW.getOptionPrice(2, "ABS"));
		System.out.println("Price of Option ABS is " + FordZTW.getOptionPrice(2, 1));
		System.out.println("=================================");
		
		
		System.out.println("=================================");
		System.out.println("Test setOption()");
		FordZTW.deleteOption("Transmission", 0);
		FordZTW.setOption("Transmission", "Automatic", 4747);
		FordZTW.setOption(1, 1, "Test setOption()", 333);
		FordZTW.print();
		System.out.println("=================================");
		
		System.out.println("=================================");
		System.out.println("Test deleteOption()");
		FordZTW.deleteOption("Transmission","99999");
		FordZTW.deleteOption(1,"Standard");
		FordZTW.deleteOption("Brakes/Traction Control",0);
		FordZTW.deleteOption(2,1);
		FordZTW.print();	
		System.out.println("=================================");
		
		//Test option update methods
		System.out.println("=================================");
		FordZTW.updateOptionName(0, 0, "color1");
		FordZTW.updateOptionName("Color", 1, "color2");
		FordZTW.updateOptionName(0, "Infra-Red Clearcoat", "color3");
		FordZTW.updateOptionName("Color", "Grabber Green Clearcoat Metallic", "color4");
		FordZTW.updateOptionPrice(0, "French Blue Clearcoat Metallic", 4);
		FordZTW.updateOptionPrice("Color", 5, 5);
		FordZTW.updateOptionPrice(0, 6, 6);
		FordZTW.updateOptionPrice(0, "Twilight Blue Clearcoat Metallic",7);
		FordZTW.printAllOptionSetInfo();
		System.out.println("Options test End");
		System.out.println("=================================\n\n");	
		
	}
	
	public static void runtestOptionSets()
	{
		FileIO fio = new FileIO();
		Automotive FordZTW = fio.buildAutomative(FILENAME);
		System.out.println("=================================");
		System.out.println("OptionSet test start");
		FordZTW.print();
		System.out.println("=================================");
		
		
		
		//Test option set get methods
		System.out.println("=================================");
		System.out.println("Test getOptionSetListSize()");
		System.out.println("Option set list size is " + FordZTW.getOptionSetListSize());
		System.out.println("=================================");
		
		
		//Test option set set methods
		System.out.println("=================================");
		System.out.println("Test setOptionSet()");
		FordZTW.setOptionSet("Color",0);
		/* Change brakes to steering wheel*/
		FordZTW.setOptionSet(1, "Car sterring wheel", 0);
		FordZTW.printAllOptionSetInfo();
		System.out.println("=================================");
		
		//Test option set delete method
		System.out.println("=================================");
		System.out.println("Test deleteOptionSet()");
		FordZTW.deleteOptionSet(2);
		FordZTW.deleteOptionSet("Side Impact Airbags");
		FordZTW.printAllOptionSetInfo();
		System.out.println("=================================");
		
		
		//Test option set update method
		System.out.println("=================================");
		FordZTW.updateOptionSet("Color",0);
		FordZTW.updateOptionSet(2,"Brakes/Traction Control",0);
		FordZTW.updateOptionSetName(1, "Transmission");
		FordZTW.updateOptionSetName("Power Moonroof", "POWER MOONROOF2");
		FordZTW.printAllOptionSetInfo();
		System.out.println("=================================");
	}
	
	

}
