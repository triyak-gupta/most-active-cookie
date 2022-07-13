package com.qc.cookiefinder.requestparser;

import java.time.LocalDate;

/**
 * Class to parse cli inputs where -f : filename & -d : date
 */
public class CommandLineInput {
	private String fileName;
	private LocalDate date;

	public CommandLineInput(String fileName, LocalDate date) {
		this.fileName = fileName;
		this.date = date;
	}

	public CommandLineInput() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
