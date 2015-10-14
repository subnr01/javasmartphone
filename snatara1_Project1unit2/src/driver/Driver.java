/**
 * 
 */
package driver;

import adapter.BuildAuto;
import exception.AutoException;
import model.Automobile;
import util.FileIO;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public class Driver {

	/**
	 * @param args
	 * @throws AutoException 
	 */
	public static void main(String[] args) throws AutoException {
		
		System.out.println("Testing start");
		System.out.println("Normal test");
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		autoBuilder.printAuto("Focus Wagon ZTW");
		System.out.println();
		
		System.out.println("Testing update");
		autoBuilder.updateOptionSetName("Focus Wagon ZTW", "Transmission",
				"#Updated Transmission#");
		autoBuilder.updateOptionPrice("Focus Wagon ZTW", "#Updated Transmission#",
				"Automatic", (float)888);
		autoBuilder.printAuto("Focus Wagon ZTW");
		System.out.println();
		
		System.out.println("Testing choice");
		Automobile auto = new FileIO().buildAutoObject("Focus.txt");
		auto.setOptionChoice("Color", "Cloud 9 White Clearcoat");
		auto.setOptionChoice("Transmission", "Standard");
		auto.setOptionChoice("Brakes/Traction Control", "ABS with Advance Trac");
		auto.setOptionChoice("Side Impact Airbags", "Selected");
		auto.setOptionChoice("Power Moonroof", "Selected");
		System.out.println("Base price is : " + auto.getBasePrice());
		auto.printChoice();
		auto.printTotalPrice();
		System.out.println();
		System.out.println("Testing Delete");
		autoBuilder.deleteAuto("Focus Wagon ZTW");
		autoBuilder.printAuto("Focus Wagon ZTW");
		
		
		System.out.println("Testing Exceptions and auto heal");
		autoBuilder.buildAuto("temp.txt");
		autoBuilder.printAuto("Focus Wagon ZTW");
		System.out.println("Test Done");
		
		
	}
}


   