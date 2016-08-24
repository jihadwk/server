package com.wk.loginserver.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Service {
	@Autowired
	private Test test;
	public void print(){
		System.out.println(test.toString());
	}
}
