/**
 * 
 */
package CoinToss;

/**
 * @author Subramanian 
 * @andrew_id snatara1
 */


public class Coin {
	/*
	 * Instance variable of current side up
	 * 
	 * */
	private String sideUp;
	/*
	 * Coin()
	 * void constructor for initialize the coin's
	 * facing up side
	 * @param sideUp
	 * 
	 * */
	public Coin(){
		toss();
	}
	/*
	 * toss()
	 * Randomly change the side up of coin
	 * @param sideUp
	 * 
	 * */
	public void toss(){
		double random = Math.random();
		if (random > 0.5) {
			this.sideUp = "heads";
		}else {
			this.sideUp = "tails";
		}
	}
	/*
	 * show the current side up of coin
	 * @param sideUp
	 * 
	 * */
	public String getSideUp(){
		return sideUp;
	}
}
