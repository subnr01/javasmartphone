/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package simparkingticket;

public class ParkingMeter {
	
	/* Number of purchased minutes
	 * for parking
	 */
	private int purchasedMinutes;
	
	/*
	 * ParkingMeter
	 * default constructor
	 * @param none
	 * 
	 */
	public ParkingMeter() {}
	
	/*
	 * ParkingMeter
	 * Constructor setting 
	 * the purchased minutes
	 * 
	 * @param purchasedMinutes
	 * 
	 */
	public ParkingMeter(int minutes) {
		if (minutes >= 0) {
			this.purchasedMinutes = minutes;
		} else {
			this.purchasedMinutes = 0;
		}
	}
	
	/*
	 * getPurchasedParkingTime()
	 * Get the purchased 
	 * parking time
	 * 
	 * @param purchasedMinutes
	 * 
	 */
	public int getPurchasedParkingTime() {
		return purchasedMinutes;
	}
	
	/*
	 * setPurchasedParkingTime()
	 * set the purchased 
	 * parking time
	 * 
	 * @param purchasedMinutes
	 * 
	 */
	public void setPurchasedParkingTime(int minutes) {
		if (minutes >= 0) {
			this.purchasedMinutes = minutes;
		}
	}
}
