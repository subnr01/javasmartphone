/**
 * 
 */
package model;

import java.util.LinkedHashMap;

import exception.AutoException;
import exception.ExceptionErrorCode;
import util.FileIO;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public class CarShowRoom {
	private static LinkedHashMap<String, Automobile> automobileList;
	
	public CarShowRoom() {
		automobileList = new LinkedHashMap<String, Automobile>();
	}
	
	// setter/getter
	public void setAutomobile(Automobile automobile){
		automobileList.put(automobile.getName(), automobile);
	}
	
	public void setAutomobile(String filename) throws AutoException{
		Automobile automobile = new FileIO().buildAutoObject(filename);
		automobileList.put(automobile.getName(), automobile);
	}
	
	public Automobile getAutomobile(String name){
		return automobileList.get(name);
	}
	
	public void updateOptionSetName(String name, String optionSetName,
			String newName) throws AutoException{
		Automobile automobile = automobileList.get(name);
		if (automobile != null) {
			automobile.updateOptionSetName(optionSetName, newName);
			automobileList.put(name, automobile);
		} else {
			throw new AutoException(ExceptionErrorCode.CarModelNotFound);
		}
	}
	
	public void updateOptionPrice(String name, String optionName,
			String option, float newPrice) throws AutoException {
		Automobile auto = automobileList.get(name);
		if (auto != null) {
			auto.updateOptionPrice(optionName, option, newPrice);
			automobileList.put(name, auto);
		} else {
			throw new AutoException(ExceptionErrorCode.CarModelNotFound);
		}
	}
	
	public void deleteAutomobile(String name){
		automobileList.remove(name);
	}
}
