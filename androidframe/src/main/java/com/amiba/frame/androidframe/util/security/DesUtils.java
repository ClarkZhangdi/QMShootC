package com.amiba.frame.androidframe.util.security;

import android.util.Base64;


import com.amiba.frame.androidframe.util.AppConstant;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * des加密
 *
 * @author wudl
 */
public class DesUtils {

    private static final String PASSWORD_CRYPT_KEY = AppConstant.SECRET_KEY[0].substring(0, 8);

    private static byte[] bytes;

    // private final static String DES = "DES";
    // private static final byte[] desKey;
    // 解密数据
    public static String decrypt(String message, int keyIndex) throws Exception {

        //		byte[] bytesrc = convertHexString(message);
        String key = AppConstant.SECRET_KEY[keyIndex].substring(0,
                AppConstant.SECRET_KEY[keyIndex].length() >= 8 ? 8 : AppConstant.SECRET_KEY[keyIndex].length());
        return decrypt(message, key);
    }

    public static String decrypt(String message, String key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, UnsupportedEncodingException,
            InvalidKeySpecException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
//		多加一个URLDecoder是项目需要，应该把这段提到外面
//		String encodeTxt = URLDecoder.decode(message, "UTF-8");
//		byte[] bytesrc = android.util.Base64.decode(encodeTxt, Base64.DEFAULT);
        byte[] bytesrc = Base64.decode(message, Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    public static byte[] encrypt(String message, String key) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(message.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(String value, int keyIndex) {
        try {
            //			value = java.net.URLEncoder.encode(value, "utf-8");
            String key = AppConstant.SECRET_KEY[keyIndex].substring(0,
                    AppConstant.SECRET_KEY[keyIndex].length() >= 8 ? 8 : AppConstant.SECRET_KEY[keyIndex].length());
            bytes = encrypt(value, key);
            //			result = toHexString(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return bytes;
        }
        return bytes;
    }

    public static String encryptStr(String value, int keyIndex) {
        return toHexString(encrypt(value, keyIndex));
    }

    public static String encryptStr(String value, String key) throws Exception {
        return toHexString(encrypt(value, key));
    }

    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    public static String toHexString(byte b[]) {
        if (b == null) return "";
        StringBuilder hexString = new StringBuilder();
        for (byte aB : b) {
            String plainText = Integer.toHexString(0xff & aB);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }
}