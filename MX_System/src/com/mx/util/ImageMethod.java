package com.mx.util;

import java.io.*;
import java.util.UUID;

import sun.misc.BASE64Decoder;

public class ImageMethod {

	/*
	 * base64字符串转化成图片，返回图片名称
	 * 参数：base64字符串imgStr，图片保存绝对路径savaPath
	 * 返回：成功创建的jpg文件名称，若为空或错误，返回null
	 */
    public static String Base64SaveAsImage(String base64imgStr,String savePath)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (base64imgStr == null) //图像数据为空  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(base64imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            } 
            File file =new File(savePath);    
            System.out.println("路径"+savePath);
          //如果文件夹不存在则创建    
          if  (!file .exists()  && !file .isDirectory())      
          {       
              System.out.println("保存图片的路径不存在，创建路径");
              file.mkdirs();//创建文件路径
          } else   
          {  
              System.out.println("//目录存在");  
          } 
            
            
            String imageName=UUID.randomUUID().toString().replace("-", "")+".jpg";//随机生成图片名称
            //生成jpeg图片  
            String imgFilePath = savePath+"/"+imageName;//新生成的图片 路径
            OutputStream out = new FileOutputStream(imgFilePath);     
            out.write(b);   //写入文件
            out.flush();  
            out.close();  
            System.out.println("图片已上传到服务器目录");
            return imageName;  
        }   
        catch (Exception e)   
        {  
            return null;  
        }  
    }  
	
	
}
