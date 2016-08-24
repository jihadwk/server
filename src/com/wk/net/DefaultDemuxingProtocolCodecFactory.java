package com.wk.net;
import com.google.protobuf.Message;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
/**
 * 协议编解码工厂（多路复用）
 * @author wukai
 *
 */
public class DefaultDemuxingProtocolCodecFactory extends DemuxingProtocolCodecFactory
{
  public DefaultDemuxingProtocolCodecFactory()
  {
	  //解码器
    super.addMessageDecoder(DefaultMessageDecoder.class);
    //编码器
    super.addMessageEncoder(getMessageTypes(), DefaultMessageEncoder.class);
  }
//  Class<?>它是个通配泛型，?可以代表任何类型
  public Set<Class<?>> getMessageTypes()
  {
    Set set = new HashSet();
    set.add(Message.class);
//    返回指定set的不可修改视图,结果集不能再被修改
    return Collections.unmodifiableSet(set);
  }
}