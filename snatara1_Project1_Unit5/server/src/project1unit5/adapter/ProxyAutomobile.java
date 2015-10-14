/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit5.adapter;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import project1unit5.util.FileIO;
import project1unit5.exception.*;
import project1unit5. model.*;
import project1unit5.scale.*;


/*
 * Abstract class having
 * implementations
 */
public abstract class ProxyAutomobile {
	/* 
	 * A fleet of Automobiles
	 */
	 private static LinkedHashMap<String, Automobile> 
	 		automobileList = new LinkedHashMap<String, Automobile>();
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
			Automobile auto = new FileIO().buildAutoObject(filename);
			automobileList.put(auto.getName(), auto);
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

		Automobile auto = automobileList.get(modelName);
		try {
			if (auto == null) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
			}else{
				auto.print();
			}
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
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
		Automobile auto = automobileList.get(modelName);
		try {
			if (auto == null) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
			}else{
				automobileList.remove(modelName);
			}
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
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
		Automobile auto = automobileList.get(ModelName);
		try {
			
			if (auto != null) {
				auto.updateOptionSetName(OptionSetName, newName);
				automobileList.put(ModelName, auto);
			} else {
				throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
			}
			
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
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
		Automobile auto = automobileList.get(args[0]); // args 0 is model name
		try {
			if (auto == null) {
				throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
			}else{
				EditOption edit = new EditOption(++tid, auto, editOptionEnum,
					args);
				edit.start();
			}
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
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
		Automobile auto = automobileList.get(ModelName);
		try {
			if (auto != null) {
				auto.updateOptionPrice(Optionname, Option, newprice);
				automobileList.put(ModelName, auto);
			} else {
				throw new AutoException(ExceptionErrorCode.CarModelNotFoundException);
			}
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
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
			Automobile auto = automobileList.get(modelName);
		try {
			if (auto == null) {
				throw new AutoException(
						ExceptionErrorCode.CarModelNotFoundException);
			}
			new FileIO().serializeAuto(auto);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
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
			automobileList.put(auto.getName(), auto);
		} catch (AutoException ae) {
			fix(ae.getErrorNumber());
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
	
	
	public void buildAutoFromProperty(Properties carProperties){
		Automobile auto = new FileIO().readInAutomobileProperty(carProperties);
		automobileList.put(auto.getName(), auto);
	}
	
	
	/*
	 * buildAutoWithTxt(String fileName)
	 * 
	 * build a automobile with input .txt file
	 * 
	 */
	public void buildAutoFromTxt(String fileName){
		try {
			Automobile auto = new FileIO().buildAutoObject(fileName);
			automobileList.put(auto.getName(), auto);
		} catch (AutoException e) {
			e.printStackTrace();
		}
	}
	/*
	 * getAllModelList()
	 * 
	 * return all the available automobile in LinkedHashMap
	 * 
	 */
	
	public ArrayList<String> getAllModelList(){
		ArrayList<String> autoNameList = new ArrayList<String>();
		for (String key : automobileList.keySet()) {
			autoNameList.add(key);
		}
		return autoNameList;
	}
	
	
	/*
	 * sendSelectedModel()
	 * 
	 * send the selected automobile model 
	 * from server to client
	 * 
	 */
	public void sendModelClient(ObjectOutputStream objectOutputStream, String modelName) throws IOException{
		Automobile selectedAuto = automobileList.get(modelName);
		objectOutputStream.writeObject(selectedAuto);
	}

}
