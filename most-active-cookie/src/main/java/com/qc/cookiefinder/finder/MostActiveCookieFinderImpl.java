package com.qc.cookiefinder.finder;

import static com.qc.cookiefinder.requestparser.LogFileParser.parseLogFile;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

import com.qc.cookiefinder.exception.LogFileParsingException;
import com.qc.cookiefinder.requestparser.CommandLineInput;
import com.qc.cookiefinder.requestparser.LogFile;

/*
 * Implementation of the most active cookie finder interface
 */
public class MostActiveCookieFinderImpl implements MostActiveCookieFinder {

	@Override
	public void findMostActiveCookie(CommandLineInput cli) throws LogFileParsingException {
		// Creating a list of cookie details in the log file
		List<LogFile> cookieDetailList = parseLogFile(cli.getFileName());
		// Grouping the frequency of cookies in the log file by timestamp
		Map<String, Long> cookieFreqByDateMap = groupCookiesByDate(cookieDetailList, cli.getDate());
		// Calculate the frequency of most active cookies
		OptionalLong mostActiveCookieFreq = mostActiveCookieFreq(cookieFreqByDateMap);
		// Scan through the list of cookies and output the ones which have the max
		// frequency value
		mostActiveCookieFreq.ifPresent(maxFreq -> outputMostActiveCookies(cookieFreqByDateMap, maxFreq));
	}

	/**
	 * Use HashMap to group cookie entries, <key, value> = <cookie date, number of
	 * occurrence>
	 */
	private Map<String, Long> groupCookiesByDate(List<LogFile> cookieDetailList, LocalDate date) {
		return cookieDetailList.stream().filter(x -> date.isEqual(x.getTimestamp().toLocalDate()))
				.collect(groupingBy(LogFile::getCookie, counting()));
	}

	/**
	 * Return the frequency of most active cookies, which is the max values of all
	 * the occurrences
	 */
	private OptionalLong mostActiveCookieFreq(Map<String, Long> cookieFreqByDateMap) {
		return cookieFreqByDateMap.values().stream().mapToLong(count -> count).max();
	}

	/**
	 * Output the most active cookies to the terminal
	 */
	private void outputMostActiveCookies(Map<String, Long> cookieFreqByDateMap, long maxFreq) {
		cookieFreqByDateMap.entrySet().stream().filter(x -> x.getValue().equals(maxFreq)).map(Map.Entry::getKey)
				.forEach(System.out::println);
	}
}
