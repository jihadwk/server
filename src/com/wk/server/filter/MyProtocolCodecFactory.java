package com.wk.server.filter;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.wk.server.message.EncodeMessage;

public class MyProtocolCodecFactory extends DemuxingProtocolCodecFactory{
	public MyProtocolCodecFactory(){
		super.addMessageDecoder(MyMessageDecoder.class);
		super.addMessageEncoder(EncodeMessage.class, MyMessageEncoder.class);
	}
}
