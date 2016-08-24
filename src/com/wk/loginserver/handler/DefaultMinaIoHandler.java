 package com.wk.loginserver.handler;
 
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.commonSocket.net.IMessage;
import com.wk.net.IoMode;
import com.wk.net.action.ActionDispatcher;
import com.wk.net.action.Request;
import com.wk.net.action.Response;
import com.wk.net.action.support.DefaultRequest;
import com.wk.net.action.support.DefaultResponse;

 /**
  * iohandler 处理器，解码后执行
  * @author wukai
  *
  */
 public class DefaultMinaIoHandler extends IoHandlerAdapter
 {
   private Logger logger = LoggerFactory.getLogger(getClass());
   private ActionDispatcher actionDispatcher;
//   private NoticeDispatcher noticeDispatcher;
//   private OnlineManage onlineManage;
   public void sessionOpened(IoSession ioSession)
     throws Exception
   {
     if (this.logger.isDebugEnabled())
       this.logger.debug("sessionOpened:" + ioSession.getService().getManagedSessions());
   }
 
   public void sessionClosed(IoSession ioSession)
     throws Exception
   {
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("sessionClosed<" + ioSession.getRemoteAddress() + ">");
     }
     Object key = ioSession.getAttribute("roleId");
//     if (key != null)
//       this.onlineManage.removeOnlineRole(key.toString());
   }
 
   public void sessionIdle(IoSession ioSession, IdleStatus idlestatus)
     throws Exception
   {
//     this.noticeDispatcher.circular(new DefaultSession(ioSession, idlestatus));
   }
 
   public void exceptionCaught(IoSession ioSession, Throwable cause)
     throws Exception
   {
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("exceptionCaught<" + ioSession.getRemoteAddress() + ">" + cause);
     }
     ioSession.closeOnFlush();
   }
   //接受到消息，根据指令等参数 通过actionDispatcher来分发业务
   public void messageReceived(IoSession ioSession, Object message) throws Exception
   {
     IoMode ioMode = null;
     try {
       Boolean isHttp = (Boolean)ioSession.getAttribute("http");
       if (isHttp.booleanValue())
         ioMode = IoMode.HTTP_CONNECT;
       else {
         ioMode = IoMode.LONG_CONNECT;
       }
 
       IMessage.PNPCMessage pMessage = (IMessage.PNPCMessage)message;
       IMessage.Head head = IMessage.Head.parseFrom(pMessage.getMsgHead());
       if (this.logger.isDebugEnabled()) {
         this.logger.debug("ioMode=" + ioMode);
         this.logger.debug("messageReceived:head\n" + head + ">");
       }
       Request request = new DefaultRequest(ioSession, head.getCommandId(), head.getSequence(), pMessage.getMsgBody(), ioMode);
       Response response = new DefaultResponse(ioSession);
       this.actionDispatcher.dispatcher(request, response);
     } catch (Exception e) {
       this.logger.error("Message Receive exception", e);
       ioSession.closeOnFlush();
     }
   }
 
   public void messageSent(IoSession ioSession, Object message)
     throws Exception
   {
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("respMessage:" + message);
     }
     Boolean isHttp = null;
     try {
       isHttp = (Boolean)ioSession.getAttribute("http");
     } finally {
       if (isHttp == null) {
         return;
       }
       if (!isHttp.booleanValue()) {
         return;
       }
       ioSession.closeOnFlush();
     }
   }
 
//   public ActionDispatcher getActionDispatcher()
//   {
//     return this.actionDispatcher;
//   }
// 
//   public void setActionDispatcher(ActionDispatcher actionDispatcher) {
//     this.actionDispatcher = actionDispatcher;
//   }
// 
//   public NoticeDispatcher getNoticeDispatcher() {
//    return this.noticeDispatcher;
//   }
// 
//   public void setNoticeDispatcher(NoticeDispatcher noticeDispatcher) {
//     this.noticeDispatcher = noticeDispatcher;
//   }
// 
//   public OnlineManage getOnlineManage() {
//     return this.onlineManage;
//   }
// 
//   public void setOnlineManage(OnlineManage onlineManage) {
//     this.onlineManage = onlineManage;
//   }
 }
