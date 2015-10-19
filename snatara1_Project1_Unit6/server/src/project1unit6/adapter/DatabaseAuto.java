/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit6.adapter;


public interface DatabaseAuto {
	/*
	 * Build Automobile to database
	 */
	public void buildDBAuto(String filename);
	
	/*
	 * Delete Automobile in Database
	 */
	public void deleteDBAuto(String AutoName);
	
	/*
	 * Update Price of Automobile
	 */
	public void updateDBAutoBasePrice(String AutoName, float newPrice);
	
	/*
	 * Update Price of option
	 */
	public void updateDBAutoOptionBasePrice(String AutoName, String OptionSetName, String OptionName, float OptionPrice);

}
