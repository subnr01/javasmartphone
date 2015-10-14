/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import exception.ExceptionErrorCode;
import model.OptionSet.Option;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public class Automobile implements Serializable{

	// instance variables
	private String name;
	private String make;
	private String model;
	private float basePrice;
	private OptionSet[] optionSetList;
	private Option[] choice;
	
	// static variables
	private static final long serialVersionUID = 1798260372953772151L;
	
	// constructor
	public Automobile(){
		
	}
	
	/**
	 * New Constructor for Project1 Unit2
	 * @param make of the car
	 * @param model of the car
	 * @param baseprice of the car
	 */
	public Automobile(String make, String model, float baseprice, int optionSetSize, int choices){
		this.name = make + model;
		this.model = model;
		this.make = make;
		this.basePrice = baseprice;
		this.optionSetList = new OptionSet[optionSetSize];
		this.choice = new Option[choices];
		
	}
	
	// getter/setter
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String getMake(){
		return make;
	}
	
	public void setMake(String make){
		this.make = make;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public float getBasePrice(){
		return basePrice;
	}
	
	public void setBasePrice(float bp){
		basePrice = bp;
	}
	
	
	// OptionSet part
	public int getOptionSetSize(){
		return optionSetList.length;
	}
	
	// get option set
	protected OptionSet getOptionSet(String name){
		for (int i = 0 ; i < optionSetList.length ; i ++) {
			if (optionSetList[i].getName().equals(name)) {
				return optionSetList[i];
			}
		}
		return null;
	}
	
	// set option set
	public void setOptionSet(String name, int optionSize) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] == null) {
				optionSetList[i] = new OptionSet(name, optionSize);
				return;
			}
		}
	}
	
	public void setOptionSet(int index, String setName, int optionSize) {
		optionSetList[index] = new OptionSet(setName, optionSize);
	}

	
	// delete option set
	public void deleteOptionSet(String name) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i].getName().equals(name)) {
				optionSetList[i] = null;
				return;
			}
		}
	}
	
	
	// update option set name	
	public void updateOptionSetName(String name, String newName) throws AutoException {
		boolean optionSetFound = false;
	
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i].getName().equals(name)) {
				optionSetList[i].setName(newName);
				optionSetFound = true;
				return;
			}
		}
		if (!optionSetFound) {
			throw new AutoException(ExceptionErrorCode.OptionSetNotFound);
		}
	}
	
	// Option Part
	protected Option getOption(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			return getOptionSet(setName).getOption(optionName);
		}
		return null;
	}
	
	public void setOption(int setIndex, int optionIndex, String optionName,
			float price) {
		if (getOptionSet(setIndex) != null) {
			optionSetList[setIndex].setOption(optionIndex, optionName, price);
		}
	}
	

	
	protected OptionSet getOptionSet(int index) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index];
			}
		}
		return null;

	}
	
	// get option price
	public float getOptionPrice(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			return getOption(setName, optionName).getPrice();
		}
		return 0;
	}

	// set option 
	public void setOption(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).setOption(optionName, price);
		}
	}

	// delete option
	public void deleteOption(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).deleteOption(optionName);
		}
	}

	// update option price
	public void updateOptionPrice(String setName, String optionName, float price) throws AutoException{
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(optionName, price);
		} else {
			throw new AutoException(ExceptionErrorCode.OptionSetNotFound);
		}	
	}
	
	// Choice part
	// get option choice
	public String getOptionChoice(String setName) {
		return getOptionSet(setName).getChoiceName();
	}
	
	// set option choice
	public void setOptionChoice(String setName, String optionName) {
		OptionSet opset = getOptionSet(setName);
		for ( int i = 0; i < choice.length; i++) {
			choice[i] = opset.getOption(optionName);
		}
		opset.setChoice(optionName);
	}
	
	// get option price
	public float getOptionChoicePrice(String setName) {
		return getOptionSet(setName).getChoicePrice();
	}
	
	// get total price
	public float getTotalPrice() {
		float sum = basePrice;
		for (Option op : choice) {
			sum += op.getPrice();
		}
		return sum;
	}
	
	// instance methods
	public void printBasicInfo(){
		System.out.println(getName());
		System.out.println("Base Price :$"
				+ String.format("%.2f", getBasePrice()));
		System.out.println();
	}
	
	public void printAllOptionSet() {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				System.out.println(optionSetList[i].getName() + ":");
				optionSetList[i].printAllOptions();
				System.out.println();
			}
			else{
				System.out.println("Option Set Not Exist or Deleted");
				System.out.println();
			}
		}
			

	}
	
	public void printChoice() {
		for (Option op : choice) {
			System.out.println("Option : " + op.getName() + "Price : "
					+ op.getPrice());
		}
	}
	
	public void printTotalPrice() {
		System.out.println("Total price : " + getTotalPrice());
	}
	
	public void print() {
		this.printBasicInfo();
		this.printAllOptionSet();
	}
	
	// static methods
}
