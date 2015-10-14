/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package adapter;

import exception.*;

import exception.FixErrorCond;
import model.*;
import util.FileIO;



public abstract class ProxyAutomobile {
	private static CarShowRoom automobileList = new CarShowRoom();
	
	public void buildAuto(String filename) {
		try {
			automobileList.setAutomobile(filename);
		} catch (AutoException autoException) {
			fix(autoException.getExceptionIndex());
		}
	}

	/**
	 * Print the information of the car 
	 * @param name of the car
	 */
	public void printAuto(String name) {
		Automobile auto = automobileList.getAutomobile(name);
		try {
			if (auto == null) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			auto.print();
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/**
	 * Delete the corresponding car 
	 * @param name of the car
	 */
	public void deleteAuto(String name) {

		Automobile auto = automobileList.getAutomobile(name);
		try {
			if (auto == null) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			automobileList.deleteAutomobile(name);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/**
	 * Update the OptionSet name of the car
	 * @param name of the car
	 * @param optionSetName of the Option set
	 * @param newName of the Option set
	 */
	public void updateOptionSetName(String name, String optionSetName,
			String newName) {
		try {
			automobileList.updateOptionSetName(name, optionSetName, newName);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/**
	 * Update Option price
	 * @param name of car
	 * @param optionSetName of the option set
	 * @param option name
	 * @param newPrice of the option
	 */
	public void updateOptionPrice(String name, String optionSetName,
			String option, float newPrice) {
		try {
			automobileList.updateOptionPrice(name, optionSetName, option, newPrice);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}
	}


	/**
	 * Serialize the car model
	 * @param name
	 */
	public void saveCarModel(String name) {
		Automobile auto = automobileList.getAutomobile(name);
		try {
			if (auto == null) {
				throw new AutoException(
						ExceptionErrorCode.SerialCarFileNotFound);
			}
			new FileIO().serializeAuto(auto);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}
	}

	
	/**
	 * fix the exceptions
	 * @param errno 
	 * index of exceptions
	 */
	public void fix(int errno) {
		 FixErrorCond fixError= new FixErrorCond();
		
	
		switch (errno) {
		case 1:
			fixError.fixFileNotFoundExceptionHandler(automobileList);
			break;
		case 7:
			fixError.fixModelCarNotFoundExceptionHandler(automobileList);
			break;
		default:
			break;
		}
	}
}
