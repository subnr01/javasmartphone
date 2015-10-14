/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit3.model;

import java.io.Serializable;
import java.util.ArrayList;
import project1unit3.exception.*;



class OptionSet implements Serializable {


	private static final long serialVersionUID = -4066412725190515238L;
	private ArrayList<Option> options;
	private String name;
	private Option choiceOption;

	/*
	 * Optionset()
	 * 
	 * default constructor
	 * 
	 */
	protected OptionSet() {}
	
	/*
	 * Optionset()
	 * 
	 * OptionSet constructor
	 * with argument
	 * 
	 */
	protected OptionSet(String name) {
		this.name = name;
		options = new ArrayList<Option>();
	}

	/*
	 * getOptionSetName()
	 * 
	 * get the name of
	 * the OptionSet
	 */

	protected String getOptionSetName() {
		return name;
	}

	/*
	 * setOptionSetName()
	 * 
	 * set the name of the
	 * OptionSet
	 */
	protected void setOptionSetName(String name) {
		this.name = name;
	}

	/*
	 * getOptionListSize
	 * 
	 * 
	 * Get the number of 
	 * OptionSets
	 * 
	 */

	protected int getOptionListSize() {
		return options.size();
	}

	/*
	 * getOption
	 * 
	 * 
	 * return the Option
	 * given the Option name
	 * 
	 */
	protected Option getOption(String optionName) {
		for (Option op : options) {
			if (op.getOptionName().equals(optionName)) {
				return op;
			}
		}
		return null;
	}

	/*
	 * setOption
	 * 
	 * Add new Option and
	 * price
	 */

	protected void setOption(String optionName, float price) {
		options.add(new Option(optionName, price));
	}

	/*
	 * deleteOption
	 * 
	 * delete option 
	 * given the name
	 */
    protected void deleteOption(String optionName) {
		for (Option op : options) {
			if (op.getOptionName().equals(optionName)) {
				options.remove(op);
				return;
			}
		}
	}


    /*
	 * updateOptionPrice
	 * 
	 * Update option price
	 * given the name and price
	 */
	protected void updateOptionPrice(String optionName, float price)
			throws AutoException {
		if (getOption(optionName) == null) {
			throw new AutoException(ExceptionErrorCode.OptionNotFoundException);
		} else {
			getOption(optionName).setPrice(price);
		}
	}

	/*
	 * printAllOptions()  
	 * 
	 * print all the option
	 * details
	 */
	protected void printAllOptions() {
		for (Option op : options) {
			System.out.println(op.getOptionName() + ":Price "
					+ String.format("%.2f", op.getPrice()));

		}

	}

	/*
	 * setChoice()
	 * 
	 * set the choice 
	 * for this OptionSet
	 */
	protected void setChoice(String optionName) {
		choiceOption = getOption(optionName);
	}

	/*
	 * getChoiceName()
	 * 
	 * return the choice name 
	 * for the OptionSet
	 */
	protected String getChoiceName() {
		return choiceOption.getOptionName();
	}

	/*
	 * getChoicePrice()
	 * 
	 * get the choice price for 
	 * the OptionSet
	 */
	protected float getChoicePrice() {
		return choiceOption.getPrice();
	}

	/*
	 * inner class Option 
	 */
	protected class Option implements Serializable {
		private static final long serialVersionUID = -911426289875231387L;

		private String name;
		private float price;

		/*
		 * Option()
		 * 
		 * Constructor
		 */

		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		/*
		 * setOptionName() 
		 * 
		 * set the 
		 * option name
		 */
		protected void setOptionName(String option) {
			this.name = option;
		}

		/*
		 * getOptionName() 
		 * 
		 * get the option name
		 */
		protected String getOptionName() {
			return name;
		}

		/*
		 * setPrice()
		 * 
		 * set the option price
		 */
		protected void setPrice(float price) {
			this.price = price;
		}

		/*
		 * getPrice() 
		 * 
		 * get the
		 * option price
		 */
		protected float getPrice() {
			return price;
		}

	}

}
