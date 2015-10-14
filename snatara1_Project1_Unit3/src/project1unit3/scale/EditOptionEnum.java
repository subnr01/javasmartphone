/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit3.scale;


public enum EditOptionEnum {
	
	/*
	 * List of options to
	 * edit
	 */
	EditOptionSetName(1),
	EditOptionPrice(2);
	private int value;

	
	private EditOptionEnum(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}
}
