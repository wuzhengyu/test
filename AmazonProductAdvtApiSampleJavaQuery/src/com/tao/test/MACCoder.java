package com.tao.test;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * MAC�㷨������ ����HmacMD5��HmacSHA1��HmacSHA256��HmacSHA384��HmacSHA512Ӧ�õĲ��趼��һģһ����
 */
class MACCoder {
	public static byte[] initHmacKey(String algorithm) throws NoSuchAlgorithmException {
		// ��ʼ��ժҪ�㷨����Կ������
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		// ������Կ
		SecretKey secretKey = generator.generateKey();
		// �����Կ
		byte[] key = secretKey.getEncoded();
		return key;
	}

	public static String encodeHmac(byte[] data, byte[] key, String algorithm) throws Exception {
		// ��ԭ��Կ
		SecretKey secretKey = new SecretKeySpec(key, algorithm);
		// ʵ����Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// ��ʼ��mac
		mac.init(secretKey);
		// ִ����ϢժҪ
		byte[] digest = mac.doFinal(data);

		return new HexBinaryAdapter().marshal(digest);// תΪʮ�����Ƶ��ַ���
	}

	public static void main(String[] args) throws Exception {
		String testString = "asdasd";
		byte[] data = testString.getBytes();
		byte[] keyHmac;
		keyHmac = MACCoder.initHmacKey("HmacMD5");
		System.out.println(MACCoder.encodeHmac(data, keyHmac, "HmacMD5"));
		keyHmac = MACCoder.initHmacKey("HmacSHA1");
		System.out.println(MACCoder.encodeHmac(data, keyHmac, "HmacSHA1"));
		keyHmac = MACCoder.initHmacKey("HmacSHA256");
		System.out.println(MACCoder.encodeHmac(data, keyHmac, "HmacSHA256"));
		keyHmac = MACCoder.initHmacKey("HmacSHA384");
		System.out.println(MACCoder.encodeHmac(data, keyHmac, "HmacSHA384"));
		keyHmac = MACCoder.initHmacKey("HmacSHA512");
		System.out.println(MACCoder.encodeHmac(data, keyHmac, "HmacSHA512"));
	}

}
