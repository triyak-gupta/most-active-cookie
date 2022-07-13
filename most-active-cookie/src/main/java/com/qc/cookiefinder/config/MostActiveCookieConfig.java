package com.qc.cookiefinder.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qc.cookiefinder.executor.MostActiveCookieExecutor;
import com.qc.cookiefinder.executor.MostActiveCookieExecutorImpl;
import com.qc.cookiefinder.executor.MostActiveCookieRunner;
import com.qc.cookiefinder.finder.MostActiveCookieFinder;
import com.qc.cookiefinder.finder.MostActiveCookieFinderImpl;

@Configuration
public class MostActiveCookieConfig {

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext,
			MostActiveCookieExecutor mostActiveCookieExecutor) {
		return new MostActiveCookieRunner(applicationContext, mostActiveCookieExecutor);
	}

	@Bean
	public MostActiveCookieFinder mostActiveCookieFinder() {
		return new MostActiveCookieFinderImpl();
	}

	@Bean
	public MostActiveCookieExecutor mostActiveCookieExecutor(MostActiveCookieFinder mostActiveCookieFinder) {
		return new MostActiveCookieExecutorImpl(mostActiveCookieFinder);
	}
}
