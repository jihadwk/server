package com.wk.loginserver.manager;

import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 单例类，管理应用的全局数据
 * @author wukai
 *
 */
public class Application {
	private final Logger logger = LoggerFactory.getLogger(Application.class);
	private static Application instance;
	private IoAcceptor acceptor;
	private NioSocketConnector connector;
	private long appId;
	//原子性操作
	public static synchronized Application getInstance(){
		if (instance==null) {
			instance = new Application();
		}
		return instance;
	}
	//存入appId
	public void bindAppId(long appId) {
		this.appId = appId;
	}
	//返回会话map表
	public Map<Long, IoSession> getSessionMap() {
		return this.acceptor.getManagedSessions();
	}
	//返回会话数量
	public int getSessionCount() {
		if(this.acceptor==null){
			
			logger.error("accepter is null .........");
			logger.error("appId: ........."+this.appId);
		}
		return this.acceptor.getManagedSessionCount();
	}
	//存入acceptor
	public void bindAcceptor(IoAcceptor acceptor) {
		this.acceptor = acceptor;
	}
	public IoAcceptor getAcceptor() {
		return this.acceptor;
	}

	public long getAppId() {
		return this.appId;
	}
	public void bindConnector(NioSocketConnector connector) {
        this.connector = connector;
	}
}
