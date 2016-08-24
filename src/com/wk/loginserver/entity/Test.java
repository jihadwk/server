package com.wk.loginserver.entity;

public class Test {
	private String test;
	private String appId;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public String toString() {
		return "Test [test=" + test + ", appId=" + appId + "]";
	}
}
