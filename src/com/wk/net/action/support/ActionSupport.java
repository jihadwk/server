package com.wk.net.action.support;

import com.wk.net.action.Action;
import com.wk.net.action.Request;
import com.wk.net.action.Response;

public abstract class ActionSupport
  implements Action
{
  public abstract String execute(Request paramRequest, Response paramResponse)
    throws Exception;
}

