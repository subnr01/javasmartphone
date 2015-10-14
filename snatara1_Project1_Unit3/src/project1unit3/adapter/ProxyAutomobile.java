/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit3.adapter;

import project1unit3.util.FileIO;
import project1unit3.exception.*;
import project1unit3. model.*;
import project1unit3.scale.*;


/*
 * Abstract class having
 * implementations
 */
public abstract class ProxyAutomobile {
	/* 
	 * A fleet of Automobiles
	 */
	private static Fleet automobileList = new Fleet();
	/* 
	 * Introducing tid to 
	 * identify the thread
	 * 
	 */
	private static int tid = 0; 
	
	/*
	 * buildAuto()
	 * 
	 * build an automobile and
	 * set it into Fleet
	 * 
	 */

	public void buildAuto(String filename) {
		try {
			automobileList.setAutomobile(filename);
		} catch (AutoException ex) {
			fix(ex.getErrorNumber());
		}

	}

	/*
	 * printAuto()
	 * 
	 * print the 
	 * automobile info
	 * 
	 */

	public void printAuto(String modelName) {
		try {
			automobileList.printAutomobile(modelName);
		} catch (AutoException ex) {
			System.out.println("ERROR: " + ex.toString());
		}

	}

	/*
	 * deleteAuto()
	 * 
	 * delete the
	 * Automobile object
	 * 
	 */
	
	public void deleteAuto(String modelName) {
		try {
			automobileList.deleteAutomoble(modelName);
		} catch (AutoException ex) {
			System.out.println("ERROR: " + ex.toString());
		}
	}

	/*
	 * updateOptionSetName()
	 * 
	 * update optionset name
	 * for the Automobile object
	 * 
	 */

	public void updateOptionSetName(String ModelName, String OptionSetName,
			String newName) {
		try {
			automobileList.updateOptionSetName(ModelName, OptionSetName, newName);
		} catch (AutoException ex) {
			System.out.println("ERROR: " + ex.toString());
		}
	}

	/*
	 * edit()
	 * 
	 * Spawning threads to
	 * synchronize updating
	 * Option or OptionSets
	 */
	public void edit(EditOptionEnum editOptionEnum, String[] args) {
		Automobile auto = null;
		try {
			auto = automobileList.getAutomobile(args[0]);
			EditOption edit = new EditOption(++tid, auto, editOptionEnum,
					args);
			edit.start();
		} catch (AutoException ex) {
			System.out.println("ERROR: " + ex.toString());
		}

	}

	/*
	 * updateOptionPrice()
	 * 
	 * update price of the
	 * option identified by 
	 * name
	 * 	  
	 */

	public void updateOptionPrice(String ModelName, String Optionname,
			String Option, float newprice) {
		try {
			automobileList.updateOptionPrice(ModelName, Optionname, Option, newprice);
		} catch (AutoException ex) {
			System.out.println("ERROR: " + ex.toString());
		}
	}

	/*
	 * saveCarModel()
	 * 
	 * save a car model, 
	 * to disk
	 * 
	 */

	public void saveCarModel(String modelName) {
		Automobile auto = null;
		try {
			auto = automobileList.getAutomobile(modelName);
		} catch (AutoException e) {
			e.printStackTrace();
		}
		try {
			if (auto == null) {
				throw new AutoException(
						ExceptionErrorCode.SavedCarFileNotFoundException);
			}
			new FileIO().serializeAuto(auto);
		} catch (AutoException ex) {
			System.out.println("ERROR: " + ex.toString());
		}
	}

	/*
	 * loadCarModel()
	 * 
	 * load a car model
	 * for disk.
	 * 
	 * if no match file, 
	 * load from default file
	 * 
	 */

	public void loadCarModel(String modelName) {
		StringBuffer sb = new StringBuffer();
		sb.append(modelName);
		sb.append(".ser");

		try {
			Automobile auto = new FileIO().deserializeAuto(sb.toString());
			automobileList.setAutomobile(auto);
		} catch (AutoException ex) {
			fix(ex.getErrorNumber());
		}
	}

	/*
	 * fix()
	 * 
	 * fix problem by
	 * input error number
	 */

	public void fix(int errno) {

		FixErrorCond FixErrorCond = new FixErrorCond();

		switch (errno) {
		case 1:
			FixErrorCond.FixFileNotFound(automobileList);
			break;
		case 5:
			FixErrorCond.SavedCarFileNotFoundExceptionHandler(automobileList);
			break;
		default:
			break;
		}
	}

}
