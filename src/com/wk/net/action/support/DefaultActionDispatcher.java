package com.wk.net.action.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wk.net.action.Action;
import com.wk.net.action.ActionDispatcher;
import com.wk.net.action.ActionFactory;
import com.wk.net.action.Request;
import com.wk.net.action.Response;
import com.wk.net.filter.FilterChainFactory;
import com.wk.net.filter.support.DefaultFilterChain;
/**
 * 业务分发器实现
 * @author wukai
 *
 */
public class DefaultActionDispatcher
  implements ActionDispatcher
{
  private Logger logger = LoggerFactory.getLogger(getClass());
//  FilterChainFactory filterChainFactory = new DefaultFilterChainFactory();
  private FilterChainFactory filterChainFactory;
  ActionFactory actionFactory;

  public void dispatcher(Request request, Response response) throws Exception
  {
    try
    {
      Action action = this.actionFactory.createAction(request.getCommandId());
           if (action == null) {
    	         this.logger.error("create action fail!");
          }
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("create action success");
      }
      DefaultFilterChain filterChain = this.filterChainFactory.createApplicationFilterChain();
      filterChain.setAction(action);
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("begin do filter");
      }
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      this.logger.error("dispatcher exception", e);
    }
  }

  public ActionFactory getActionFactory() {
    return this.actionFactory;
  }

  public void setActionFactory(ActionFactory actionFactory) {
    this.actionFactory = actionFactory;
  }

  public FilterChainFactory getFilterChainFactory() {
    return this.filterChainFactory;
  }

  public void setFilterChainFactory(FilterChainFactory filterChainFactory) {
    this.filterChainFactory = filterChainFactory;
  }
}