package com.wk.server.filter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.wk.server.message.EncodeMessage;

public class MyProtocolCodecFactory extends DemuxingProtocolCodecFactory{
	public MyProtocolCodecFactory(){
		super.addMessageDecoder(MyMessageDecoder.class);
		super.addMessageEncoder(EncodeMessage.class, MyMessageEncoder.class);
	}
//  Class<?>它是个通配泛型，?可以代表任何类型
  public Set<Class<?>> getMessageTypes()
  {
    Set set = new HashSet();
    set.add(EncodeMessage.class);
//    返回指定set的不可修改视图,结果集不能再被修改
    return Collections.unmodifiableSet(set);
  }
}
