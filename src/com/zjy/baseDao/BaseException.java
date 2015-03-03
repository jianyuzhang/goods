/**
 * 
 */
package com.zjy.baseDao;

/**
 * @author Hing<xingguang.ren@pactera.com>
 * @since 2014年4月19日
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -1777775219273982174L;

	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
