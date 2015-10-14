package adapter;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public interface CreateAuto {
	
	/**
	 * build an instance of Automobile 
	 * out of a text file
	 * 
	 * @param filename the specific text file name
	 */
	public void buildAuto(String filename);
	
	/**
	 * This function searches and 
	 * prints the properties of a given Automobile
	 * 
	 * @param Name
	 */
	public void printAuto(String modelName);
}
