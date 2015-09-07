/**
 * @author Subramanian 
 * @andrew id snatara1
 */


package simparkingticket;



public class PoliceOfficer {

	private String name; //name of the officer
	private String badgeNumber; //badge number of the officer
	
	/*
	 * PoliceOfficer()
	 * set the name
	 * and badge number 
	 * for the police officer
	 * 
	 * @param badgeNumber,name
	 * 
	 * */
	public PoliceOfficer(String name,String badgeNumber) {
			this.name = name;
			this.badgeNumber = badgeNumber;
	}
	/*
	 * getName()  
	 * get the name
	 * of the officer
	 * 
	 * @param none
	 * 
	 * */
	public String getName() {
		return name;
	}
	
	
	/*
	 * setName() 
	 * set the name
	 * of the officer
	 * 
	 * @param name
	 * 
	 * */
	public void setName(String name) {
			this.name = name;
	}
	
	
	/*
	 * getBadgeNumber() 
	 * get the badge number 
	 * of the officer 
	 * 
	 * @param none
	 * 
	 * */
	public String getBadgeNumber() {
		return badgeNumber;
	}
	
	/*
	 * setBadgeNumber()
	 * set the badge number 
	 * of the officer
	 * 
	 * @param badgeNumber
	 * 
	 * */
	public void setBadgeNumber(String badgeNumber) {
			this.badgeNumber = badgeNumber;
	}
	
	
	/*
	 * CheckParkingtimeExpired()
	 * Check whether parking 
	 * time for the car
	 * has expired.
	 * 
	 * @param purchasedTime,parkedTime 
	 */
	public boolean CheckParkingtimeExpired(int purchasedTime, int parkedTime) {
		if (purchasedTime >= parkedTime) {
			return false;
		}else {
			return true;
		}
	}
	
	/*
	 * issueTicket()
	 * issue fine 
	 * 
	 * @param parkedCar,parkingMeter
	 */
	
	public void issueTicket(ParkedCar parkedCar, ParkingMeter parkingMeter) {
		ParkingTicket parkingTicket = new ParkingTicket(parkedCar, this, parkingMeter);
		System.out.println();
		System.out.println("Parking Ticket Info");
		System.out.println("-------------------");
		System.out.println();
		parkingTicket.reportIllegallyParkedCarInfo();
		System.out.println();
		parkingTicket.reportFine();
		System.out.println();
		parkingTicket.reportOfficerInfo();
		System.out.println("-------------------");
		System.out.println();
	}
}
