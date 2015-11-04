package com.siteimprove.regularexp.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FilePatternMatcherException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String source;
	private String message;
	private Exception originalException;
	
	
	public FilePatternMatcherException(String source, String message){
		this(source, message, null);
	}
	
	public FilePatternMatcherException(String source, String message, Exception originalException) {
		this.source = source;
		this.message = message;
		this.originalException = originalException;
	}

	public String toString() {
		return "Source: " + source + "\n\tMessage: " + message + addExeptionMessage();
	}

	private String addExeptionMessage() {
		String res = "";
		if (this.originalException != null) {
			res = res + "\nOriginal Error: \n" + originalException.toString();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			originalException.printStackTrace(pw);
			res += sw.toString();
		}
		return res;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
