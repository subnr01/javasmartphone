/**
 * 
 */
package exception;

import util.FileIO;

import java.io.IOException;

import model.Automobile;
import model.CarShowRoom;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public class FixErrorCond {
	
	private static final String DEFAULT_CAR_MODEL_FILE = "default_car_model.txt";

	private static final String DEFAULT_SAVED_MODEL_FILE = "default_saved_model.ser";

	//fix by loading from default file
	public Automobile fixFileNotFoundExceptionHandler(){
		Automobile auto = null;
		try {
			System.out.println("Invalid filename");
			System.out.println("Fix it by loading default file name");
			auto = new FileIO().buildAutoObject(DEFAULT_CAR_MODEL_FILE);			
		} catch (AutoException e) {
			System.out.println("Default Model File Not Find Exception: "
					+ e.toString());
		}
		return auto;
	}

	// fix missing base price problem by returning 0
	public String fixNoBasePriceExceptionHandler() {
		return "0";
	}

	// fix missing option price problem by returning  0
	public String[] fixNoOptionPriceExceptionHandler(String[] input) {
		String[] output = new String[2];
		output[0] = input[0];
		output[1] = "0";
		return output;
	}

	
	
	 
	//fix missing mode car not found
	public Automobile fixModelCarNotFoundExceptionHandler(){
		Automobile auto = null;
		try {
			auto = new FileIO().deserializeAuto(DEFAULT_SAVED_MODEL_FILE);
		} catch (Exception e) {
			System.out.println("Default Model File Not Find Exception: "
					+ e.toString());
		}
		return auto;
	}				
}
