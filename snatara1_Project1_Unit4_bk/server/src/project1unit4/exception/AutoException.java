/**
 * Author: Subramanian N
 * Andrew id: snatara1
 */

package project1unit4.exception;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Date;


public class AutoException extends Exception {

	private static final long serialVersionUID = -3910053733037358031L;
	private int errorNum;
	private String name;

	public AutoException(ExceptionErrorCode exception) {
		this.errorNum = exception.getErrorNum();
		this.name = exception.toString();
		log();

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.errorNum);
		sb.append(" ");
		sb.append(this.name);
		return sb.toString();
	}

	public int getErrorNumber() {
		return errorNum;
	}

	public void log() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		StringBuffer output = new StringBuffer();
		output.append(ts.toString());
		output.append("\t");
		output.append("errorNum ");
		output.append(this.errorNum);
		output.append(":");
		output.append(this.name);
		output.append("\n");
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("exception.log", true)));
			bw.write(output.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.toString());
		}

	}

}
