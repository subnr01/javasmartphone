/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit3.driver;

import java.io.IOException;
import project1unit3.adapter.BuildAuto;


public class Driver {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		
		runNormalTest();
	}

	public static void runNormalTest() {
		System.out.println("Normal Test Start");
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		autoBuilder.printAuto("Focus Wagon ZTW");
		
		System.out.println("\n Testing update\n");
		System.out.println("---------------");

		autoBuilder.updateOptionSetName("Focus Wagon ZTW", "Color",
				"!!!Changed Color!!!");
		autoBuilder.updateOptionPrice("Focus Wagon ZTW", "Transmission",
				"Automatic", (float) 9999.99);

		autoBuilder.printAuto("Focus Wagon ZTW");

		
		System.out.println("\n Testing delete\n");
		System.out.println("---------------");
		autoBuilder.deleteAuto("Focus Wagon ZTW");
		System.out.println ("Automobile deleted");
		System.out.println ("Attempting to print details of deleted Automobile");
		autoBuilder.printAuto("Focus Wagon ZTW");
		System.out.println("Normal Test End\n\n\n");
	}
	
	

}




