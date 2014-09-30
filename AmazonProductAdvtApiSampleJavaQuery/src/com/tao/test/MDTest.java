package com.tao.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * MD���ܹ�����
 */
class MDCoder {
	// MD2����
	public static String encodeMd2(byte[] data) throws Exception {
		// ��ʼ��MessageDigest
		MessageDigest md = MessageDigest.getInstance("MD2");
		// ִ��ժҪ��Ϣ
		byte[] digest = md.digest(data);
		// ��ժҪ��Ϣת��Ϊ32λ��ʮ�������ַ���
		return new String(new HexBinaryAdapter().marshal(digest));
	}

	// MD5����
	public static String encodeMd5(byte[] data) throws Exception {
		// ��ʼ��MessageDigest
		MessageDigest md = MessageDigest.getInstance("MD5");
		// ִ��ժҪ��Ϣ
		byte[] digest = md.digest(data);
		// ��ժҪ��Ϣת��Ϊ32λ��ʮ�������ַ���
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
