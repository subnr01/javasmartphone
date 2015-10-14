/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package adapter;

import exception.*;
import model.*;
import util.FileIO;



public abstract class ProxyAutomobile {
	private Automobile a1;
	
	public void buildAuto(String filename) {
		try {
			a1 = new FileIO().buildAutoObject(filename);
		} catch (AutoException autoException) {
			fix(autoException.getExceptionIndex());
		}
	}

	/**
	 * Print the information of the car 
	 * @param name of the car
	 */
	public void printAuto(String name) {
		
		try {
			if (a1 == null || !a1.getName().equals(name)) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			a1.print();
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/**
	 * Delete the corresponding car 
	 * @param name of the car
	 */
	public void deleteAuto(String name) {

		try {
			if (a1 == null || !a1.getName().equals(name)) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			a1 = null;
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
			if (a1 == null || !a1.getName().equals(name)) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			a1.updateOptionSetName(optionSetName, newName);
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
			if (a1 == null || !a1.getName().equals(name)) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			a1.updateOptionPrice(optionSetName, option, newPrice);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}
	}


	/**
	 * Serialize the car model
	 * @param name
	 */
	public void saveCarModel(String name) {
		try {
			if (a1 == null || !a1.getName().equals(name)) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFound);
			}
			new FileIO().serializeAuto(a1);
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
			a1  = fixError.fixFileNotFoundExceptionHandler();
			break;
		case 7:
			a1 = fixError.fixModelCarNotFoundExceptionHandler();
			break;
		default:
			break;
		}
	}
}
