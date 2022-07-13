package com.qc.cookiefinder;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qc.cookiefinder.exception.LogFileParsingException;
import com.qc.cookiefinder.finder.MostActiveCookieFinderImpl;
import com.qc.cookiefinder.requestparser.CommandLineInput;

@ExtendWith(SpringExtension.class)
public class MostActiveCookieTests {

	private MostActiveCookieFinderImpl mostActiveCookieFinder;
	private PrintStream out = System.out;
	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		mostActiveCookieFinder = new MostActiveCookieFinderImpl();
		PrintStream output = new PrintStream(byteArrayOutputStream);
		System.setOut(output);
	}

	@AfterEach
	public void tearDown() {
		System.setOut(out);
	}

	/** When file path and date are valid, output the most active cookie strings */
	@Test
	public void filterMostActiveCookies_ValidFilePathAndDate_OutputCookieStrings() throws LogFileParsingException {
		CommandLineInput commandInput = new CommandLineInput("cookie_log.csv", parse("2018-12-09"));
		mostActiveCookieFinder.findMostActiveCookie(commandInput);
		assertThat(byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
		assertThat(!byteArrayOutputStream.toString().contains("SAZuXPGUrfbcn5UA"));
		assertThat(!byteArrayOutputStream.toString().contains("5UAVanZf6UtGyKVS"));
		assertThat(!byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
	}

	/**
	 * When a specific date has more than one most active cookies, output all of
	 * them
	 */
	@Test
	public void filterMostActiveCookies_HasMoreThanOneMostActiveCookies_OutputAllOfThem()
			throws LogFileParsingException {
		CommandLineInput commandInput = new CommandLineInput("cookie_log.csv", parse("2018-12-06"));
		mostActiveCookieFinder.findMostActiveCookie(commandInput);
		assertThat(byteArrayOutputStream.toString().contains("8xYHIASHaBa79xzf"));
		assertThat(byteArrayOutputStream.toString().contains("1dSLJdsaDJLDsdSd"));
		assertThat(!byteArrayOutputStream.toString().contains("A8SADNasdNadBBda"));
	}

	/** When file path is invalid, throw exception */
	@Test
	public void filterMostActiveCookies_InvalidFilePath_ThrowException() {
		CommandLineInput commandInput = new CommandLineInput("src/	cookie_log.csv", parse("2018-12-09"));
		assertThatThrownBy(() -> mostActiveCookieFinder.findMostActiveCookie(commandInput))
				.isInstanceOf(LogFileParsingException.class);
	}

	/** When date is not exist, output is empty (no cookie) */
	@Test
	public void filterMostActiveCookies_InvalidDate_ThrowException() throws LogFileParsingException {
		CommandLineInput commandInput = new CommandLineInput("cookie_log.csv", parse("2021-12-09"));
		mostActiveCookieFinder.findMostActiveCookie(commandInput);
		assertThat(byteArrayOutputStream.toString().isEmpty());
	}

}
