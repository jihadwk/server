package com.wk.net.filter;

import com.wk.net.filter.support.DefaultFilterChain;
/**
 * 过滤链创建工厂
 * @author wukai
 *
 */
public abstract interface FilterChainFactory
{
	/**
	 * 创建过滤链
	 * @return
	 */
  public abstract DefaultFilterChain createApplicationFilterChain();
}

