/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit1.util;

import java.io.*;


import project1unit1.model.*;


public class FileIO {
	private static final int NUM_BASE_ATTR = 3;
	
	
	public Automotive buildAutomative(String filename) {

		Automotive auto = null; 
		BufferedReader br = null; 
		String line; 
		
		/*
		 * baseAttr stores the name, 
		 * base price and number of options
		 * 
		 */
		String[] baseAttr = new String[NUM_BASE_ATTR]; 
		/* 
		 * Store the optionSet
		 * information from file
		 */
		String[] OptionSetInfo; 
		/* 
		 * Store the optionSet options
		 * information from file
		 */
		String[] optionsInfo;
		int optionSetSize = 0; 
		
		try {
			br = new BufferedReader(new FileReader(filename));

			/* Read Base attributes */
			for (int i = 0; i < baseAttr.length; i++) {
				baseAttr[i] = br.readLine();
			}
			auto = new Automotive(baseAttr[0],
					Float.parseFloat(baseAttr[1]),
					Integer.parseInt(baseAttr[2]));

			/* Get the number of OptionSets */
			optionSetSize = auto.getOptionSetListSize();
			
			for (int i = 0; i < optionSetSize; i++) {
				line = br.readLine();
				OptionSetInfo = line.split(":");
				auto.setOptionSet(i, OptionSetInfo[0],
						Integer.parseInt(OptionSetInfo[1]));

				/* Get info of the options */
				for (int j = 0; j < Integer.parseInt(OptionSetInfo[1]); j++){
					line = br.readLine();
					optionsInfo = line.split(",");
					
					auto.setOption(i, j, optionsInfo[0],
							Float.parseFloat(optionsInfo[1]));	
				}
				
			}

		} catch (IOException e) {
			System.out.println("ERROR: " + e.toString());
		} finally {
			try {
				br.close();
			} catch (IOException brCloseException) {
				System.out.println("ERROR: " + brCloseException.toString());
			}
		}
		return auto;
	}
	
	
	/*
	 *  serializeOutput
	 *  Serialize
	 *  automotive objects
	 *  to file
	 *  
	 */
	public void serializeOutput(Automotive auto) {
		ObjectOutputStream os = null;
		try {
			/* 
			 * Write object
			 * to auto.ser file
			 */
			os = new ObjectOutputStream(new FileOutputStream("auto.ser"));
			os.writeObject(auto);
			os.close();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.toString());
			System.exit(1);
		} finally {
			try {
				os.close();
			} catch (IOException streamCloseException) {
				System.out.println("ERROR: "
						+ streamCloseException.toString());
			}
		}
	}

	/*
	 *  serializeInput
	 *  Serialize 
	 *  automotive objects
	 *  from file
	 *  
	 */
	public Automotive serializeInput(String filename) {
		ObjectInputStream is = null;
		Automotive auto = null;
		try {
			is = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automotive) is.readObject();

		} catch (IOException e) {
			System.out.println("ERROR: " + e.toString());
			System.exit(1);

		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: " + e.toString());
			System.exit(1);

		} finally {
			try {
				is.close();
			} catch (IOException streamCloseException) {
				System.out.println("ERROR: "
						+ streamCloseException.toString());
			}
		}
		return auto;

	}

}