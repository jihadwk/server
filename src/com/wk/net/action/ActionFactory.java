package com.wk.net.action;

public abstract interface ActionFactory
{
  public abstract Action createAction(int paramInt)throws Exception;
}

