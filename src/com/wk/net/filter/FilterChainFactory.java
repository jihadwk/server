package com.wk.net.filter;

import com.wk.net.filter.support.DefaultFilterChain;

public abstract interface FilterChainFactory
{
  public abstract DefaultFilterChain createApplicationFilterChain();
}

