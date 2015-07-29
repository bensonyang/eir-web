/**
 * Project: encryption
 * 
 * File Created at 2011-11-7
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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 * 加密/解密工具
 * 
 * @author liang.liu
 * 
 */
public abstract class EncryptionUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(EncryptionUtils.class);

    private static final String ENCODING_KEY = "ASCII";
	private static final String ENCODING_TEXT = "UTF-8";

	/**
	 * 区别AES算法对空格字符的混淆
	 */
	private static final String SPACE_INSTEAD = "~$#!^";
	private static final String SPACE2_INSTEAD = "^#~$!";
	private static final String SPACE2 = String.valueOf((char) 0);
	private static final String SPACELIST = " " + SPACE2;

	/**
	 * 加密普通文本(无法从.Net解密)
	 * 
	 * @param text
	 *            待加密文本
	 * @return
	 */
	public static String encrypt(String text) {
		// 空处理
		if (StringUtils.isBlank(text)) {
			return EMPTY;
		}
		text = text.replace(" ", SPACE_INSTEAD).replace(SPACE2, SPACE2_INSTEAD);
		try {
			byte[] bytes = text.getBytes(ENCODING_TEXT);
			byte[] encryptedBytes = encrypt(bytes, Constants.ENCRYPT_KEY.getBytes(ENCODING_KEY),
					Constants.ENCRYPT_IV.getBytes(ENCODING_KEY));
			if (!ArrayUtils.isEmpty(encryptedBytes)) {
				return parseByte2Hex(encryptedBytes);
			}
		} catch (UnsupportedEncodingException e) {
			// do nothing
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return EMPTY;
	}

	/**
	 * 解密普通文本(不可解密由.Net加密的普通文本)
	 * 
	 * @param cipherText
	 *            待解密文本
	 * @return
	 */
	public static String decrypt(String cipherText) {
		// 空处理
		if (StringUtils.isBlank(cipherText)) {
			return EMPTY;
		}
		try {
			byte[] cipherBytes = parseHex2Byte(cipherText);
			byte[] decryptedBytes = decrypt(cipherBytes, Constants.ENCRYPT_KEY.getBytes(ENCODING_KEY), Constants.ENCRYPT_IV
					.getBytes(ENCODING_KEY));
			if (!ArrayUtils.isEmpty(decryptedBytes)) {
				String decrypted = new String(decryptedBytes, ENCODING_TEXT);
				return StringUtils.strip(decrypted, SPACELIST).replace(SPACE_INSTEAD, " ")
						.replace(SPACE2_INSTEAD, SPACE2);
			}
		} catch (UnsupportedEncodingException e) {
			// do nothing
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return EMPTY;
	}

	/**
	 * 使用AES算法,使用指定key和IV对指定字节数组进行加密
	 */
	private static byte[] encrypt(byte[] bytes, byte[] key, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec KeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, KeySpec, new IvParameterSpec(iv));
			return cipher.doFinal(padWithZeros(bytes));
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	/**
	 * 使用AES算法，使用指定key和IV对指定字节数组进行解密
	 */
	private static byte[] decrypt(byte[] bytes, byte[] key, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec KeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, KeySpec, new IvParameterSpec(iv));
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		}
	}

	private static byte[] padWithZeros(byte[] input) {
		int rest = input.length % 16;
		if (rest > 0) {
			byte[] result = new byte[input.length + (16 - rest)];
			System.arraycopy(input, 0, result, 0, input.length);
			return result;
		}
		return input;
	}

	private static byte[] parseHex2Byte(String hexText) {
		if (StringUtils.isEmpty(hexText)) {
			return null;
		}
		byte[] result = new byte[hexText.length() / 2];
		for (int i = 0; i < hexText.length() / 2; i++) {
			int high = Integer.parseInt(hexText.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexText.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	private static String parseByte2Hex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toLowerCase());
		}
		return sb.toString();
	}

	/**
	 * 返回md5加密串
	 * 
	 * @param plainText
	 *            加密文本
	 * @param needShort
	 *            是否返回16位，否则返回32位
	 * @param uppercase
	 *            是否返回大写形式
	 * @return
	 */
	public static String md5Hex(String plainText, boolean needShort, boolean uppercase) {
		String encrypted = DigestUtils.md5Hex(plainText);
		if (needShort) {
			encrypted = encrypted.substring(8, 24);
		}
		if (uppercase) {
			encrypted = encrypted.toUpperCase();
		}
		return encrypted;
	}



}
