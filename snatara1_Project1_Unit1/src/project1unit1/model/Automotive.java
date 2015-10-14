/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit1.model;

import java.io.Serializable;

import project1unit1.model.OptionSet.Option;


public class Automotive implements Serializable {

	private static final long serialVersionUID = 6139996273425953342L;
	/* Name of the car*/
	private String name; 
	/* Base price of the car */
	private float basePrice;
	/* List of options */
	private OptionSet[] optionSetList;


	/*
	 * Automotive() 
	 * Default constructor 
	 */
	public Automotive() {}

	/*
	 * Automotive()
	 * Constructor with Arguments
	 * 
	 */
	public Automotive(String name, float basePrice, int NumOfOptions) {
		this.name = name;
		this.basePrice = basePrice;
		this.optionSetList = new OptionSet[NumOfOptions];
	}
	
	/*
	 * getName() 
	 * get the name of the automotive
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * setName() 
	 * set the name of the automotive
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * getBasePrice() 
	 * Get the base price of 
	 * the automotive
	 * 
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/*
	 * setBasePrice()
	 * Set the base price of 
	 * the automotive
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}


	/*
	 * getOptionSet()
	 * Get OptionSet Value from 
	 * the index
	 */
	public OptionSet getOptionSet(int index) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index];
			}
		}
		return null;

	}

	/*
	 * getOptionSet()
	 * Get OptionSet Value from 
	 * the name
	 */
	public OptionSet getOptionSet(String setName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i];
				}
			}
		}
		return null;
	}

	/*
	 * setOptionSet()
	 * Set OptionSet Value to 
	 * the name
	 */
	public void setOptionSet(String setName, int optionSize) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] == null) {
				optionSetList[i] = new OptionSet(setName, optionSize);
				return;
			}
		}
	}

	/*
	 * setOptionSet()
	 * Set OptionSet Value to 
	 * the name
	 */
	public void setOptionSet(int index, String setName, int optionSize) {
		optionSetList[index] = new OptionSet(setName, optionSize);
	}

	/*
	 * deleteOptionSet()
	 * Delete OptionSet Value referred by 
	 * the index
	 */
	public void deleteOptionSet(int index) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				optionSetList[index] = null;
			}
		}
	}

	/*
	 * deleteOptionSet()
	 * Delete OptionSet Value referred by 
	 * the name
	 */
	public void deleteOptionSet(String setName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i] = null;
				}
			}
		}
	}

	
	/*
	 * updateOptionSet()
	 * Update OptionSet Value 
	 * referred by 
	 * the name
	 */
	public void updateOptionSet(String setName, int optionSize) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i] = new OptionSet(setName, optionSize);
					return;
				}
			}
		}
	}

	/*
	 * updateOptionSet()
	 * Update OptionSet Value 
	 * referred by 
	 * the index and name
	 */
	public void updateOptionSet(int index, String setName, int optionSize) {
		if (index < optionSetList.length && index >= 0) {
			optionSetList[index] = new OptionSet(setName, optionSize);
		}
	}

	/*
	 * updateOptionSetName()
	 * Update name of OptionSet  
	 * referred by 
	 * the name
	 */
	public void updateOptionSetName(String setName, String newSetName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i].setOptionSetName(newSetName);
					return;
				}
			}
		}
	}

	/*
	 * updateOptionSetName()
	 * Update name of OptionSet  
	 * referred by 
	 * the index
	 */
	public void updateOptionSetName(int index, String newSetName) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				optionSetList[index].setOptionSetName(newSetName);
			}
		}
	}


	/*
	 * getOption()
	 * Get the Option object 
	 * inside the
	 * OptionSet
	 * 
	 */
	public Option getOption(String setName, String optionName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(optionName);
				}
			}
		}
		return null;
	}

	/*
	 * getOption()
	 * Get the Option object 
	 * inside the
	 * OptionSet referred
	 * by index
	 * 
	 */
	public Option getOption(String setName, int index) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(index);
				}
			}
		}
		return null;
	}
	
	/*
	 * getOption()
	 * Get the Option object 
	 * inside the
	 * OptionSet referred
	 * by index
	 * 
	 */
	public Option getOption(int index, String optionName) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index].getOption(optionName);
			}
		}
		return null;
	}

	/*
	 * getOption()
	 * Get the Option object 
	 * inside the
	 * OptionSet referred
	 * by option set index
	 * and option index
	 * 
	 */
	public Option getOption(int index, int opIndex) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index].getOption(opIndex);
			}
		}
		return null;
	}

	/*
	 * getOptionPrice()
	 * Get the price of the
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name
	 * 
	 */
	public float getOptionPrice(String setName, String optionName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(optionName).getPrice();
				}
			}
		}
		return 0;
	}

	/*
	 * getOptionPrice()
	 * Get the price of the
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by index
	 * 
	 */
	public float getOptionPrice(String setName, int index) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(index).getPrice();
				}
			}
		}
		return 0;
	}

	/*
	 * getOptionPrice()
	 * Get the price of the
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by option Name
	 * 
	 */	
	public float getOptionPrice(int index, String optionName) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index].getOption(optionName).getPrice();
			}
		}
		return 0;
	}

	/*
	 * getOptionPrice()
	 * Get the price of the
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by option Name
	 * 
	 */	
	public float getOptionPrice(int index, int OpIndex) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index].getOption(OpIndex)
						.getPrice();
			}
		}
		return 0;
	}

	
	/*
	 * setOption()
	 * Set the price of the
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by option Name
	 * 
	 */	
	public void setOption(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).setOption(optionName, price);
		}

	}

	/*
	 * setOption()
	 * Set the price of the
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by option index
	 * 
	 */	
	public void setOption(int index, int opIndex, String optionName,
			float price) {
		if (getOptionSet(index) != null) {
			optionSetList[index].setOption(opIndex, optionName, price);
		}
	}

	
	/*
	 * deleteOption()
	 * Delete the 
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name
	 * 
	 */	
	public void deleteOption(String setName, String optionName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i].deleteOption(optionName);
				}
			}
		}
	}


	/*
	 * deleteOption()
	 * Delete the 
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by index
	 * 
	 */
	public void deleteOption(int index, String optionName) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				optionSetList[index].deleteOption(optionName);
			}
		}
	}


	/*
	 * deleteOption()
	 * Delete the 
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by Option Set name
	 * 
	 */
	public void deleteOption(String setName, int index) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i].deleteOption(index);
				}
			}
		}
	}

	/*
	 * deleteOption()
	 * Delete the 
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by Option Set name
	 * 
	 */
	public void deleteOption(int index, int opIndex) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				optionSetList[index].deleteOption(opIndex);
			}
		}
	}

	/*
	 * updateOptionName()
	 * Update the name of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name
	 * 
	 */
	public void updateOptionName(String setName, String optionName,
			String newOptionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionName(optionName, newOptionName);
		}
	}

	/*
	 * updateOptionName()
	 * Update the name of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name and index
	 * 
	 */
	public void updateOptionName(String setName, int index,
			String newOptionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionName(index, newOptionName);
		}
	}

	/*
	 * updateOptionName()
	 * Update the name of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name and index
	 * 
	 */
	public void updateOptionName(int index, String optionName,
			String newOptionName) {
		if (getOptionSet(index) != null) {
			getOptionSet(index).updateOptionName(optionName, newOptionName);
		}
	}
	
	/*
	 * updateOptionName()
	 * Update the name of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name and index
	 * 
	 */
	public void updateOptionName(int index, int opIndex,
			String newOptionName) {
		if (getOptionSet(index) != null) {
			getOptionSet(index).updateOptionName(opIndex, newOptionName);
		}
	}

	/*
	 * updateOptionPrice()
	 * Update the price of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name 
	 * 
	 */
	public void updateOptionPrice(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(optionName, price);
		}
	}

	/*
	 * updateOptionPrice()
	 * Update the price of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name and index
	 * 
	 */
	public void updateOptionPrice(String setName, int index, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(index, price);
		}
	}

	/*
	 * updateOptionPrice()
	 * Update the price of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name and index
	 * 
	 */
	public void updateOptionPrice(int index, String optionName, float price) {
		if (getOptionSet(index) != null) {
			getOptionSet(index).updateOptionPrice(optionName, price);
		}
	}

	/*
	 * updateOptionPrice()
	 * Update the price of
	 * Option object 
	 * inside the
	 * OptionSet referred
	 * by name and index
	 * 
	 */
	public void updateOptionPrice(int index, int opIndex, float price) {
		if (getOptionSet(index) != null) {
			getOptionSet(index).updateOptionPrice(opIndex, price);
		}
	}

	
	/*
	 * getoptionSetListSize
	 * Get the number of
	 * options for the automotive
	 * 
	 */
	public int getOptionSetListSize() {
		return optionSetList.length;
	}
	
	
	/*
	 * printAutomotiveDetails() 
	 * print all the 
	 * automotive information.
	 */

	public void print() {
		this.displayBaseInformation();
		this.printAllOptionSetInfo();
	}

	/*
	 * displayBaseInformation() 
	 * display the the base 
	 * information of the car
	 * 
	 */
	public void displayBaseInformation() {
		System.out.println(this.getName());
		System.out.println("Base Price :"
				+ String.format("%.2f", this.getBasePrice()));
		System.out.println();
	}
	
	
	/*
	 * printAllOptionSetInfo()
	 * 
	 * print all options, their option
	 * sets and alike
	 */
	public void printAllOptionSetInfo() {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				System.out.println(optionSetList[i].getOptionSetName() + ":");
				optionSetList[i].printAllOptions();
				System.out.println();
			}
			else{
				System.out.println("Option Set Does not Exist");
				System.out.println();
			}
			
		}
	}

}
