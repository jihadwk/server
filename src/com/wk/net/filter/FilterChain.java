package com.wk.net.filter;

import com.wk.net.action.Action;
import com.wk.net.action.Request;
import com.wk.net.action.Response;

public abstract interface FilterChain
{
  public abstract void doFilter(Request paramRequest, Response paramResponse)throws Exception;
//  public abstract void doFilter(Session paramSession)throws Exception;
  public abstract Action getAction();
//  public abstract Notice getNotice();
}
