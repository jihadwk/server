package com.wk.server.action;

import org.apache.mina.core.session.IoSession;

import com.wk.server.message.DecodeMessage;

/**
 * 基本发送消息的Action
 */
public interface IBaseAction {

	/**
	 * 所有的响应必须实现的方法
	 * 
	 * @param message
	 */
	public void doAction(IoSession session, DecodeMessage message);

}