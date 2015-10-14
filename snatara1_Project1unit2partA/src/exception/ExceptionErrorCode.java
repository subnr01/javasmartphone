/**
 * 
 */
package exception;

/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

public enum ExceptionErrorCode {
	FileNotFound(1),
	FileNoBasePrice(2),
	FileNoOptionPrice(3),
	CarModelNotFound(4),
	OptionSetNotFound(5),
	OptionNotFound(6),
	SerialCarFileNotFound(7);
	
	private int exceptionIndex;
	
	private ExceptionErrorCode(int index){
		exceptionIndex = index;
	}
	
	// getter
	public int getExceptionIndex(){
		return exceptionIndex;
	}
}
