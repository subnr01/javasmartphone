/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package simparkingticket;



public class ParkedCar {
	
	private String make;  //make of the parked car
	private String model; //model of the parked car
	private String color; //color of the parked car
	private String licenseNumber; //license number of the parked car
	private int parkedMinutes;    //Number of minutes the car is parked  

	/*
	 * ParkedCar() 
	 * Constructor to set the the attributes 
	 * of the parked car.
	 * 
	 * @param : make,model,color,licenseNumber,parkedMinutes
	 * 
	 */
	public ParkedCar(String make, String model, String color,
			String licenseNumber, int parkedMinutes) {
			this.make = make;
			this.model = model;
			this.color = color;
			this.licenseNumber = licenseNumber;
		if (parkedMinutes >= 0) {
			this.parkedMinutes = parkedMinutes;
		}else {
			this.parkedMinutes = 0;
		}
	}

	/*
	 * getMake()  
	 * get the make 
	 * of the parked car
	 * 
	 * @param none
	 */
	public String getMake() {
		return make;
	}

	/*
	 * setMake()  
	 * set the make of
	 * the parked car
	 * 
	 * @param make
	 */
	public void setMake(String make) {
			this.make = make;
	}

	/*
	 * getModel()  
	 * get the model of the
	 * parked car
	 * 
	 * @param none
	 */
	public String getModel() {
		return model;
	}

	/*
	 * setModel()
	 * set the model of 
	 * the parked car.
	 * 
	 * @param model
	 */
	public void setModel(String model) {
			this.model = model;
	}

	/*
	 * getColor()
	 * Get the color of
	 * the parked car.
	 * 
	 * @param none
	 */
	public String getColor() {
		return color;
	}

	/*
	 * setColor() 
	 * Set the color of
	 * the parked car
	 * 
	 * @param color
	 */
	public void setColor(String color) {
			this.color = color;
	}

	/*
	 * getLicenseNumber()
	 * Get the license number 
	 * of the parked car
	 * 
	 * @param none
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/*
	 * setlicenseNumber()
	 * Set the license number 
	 * of the parked car
	 * 
	 * @param licensenumber
	 */
	public void setLicenseNumber(String licenseNumber) {
			this.licenseNumber = licenseNumber;
	}

	/*
	 * getParkedMinutes()
	 * Get the number of minutes
	 * the car is parked.
	 * 
	 * @param none
	 */
	public int getParkedMinutes() {
		return parkedMinutes;
	}

	/*
	 * setParkedMinutes()
	 * Set the parking minutes
	 * for the car
	 * 
	 * @param parkedMinutes
	 */
	public void setParkedMinutes(int parkedMinutes) {
		if (parkedMinutes >= 0) {
			this.parkedMinutes = parkedMinutes;
		}
	}
}
