package com.wk.net.action;

public abstract interface Action
{
  public abstract String execute(Request paramRequest, Response paramResponse)
    throws Exception;
}

