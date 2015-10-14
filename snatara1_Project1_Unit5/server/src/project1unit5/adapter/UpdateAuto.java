/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit5.adapter;

/*
 * Interfaces to update 
 * OptionSet
 * and 
 * Option
 */
public interface UpdateAuto {
	
	public void updateOptionSetName(String ModelName, String OptionSetName,
			String newName);
	
	public void updateOptionPrice(String ModelName, String Optionname,
			String Option, float newprice);
}
