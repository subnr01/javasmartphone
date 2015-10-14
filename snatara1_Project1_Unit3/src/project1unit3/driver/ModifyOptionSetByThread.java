/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit3.driver;


import project1unit3.adapter.BuildAuto;
import project1unit3.scale.EditOptionEnum;

public class ModifyOptionSetByThread {
	public static void main(String[] args) {
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		
		/*
		 * { Model, OptionSet name, New OptionSet name }
		 * 
		 */
		String[] args1 = {"Focus Wagon ZTW","Transmission","Transmission2"};
		String[] args2 = {"Focus Wagon ZTW","Transmission2","Transmission"};
		
		
		autoBuilder.edit(EditOptionEnum.EditOptionSetName, args1);
		autoBuilder.edit(EditOptionEnum.EditOptionSetName, args2);
	}
	
	
}
