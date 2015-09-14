/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package lab2.exceptions;

import java.io.IOException;

/*
 * Score number is not
 * equal to 5
 */

public class InvalidScoreNumberException extends IOException {

	private static final long serialVersionUID = 1L;

	/*
	 * InvalidScoreNumberException() 
	 * 
	 * Calling parent constructor
	 * 
	 */
	public InvalidScoreNumberException(String msg) {
		super(msg);
	}
}
