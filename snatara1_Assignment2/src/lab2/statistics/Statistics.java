/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package lab2.statistics;

import lab2.util.*;
import lab2.model.*;


public class Statistics extends AbstractStatistics implements PrintInfo {
	private int[] lowscores;
	private int[] highscores;
	private float[] avgscores;
	
	/* 
	 * Statistics constructor
	 * 
	 * @param none
	 */
	public Statistics() {
		lowscores = new int[ ConstantValues.NUM_OF_QUIZS];
		highscores = new int[ ConstantValues.NUM_OF_QUIZS];
		avgscores = new float[ ConstantValues.NUM_OF_QUIZS];
		
	}

	/*
	 * findhigh() 
	 * 
	 * find the highest score for every quiz 
	 * for all students
	 * 
	 * @param student array
	 */
	public void findhigh(Student[] a) {
		// if there is no student input, return
		if (a[0] == null) {
			return;
		}
		int[] scoreArr = new int[highscores.length];
		// initialize for finding
		for (int index = 0; index < highscores.length; index++) {
			highscores[index] = a[0].getScore()[index];
			scoreArr[index] = a[0].getScore()[index];
		}
		// finding the highest
		for (int st = 1; st < a.length; st++) {
			if (a[st] == null) {
				return;
			}
			for (int index = 0; index < highscores.length; index++) {
				scoreArr[index] = a[st].getScore()[index];
				if (scoreArr[index] > highscores[index]) {
					highscores[index] = scoreArr[index];
				}
			}
		}
	}

	/*
	 * findlow() 
	 * 
	 * find the lowest score for every quiz 
	 * for all students
	 * 
	 * @param student array
	 */
	public void findlow(Student[] a) {
		// if there is no student input, return
		if (a[0] == null) {
			return;
		}
		int[] scoreArr = new int[lowscores.length];
		// initialize for finding
		for (int index = 0; index < lowscores.length; index++) {
			lowscores[index] = a[0].getScore()[index];
			scoreArr[index] = a[0].getScore()[index];
		}

		for (int st = 1; st < a.length; st++) {
			if (a[st] == null) {
				return;
			}
			// finding the highest
			for (int index = 0; index < lowscores.length; index++) {
				scoreArr[index] = a[st].getScore()[index];
				if (scoreArr[index] < lowscores[index]) {
					lowscores[index] = scoreArr[index];
				}
			}
		}
	}

	/*
	 * findavg() 
	 * 
	 * find the average score for every quiz 
	 * for all students
     *
	 * @param array of students
	 */
	public void findavg(Student[] a) {
		if (a[0] == null) {
			return;
		}
		int stuCount = 0;
		for (Student st : a) {
			if (st == null) {
				break;
			}
			for (int index = 0; index < avgscores.length; index++) {
				avgscores[index] += st.getScore()[index];
			}
			stuCount++;
		}
		for (int index = 0; index < avgscores.length; index++) {
			avgscores[index] /= stuCount;
		}
	}

	/*
	 * printScoreStatistics()
	 * 
	 * print the high low 
	 * and average scores
	 * 
	 * @param none
	 */
	public void printScoreStatistics() {
		System.out.print("High Score");
		for (int i = 0; i < highscores.length; i++) {
			System.out.print("\t" + highscores[i]);
		}
		System.out.println();

		System.out.print("Low Score");
		for (int i = 0; i < lowscores.length; i++) {
			System.out.print("\t" + lowscores[i]);
		}
		System.out.println();

		System.out.print("Average\t");
		for (int i = 0; i < avgscores.length; i++) {
			System.out.print("\t" + String.format("%.1f", avgscores[i]));
		}
		System.out.println();

	}
	public void printScores() {
		throw new UnsupportedOperationException();
	};
	
	/* getters and setters */
	public int[] getLowscores()
	{
		return lowscores;
	}
	
	public int[] getHighscores()
	{
		return highscores;
	}
	
	public float[] getAvgscores()
	{
		return avgscores;
	}
	
	public void setLowscores( int[] lowscores)
	{
		this.lowscores = lowscores;
	}
	
	public void setAvgscores( float[] avgscores)
	{
		this.avgscores = avgscores;
	}
	
	public void setHighscores( int[] highscores)
	{
		this.highscores = highscores;
	}
}
