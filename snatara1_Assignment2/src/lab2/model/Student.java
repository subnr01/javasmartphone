/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package lab2.model;




import lab2.util.*;


public class Student extends GuysAndGals implements PrintInfo {
	
	private int SID;
	private int scores[] = new int[ConstantValues.NUM_OF_QUIZS];

	/*
	 * getSID() 
	 * 
	 * @param none
	 */

	public int getSID() {
		return SID;
	}

	/*
	 * setSID() 
	 * 
	 * @param studentID
	 */
	public void setSID(int studentID) {
		this.SID = studentID;
	}

	/*
	 * getScore() 
	 * 
	 * @param none
	 */
	public int[] getScore() {
		return scores;
	}

	/*
	 * setScore() 
	 * 
	 * @param array of scores
	 * 
	 *
	 */
	public void setScore(int[] scores) {
		this.scores = scores;
	}

	/*
	 * printScoreStatistics() 
	 * print student ID and scores
	 *
	 * @param none
	 */
	public void printScores() {
		
		System.out.print(String.format("%04d", SID));

		for (int i = 0; i < scores.length; i++) {
			System.out.print("\t" + String.format("%03d", scores[i]));
		}
		System.out.println();
	}
	
	
	public void printScoreStatistics() {
		throw new UnsupportedOperationException();
	}
}
