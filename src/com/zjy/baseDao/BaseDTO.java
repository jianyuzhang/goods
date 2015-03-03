/**
 * 
 */
package com.zjy.baseDao;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hing<xingguang.ren@pactera.com>
 * @since 2014年4月19日
 */
public class BaseDTO implements Serializable {

	private static final long serialVersionUID = -1063188008194990237L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
