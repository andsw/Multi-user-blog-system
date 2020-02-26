package cn.jxufe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author hsw
 * @date 2020/2/22 10:00 下午
 */
public class EncodeUtil {

    private static MessageDigest md5Encoder;

    static {
        try {
            md5Encoder = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * md5加盐加密password
     * @param password pw
     * @return [Ciphertext，Salt】
     */
    public static String[] generate(String password) {
        // add salt
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        salt.append(random.nextInt(99999999))
            .append(random.nextInt(99999999));
        // 凑足16位
        while (salt.length() < 16) {
            salt.append("0");
        }

        return new String[]{md5Encode(password + salt.toString()), salt.toString()};
    }

    /**
     * md5加盐加密password
     * @param password pw
     * @param salt 如上一个方法所示，salt是一个长为16的全数字字符串
     * @return 加密后密码
     */
    public static String generate(String password, String salt) {
        return md5Encode(password + salt);
    }

    public static String generateToken(String username, String email, Integer userId) {
        return md5Encode(username + email + userId);
    }

    /**
     * md5加密
     * @param Ciphertext 加盐后的密码
     * @return 密文
     */
    private static String md5Encode(String Ciphertext) {
        byte[] bytes = md5Encoder.digest(Ciphertext.getBytes());
        return bytesToHex(bytes);
    }

    /**
     * 字节数组转16进制
     * @param bytes 需要转换的byte数组
     * @return  转换后的Hex字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final String[] generate = generate("1");
        System.out.println(generate[0] + " " + generate[1]);

    }
}
