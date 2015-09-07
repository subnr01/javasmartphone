/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package simcointoss;

/*
 * Class to demonstrate the 
 * tossing of a coin
 * 
 */

public class Coin {
	
	/*
	 * variable denoting side of 
	 * the coin that is up.
	 */
	private String sideUp;
	
	/*
	 * Coin()
	 * default constructor
	 * setting a side up in
	 * random
	 * 
	 */
	public Coin(){
		toss();
	}
	
	/*
	 * toss()
	 * Method to randomly change the 
	 * side of the coin that is up.
	 * 
	 */
	public void toss()  {
		double randVal = Math.random();
		if (randVal > 0.5) {
			sideUp = "heads";
		}else {
			sideUp = "tails";
		}
	}
	
	
	/*
	 * getSideUp()
	 * return the current side 
	 * of coin that is up
	 * 
	 */
	public String getSideUp()  {
		return sideUp;
	}
}
