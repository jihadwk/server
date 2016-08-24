package com.wk.net.action;
/**
 * 业务分发器
 * @author wukai
 *
 */
public abstract interface ActionDispatcher
{
  public abstract void dispatcher(Request paramRequest, Response paramResponse)throws Exception;
}
