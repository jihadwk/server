package com.wk.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseConfig implements Configurable {
	private Logger logger = LoggerFactory.getLogger(BaseConfig.class);
	//属性对象
	protected Properties prop;
	//	配置文件对象
	private File configFile;
	//	上次配置改变时间
	private long lastModifyTime;
	//文件名
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void init(){
		load();
	}
	//加载配置文件
	public void load(){
		try {
			this.prop = new Properties();
			this.configFile = new File(System.getProperty("user.dir")+this.fileName);
			this.prop.load(new FileInputStream(this.configFile));
			this.lastModifyTime = this.configFile.lastModified();
		} catch (IOException e) {
			// TODO: handle exception
			logger.error("load properties config file error",e);
		}
	}
	@Override
	public String getConfig(String key) {
		// TODO Auto-generated method stub
		if ((this.configFile==null)||(this.prop==null)) {
			init();
		}
		if (hasModified()) {
			if (logger.isDebugEnabled()) {
				logger.debug("reload config file for lasttime=" + this.lastModifyTime + " and new time=" + this.configFile.lastModified());
			}
			load();
		}
		return this.prop.getProperty(key);
	}
	public boolean hasModified() {
		return this.configFile.lastModified() > this.lastModifyTime;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer = new StringBuffer();
		String key;
		String value;
		for(Enumeration e = this.prop.keys();e.hasMoreElements();stringBuffer.append("{"+key+"="+value+"}")){
			key = (String) e.nextElement();
			value = (String) this.prop.get(key);
		}
		return stringBuffer.toString();
	} 
}
