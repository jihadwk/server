package com.wk.net.action;

import com.google.protobuf.ByteString;
import com.wk.net.IoMode;

import org.apache.mina.core.session.IoSession;
/**
 * 来自客户端的请求的封装
 * @author wukai
 *
 */
public abstract interface Request
{
  public abstract IoSession getSession();

  public abstract void setSession(IoSession paramIoSession);

  public abstract int getCommandId();

  public abstract void setCommandId(int paramInt);

  public abstract ByteString getMessage();

  public abstract long getSequence();

  public abstract void setSequence(long paramLong);

  public abstract IoMode getIoMode();

  public abstract void setIoMode(IoMode paramIoMode);

  public abstract Object getAttribute(String paramString);

  public abstract void setAttribute(String paramString, Object paramObject);

  public abstract Object removeAttribute(String paramString);
}

