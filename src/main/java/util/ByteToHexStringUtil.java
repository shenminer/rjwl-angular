package util;

public class ByteToHexStringUtil {
	/**
	 * byte转16进制字符串
	 * 
	 * @param array
	 * @return
	 */
	public static String byteToHexString(byte[] array) {
		StringBuilder sb = new StringBuilder();
		for (Byte b : array) {
			int number = b & 0xff;
			String hex = Integer.toHexString(number);
			if (hex.length() == 1) {
				sb.append("0" + hex);
			} else {
				sb.append(hex);
			}
		}
		return sb.toString();
	}
}
