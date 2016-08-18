package com.wk.server.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.wk.server.message.EncodeMessage;

public class MyMessageEncoder<T extends EncodeMessage> implements MessageEncoder<T> {

	@Override
	public void encode(IoSession session, T message, ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		out.write(message.toBuffer());
	}

}
