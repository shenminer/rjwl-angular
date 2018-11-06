package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class SignUtil {
	private final static String TOKEN = "mywechat";
	private final static Logger LOGGER = Logger.getLogger(SignUtil.class);

	public final static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { TOKEN, timestamp, nonce };
		// 将token、timestamp、nonce 三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = ByteToHexStringUtil.byteToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		content = null;
		LOGGER.info("my signature =" + tmpStr);
		return tmpStr != null ? tmpStr.equals(signature) : false;
	}
}
