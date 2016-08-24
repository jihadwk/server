package com.wk.net.filter;

import com.wk.net.action.Request;
import com.wk.net.action.Response;

/**
 * @author denny zhao
 *
 */
public abstract interface Filter
{
  public abstract void init();
  public abstract void doFilter(Request paramRequest, Response paramResponse, FilterChain paramFilterChain) throws Exception;
//  public abstract void doFilter(Session paramSession, FilterChain paramFilterChain) throws Exception;
  public abstract void destroy();
}

