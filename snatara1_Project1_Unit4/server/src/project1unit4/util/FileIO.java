/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit4.util;

import java.io.*;
import java.util.Properties;


import project1unit4.exception.*;
import project1unit4.model.Automobile;

public class FileIO {
	private static final int NUM_BASE_ATTR = 3;
	public Automobile buildAutoObject(String filename) throws AutoException {

		Automobile auto = null; 
		BufferedReader br = null; 
		String curLine; 
		// name, base price and option set size
		String[] baseAttr = new String[NUM_BASE_ATTR]; 
		String[] curOptionSet; 
		String[] curOptionDetail; 

		try {
			br = new BufferedReader(new FileReader(filename));

			// read in base info
			for (int i = 0; i < baseAttr.length; i++) {
				baseAttr[i] = br.readLine();
			}
			
			try {
				if (baseAttr[2].length() == 0) {
					throw new AutoException(
							ExceptionErrorCode.FileBasePriceNotFoundException);
				}
			} catch (AutoException e) {
				FixErrorCond fixHelper = new FixErrorCond();
				baseAttr[2] = fixHelper.fixNoBasePriceExceptionHandler();
			}
			
			auto = new Automobile(baseAttr[0],
					baseAttr[1],
					Float.parseFloat(baseAttr[2]));
			
			// get option set size
			while ((curLine = br.readLine()) != null){
				curOptionSet = curLine.split(":");
				auto.setOptionSet(curOptionSet[0]);
				for (int j = 0; j < Integer.parseInt(curOptionSet[1]); j++){
					curLine = br.readLine();
					curOptionDetail = curLine.split(",");
					
					auto.setOption(curOptionSet[0], curOptionDetail[0],
							Float.parseFloat(curOptionDetail[1]));	
				}
			}

		} catch (FileNotFoundException fe) {
			// if file not found, FixHelper will fix it
			throw new AutoException(ExceptionErrorCode.FileNotFoundException);
		} catch (IOException e) {
			System.out.println("ERROR: " + e.toString());
		} finally {
			try {
				if (br != null) {// if file not found, it will be null
					br.close();
				}
			} catch (IOException brCloseException) {
				System.out.println("ERROR: " + brCloseException.toString());
			}
		}
		return auto;
	}
	
	public void serializeAuto(Automobile auto){
		try{
            // StringBuffer to build file name for output
            StringBuffer sb = new StringBuffer();
            sb.append(auto.getName());
            sb.append(".ser");
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(sb.toString()));
			out.writeObject(auto);
			out.close();
		} catch (Exception e){
			System.out.print("Error: " + e);
			System.exit(1);
		}
	}
	
	public Automobile deserializeAuto(String filename) throws AutoException{
		Automobile auto = null;
		try{
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(filename));
			auto = (Automobile) in.readObject();
			in.close();
		} catch (Exception e){
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return auto;
	}
	
	public Automobile readInAutomobileProperty(Properties props){
		Automobile auto = null;
		auto = new Automobile(props.getProperty("CarMake"),
				props.getProperty("CarModel"), Float.parseFloat(props
						.getProperty("BasePrice")));

		String option = "Option";
		String optionValue = "OptionValue";
		String optionPrice = "OptionPrice";

		for (char optionNum = '1'; props.getProperty(option + optionNum) != null; optionNum++) {
			auto.setOptionSet(props.getProperty(option + optionNum));

			for (char optionValueNum = 'a'; props.getProperty(optionValue
					+ optionNum + optionValueNum) != null; optionValueNum++) {
				auto.setOption(
						props.getProperty(option + optionNum),
						props.getProperty(optionValue + optionNum
								+ optionValueNum),
						Float.parseFloat(props.getProperty(optionPrice
								+ optionNum + optionValueNum)));
			}

		}

		return auto;

	}

	
	
}
