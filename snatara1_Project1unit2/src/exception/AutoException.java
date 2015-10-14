/**
 * 
 */
package exception;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public class AutoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int exceptionIndex;
	private String exceptionName;
	
	// constructor
	public AutoException(ExceptionErrorCode exception){
		exceptionIndex = exception.getExceptionIndex();
		exceptionName = exception.toString();
		log();
	}
	
	@Override
	public String toString(){
		return "Exception: " + exceptionIndex + " " + exceptionName;
	}
	
	// getter
	public int getExceptionIndex(){
		return exceptionIndex;
	}
	
	/*
	 * log AutoException with 
	 * timestamps into a log file
	 * */
	public void log(){
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String logline = timestamp.toString() + " " + this.toString() + "\n";
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("exception.log", true)));
			bw.write(logline);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}
	}
}
