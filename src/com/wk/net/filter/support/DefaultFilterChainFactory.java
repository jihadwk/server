package com.wk.net.filter.support;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.wk.net.filter.Filter;
import com.wk.net.filter.FilterChainFactory;

public class DefaultFilterChainFactory
  implements FilterChainFactory
{
  private List<Filter> filterList;

  public DefaultFilterChainFactory()
  {
    this.filterList = new LinkedList<Filter>();
  }

  public List<Filter> getFilterList() {
    return this.filterList;
  }

  public void setFilterList(List<Filter> filterList) {
    this.filterList = filterList;
  }

  public synchronized DefaultFilterChain createApplicationFilterChain() {
    DefaultFilterChain chain = new DefaultFilterChain();
    Iterator<Filter> it = this.filterList.iterator();
    while (it.hasNext()) {
      Filter filter = (Filter)it.next();
      chain.addFilter(filter);
    }
    return chain;
  }
}