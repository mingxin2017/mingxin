package com.mx.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
* 类名: SignUtil </br>
* 描述: 检验signature 工具类 </br>
* 开发人员： souvc </br>
* 创建时间：  2015-9-29 </br>
* 发布版本：V1.0  </br>
 */
public class WeixinSignUtil {
    
    // 与接口配置信息中的Token要一致
	
	//公司服务器花生壳url(ming1315微信号)
	public final static String serverUrl="http://mingxin2017.iask.in/MX_System/";
	private static String token = "weixin_mingxin";
    public final static String AppID = "wx34c7cde93a1c5c40";
    public final static String AppSecret = "97a28450aed0e10b0299ff88ae482bac";
	
	//本地花生壳url(wulm3344520微信号)
//	public final static String serverUrl="http://mingxin.imwork.net/MX_System/";
//    private static String token = "weixin_mingxin";
//    public final static String AppID = "wxa8fba1aad753cd22";
//    public final static String AppSecret = "73f63922bd780e08cff223f7a422c4f6";
    
	//public final static String serverUrl="http://mingxin.imwork.net/MX_System/";
	//public final static String serverUrl="http://mingxin2017.ngrok.cc/MX_System/";
    //private static String token = "weixin_mingxin";

    //public final static String AppID = "wx34c7cde93a1c5c40";
    //public final static String AppSecret = "97a28450aed0e10b0299ff88ae482bac";
    
	/*public final static String serverUrl="http://d1a7069951.iask.in/MX_System/";
    private static String token = "freamwechat";
    public final static String AppID = "wxb235c46c4c2740a9";
    public final static String AppSecret = "bbd63f207a5dd01e7d253174be7d5e0e";*/
    
    
    
    /**
    * 方法名：checkSignature</br>
    * 详述：验证签名</br>
    * 开发人员：wulm</br>
    * 创建时间：2017-6-2  </br>
    * @param signature
    * @param timestamp
    * @param nonce
    * @return
    * @throws
     */
    public static boolean checkSignature(String signature, String timestamp,String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);
        
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        content = null;
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
    * 方法名：byteToStr</br>
    * 详述：将字节数组转换为十六进制字符串</br>
    * 开发人员：wulm</br>
    * 创建时间：2017-6-2  </br>
    * @param byteArray
    * @return
    * @throws
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
    * 方法名：byteToHexStr</br>
    * 详述：将字节转换为十六进制字符串</br>
    * 开发人员：wulm</br>
    * 创建时间：2017-6-2  </br>
    * @param mByte
    * @return
    * @throws
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}