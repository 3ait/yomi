package com.payment.epayment;

/**
 * Discription: MD5 加密工具类
 **/

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
public class EncryptUtil {

	public static final String encode = "UTF-8";
	/**
	 * 产生长度为 32 的 MD5 编码后字串(大写的)
	 * @param str 原字串
	 * @return
	 */
	public final static String encodeStr(String str){
		return EncryptUtil.byte2hex(EncryptUtil.encode(str.getBytes())).toUpperCase();
	}
	/**
	 * MD5编码
	 * @param origin 原始字符串
	 * @return 经过MD5加密之后的结果
	 */
	public static String doEncrypt(String origin,String sign_type) {
		String resultString = null;
		try {
			resultString = origin;
			if(sign_type=="SHA2"){
				sign_type="SHA-256";
			}
			MessageDigest md = MessageDigest.getInstance(sign_type);
			// resultString = byteArrayToHexString(md.digest(resultString.getBytes()));//原文件内容，可能原因是：win2003时系统缺省编码为GBK，win7为utf-8
//			resultString = byte2hex(md.digest(resultString.getBytes("ISO8859-1")));
			resultString = byte2hex(md.digest(resultString.getBytes(encode)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	

	public final static byte[] encode(byte[] btInput) {
		try {
			MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			return md;
		} catch (Exception e) {
			return null;
		}
	}

	public final static String MD5(String str) {
		String strMd5 = "";
		try{
//			strMd5 = MD5Util.byte2hex(MD5Util.encode(str.getBytes("GBK")));
			strMd5 = EncryptUtil.byte2hex(EncryptUtil.encode(str.getBytes(encode)));
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return strMd5;
	}
	
	public static String encodeStrCharset(String str, String charsetname) {
		String resultString = str;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			if (charsetname == null || "".equals(charsetname))
				resultString = byte2hex(md.digest(resultString.getBytes()));
			else
				resultString = byte2hex(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	 /**
     * 字符串转java字节码
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节

            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        b = null;
        return b2;
    }
    private static String randVal(int count) {
		Random rand = new Random();
		char chr;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			switch (rand.nextInt(3)) {
			case 0:
				chr = (char) (rand.nextInt(10) + (int) '0');
				break;
			case 1:
				chr = (char) (rand.nextInt(26) + (int) 'a');
				break;
			case 2:
				chr = (char) (rand.nextInt(26) + (int) 'A');
				break;
			default:
				chr = (char) (rand.nextInt(26) + (int) 'A');
			}
			sb.append(chr);
		}
		return sb.toString();
	}
    
    public static String byte2hex(byte[] b) { 
        String hs = ""; 
        String stmp = ""; 
        for (int n = 0; n < b.length; n++) { 
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) { 
                hs = hs + "0" + stmp; 
            } else { 
                hs = hs + stmp; 
            } 
        } 
        return hs.toLowerCase(); 
    }

//	/**
//	 * MD5编码
//	 * @param origin 原始字符串
//	 * @return 经过MD5加密之后的结果
//	 */
//	public static String MD5Encode(String origin) {
//		String resultString = null;
//		try {
//			resultString = origin;
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			// resultString = byteArrayToHexString(md.digest(resultString.getBytes()));//原文件内容，可能原因是：win2003时系统缺省编码为GBK，win7为utf-8
//			resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));//正确的写法
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resultString;
//	}

	public static String byte2hex2(byte[] b){
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		char str[] = new char[16 * 2]; /* 每个字节用 16 进制表示的话，使用两个字符 */
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
			byte byte0 = b[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		return new String(str);
	}

	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		String pwd = "654321";
//		String randVal = "DAESTE253S3SAD533S3S"; //20 个随机字串
//		String s = pwd + randVal;
//		String pwdHash = null;
//		byte[] bytes = MD5Util.encode(s.getBytes("GBK")); //3.
//		pwdHash = new String(Base64Util.encode(bytes)); //4.
		System.out.println(EncryptUtil.byte2hex(EncryptUtil.encode(pwd.getBytes("GBK"))));
		
//		DESEncryptor desEncryptor = new DESEncryptor();
//		System.out.println("DES:"+desEncryptor.encode("111"));
		
	}
}
