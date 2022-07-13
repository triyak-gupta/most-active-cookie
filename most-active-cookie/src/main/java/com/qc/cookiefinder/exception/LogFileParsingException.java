package com.qc.cookiefinder.exception;


/**
 * @author Triyak
 *Custom exception for parsing the cookie log file
 */
public class LogFileParsingException extends Exception {
	private static final long serialVersionUID = 1L;

	public LogFileParsingException(Throwable error) {
		super(error);
	}
}
