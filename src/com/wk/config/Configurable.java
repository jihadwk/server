package com.wk.config;

public interface Configurable {
	/**
	 * 根据键值得到配置的属性值
	 * @param key
	 * @return
	 */
	 public String getConfig(String key);

	 /**
	  * 以键值对的方式输出配置字符串
	  * @return
	  */
	 public String toString();
}
