/**
 * 
 */
package util;

import java.io.*;

import exception.AutoException;
import exception.ExceptionErrorCode;
import exception.FixErrorCond;
import model.Automobile;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */


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
				// if there is no base price , FixHelper will set it 0
				if (baseAttr[2].length() == 0) {
					throw new AutoException(
							ExceptionErrorCode.FileNoBasePrice);
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
			throw new AutoException(ExceptionErrorCode.FileNotFound);
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		} finally {
			try {
				if (br != null) {// if file not found, it will be null
					br.close();
				}
			} catch (IOException brCloseException) {
				System.out.println("Error -- " + brCloseException.toString());
			}
		}
		return auto;
	}
	
	public void serializeAuto(Automobile auto){
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("auto.ser"));
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
	
}
