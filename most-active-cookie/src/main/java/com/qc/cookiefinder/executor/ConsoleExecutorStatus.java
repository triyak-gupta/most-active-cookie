package com.qc.cookiefinder.executor;

public enum ConsoleExecutorStatus {
	SUCCESS(0), FAILED(1);

	private final int value;

	ConsoleExecutorStatus(int value) {
	    this.value = value;
	  }

	public int getValue() {
		return value;
	}
}
