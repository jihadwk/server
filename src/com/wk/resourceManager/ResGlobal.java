package com.wk.resourceManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResGlobal {
	private static Logger log = LoggerFactory.getLogger(ResGlobal.class);
	private static ResGlobal instance;
	public static synchronized ResGlobal getInstance() {
		if (instance == null) {
			instance = new ResGlobal();
			instance.init();
		}
		return instance;
	}
	
	public void init(){
		log.info("初始化基础信息 开始................");
	}
}
