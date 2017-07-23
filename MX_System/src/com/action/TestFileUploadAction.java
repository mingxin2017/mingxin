package com.action;

import java.io.File;
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

public class TestFileUploadAction {

	// ����ֵ�����ļ����������Ӧ����upload3.js�е�name���ԣ�name����ֵΪfile����ʱstruts�Ϳ��Ի�ȡ��file���ļ����󣬲���Ҫʵ������struts��ܻ��Զ�ע�����ֵ���򿪵��Դ��ڣ���һ�¾�������
	private File uploadFile;
	// ���ļ��ϴ����ļ�����spring�ϴ����ԣ��ļ�����ʽΪname����+FileName
	private String uploadFileFileName;

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	// ���ļ��ϴ���̨����
	public void ajaxAttachUpload() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = 10010;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// ����������
		String ymd = sdf.format(new Date());

		String showPath="/WeixinPages/common/fileupload/vedio/" + userId + "/" + ymd+ "/";
		
		String savePath = ServletActionContext.getServletContext().getRealPath(showPath);

		File uploadDir = new File(savePath);
		
		Map<String, String> map = new HashMap<String, String>();
		
		
		if (uploadDir.exists()) {// Ŀ¼����˵�������Ѿ��ϴ�����Ƶ��
			map.put("status", "ʧ�ܣ��Ѵ���");// ����ʧ��
			map.put("url", "/");// ·��Ϊ��
			
		//} else {
			//uploadDir.mkdirs();// �����û���ƵĿ¼
			// String fileExt = "";// �ļ���׺
			System.out.println(uploadFile+"---"+uploadFileFileName);
			if (uploadFile != null && uploadFileFileName != null) {
				String fileExt = uploadFileFileName
						.substring(uploadFileFileName.lastIndexOf(".") + 1)
						.trim().toLowerCase();// ��ȡ�ļ���׺

				String newName = ymd + "_" + userId + "." + fileExt;// �����ɵ��ļ���

				File dirFile = new File(savePath);

				File destFile = new File(dirFile, newName);
				FileUtils.copyFile(uploadFile, destFile);// ���ļ����Ƶ�������Ŀ¼��
				
				map.put("status", "�ɹ�");// ����ʧ��
				map.put("url", "/");// ·��Ϊ��
			}else{
				
			}

		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		System.out.println("����·����" + savePath);
	}
}
