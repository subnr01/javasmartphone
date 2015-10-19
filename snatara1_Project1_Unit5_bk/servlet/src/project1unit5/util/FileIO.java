/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package project1unit5.util;

import java.io.*;
import java.util.ArrayList;


import project1unit5.exception.*;
import project1unit5.model.Automobile;

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
	
	// return a list of all available automobile file
	public String[] getAutoFileList(String fileName){
			BufferedReader br = null; // buffer reader
			ArrayList<String> autoArrayList = new ArrayList<String>();
			String file = null;
			try {
				br = new BufferedReader(new FileReader(new File(fileName)));
				while ((file = br.readLine()) != null) {
					autoArrayList.add(file);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] autoNameList = new String[autoArrayList.size()];
			for (int i = 0; i < autoNameList.length; i++) {
				autoNameList[i] = autoArrayList.get(i);
			}
			return autoNameList;
		}
	
}
