package com.wk.net.filter.support;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wk.net.action.Action;
import com.wk.net.action.Request;
import com.wk.net.action.Response;
import com.wk.net.filter.Filter;
import com.wk.net.filter.FilterChain;
/**
 * 过滤链实现，拥有一个链式集合
 * 增加filter ，获取，处理
 * @author wukai
 *
 */
public class DefaultFilterChain implements FilterChain {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<Filter> filters = new LinkedList<Filter>();
	private Action action;
//	private Notice notice;
	private Iterator<Filter> it = null;

	public void doFilter(Request request, Response response) throws Exception {
		if (hasMoreFilter())
			getFilter().doFilter(request, response, this);
		else if (this.action != null)
			this.action.execute(request, response);
		else
			this.logger.warn("Action is null,instruction is not executed!" + request.getMessage().toString());
	}

//	public void doFilter(Session session) throws Exception {
//		if (this.notice != null) {
//			if (session.isIdleEvent(IdleStatus.READER_IDLE))
//				this.notice.readerIdleEvent(session);
//			else if (session.isIdleEvent(IdleStatus.WRITER_IDLE))
//				this.notice.writerIdleEvent(session);
//			else if (session.isIdleEvent(IdleStatus.BOTH_IDLE))
//				this.notice.bothIdleEvent(session);
//		} else
//			this.logger.warn("Notice is null,instruction is not executed!" + session.getMessage().toString());
//	}

	private boolean hasMoreFilter() {
		if (this.it == null) {
			this.it = this.filters.iterator();
		}
		return this.it.hasNext();
	}

	private Filter getFilter() {
		if (this.it == null) {
			this.it = this.filters.iterator();
		}
		return (Filter) this.it.next();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return this.action;
	}

//	public void setNotice(Notice notice) {
//		this.notice = notice;
//	}
//
//	public Notice getNotice() {
//		return null;
//	}

	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}
}
