package Test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD加密工具类
 */
class MDCoder {
	// MD5加密 返回十六进制的字符串
	public static String encodeMd5Hex(byte[] data) throws Exception {
		return DigestUtils.md5Hex(data);
	}

	// MD5加密,返回十进制的字节数组
	public static byte[] encodeMd5(byte[] data) {
		return DigestUtils.md5(data);
	}
}

public class MDTest {
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		String testString = "123456asdasdfsdfsdfsdf";
		// 十六进制的字符串
		String digestString = MDCoder.encodeMd5Hex(testString.getBytes());
		System.out.println(digestString);

		// 字节数组
		byte[] digest = MDCoder.encodeMd5(testString.getBytes());
		String dString = new String(Hex.encodeHex(digest));
		System.out.println(dString);
	}
}