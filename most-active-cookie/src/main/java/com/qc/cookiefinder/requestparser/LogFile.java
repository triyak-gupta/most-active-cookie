package com.qc.cookiefinder.requestparser;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

/*
 * Model class to map cookie and timestamp from the log file
 */
public class LogFile {
	/**
	 * cookie name (Position 0)
	 */
	@CsvBindByPosition(position = 0)
	private String cookie;
	/**
	 * time stamp (Position 1)
	 */
	@CsvBindByPosition(position = 1)
	@CsvDate(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private LocalDateTime timestamp;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/** Cookie log entry as a string */
	@Override
	public String toString() {
		return "Cookie Log Entry: cookie=" + cookie + ", timestamp=" + timestamp;
	}
}
