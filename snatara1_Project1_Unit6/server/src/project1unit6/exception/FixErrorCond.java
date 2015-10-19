/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit6.exception;

import java.util.LinkedHashMap;

import project1unit6.util.FileIO;
import project1unit6.model.Automobile;



public class FixErrorCond {
	/* default car model*/
	private static final String DEFAULT_CAR_MODEL_FILE = "default_car_model.txt";
	/* load default saved car model */
	private static final String DEFAULT_SAVED_CAR_MODEL = "Default_Audi.ser";

	/*
	 * Fix by loading data from default car model 
	 */
	public void FixFileNotFound(LinkedHashMap<String, Automobile> autoList) {
		Automobile auto = null;
		try {
			auto = new FileIO().buildAutoObject(DEFAULT_CAR_MODEL_FILE);
		} catch (AutoException e) {
			System.out.println("Default Model File Not Find Exception: "
					+ e.toString());
		}
		autoList.put(auto.getName(), auto);
	}

	/*
	 * Fix by setting base price
	 * to 0.
	 */
	public String fixNoBasePriceExceptionHandler() {
		return "0";
	}

	/*
	 * Fix by setting Option price
	 * to 0.
	 */
	public String[] fixNoOptionPriceExceptionHandler(String[] input) {
		String[] output = new String[2];
		output[0] = input[0];
		output[1] = "0";
		return output;
	}

	/*
	 * Fix by loading from the 
	 * saved data file
	 */
	public void SavedCarFileNotFoundExceptionHandler(LinkedHashMap<String, Automobile> autoList) {	
		Automobile auto = null;
		try {
			auto = new FileIO().deserializeAuto(DEFAULT_SAVED_CAR_MODEL);
		} catch (AutoException e) {
			System.out.println("Data file not found: "
					+ e.toString());
		}
		autoList.put(auto.getName(), auto);
	}

}
