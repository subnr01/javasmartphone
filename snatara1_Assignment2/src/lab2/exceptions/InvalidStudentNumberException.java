/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package lab2.exceptions;

import java.io.IOException;

/*
 * Student number is not equal
 * to 40
 */

public class InvalidStudentNumberException extends IOException {
	private static final long serialVersionUID = 1L;

	/*
	 * InvalidStudentNumberException() 
	 * calling Parent constructor
	 */
	
	public InvalidStudentNumberException(String msg) {
		super(msg);
	}
}
