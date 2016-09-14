package com.wk.loginserver.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bw.application.message.RegisterChannel.RegisterChannelRequest;
import com.wk.loginserver.service.channel.ChannelManager;
import com.wk.net.action.Action;
import com.wk.net.action.Request;
import com.wk.net.action.Response;
@Controller("registerChannelAction")
public class RegisterChannelAction implements Action {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private ChannelManager channelManager;


	public ChannelManager getChannelManager() {
		return channelManager;
	}

	public void setChannelManager(ChannelManager channelManager) {
		this.channelManager = channelManager;
	}

	@Override
	public String execute(Request paramRequest, Response paramResponse)
			throws Exception {
		
		RegisterChannelRequest reqMsg = RegisterChannelRequest.parseFrom(paramRequest.getMessage());
		channelManager.registerChannelActive(reqMsg.getAppId(), reqMsg.getRoleCount());
		
		
		
		logger.debug(reqMsg);
		return null;
	}

}
