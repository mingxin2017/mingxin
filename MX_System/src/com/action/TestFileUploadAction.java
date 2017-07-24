package com.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.multipart.MultipartFile;

import com.util.FileUtil;

public class TestFileUploadAction {

	// ����ֵ�����ļ����������Ӧ����upload3.js�е�name���ԣ�name����ֵΪfile����ʱstruts�Ϳ��Ի�ȡ��file���ļ����󣬲���Ҫʵ������struts��ܻ��Զ�ע�����ֵ���򿪵��Դ��ڣ���һ�¾�������
	private File myFile;
	// ���ļ��ϴ����ļ�����spring�ϴ����ԣ��ļ�����ʽΪname����+FileName
	private String myFileFileName;

	private String myFileContentType;

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	// ���ļ��ϴ���̨����
	public void ajaxAttachUpload() throws IOException  {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = 10010;

		//File m=(File) request.getAttribute("myFile");
		//System.out.println("------"+m.getAbsolutePath());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// ����������
		String ymd = sdf.format(new Date());

		String showPath="/WeixinPages/common/fileupload/vedio/" + userId + "/" + ymd+ "/";
		
		String savePath = ServletActionContext.getServletContext().getRealPath(showPath);

		File uploadDir = new File(savePath);
		
		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println(this.getMyFile()+"---"+this.getMyFileFileName()+"------"+myFileContentType+"------");
		
		
		
		if (!uploadDir.exists()  && !uploadDir.isDirectory()) {// Ŀ¼����˵�������Ѿ��ϴ�����Ƶ��
			uploadDir.mkdirs();// �����û���ƵĿ¼
		}
			//map.put("status", "ʧ�ܣ��Ѵ���");// ����ʧ��
			//map.put("url", "/");// ·��Ϊ��
			
		//} else {
			//
			// String fileExt = "";// �ļ���׺
		
			//System.out.println(myFile+"---"+myFileFileName);
			if (myFile != null && myFileFileName != null) {
				String fileExt = myFileFileName
						.substring(myFileFileName.lastIndexOf(".") + 1)
						.trim().toLowerCase();// ��ȡ�ļ���׺

				String newName = ymd + "_" + userId + "." + fileExt;// �����ɵ��ļ���

				System.out.println(newName);
				//File dirFile = new File(savePath);
				
				File destFile = new File(savePath, newName);
				
//				FileOutputStream fos = null;
//		        FileInputStream fis = null;
//		        try {
//		            // �����ļ������
//		            System.out.println(myFile);
//		            fos = new FileOutputStream(savePath+ "\\" + newName);
//		            // �����ļ��ϴ���
//		            fis = new FileInputStream(myFile);
//		            byte[] buffer = new byte[1024];
//		            int len = 0;
//		            while ((len = fis.read(buffer)) > 0) {
//		                fos.write(buffer, 0, len);
//		            }
//		        } catch (Exception e) {
//		            System.out.println("�ļ��ϴ�ʧ��");
//		            e.printStackTrace();
//		        } finally {
//		        	fos.close();
//		        	fis.close();
//		            //close(fos, fis);
//		        }
				
				FileUtils.copyFile(myFile, destFile);// ���ļ����Ƶ�������Ŀ¼��
				//FileUtil.randomAccessFile(savePath, myFile);
//				BufferedInputStream inputStream=null;
//				inputStream = new BufferedInputStream(new FileInputStream(myFile));
				
				
				
				System.out.println("�ļ�·��"+myFile+destFile);
				map.put("status", "�ɹ�");// ����ʧ��
				map.put("url", "/");// ·��Ϊ��
			}else{
				
			}

		
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		//System.out.println("����·����" + savePath);
	}
}
