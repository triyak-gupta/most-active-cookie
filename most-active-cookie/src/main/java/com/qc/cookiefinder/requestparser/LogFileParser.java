package com.qc.cookiefinder.requestparser;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Paths.get;
import static java.time.LocalDate.parse;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBeanBuilder;
import com.qc.cookiefinder.exception.LogFileParsingException;

/*
 * 
 */
public class LogFileParser {
	private static final Logger LOG = LoggerFactory.getLogger(LogFileParser.class);

	public static List<LogFile> parseLogFile(String fileName) throws LogFileParsingException {
		try {
			return new CsvToBeanBuilder<LogFile>(newBufferedReader(get(fileName))).withType(LogFile.class)
					.withFilter(line -> !line[0].contains("cookie")).build().parse();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new LogFileParsingException(e);
		}
	}

	/** Parsing command line input */
	public static CommandLineInput parseCommandInput(String[] args) throws LogFileParsingException {
		CommandLineInput commandLineInput = new CommandLineInput();
		CommandLineParser commandLineParser = new DefaultParser();
		Options options = parseCommandOption();

		try {
			CommandLine commandLine = commandLineParser.parse(options, args);
			commandLineInput.setFileName(commandLine.getOptionValue("file"));
			commandLineInput.setDate(parse(commandLine.getOptionValue("date")));
			return commandLineInput;
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			outputCommandHelp(options);
			throw new LogFileParsingException(e);
		}
	}

	/** Parsing command line options (file name and selected date) */
	public static Options parseCommandOption() {
		Options commandOptions = new Options();
		// File name of cookie log
		Option fileName = new Option("f", "file", true, "The path of cookie log file");
		fileName.setRequired(true);
		commandOptions.addOption(fileName);
		// Selected date to get the most active cookie
		Option selectedDate = new Option("d", "date", true, "The specific date to get most active cookie");
		selectedDate.setRequired(true);
		commandOptions.addOption(selectedDate);
		return commandOptions;
	}

	/** Help message for command line options */
	private static void outputCommandHelp(Options options) {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("Cookie Log Filter", options);
	}
}
