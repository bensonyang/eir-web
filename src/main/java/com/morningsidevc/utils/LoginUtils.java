/**
 * Project: avatar-biz
 * 
 * File Created at 2011-9-13
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.morningsidevc.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 登录相关工具类
 * 
 * @author liang.liu
 * 
 */
public class LoginUtils {

    private final Logger LOGGER = Logger.getLogger(getClass());

    /**
	 * 等号
	 */
	private static final String EQUAL = "=";

	/**
	 * 分号
	 */
	private static final String SEMICOLON = ";";

	/**
	 * 登录帐号cookie[ua]名称
	 */
	private static final String COOKIE_USER_ACCOUNT = "ua";

	/**
	 * 是否已记录登录日志cookie[ll]名称
	 */
	private static final String COOKIE_LOGIN_LOGED = "ll";

	/**
	 * 一年的总秒数
	 */
	private static final int SECONDS_OF_YEAR = 31536000;

	private static final String UNKNOWN = "unknown";

	private static final String DEFAULT_IP = "0.0.0.0";

	/**
	 * 是否已记录登录日志cookie的默认值
	 */
	private static final String LOGIN_LOGED = "login_log_loged_";

	/**
	 * 登录辅助工具：完成cookie[dper]及request中BIZ_CONTEXT_USER_ID的添加
	 * 
	 * @param userId
	 * @param keepLogin
	 */
	public static void signon(int userId, boolean keepLogin, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(Constants.BIZ_CONTEXT_USER_ID, String.valueOf(userId));
		addSignonCookie(userId, keepLogin, response);
	}

	/**
	 * 退出辅助工具：完成cookie[dper]及requst中BIZ_CONTEXT_USER_ID的移除
	 */
	public static void signout(HttpServletRequest request, HttpServletResponse response) {
		// 从request中移除BIZ_CONTEXT_USER_ID
		if (request != null) {
			request.removeAttribute(Constants.BIZ_CONTEXT_USER_ID);
		}
		// 删除cookie[euer]
		addCookie(response, Constants.USER_INFO_COOKIE_NAME, "", 0);
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 *            响应
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie的值
	 * @param maxAge
	 *            过期时间(秒数)
	 */
	private static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(Constants.COOKIE_DOMAIN);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 获取用户IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserIP(HttpServletRequest request) {

        return "";
	}


	private static final char SPLIT = '|';// dper中使用的分隔符
	private static final int SECONDS_OF_DAY = 86400; // 一天的总秒数
	private static final int EXPIRE_DATE = 31;// 保持登录的天数

	private static void addSignonCookie(int userId, boolean keepLogin, HttpServletResponse response) {
		String euser = EncryptionUtils.encrypt(getEuserToken(userId, keepLogin));
		addEUserCookie(euser, keepLogin, response);
	}

	private static String getEuserToken(int userId, boolean keepLogin) {

		// 参数检查
		if (userId < 1) {
			return "";
		}

		// 计算过期时间
		Calendar expireTime = Calendar.getInstance();
		if (keepLogin) {
			// 保持登录时,　存储过期时间为31天(cookie过期时间也是31天)
			expireTime.add(Calendar.DAY_OF_MONTH, EXPIRE_DATE);
		} else {
			// 未保持登录时, dper中存储过期时间为8小时(cookie过期时间为会话cookie)
			expireTime.add(Calendar.HOUR_OF_DAY, 8);
		}

		// 组装dper token
		StringBuffer sb = new StringBuffer();
		sb.append(userId);
		sb.append(SPLIT);
		sb.append(expireTime.getTime().getTime() / 1000);
		sb.append(SPLIT);
		sb.append(keepLogin ? '1' : '0');

		return sb.toString();
	}
	
	private static void addEUserCookie(String euser, boolean keepLogin, HttpServletResponse response) {
		SimpleDateFormat cookieFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
		cookieFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		int maxAge = -1;// 默认为会话cookie
		if (keepLogin && !StringUtils.isBlank(euser)) {
			maxAge = EXPIRE_DATE * SECONDS_OF_DAY;
		}

		// 为给cookie[dper]添加HttpOnly属性，手动设置header[Set-Cookie]
		StringBuilder dperCookie = new StringBuilder();
		dperCookie.append(Constants.USER_INFO_COOKIE_NAME);
		dperCookie.append(EQUAL);
		dperCookie.append(euser);
		dperCookie.append(SEMICOLON);

		dperCookie.append("Domain");
		dperCookie.append(EQUAL);
		dperCookie.append(Constants.COOKIE_DOMAIN);
		dperCookie.append(SEMICOLON);

		if (maxAge > 0) {
			dperCookie.append("Expires");
			dperCookie.append(EQUAL);
			Calendar now = Calendar.getInstance();
			now.add(Calendar.SECOND, maxAge);
			dperCookie.append(cookieFormat.format(now.getTime()));
			dperCookie.append(SEMICOLON);
		}

		dperCookie.append("Path");
		dperCookie.append(EQUAL);
		dperCookie.append("/");
		dperCookie.append(SEMICOLON);

		dperCookie.append("HttpOnly");

        response.addHeader("Set-Cookie", dperCookie.toString());
	}

	/**
	 * 从dper中解析用户ID
	 */
	public static String getUserId(String dper) {
		// 以'|'分隔dper，确认格式
		String[] tokens = dper.split("[|]");
		if (tokens == null || tokens.length < 3) {
			return null;
		}

		// 解析用户ID
		String userId = tokens[0];
		try {
			if (Integer.parseInt(userId) < 1) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

		// 解析过期时间
		try {
			Double expired = Double.valueOf(tokens[1]);
			long currentSeconds = Calendar.getInstance().getTime().getTime() / 1000;
			if (expired.intValue() < currentSeconds) {
				// 判断dper中的过期时间
				return null;
			}
		} catch (Exception e) {
			return null;
		}

		return userId;
	}

}
