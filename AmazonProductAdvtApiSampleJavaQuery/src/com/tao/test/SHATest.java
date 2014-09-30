package com.tao.test;

import java.security.MessageDigest;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * ������
 */
class SHACoder {
	/**
	 * SHA-1��ϢժҪ�㷨
	 */
	public static String encodeSHA(byte[] data) throws Exception {
		// ��ʼ��MessageDigest,SHA��SHA-1�ļ��
		MessageDigest md = MessageDigest.getInstance("SHA");
		// ִ��ժҪ����
		byte[] digest = md.digest(data);
		return new HexBinaryAdapter().marshal(digest);
	}

	/**
	 * SHA-256��ϢժҪ�㷨
	 */
	public static String encodeSHA256(byte[] data) throws Exception {
		// ��ʼ��MessageDigest,SHA��SHA-1�ļ��
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// ִ��ժҪ����
		byte[] digest = md.digest(data);
		return new HexBinaryAdapter().marshal(digest);
	}

	/**
	 * SHA-384��ϢժҪ�㷨
	 */
	public static String encodeSHA384(byte[] data) throws Exception {
		// ��ʼ��MessageDigest,SHA��SHA-1�ļ��
		MessageDigest md = MessageDigest.getInstance("SHA-384");
		// ִ��ժҪ����
		byte[] digest = md.digest(data);
		return new HexBinaryAdapter().marshal(digest);
	}

	/**
	 * SHA-512��ϢժҪ�㷨
	 */
	public static String encodeSHA512(byte[] data) throws Exception {
		// ��ʼ��MessageDigest,SHA��SHA-1�ļ��
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		// ִ��ժҪ����
		byte[] digest = md.digest(data);
		return new HexBinaryAdapter().marshal(digest);
	}
}

/**
 * ������
 */
public class SHATest {
	public static void main(String[] args) throws Exception {
       String testString="asd`12asd31";
       System.out.println(SHACoder.encodeSHA(testString.getBytes()));
       System.out.println(SHACoder.encodeSHA256(testString.getBytes()));
       System.out.println(SHACoder.encodeSHA384(testString.getBytes()));
       System.out.println(SHACoder.encodeSHA512(testString.getBytes()));
	}
}