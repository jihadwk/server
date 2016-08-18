package com.wk.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wk.server.filter.MyProtocolCodecFactory;
import com.wk.server.handler.MyHandler;

public class ServerStart {
	private static Logger logger = LoggerFactory.getLogger(ServerStart.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetSocketAddress address = new InetSocketAddress(10000);
			IoAcceptor ioAcceptor = new NioSocketAcceptor();
			ioAcceptor.getSessionConfig().setReadBufferSize(2048);
//		ioAcceptor.getSessionConfig().setIdleTime(arg0, arg1);
			ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyProtocolCodecFactory()));
			ioAcceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
			ioAcceptor.setHandler(new MyHandler());
			ioAcceptor.bind(address);
			logger.error("启动成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("启动失败");
			e.printStackTrace();
		}
	}

}
