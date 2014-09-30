package Test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD���ܹ�����
 */
class MDCoder {
	// MD5���� ����ʮ�����Ƶ��ַ���
	public static String encodeMd5Hex(byte[] data) throws Exception {
		return DigestUtils.md5Hex(data);
	}

	// MD5����,����ʮ���Ƶ��ֽ�����
	public static byte[] encodeMd5(byte[] data) {
		return DigestUtils.md5(data);
	}
}

public class MDTest {
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		String testString = "123456asdasdfsdfsdfsdf";
		// ʮ�����Ƶ��ַ���
		String digestString = MDCoder.encodeMd5Hex(testString.getBytes());
		System.out.println(digestString);

		// �ֽ�����
		byte[] digest = MDCoder.encodeMd5(testString.getBytes());
		String dString = new String(Hex.encodeHex(digest));
		System.out.println(dString);
	}
}