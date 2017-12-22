package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 99550 on 2017/12/18.
 */
public class MD5 {
    public static String getMD5(String password){
        String temp="";
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] encodeMd5Digest = md5Digest.digest(password.getBytes());
            temp = convertByteToHexString(encodeMd5Digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return temp;
    }
    private static String convertByteToHexString(byte[] bytes){
        String result="";
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xff;
            String tempHex = Integer.toHexString(temp);
            if(tempHex.length()<2){
                result +="0"+tempHex;
            }else {
                result+=tempHex;
            }
        }
        return result;
    }
}
