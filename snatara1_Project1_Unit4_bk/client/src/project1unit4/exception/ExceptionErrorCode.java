/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit4.exception;


public enum ExceptionErrorCode {
	/*
	 * List of error conditions
	 */
	FileNotFoundException(1), 
	FileBasePriceNotFoundException(2), 
	FileOptionPriceNotFoundException(3), 
	CarModelNotFoundException(4),
	SavedCarFileNotFoundException(5), 
	OptionSetNotFoundException(6), 
	OptionNotFoundException(7);
	private int errorNum;

	private ExceptionErrorCode(int errorNum) {
		this.setErrorNum(errorNum);
	}

	public int getErrorNum() {
		return errorNum;
	}

	private void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}
}
