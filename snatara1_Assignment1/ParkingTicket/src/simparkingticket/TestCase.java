package simparkingticket;

public class TestCase {
	
	/*
	 * Variables needed for running
	 * the test.
	 */
	private String testCaseTitle;
	private ParkedCar parkedCar;
	private PoliceOfficer policeOfficer;
	private ParkingMeter parkingMeter;
	
	public TestCase(String title, String make, String model,
			String color, String licenseNumber, int parkedTime,
			int purchasedMin, String name, String badge){
		this.testCaseTitle = title;
		parkedCar = new ParkedCar(make, model, color, licenseNumber, parkedTime);
		policeOfficer = new PoliceOfficer(name, badge);
		parkingMeter = new ParkingMeter(purchasedMin);
	}
	
	public String getDescription(){
		return this.testCaseTitle;
	}

	public int getParkedMinutes(){
		return parkedCar.getParkedMinutes();
	}
	
	public int getPurchasedTime(){
		return parkingMeter.getPurchasedParkingTime();
	}
	
	public void checkCars(){
		boolean legal = policeOfficer.CheckParkingtimeExpired(this.getParkedMinutes()
														,this.getPurchasedTime()); 
														
		if (legal) {
			System.out.println("Parking is legal");
		}else {
			System.out.println("Parking is illegal");
			policeOfficer.issueTicket(parkedCar, parkingMeter);
		}
		
	}
}