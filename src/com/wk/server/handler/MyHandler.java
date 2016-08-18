package com.wk.server.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.wk.server.Config;

public class MyHandler extends IoHandlerAdapter{
	@Override
	public void sessionCreated(IoSession session) {
//		if (this.logger.isDebugEnabled())
//			this.logger.debug("创建会话!");
	}

	@Override
	public void sessionOpened(IoSession session) {
//		if (this.logger.isDebugEnabled())
//			this.logger.debug("打开会话!");
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE,
				Config.IDLE_TIME);
	}

	@Override
	public void sessionClosed(IoSession session) {
//		if (this.logger.isDebugEnabled())
//			this.logger.debug("关闭会话!");
//		if (actionCntroller != null)
//			actionCntroller.sessionClose(session);
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
//		if (this.logger.isDebugEnabled())
//			this.logger.debug("接收到的消息: " + message.toString());
//		actionCntroller.doAction(session, (IoBuffer) message);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
//		if (this.logger.isDebugEnabled())
//			this.logger.debug("Idle 状态:" + status.toString());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
//		this.logger.error("会话发生异常！" + session.getRemoteAddress(), cause);
	}
}
