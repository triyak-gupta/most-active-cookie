package com.qc.cookiefinder.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.qc.cookiefinder.*"})
public class MostActiveCookieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MostActiveCookieApplication.class, args);
	}

}
