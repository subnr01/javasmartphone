
/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package lab2.main;

import java.io.*;
import lab2.util.*;
import lab2.model.*;
import lab2.statistics.*;
import lab2.exceptions.*;



public class mainDriver {
	
	
	public static void runTest( String filename) {
		Student lab2Scores[] = new Student[40];

		try {
		/* Populate the student info */
		lab2Scores = util.readFile(filename, lab2Scores);
		} catch (InvalidStudentNumberException ex) {
			System.out.println("ERR: " + ex.toString());
			return;
		} catch (InvalidScoreNumberException ex) {
			System.out.println("Error: " + ex.toString());
			return;
		
		} catch (NoInputException ex) {
			System.out.println("Error: " + ex.toString());
			return;
		
		} catch (IOException ex) {
			System.out.println("Error: " + ex.toString());
			return;
		}
	
		
		/* Determine the statistics */
		Statistics lab2Stats = new Statistics();
		lab2Stats.findlow(lab2Scores);
		lab2Stats.findhigh(lab2Scores);
		lab2Stats.findavg(lab2Scores);

		System.out.println("SID\t" + "Q1\t" + "Q2\t" + "Q3\t" + "Q4\t" + "Q5");
		for (int i = 0; i < lab2Scores.length && lab2Scores[i] != null; i++) {
			lab2Scores[i].printScores();
		}
		System.out.println();
		
		/* print statistics information */
		lab2Stats.printScoreStatistics();
		
	}
	public static void main(String[] args) {
		
		String filename = null;
		System.out.println("****************************");
		System.out.println("Demonstrating Valid Input Case");
		 System.out.println("--------------------------------------");
		filename = "filename.txt";
		runTest(filename);
		System.out.println("****************************");
		System.out.println("\n Demonstrating First Exception case");
		 System.out.println("--------------------------------------");
		filename = "filename_execp.txt";
		runTest(filename);
		System.out.println("****************************");
		System.out.println("\n Demonstrating Second Exception case");
		 System.out.println("--------------------------------------");
		filename = "filename_execp2.txt";
	    runTest(filename);
	    System.out.println("****************************");
	    System.out.println("\n Demonstrating Third Exception case");
	    System.out.println("--------------------------------------");
		filename = "filename_execp3.txt";
	    runTest(filename);
	    System.out.println("****************************");
	    System.out.println("\n Demonstrating Fourth Exception case");
	    System.out.println("--------------------------------------");
		filename = "filename_execp4.txt";
	    runTest(filename);
	    System.out.println("****************************");
	    System.out.println("\n Demonstrating Fifth Exception case");
	    System.out.println("--------------------------------------");
		filename = "filename_execp5.txt";
	    runTest(filename);
	    System.out.println("****************************");
	    
	    
	    
	    
	}
}