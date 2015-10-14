/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.text.html.Option;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public class OptionSet implements Serializable{
	// instance variables
	private Option options[];
	private String name;
	private Option choiceOption;
	
	// static variables
	private static final long serialVersionUID = 124656345542354L;
	
	// constructor
	protected OptionSet (String name, int optionSize){
		this.name = name;
		options = new Option[optionSize];
	}
	
	protected OptionSet(){}
	
	// getter/setter
	protected String getName(){
		return name;
	}
	
	protected void setName(String n){
		name = n;
	}
	
	protected int getOptionSize(){
		return options.length;
	}
	
	// get option 
	protected Option getOption(String name){
		for (Option op : options) {
			if (op.getName().equals(name)) {
				return op;
			}
		}
		return null;
	}
	
	// set option 
	
	protected void setOption(String name, float price) {
		for (int i = 0; i < options.length; i++) {
			Option temp = new Option(name, price);
			options[i] = temp;
		}
	}
	
	protected void setOption(int index, String optionName, float price) {
		if (index < options.length && index >= 0) {
				options[index] = new Option(optionName, price);
		}
	}
	
	// delete
	protected void deleteOption(String name) {
		for (int i = 0; i < options.length; i++) {
			if (options[i].getName().equals(name)) {
				options[i] = null;
				return;
			}			
		}
	
	}
	
	// update 
	protected void updateOptionPrice(String name,float price) {
		if (getOption(name) == null) {
			return;
		} else {
			getOption(name).setPrice(price);
		}
	}

	
	protected void updateOptionName(String name,String newName) {
		if (getOption(name) == null) {
			return;
		} else {
			getOption(name).setName(newName);
		}
	}
	
	// choice part
	protected void setChoice(String optionName) {
		choiceOption = getOption(optionName);
	}
	
	protected String getChoiceName() {
		return choiceOption.getName();
	}
	
	protected float getChoicePrice() {
		return choiceOption.getPrice();
	}
	
	// instance methods
	protected void printAllOptions(){
		for (Option op : options) {
			System.out.println(op.getName() + ":Price "
					+ String.format("%.2f", op.getPrice()));

		}
	}
	
	// static methods
	protected class Option implements Serializable{
		
		// instance variables
		private String name;
		private float price;
		
		// static variables
		private static final long serialVersionUID = 54643216846842L;
		
		// constructor
		protected Option(){
			
		}
		
		protected Option(String n, float p){
			name = n;
			price = p;
		}
		
		// getter/setter
		protected void setName(String n){
			name = n;
		}
		
		protected String getName(){
			return name;
		}
		
		protected void setPrice(float p){
			price = p;
		}
		
		protected float getPrice(){
			return price;
		}
		
		
		
	}
}
