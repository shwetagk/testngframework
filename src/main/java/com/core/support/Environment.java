package com.core.support;

public enum Environment {
	
	WINDOWS("Windows 10"),
	IOS(),
	ANDROID("Android 10.0");
	
	private final String[] env;
	Environment(String ...env) {
		this.env = env;
	}
}
