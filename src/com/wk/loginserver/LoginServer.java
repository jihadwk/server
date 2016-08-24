package com.wk.loginserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wk.loginserver.entity.Service;
import com.wk.loginserver.entity.Test;

public class LoginServer {
	
	public static void main(String[] args){
		ApplicationContext context = new FileSystemXmlApplicationContext("resources/config/serverCfg/spring-application.xml");
//		Test test = (Test) context.getBean("test");
		Service service = (Service) context.getBean("service");
		service.print();
	}
}
