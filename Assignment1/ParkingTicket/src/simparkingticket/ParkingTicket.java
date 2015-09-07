/**
 * Author: Subramanian 
 * Andrew id: snatara1
 */


package simparkingticket;



public class ParkingTicket {
	
	private ParkedCar parkedCar;
	private double fine; //parking fine
	private PoliceOfficer policeOfficer; //police officer issuing ticket
	private ParkingMeter parkingMeter; 
	private static final double BASE_FINE = 25.00; // base fine 
	private static final double EXTRA_PER_HOUR_FINE = 10.00; // fine per extra hour

	/*
	 * ParkingTicket() 
	 * set the parking ticket 
	 * information
	 * 
	 * @param : parkedCarInfo,policeOfficerInfo,
	 *          parkingMitutes, purchasedMinutes
	 */
	public ParkingTicket(ParkedCar parkedCar, PoliceOfficer policeOfficer,
			ParkingMeter parkingMeter) {
		this.parkedCar = parkedCar;
		this.policeOfficer = policeOfficer;
		this.parkingMeter = parkingMeter;
	}

	/*
	 * reportIllegallyParkedCarInfo() 
	 * report  information 
	 * about the parked car
	 * 
	 * @param none
	 */
	public void reportIllegallyParkedCarInfo() {
		System.out.println("Car Information:");
		String parkedCarInfo = "Make: " + parkedCar.getMake() + "\nModel: "
				+ parkedCar.getModel() + "\nColor: " + parkedCar.getColor()
				+ "\nLicense Number: " + parkedCar.getLicenseNumber();
		System.out.println(parkedCarInfo);
	}

	/*
	 * reportOfficerInfo() 
	 * report the name and
	 * badge of the police
	 * officer
	 * 
	 * @param none
	 */
	public void reportOfficerInfo() {
		System.out.println("Police Officer Info:");
		String officer = "Officer Name : " + policeOfficer.getName()
				+ "\nOfficer Badge Number : " + policeOfficer.getBadgeNumber();
		System.out.println(officer);
	}

	/*
	 * reportFine() 
	 * report fine
	 * 
	 * @param none
	 */
	public void reportFine() {
		calculateFine();
		System.out.println("Parking Fine : $" + fine);
	}

	/*
	 * calculateFine() 
	 * calculate the  
	 * parking fine
	 * 
	 * @param : none
	 */
	private void calculateFine() {
		int extraMinParked;
		this.fine = BASE_FINE;
		
		
		extraMinParked = parkedCar.getParkedMinutes() 
				           - parkingMeter.getPurchasedParkingTime();
		
		if ( extraMinParked > 60) {
			this.fine += EXTRA_PER_HOUR_FINE
							* (extraMinParked)/60;
		}
	}
}
