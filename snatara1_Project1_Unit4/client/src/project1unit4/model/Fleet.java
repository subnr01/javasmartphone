/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit4.model;

import java.util.LinkedHashMap;

import project1unit4.exception.*;
import project1unit4.util.FileIO;

public class Fleet {

	/* List of automobiles */
	private static LinkedHashMap<String, Automobile> autoList;

	
	/*
	 * Fleet()
	 * 
	 */
	public Fleet() {
		autoList = new LinkedHashMap<String, Automobile>();
	}
	
	
	/*
	 * setAutomobile()
	 * 
	 * add a automobile 
	 * to the list given 
	 * the automobile object.
	 */
	public void setAutomobile(Automobile auto) {
		autoList.put(auto.getName(), auto);
	}

	/*
	 * setAutomobile()
	 * 
	 * add a automobile 
	 * to the list given 
	 * the file containing
	 * the automobile details.
	 */
	public void setAutomobile(String fileName) throws AutoException {
		Automobile auto = new FileIO().buildAutoObject(fileName);
				autoList.put(auto.getName(), auto);
	}
	
	/*
	 * getAutomobile()
	 * 
	 * get the automobile 
	 * in the list
	 * */
	public Automobile getAutomobile(String modelName) throws AutoException {
		Automobile auto = autoList.get(modelName);
		if (auto == null) {
			throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
		}else{
			return autoList.get(modelName);
		}
	}
	
	/*
	 * updateOptionSetName()
	 * 
	 * update the OptionSet name 
	 * given the Model name
	 * 
	 */
	
 	public void updateOptionSetName(String ModelName, String OptionSetName,
			String newName) throws AutoException {
		Automobile auto = autoList.get(ModelName);
		if (auto != null) {
			auto.updateOptionSetName(OptionSetName, newName);
			autoList.put(ModelName, auto);
		} else {
			throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
		}
	}
	
 	/*
	 * updateOptionPrice()
	 * 
	 * update the OptionSet price  
	 * given the Model name and price
	 * 
	 */
	public void updateOptionPrice(String ModelName, String Optionname,
			String Option, float newprice) throws AutoException {
		Automobile auto = autoList.get(ModelName);
		if (auto != null) {
			auto.updateOptionPrice(Optionname, Option, newprice);
			autoList.put(ModelName, auto);
		} else {
			throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
		}
	}
	
	/*
	 * deleteAutomobile()
	 * 
	 * delete an automobile
	 *  from the list 
	 */
	public void deleteAutomoble(String modelName) throws AutoException{
		Automobile auto = autoList.get(modelName);
		if (auto == null) {
			throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
		}else{
			autoList.remove(modelName);
		}
	}
	
	/*
	 * deleteAutomobile()
	 * 
	 * print the automobile 
	 * information from the
	 * list
	 *  
	 */
    public void printAutomobile(String modelName) throws AutoException{
    		Automobile auto = autoList.get(modelName);
    		if (auto == null) {
    			throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
		}else{
			auto.print();
		}
    }

}
