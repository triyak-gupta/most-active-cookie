package com.qc.cookiefinder.executor;

import static com.qc.cookiefinder.executor.ConsoleExecutorStatus.FAILED;
import static com.qc.cookiefinder.executor.ConsoleExecutorStatus.SUCCESS;
import static com.qc.cookiefinder.requestparser.LogFileParser.parseCommandInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qc.cookiefinder.exception.LogFileParsingException;
import com.qc.cookiefinder.finder.MostActiveCookieFinder;
import com.qc.cookiefinder.requestparser.CommandLineInput;

/*
 * Implementation of the cli executor
 */
public class MostActiveCookieExecutorImpl implements MostActiveCookieExecutor {
	private static final Logger LOGGER = LoggerFactory.getLogger(MostActiveCookieExecutorImpl.class);
	private MostActiveCookieFinder cookieFinder;

	public MostActiveCookieExecutorImpl(MostActiveCookieFinder mostActiveCookieFinder) {
		this.cookieFinder = mostActiveCookieFinder;
	}

	@Override
	public int executeProcess(String[] args) {
		try {
			CommandLineInput commandInput = parseCommandInput(args);
			cookieFinder.findMostActiveCookie(commandInput);
			return SUCCESS.getValue();
		} catch (LogFileParsingException | RuntimeException e) {
			LOGGER.error("Program failed!", e);
		}
		return FAILED.getValue();
	}

}
