package com.wk.net.action.support;

import org.apache.mina.core.session.IoSession;

import com.wk.net.action.Response;

public class DefaultResponse
  implements Response
{
  private IoSession ioSession;

  public DefaultResponse(IoSession ioSession)
  {
    this.ioSession = ioSession;
  }

  public void write(Object message) {
    this.ioSession.write(message);
  }

public IoSession getIoSession() {
	return ioSession;
}

public void setIoSession(IoSession ioSession) {
	this.ioSession = ioSession;
}
  
}