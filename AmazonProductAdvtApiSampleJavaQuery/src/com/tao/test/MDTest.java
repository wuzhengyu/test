package com.tao.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * MD加密工具类
 */
class MDCoder {
	// MD2加密
	public static String encodeMd2(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("MD2");
		// 执行摘要信息
		byte[] digest = md.digest(data);
		// 将摘要信息转换为32位的十六进制字符串
		return new String(new HexBinaryAdapter().marshal(digest));
	}

	// MD5加密
	public static String encodeMd5(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("MD5");
		// 执行摘要信息
		byte[] digest = md.digest(data);
		// 将摘要信息转换为32位的十六进制字符串
		return new String(new HexBinaryAdapter().marshal(digest));
	}
}

public class MDTest {
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		String testString = "123456asdasdfsdfsdfsdf";
		System.out.println(MDCoder.encodeMd2(testString.getBytes()));
		System.out.println(MDCoder.encodeMd5(testString.getBytes()));
	}
}
