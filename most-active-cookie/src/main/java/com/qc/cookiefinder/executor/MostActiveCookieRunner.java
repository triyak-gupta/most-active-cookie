package com.qc.cookiefinder.executor;

import static java.lang.System.exit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class MostActiveCookieRunner implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(MostActiveCookieRunner.class);
	private ApplicationContext applicationContext;
	private MostActiveCookieExecutor processExecutor;

	public MostActiveCookieRunner(ApplicationContext applicationContext, MostActiveCookieExecutor processExecutor) {
		this.applicationContext = applicationContext;
		this.processExecutor = processExecutor;
	}

	@Override
	public void run(String[] args) {
		LOG.info("Most active cookie finder started!");
		terminate(() -> processExecutor.executeProcess(args));
	}

	private void terminate(ExitCodeGenerator exitCodeGenerator) {
		exit(SpringApplication.exit(applicationContext, exitCodeGenerator));
	}
}
