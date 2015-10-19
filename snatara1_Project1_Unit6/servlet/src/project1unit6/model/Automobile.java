/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import project1unit6.exception.*;
import project1unit6.model.OptionSet.Option;


public class Automobile implements Serializable {

	private static final long serialVersionUID = -7604648993594724965L;
	private String name;
	private String make;
	private String model;
	private float basePrice;
	private ArrayList<OptionSet> optionSets;
	private ArrayList<Option> choice;

	/*
	 * Default constructor
	 */
	public Automobile() {}
	
	
	/*
	 * automobile()
	 * 
	 * constructor with
	 * arguments
	 */

	public Automobile(String make, String model, float basePrice) {
		this.make = make;
		this.model = model;
		this.name = make + " " + model;
		this.basePrice = basePrice;
		this.optionSets = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}

	/*
	 * getName()
	 * 
	 * Get the name of
	 * the automobile
	 */
	public String getName() {
		name = make + " " + model;
		return name;
	}

	/*
	 * getMake()
	 * 
	 * get the make of 
	 * the automobile
	 */
	public String getMake() {
		return make;
	}

	/*
	 * setMake()
	 * 
	 * set the make of this automobile
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/*
	 * getModel()
	 * 
	 * get the model of this automobile
	 */
	public String getModel() {
		return model;
	}

	/*
	 * setModel()
	 * 
	 * set the model of this automobile
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/*
	 * getBasePrice()
	 * 
	 * get the base price of this automobile
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/*
	 * setBasePrice()
	 * 
	 * set the base price 
	 * of the automobile
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	/*
	 * printBaseInfo()
	 * 
	 * print the base
	 * information of the automobile
	 * 
	 */
	public void printBaseInfo() {
		System.out.println(this.getName());
		System.out.println("Base Price :"
				+ String.format("%.2f", this.getBasePrice()));
		System.out.println();
	}


	/*
	 * getOptionSetListSize()
	 * 
	 * return the number of
	 * the OptionSets
	 * 
	 */
	public int getOptionSetListSize() {
		return optionSets.size();
	}

	/*
	 * getOptionSet()
	 * 
	 * return the OptionSet
	 * 
	 */
	protected OptionSet getOptionSet(String setName) {
		for (OptionSet opset : optionSets) {
			if (opset.getOptionSetName().equals(setName)) {
				return opset;
			}
		}
		return null;
	}
	
	/*
	 * getOptionSetName()
	 * 
	 * get the
	 * OptonSet name
	 * 
	 */
	
	public String getOptionSetName(String optionSetName) throws AutoException {
		if (getOptionSet(optionSetName) == null) {
			throw new AutoException(ExceptionErrorCode.OptionSetNotFoundException);
		}
		else{
			return getOptionSet(optionSetName).getOptionSetName();
		}
		
	}
	
	

	/*
	 * setOptionSet()
	 * 
	 * set the
	 * OptionSet by name
	 */
	public void setOptionSet(String setName) {
		optionSets.add(new OptionSet(setName));
	}

	/*
	 * deleteOptionSet()
	 * 
	 * Delete the OptionSet 
	 * by name
	 * 
	 */

	public void deleteOptionSet(String setName) {

		for (OptionSet opset : optionSets) {
			if (opset.getOptionSetName().equals(setName)) {
				optionSets.remove(opset);
				return;
			}
		}
	}

	/*
	 * updateOptionSet()
	 * 
	 * Update the name of the
	 * OptionSet
	 */

	public void updateOptionSetName(String setName, String newSetName)
			throws AutoException {
		boolean updateFlag = false;
		for (OptionSet opset : optionSets) {
			if (opset.getOptionSetName().equals(setName)) {
				opset.setOptionSetName(newSetName);
				return;

			}
		}
		if (!updateFlag) {
			throw new AutoException(ExceptionErrorCode.OptionSetNotFoundException);
		}

	}
	
	

