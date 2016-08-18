package com.wk.server.controller;

import org.apache.mina.core.session.IoSession;

import com.wk.server.action.IBaseAction;
import com.wk.server.message.DecodeMessage;

/**
 * 对Action进行分发的Controller，所有的分发器必须实现这个接口
 * 
 */
public interface ActionController {
	/**
	 * 根据Decoder的指令编号获得对应的Action
	 * 
	 * @param message
	 * @return
	 */
	public IBaseAction getAction(DecodeMessage message);

	/**
	 * 当客户端关闭的时候需会调用这个方法
	 * 
	 * @param session
	 */
	public void sessionClose(IoSession session);
	
	/**
	 * 创建链接的时候调用此方法
	 * @param session
	 */
	public void sessionOpen(IoSession session);
}
