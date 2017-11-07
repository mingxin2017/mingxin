package com.mx.ssh.util;

import java.io.*;
import java.util.UUID;

import sun.misc.BASE64Decoder;

public class ImageMethod {

	/*
	 * base64�ַ���ת����ͼƬ������ͼƬ���� ������base64�ַ���imgStr��ͼƬ�������·��savaPath
	 * ���أ��ɹ�������jpg�ļ����ƣ���Ϊ�ջ���󣬷���null
	 */
	public static String Base64SaveAsImage(String base64ImgPreview,String base64imgStr, String savePath) { // ���ֽ������ַ�������Base64���벢����ͼƬ
		if (base64imgStr == null||base64ImgPreview==null) // ͼ������Ϊ��
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����ͼƬ
			byte[] b1 = decoder.decodeBuffer(base64imgStr);
			for (int i = 0; i < b1.length; ++i) {
				if (b1[i] < 0) {// �����쳣����
					b1[i] += 256;
				}
			}
			
			//��������ͼ
			byte[] b2 = decoder.decodeBuffer(base64ImgPreview);
			for (int i = 0; i < b2.length; ++i) {
				if (b2[i] < 0) {// �����쳣����
					b2[i] += 256;
				}
			}
			
			File file1 = new File(savePath+"/img/");
			File file2 = new File(savePath+"/preview/");
			System.out.println("·��" + savePath);
			// ����ļ��в������򴴽�
			if (!file1.exists() && !file1.isDirectory()) {
				System.out.println("����ͼƬ��·�������ڣ�����·��");
				file1.mkdirs();// �����ļ�·��
			}
			if (!file2.exists() && !file2.isDirectory()) {
				System.out.println("����ͼƬ��·�������ڣ�����·��");
				file2.mkdirs();// �����ļ�·��
			}

			String Name = UUID.randomUUID().toString().replace("-", "")
					+ ".jpg";// �������ͼƬ����
			
			String imageName = "i_"+Name;// �������ͼƬ����
			String imagePreviewName = "p_"+Name;// �������ͼƬ����
			// ����jpegͼƬ
			String imgFilePath = savePath + "/img/" + imageName;// �����ɵ�ͼƬ ·��
			String imgPreviewFilePath = savePath + "/preview/" + imagePreviewName;// �����ɵ�ͼƬ ·��
			
			
			OutputStream out1 = new FileOutputStream(imgFilePath);
			out1.write(b1); // д���ļ�1
			out1.flush();
			out1.close();
			
			OutputStream out2 = new FileOutputStream(imgPreviewFilePath);
			out2.write(b2); // д���ļ�2����ͼ
			out2.flush();
			out2.close();
			System.out.println("ͼƬ���ϴ���������Ŀ¼");
			return imageName;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * base64�ַ���ת����ͼƬ������ͼƬ���� ������base64�ַ���imgStr��ͼƬ�������·��savaPath
	 * ���أ��ɹ�������jpg�ļ����ƣ���Ϊ�ջ���󣬷���null
	 */
	public static String SaveImage(File imgFile, String savePath)
			throws IOException {

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				imgFile));

		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();// �����ļ���
		}

		String imageName = UUID.randomUUID().toString().replace("-", "")
				+ ".jpg";// �������ͼƬ����
		// ����jpegͼƬ
		String imgFilePath = savePath + "/" + imageName;// �����ɵ�ͼƬ ·��
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
	        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("ϵͳĿ¼ͼƬɾ���ɹ���");
	                return true;
	            } else {
	                System.out.println("ϵͳĿ¼ͼƬɾ��ʧ�ܣ�");
	                return false;
	            }
	        } else {
	            System.out.println("ϵͳĿ¼���ļ������ڣ�");
	            return true;
	        }
	}

}
