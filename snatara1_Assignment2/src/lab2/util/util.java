/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */
package lab2.util;

import java.io.*;
import java.util.*;
import lab2.model.*;
import lab2.exceptions.*;


public class util {

	/*
	 * readFile()
	 * read contents of the
	 * file into the Student
	 * array
	 * 
	 *  @param filename
	 *  @param student array
	 */
	public static Student[] readFile(String filename, Student[] stu) throws IOException {
		BufferedReader br = null;
		String line;
		boolean input = false;
		/* Number of students */
		int index = 0;
		try {
			
			/* Open the file using the FileReader */
			br = new BufferedReader(new FileReader(new File(filename)));
			boolean eof = false;
			
			line = br.readLine();
			
			/* If the first line itself is null */
			if (line == null) { 
				eof = true;
				br.close();
				throw new NoInputException("Empty file");
			}

			while (!eof) {
				line = br.readLine();

				if (line == null) {
					eof = true;
					if (!input) {
						br.close();
						throw new NoInputException("No input found");
					}
					break;
				} else {
					input = true;
					if (index < ConstantValues.MAX_STUDENT_NUM) {
						StringTokenizer str = new StringTokenizer(line);
						int SID = 0;
						int[] scoresInLine = new int[5];
						int count = 0;

						/* Retrieve SID */
						SID = Integer.parseInt(str.nextToken());

						/*  Retrieve scores */
						while (str.hasMoreTokens()) {
							if (count  == 5) {
								br.close();
								throw new InvalidScoreNumberException(
										"Number of Quiz scores is more than 5");
							}
							scoresInLine[count] = Integer.parseInt(str
									.nextToken());
							count++;
						}
						if (count  != 5) {
							br.close();
							throw new InvalidScoreNumberException(
									"Number of Quiz scores is less than 5");
						}
						

						/* Build Student object */
						stu[index] = new Student();
						stu[index].setSID(SID); 
						stu[index].setScore(scoresInLine); 

						index++;

					} else {
						/* Student number is more than required */
						if (line != null) {
							br.close();
							throw new InvalidStudentNumberException(
									"Input Student Number is greater than 40");
							
						}
					}
				}
			}

			br.close();
			} catch (IOException ex) {
				br.close();
				throw ex;
		}
		return stu;

	}
}
