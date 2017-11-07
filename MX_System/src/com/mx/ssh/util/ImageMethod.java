package com.mx.ssh.util;

import java.io.*;
import java.util.UUID;

import sun.misc.BASE64Decoder;

public class ImageMethod {

	/*
	 * base64字符串转化成图片，返回图片名称 参数：base64字符串imgStr，图片保存绝对路径savaPath
	 * 返回：成功创建的jpg文件名称，若为空或错误，返回null
	 */
	public static String Base64SaveAsImage(String base64ImgPreview,String base64imgStr, String savePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (base64imgStr == null||base64ImgPreview==null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码图片
			byte[] b1 = decoder.decodeBuffer(base64imgStr);
			for (int i = 0; i < b1.length; ++i) {
				if (b1[i] < 0) {// 调整异常数据
					b1[i] += 256;
				}
			}
			
			//解码缩略图
			byte[] b2 = decoder.decodeBuffer(base64ImgPreview);
			for (int i = 0; i < b2.length; ++i) {
				if (b2[i] < 0) {// 调整异常数据
					b2[i] += 256;
				}
			}
			
			File file1 = new File(savePath+"/img/");
			File file2 = new File(savePath+"/preview/");
			System.out.println("路径" + savePath);
			// 如果文件夹不存在则创建
			if (!file1.exists() && !file1.isDirectory()) {
				System.out.println("保存图片的路径不存在，创建路径");
				file1.mkdirs();// 创建文件路径
			}
			if (!file2.exists() && !file2.isDirectory()) {
				System.out.println("保存图片的路径不存在，创建路径");
				file2.mkdirs();// 创建文件路径
			}

			String Name = UUID.randomUUID().toString().replace("-", "")
					+ ".jpg";// 随机生成图片名称
			
			String imageName = "i_"+Name;// 随机生成图片名称
			String imagePreviewName = "p_"+Name;// 随机生成图片名称
			// 生成jpeg图片
			String imgFilePath = savePath + "/img/" + imageName;// 新生成的图片 路径
			String imgPreviewFilePath = savePath + "/preview/" + imagePreviewName;// 新生成的图片 路径
			
			
			OutputStream out1 = new FileOutputStream(imgFilePath);
			out1.write(b1); // 写入文件1
			out1.flush();
			out1.close();
			
			OutputStream out2 = new FileOutputStream(imgPreviewFilePath);
			out2.write(b2); // 写入文件2缩略图
			out2.flush();
			out2.close();
			System.out.println("图片已上传到服务器目录");
			return imageName;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * base64字符串转化成图片，返回图片名称 参数：base64字符串imgStr，图片保存绝对路径savaPath
	 * 返回：成功创建的jpg文件名称，若为空或错误，返回null
	 */
	public static String SaveImage(File imgFile, String savePath)
			throws IOException {

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				imgFile));

		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();// 创建文件夹
		}

		String imageName = UUID.randomUUID().toString().replace("-", "")
				+ ".jpg";// 随机生成图片名称
		// 生成jpeg图片
		String imgFilePath = savePath + "/" + imageName;// 新生成的图片 路径
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(imgFilePath));

		int i;
		while ((i = in.read()) != -1) {
			out.write(i);
		}
		out.flush();
		out.close();
		in.close();
		return imageName;
	}

	public static boolean deleteImage(String realSavePath) {
		// TODO Auto-generated method stub
		 File file = new File(realSavePath);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("系统目录图片删除成功！");
	                return true;
	            } else {
	                System.out.println("系统目录图片删除失败！");
	                return false;
	            }
	        } else {
	            System.out.println("系统目录下文件不存在！");
	            return true;
	        }
	}

}
