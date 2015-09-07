package simparkingticket;

public class Simulator {
	
	//Array of test cases
	private static TestCase[] testCases = {
			new TestCase("Parking time within the purchased time",
					"Toyota","RAV4","BLACK","123456", 50, 60,
					"John","11111"),
			new TestCase("Parking time is equal to the purchased parking time",
					"Jagaur","XF","WHITE","123457", 60, 60,
					"Sam","22222"),
			new TestCase("Parking time is greater than purchased parking time"
					+ "but within 60 minutes",
					"BMW","i8","BLACK","123458",61,60,
					"Steve","33333"),
			new TestCase("Parking time is greater than purchased parking time "
					+ "by exactly equal to 60 minutes",
					"Mercedes","Benz","WHITE","123459", 120, 60,
					"Daryl","44444"),		
			new TestCase("Parking time is greater than purchased parking time "
					+ "by more than 60 minutes",
					"AUDI","A3","WHITE","123556", 180, 60,
					"Mike","55555"),
	};
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		System.out.println("Simulating test cases for Parking Ticket");
		
		for (int i = 0; i < testCases.length; i++){
			System.out.println();
			System.out.println("====================");
			System.out.println("Test " + (i+1) + ": " 
							+ testCases[i].getDescription());
			System.out.println("The Car has been parked for " 
							+ testCases[i].getParkedMinutes() + " minutes");
			System.out.println("Purchased parking time is "
							+ testCases[i].getPurchasedTime() + " minutes");
			testCases[i].checkCars();
			System.out.println("====================");
		}
	}
}