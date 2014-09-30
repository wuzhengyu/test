package com.tao.test;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * MAC算法工具类 对于HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512应用的步骤都是一模一样的
 */
class MACCoder {
	public static byte[] initHmacKey(String algorithm) throws NoSuchAlgorithmException {
		// 初始化摘要算法的密钥产生器
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		// 产生密钥
		SecretKey secretKey = generator.generateKey();
		// 获得密钥
		byte[] key = secretKey.getEncoded();
		return key;
	}

	public static String encodeHmac(byte[] data, byte[] key, String algorithm) throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, algorithm);
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(data);

		return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
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
