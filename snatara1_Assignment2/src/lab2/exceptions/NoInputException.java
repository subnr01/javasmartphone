/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package lab2.exceptions;

import java.io.IOException;
/*
 * Something wrong with the 
 * input file.
 */

public class NoInputException extends IOException {
	private static final long serialVersionUID = 1L;

	/*
	 * NoInputException() 
	 * 
	 * Calling parent constructor
	 * 
	 */
	public NoInputException(String msg) {
		super(msg);
	}

}
