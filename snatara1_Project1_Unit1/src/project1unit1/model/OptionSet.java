/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit1.model;

import java.io.Serializable;


class OptionSet implements Serializable {
	

	private static final long serialVersionUID = -6768245112432619563L;
	/* list of options*/
	private Option options[];
	/* name of the optionSet */
	private String name;

	/*
	 * Optionset()
	 * 
	 * Default constructor
	 * 
	 * 
	 */
	protected OptionSet() {}
	
	/*
	 * Optionset()
	 * 
	 * Constructor with arguments
	 *  
	 */
	protected OptionSet(String name, int numOfOptions) {
		this.name = name;
		options = new Option[numOfOptions];
	}
	
	
	/*
	 * getOptionSetName()
	 * 
	 * get the name of 
	 * the option set
	 * 
	 * */

	protected String getOptionSetName() {
		return name;
	}
	
	/*
	 * setOptionSetName()
	 * 
	 * set the name of 
	 * the option set
	 * 
	 * */
	protected void setOptionSetName(String name) {
		this.name = name;
	}
	
	
	
	/*
	 * getOptionListSize
	 * Get the number of options
	 * in this OptionList
	 *
	 */

	protected int getOptionListSize() {
		return options.length;
	}
	
	/*
	 * getOption
	 * 
	 * get the corresponding
	 * option from option
	 * name
	 *
	 */
	protected Option getOption(String optionName) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				if (options[i].getOptionName().equals(optionName)) {
					return options[i];
				}
			}
		}
		return null;
	}
	
	/*
	 * getOption
	 * 
	 * get the corresponding
	 * option from index
	 * 
	 *
	 */
	protected Option getOption(int index) {
		if (index < options.length && index >= 0) {
			if (options[index] != null) {
				return options[index];
			}
		}
		return null;
	}
	
	

	/*
	 * setOption
	 * Set value to the
	 * option given by 
	 * optionName
	 * 
	 */

	protected void setOption(String optionName, float price) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == null) {
				options[i] = new Option(optionName, price);
				return;
			}
		}
	}

	/*
	 * setOption
	 * Set value to the
	 * option given by 
	 * index.
	 * 
	 */
	protected void setOption(int index, String optionName, float price) {
		if (index < options.length && index >= 0) {
				options[index] = new Option(optionName, price);
		}
	}

	/*
	 * deleteOption
	 * Delete value to the
	 * option given by 
	 * optionName.
	 * 
	 */
	protected void deleteOption(String optionName) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				if (options[i].getOptionName().equals(optionName)) {
					options[i] = null;
				}
			}
		}
	}
	
	/*
	 * deleteOption
	 * Delete value to the
	 * option given by 
	 * index.
	 * 
	 */
	protected void deleteOption(int index) {
		if (index < options.length && index >= 0) {
			if (options[index] != null) {
				options[index] = null;
			}
		}
	}

	/*
	 * updateOptionPrice
	 * Update value to the
	 * option given by 
	 * optionName.
	 * 
	 */
	protected void updateOptionPrice(String optionName,float price) {
		if (getOption(optionName) == null) {
			return;
		} else {
			getOption(optionName).setPrice(price);
		}
	}
	
	/*
	 * updateOptionPrice
	 * Update value to the
	 * option given by 
	 * index.
	 * 
	 */
	protected void updateOptionPrice(int index,float price) {
		if (getOption(index) == null) {
			return;
		} else {
			getOption(index).setPrice(price);
		}
	}
	
	/*
	 * updateOptionName
	 * Update value to the
	 * option given by 
	 * newOptionName.
	 * 
	 */
	protected void updateOptionName(String optionName,String newOptionName) {
		if (getOption(optionName) == null) {
			return;
		} else {
			getOption(optionName).setOptionName(newOptionName);
		}
	}
	
	
	/*
	 * updateOptionName
	 * Update value to the
	 * option given by 
	 * index.
	 * 
	 */
	protected void updateOptionName(int index,String newOptionName) {
		if (getOption(index) == null) {
			return;
		} else {
			getOption(index).setOptionName(newOptionName);
		}
	}
	
	/*
	 * printAllOptions()
	 * to print all the option in order
	 * 
	 * */
	protected void printAllOptions(){
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				System.out.println(options[i].getOptionName()+":Price "+String.format("%.2f", options[i].getPrice()));
			}
			else{
				System.out.println("Option Does not exist");
			}
			
		}
	}
	
	/*
	 * inner class Option
	 * 
	 * */
	protected class Option implements Serializable {
		
		private static final long serialVersionUID = -843984857611600380L;
		
		/* Name of the option*/
		private String name;
		/* Price of the option */
		private float price;
		
		/*
		 * Option()
		 * Default Constructor
		 * 
		 * */
		protected Option() {}

		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}
		
		/*
		 * setOptionName()
		 * set the 
		 * option name
		 * 
		 * */
		protected void setOptionName(String option) {
			this.name = option;
		}
		
		/*
		 * getOptionName()
		 * get the 
		 * option name
		 * 
		 * */
		protected String getOptionName() {
			return name;
		}
		
		
		/*
		 * getOptionPrice()
		 * get the 
		 * option price
		 * 
		 * */
		protected void setPrice(float price) {
			this.price = price;
		}
		
		
		/*
		 * getOptionPrice()
		 * get the 
		 * option price
		 * 
		 * */
		protected float getPrice() {
			return price;
		}

	}

}
