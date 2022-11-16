package cc.kkon.jwt.utils;


/**
 * 进制转换工具类
 *
 * @author yui
 */
public class Bins {

    /**
     * 二进制转16进制字符串
     *
     * @param src 字节数组
     * @return 16进制字符串
     */
    public static String binToHex(byte[] src) {
        if (src == null || src.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }


}
