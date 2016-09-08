package com.wk.exception;

public class CacheDaoException extends RuntimeException{
	private static final long serialVersionUID = 1636065759470999050L;

	public CacheDaoException() {
	}

	/**
	 * @param msg
	 */
	public CacheDaoException(String msg) {
		super(msg);
	}

	/**
	 * @param cause
	 */
	public CacheDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public CacheDaoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
