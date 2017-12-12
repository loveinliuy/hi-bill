package loveinliuy.bill.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
public class Md5Util {

    /**
     * 将给定字符串使用MD5加密
     *
     * @param src   给定字符串
     * @param depth 加密迭代次数
     * @return 加密后的字符串
     */
    public static String encrypt(String src, int depth) {
        for (int i = 0; i < depth; i++) {
            src = encrypt(src);
        }
        return src;
    }

    /**
     * 将给定字符串使用MD5加密
     *
     * @param src 给定字符串
     * @return MD5加密后的字符串
     */
    public static String encrypt(String src) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(src.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
