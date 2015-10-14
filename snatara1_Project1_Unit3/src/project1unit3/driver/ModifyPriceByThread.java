/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit3.driver;


import project1unit3.adapter.BuildAuto;
import project1unit3.scale.EditOptionEnum;

public class ModifyPriceByThread {
	public static void main(String[] args) {
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		
		/*
		 * { Model, OptionSet name, Option, Price Seed }
		 * 
		 */
		String[] args1 = {"Focus Wagon ZTW","Transmission","Automatic", "5"};
		String[] args2 = {"Focus Wagon ZTW","Transmission","Automatic","10"};
		
		/*
		 * The edit Option price
		 * updates the price 
		 * by adding the current price
		 * with the seed value
		 */
		autoBuilder.edit(EditOptionEnum.EditOptionPrice, args1);
		autoBuilder.edit(EditOptionEnum.EditOptionPrice, args2);
		
	}
}
