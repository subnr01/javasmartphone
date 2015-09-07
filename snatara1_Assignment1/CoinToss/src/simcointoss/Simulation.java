/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package simcointoss;


/** 
 * Simulating the
 * tossing of a coin 
 * 
 */

public class Simulation {
	
	public static void main(String[] args) {
		
		int[] testcases = {10, 20, 0, -1};
		
		System.out.println( "Simulation has started\n");
		
		/* Start the simulation */
		for (int i = 0; i < testcases.length; i++) {
			System.out.println("Running simulation with input: "+testcases[i]);
			SimulatingToss(testcases[i]);
			System.out.println();
		}
		System.out.println( "Simulation has ended");
		
	}
	
	public static void SimulatingToss(int NumOfTosses) {

		int headsUpNum = 0; 
		int tailsUpNum = 0; 
		String currSideUp; 
		
		Coin newCoin = new Coin();
		System.out.println("Coin Initial Face is : " + newCoin.getSideUp());
		
		
		/* Testing for 0 case or negative case */
		if ( NumOfTosses <= 0) {
			System.out.println("Coin not tossed");
			System.out.println("Invalid input \""+NumOfTosses+ "\"");
			System.out.println("Provide a input greater than zero");
			return;
		} 
		
		
		System.out.println("Toss the Coin " +NumOfTosses+ "  times");
		for (int i = 0; i < NumOfTosses; i++) {
			newCoin.toss();
			currSideUp = newCoin.getSideUp();
			System.out.println("Current Face after toss " + (i+1) + " is " + currSideUp);
			if (currSideUp.equals("heads")) {
				headsUpNum++;
			} else {
				tailsUpNum++;
			}
		}
		System.out.println("Number of Heads Face : " + headsUpNum);
		System.out.println("Number of Tails Face : " + tailsUpNum);
		
	}

}
