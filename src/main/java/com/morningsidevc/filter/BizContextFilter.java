/**
 * Project: avatar-biz
 * 
 * File Created at 2010-8-30
 * $Id$
 * 
 * Copyright 2010 dianping.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.morningsidevc.filter;

import com.morningsidevc.utils.Constants;
import com.morningsidevc.utils.EncryptionUtils;
import com.morningsidevc.utils.LoginUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * User: liang.liu
 *
 */
public class BizContextFilter implements Filter {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		// 解析用户cookie[dper]
		setUserInfo(request, response);

		chain.doFilter(request, response);
	}

	/**
	 * set user id in cookie
	 * 
	 * @param request
	 * @param response
	 * @throws java.io.IOException
	 */
	private void setUserInfo(ServletRequest request, ServletResponse response) throws IOException {
		String encryptUserInfoString = "";
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null) {
					if (Constants.USER_INFO_COOKIE_NAME.equals(cookie.getName())) {
						encryptUserInfoString = cookie.getValue();
						break;
					}
				}
			}
		}
		if (!StringUtils.isEmpty(encryptUserInfoString)) {
			try {
				String userInfoStr = EncryptionUtils.decrypt(encryptUserInfoString);
				String userId = LoginUtils.getUserId(userInfoStr);
				if (StringUtils.isNotBlank(userId)) {
					request.setAttribute(Constants.BIZ_CONTEXT_USER_ID, userId);
				} else {
					LoginUtils.signout((HttpServletRequest) request, (HttpServletResponse) response);
				}
			} catch (Exception e) {
				// may be invalid token
				LoginUtils.signout((HttpServletRequest) request, (HttpServletResponse) response);
			}
		}
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
