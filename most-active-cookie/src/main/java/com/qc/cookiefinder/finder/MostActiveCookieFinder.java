package com.qc.cookiefinder.finder;

import com.qc.cookiefinder.exception.LogFileParsingException;
import com.qc.cookiefinder.requestparser.CommandLineInput;

/*
 * Interface to find the most active cookie from the log file
 */
public interface MostActiveCookieFinder {
	void findMostActiveCookie(CommandLineInput cli) throws LogFileParsingException;

}