	/*
	 * printAllOptionSet()
	 * 
	 * print the
	 * OptionSet Details
	 */
	
	public void printAllOptionSet() {
		for (OptionSet opset : optionSets) {
			System.out.println(opset.getOptionSetName() + ":");
			opset.printAllOptions();
			System.out.println();
		}
	}
	

	/*
	 * getOption
	 * 
	 * get the Option given the
	 * OptionSet name and Option name
	 */

	protected Option getOption(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			return getOptionSet(setName).getOption(optionName);
		}
		return null;
	}

	/*
	 * getOptionPrice
	 * 
	 * get the price of the Option given the
	 * OptionSet name and Option name
	 */
	public float getOptionPrice(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			return getOption(setName, optionName).getPrice();
		}
		return 0;
	}

	
	/*
	 * getOptionPrice
	 * 
	 * set the price of the Option given the
	 * OptionSet name and Option name and price.
	 */
	public void setOption(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).setOption(optionName, price);
		}

	}

	/*
	 * deleteOption()
	 * 
	 * delete Option given the  
	 * OptionSet name and option name
	 */

	public void deleteOption(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).deleteOption(optionName);
		}
	}

	/*
	 * updateOptionPrice()
	 * 
	 * Update price of the Option given the  
	 * OptionSet name and option name and price
	 */

	public void updateOptionPrice(String setName, String optionName, float price)
			throws AutoException {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(optionName, price);
		} else {
			throw new AutoException(ExceptionErrorCode.OptionSetNotFoundException);
		}
	}



	/*
	 * print()
	 * 
	 * Print all the details
	 */
	public void print() {
		this.printBaseInfo();
		this.printAllOptionSet();
	}

	
	/*
	 * setOptionChoice()
	 * 
	 * set option choice 
	 * and add it to choice list
	 */
	public void setOptionChoice(String setName, String optionName) {
		OptionSet opset = getOptionSet(setName);
		choice.add(opset.getOption(optionName));
		opset.setChoice(optionName);
	}

	/*
	 * getOptionChoice()
	 * 
	 * get the option choice
	 */
	public String getOptionChoice(String setName) {
		return getOptionSet(setName).getChoiceName();
	}

	/*
	 * getOptionChoicePrice()
	 * 
	 * get the option 
	 * choice price
	 */
	public float getOptionChoicePrice(String setName) {
		return getOptionSet(setName).getChoicePrice();
	}

	/*
	 * getTotalPrice()
	 * 
	 * get total price 
	 * for all choice selected
	 */
	public float getTotalPrice() {
		float sum = basePrice;
		for (Option op : choice) {
			sum += op.getPrice();
		}
		return sum;
	}
	
	/*
	 * printChoice()
	 * 
	 * print all the choices
	 * selected
	 */
	public void printChoice() {
		for (OptionSet opSet : optionSets) {
			System.out.println(opSet.getOptionSetName() + ":"  + opSet.getChoiceName());
		}
		
		printTotalPrice();
	}
	
	/*
	 * printTotalPrice()
	 * 
	 * print total price
	 * for all the choices 
	 * selected
	 */
	public void printTotalPrice() {
		System.out.println("Total price : " + getTotalPrice());
	}
	
	// used for client configure car
		public String printOptionSetName(int i){
			return optionSets.get(i).getOptionSetName();
		}
		
		public void printOption(String setName){
			getOptionSet(setName).printAllOptions();
		}
		
		public int getOptionListSize(String optionSetName){
			int length = getOptionSet(optionSetName).getOptionListSize();
			return length;
		}
		
		// used for car configuration
		public void setOptionChoice(String setName, int index){
			OptionSet opset = getOptionSet(setName);
			choice.add(opset.getOption(index));
			opset.setChoice(index);
		}

		public LinkedHashMap<String, Float> getOptionSetMap(String optionSetName){
			LinkedHashMap<String, Float> optionMap = getOptionSet(optionSetName).getAllOptionLHM();
			return optionMap;
		}
}
