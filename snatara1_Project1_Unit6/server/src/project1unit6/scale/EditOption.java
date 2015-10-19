/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit6.scale;

import project1unit6.exception.AutoException;
import project1unit6.model.Automobile;

public class EditOption extends Thread {
	/* Automobile object */
	private Automobile editAuto;
	/* Option to edit */
	private int editOptionEnum; 
	private String[] args; 

	
	/* thread ID */
	private int tid; 
	
	
	/*
	 * EditOption()
	 * 
	 * constructor wit
	 * arg
	 */
	public EditOption(int tid, Automobile editAuto,
			EditOptionEnum editOptionEnum, String[] args) {
		this.editAuto = editAuto;
		this.editOptionEnum = editOptionEnum.getValue();
		this.args = args;
		this.tid = tid;
	}

	/*
	 * run()
	 * 
	 * start the thread
	 */
	@Override
	public void run() {
		switch (editOptionEnum) {
		case 1:

			synchronisedUpdateOptionSetName();
			break;
		case 2:
			try {
				synchronisedUpdateOptionPrice();
			} catch (NumberFormatException | AutoException ex) {
				System.out.println("Error: " + ex.toString());
			}
			break;
		default:
			break;
		}
	}

	/*
	 * synchronisedUpdateOptionSetName()
	 * 
	 * update 
	 * OptionSet name
	 */
	public void synchronisedUpdateOptionSetName() {
		
		/*
		 * Demonstrating updates by threads
		 * in a circular fashion.
		 * If the updates are synchronized between threads,
		 * then the latest value is updated
		 * at the end of each thread execution.
		 * 
		 * If the updates are not synchronized, 
		 * then the thread execution get interleaved
		 * and thus can result in undefined 
		 * behavior.
		 * 
		 */
		String[] optionSetList = { args[1], "Set1", "Set2", "Set3", "Set4",
				"Set5", "Set6", "Set7", args[2] };

		/*
		 * Comment synchronized keyword to 
		 * demonstrate the race 
		 * conditions
		 */
	    synchronized (editAuto) { 
			for (int i = 0; i < optionSetList.length - 1; i++) {
				
				randomWait();
				try {
					editAuto.updateOptionSetName(optionSetList[i],
							optionSetList[i + 1]);
					System.out
							.println("Thread"
									+ tid
									+ " : "
									+ "Change OptionSet Name From "
									+ optionSetList[i]
									+ " To "
									+ editAuto
											.getOptionSetName(optionSetList[i + 1]));

				} catch (AutoException ae) {
					System.out.println("Thread" + tid + " : "
							+ "Error: " + ae.toString());
				}

			}
		}
	}

	

	
	/*
	 * synchronisedUpdateOptionPrice()
	 * 
	 * update price
	 * of the Option
	 */
	public void synchronisedUpdateOptionPrice() throws NumberFormatException,
			AutoException {
		/*
		 * Comment synchronized keyword to 
		 * demonstrate the race 
		 * conditions
		 */
		 float price = editAuto.getOptionPrice(args[1], args[2]);
		 
		synchronized (editAuto) { 
			
		 	price = editAuto.getOptionPrice(args[1], args[2]);
		 	System.out.println("Thread " + tid + " " + args[1] + " "
					+ args[2] + " Price: "
					+ editAuto.getOptionPrice(args[1], args[2]));
				randomWait();
				addPrice(price,Float.parseFloat(args[3]));
				editAuto.updateOptionPrice(args[1], args[2],
						addPrice(price,Float.parseFloat(args[3])));
				
			
		 }
		 System.out.println("Price After Update by Thread" + tid +" is "
				  		+editAuto.getOptionPrice(args[1], args[2]));		
	}
	
	private float addPrice(float Price, float increment)
	{
		return Price + increment;
	}
	

	/*
	 * randomWait()
	 * wait for a
	 * random time
	 */
	void randomWait() {
		try {
			Thread.currentThread();
			Thread.sleep((long) (3000 * Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
