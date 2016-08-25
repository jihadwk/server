package com.wk.net.filter.support;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wk.net.action.Request;
import com.wk.net.action.Response;
import com.wk.net.filter.Filter;
import com.wk.net.filter.FilterChain;
/**
 * action执行之前的操作，这个filter好像没什么用
 * @author wukai
 *
 */
public class CommandCollectFilter
  implements Filter
{
  private Logger logger = LoggerFactory.getLogger(getClass());
  private String destinationName;

  public String getDestinationName()
  {
    return this.destinationName;
  }

  public void setDestinationName(String destinationName) {
    this.destinationName = destinationName;
  }

  public void init() {
  }

  public void doFilter(Request request, Response response, FilterChain chain) throws Exception {
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("Message:" + request.getMessage().toStringUtf8());
      this.logger.debug("REQUEST START:sessionId=" + request.getSession().getId() + ", sequence=" + 
        request.getSequence());
    }
    IoSession session = request.getSession();
    CommandNotify notify = new CommandNotify();
    notify.setSessionId(String.valueOf(request.getSession().getId()));
    notify.setAppId((String)session.getAttribute("appid"));
    notify.setUserId((String)session.getAttribute("userid"));
    notify.setRefId((String)session.getAttribute("refid"));
    notify.setMobile((String)session.getAttribute("mobile"));
    notify.setReleaseFlag((String)session.getAttribute("releaseflag"));
    notify.setVersion((String)session.getAttribute("medletversion"));
    notify.setUa((String)session.getAttribute("ua"));
    notify.setChannelKey((String)session.getAttribute("rinfo"));
    notify.setFid((String)session.getAttribute("fid"));
    notify.setMessage(request.getMessage());
    
    chain.doFilter(request, response);
    
    if (this.logger.isDebugEnabled())
      this.logger.debug("RESPONSE FINISHED:sessionId=" + request.getSession().getId() + ", sequence=" + 
        request.getSequence());
  }
//     public void doFilter(Session session, FilterChain filterchain)
//       throws Exception
//     {
//       filterchain.doFilter(session);
//     }
  public void destroy()
  {
  }
}