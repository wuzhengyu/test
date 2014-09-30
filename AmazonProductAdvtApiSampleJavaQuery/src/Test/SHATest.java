package Test;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ������
 */
class SHACoder {
	/**
	 * SHA-1��ϢժҪ�㷨,�����ֽ�����
	 */
	public static byte[] encodeSHA(byte[] data) throws Exception {
		return DigestUtils.sha1(data);
	}
	
	/**
	 * SHA-1��ϢժҪ�㷨,����ʮ�������ַ���
	 */
	public static String encodeSHAHex(byte[] data) throws Exception {
		return DigestUtils.sha1Hex(data);
	}

	/**
	 * SHA-256��ϢժҪ�㷨,�����ֽ�����
	 */
	public static byte[] encodeSHA256(byte[] data) throws Exception {
		return DigestUtils.sha256(data);
	}
	
	/**
	 * SHA-256��ϢժҪ�㷨,����ʮ�������ַ���
	 */
	public static String encodeSHA256Hex(byte[] data) throws Exception {
		return DigestUtils.sha256Hex(data);
	}

	/**
	 * SHA-384��ϢժҪ�㷨,�����ֽ�����
	 */
	public static byte[] encodeSHA384(byte[] data) throws Exception {
		return DigestUtils.sha384(data);
	}
	
	/**
	 * SHA-384��ϢժҪ�㷨,����ʮ�������ַ���
	 */
	public static String encodeSHA384Hex(byte[] data) throws Exception {
		return DigestUtils.sha384Hex(data);
	}
	
	/**
	 * SHA-512��ϢժҪ�㷨,�����ֽ�����
	 */
	public static byte[] encodeSHA512(byte[] data) throws Exception {
		return DigestUtils.sha512(data);
	}
	
	/**
	 * SHA-512��ϢժҪ�㷨,����ʮ�������ַ���
	 */
	public static String encodeSHA512Hex(byte[] data) throws Exception {
		return DigestUtils.sha512Hex(data);
	}
}

/**
 * ������
 */
public class SHATest {
	public static void main(String[] args) throws Exception {
       String testString="asd`12asd31";
       System.out.println(SHACoder.encodeSHAHex(testString.getBytes()));
       System.out.println(SHACoder.encodeSHA256Hex(testString.getBytes()));
       System.out.println(SHACoder.encodeSHA384Hex(testString.getBytes()));
       System.out.println(SHACoder.encodeSHA512Hex(testString.getBytes()));
	}
}
