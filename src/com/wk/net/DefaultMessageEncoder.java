package com.wk.net;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.commonSocket.net.IMessage;
import com.google.protobuf.Message;
import com.wk.util.DefaultMessageParser;
import com.wk.util.PnpcAdler32;
/**
 * 编码器：将message对象转为二进制数据流
 * @author wukai
 *
 */
public class DefaultMessageEncoder implements MessageEncoder<Object>
{
	//编码message ，message只需要具有：可以写入数据，可以转为字节数组 生成数据块
	//自己写的message数据块 和 protobuf生成的数据块 类似
  public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
    throws Exception
  {
//    Message body = (Message)message;
//    int commandId = ((Integer)DefaultMessageParser.getInstance().getCommandMap().get(body.getDescriptorForType().getName())).intValue();
////    IMessage.Head head = IMessage.Head.newBuilder().setCommandId(commandId).setSequence(1L).setCheckSum(PnpcAdler32.adler32(body.toByteArray())).build();
//    IMessage.Head head = IMessage.Head.newBuilder().setCommandId(commandId).setSequence(1L).setCheckSum(body.toByteArray().length).build();
//    IMessage.PNPCMessage msg = IMessage.PNPCMessage.newBuilder().setMsgHead(head.toByteString()).setMsgBody(body.toByteString()).build();
//    IoBuffer buf = IoBuffer.allocate(256);
//    buf.setAutoExpand(true);
//    //可以不要长度
////    buf.putInt(msg.toByteArray().length);
//    buf.put(msg.toByteArray());
//    buf.flip();
//    out.write(buf);
    
    //111
         Message body = (Message)message;
         int commandId = ((Integer)DefaultMessageParser.getInstance().getCommandMap().get(body.getDescriptorForType().getName())).intValue();
         IMessage.Head head = IMessage.Head.newBuilder().setCommandId(commandId).setSequence(1L).setCheckSum(PnpcAdler32.adler32(body.toByteArray())).build();
         IMessage.PNPCMessage msg = IMessage.PNPCMessage.newBuilder().setMsgHead(head.toByteString()).setMsgBody(body.toByteString()).build();
         IoBuffer buf = IoBuffer.allocate(256);
         buf.setAutoExpand(true);
         System.out.println("下发时的数据长度:"+msg.toByteArray().length);
         buf.putInt(msg.toByteArray().length);
         buf.put(msg.toByteArray());
         buf.flip();
         out.write(buf);
  }
}