package com.wk.loginserver.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bw.application.message.AreaList.AreaListRequest;
import com.bw.application.message.AreaList.AreaListResponse;
import com.bw.baseJar.vo.BwAreaVO;
import com.wk.loginserver.service.channel.ChannelManager;
import com.wk.net.action.Action;
import com.wk.net.action.Request;
import com.wk.net.action.Response;

/**
 * @author denny zhao
 *
 */
@Controller("areaListAction")
public class AreaListAction implements Action {
	@Autowired
	private ChannelManager channelManager;
	@Override
	public String execute(Request paramRequest, Response paramResponse)
			throws Exception {
		AreaListRequest request=AreaListRequest.parseFrom(paramRequest.getMessage());
		AreaListResponse.Builder builder=AreaListResponse.newBuilder();
		try {
			 ConcurrentHashMap<Long, BwAreaVO> bwAreaVOMap= channelManager.getBwAreaVOMap();
			    //默认下发所有的分区信息
			 Collection<BwAreaVO> c=bwAreaVOMap.values();
			 Iterator<BwAreaVO> i=c.iterator();
			 while(i.hasNext()){
				 AreaListResponse.AreaData.Builder ad=AreaListResponse.AreaData.newBuilder();
				 BwAreaVO bv=i.next();
				 ad.setAreaId((int)bv.getAreaId());
				 ad.setAreaName(bv.getAreaName());
				 builder.addAreaDataList(ad.build());
			 }
			builder.setResult(0);
		} catch (Exception e) {
			e.printStackTrace();
			builder.setResult(1);
			builder.setInfo(e.getMessage());
		} finally {
			
			paramResponse.write(builder.build());
		}
		return null;
	}
	public ChannelManager getchannelManager() {
		return channelManager;
	}
	public void setchannelManager(ChannelManager channelManager) {
		this.channelManager = channelManager;
	}
	

}
