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

	// 属性值，单文件的情况，对应的是upload3.js中的name属性，name属性值为file，此时struts就可以获取到file的文件对象，不需要实例化，struts框架会自动注入对象值，打开调试窗口，看一下就明白了
	private File myFile;
	// 单文件上传的文件名，spring上传特性，文件名格式为name属性+FileName
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

	// 单文件上传后台代码
	public void ajaxAttachUpload() throws IOException  {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		int userId = 10010;

		//File m=(File) request.getAttribute("myFile");
		//System.out.println("------"+m.getAbsolutePath());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 日期年月日
		String ymd = sdf.format(new Date());

		String showPath="/WeixinPages/common/fileupload/vedio/" + userId + "/" + ymd+ "/";
		
		String savePath = ServletActionContext.getServletContext().getRealPath(showPath);

		File uploadDir = new File(savePath);
		
		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println(this.getMyFile()+"---"+this.getMyFileFileName()+"------"+myFileContentType+"------");
		
		
		
		if (!uploadDir.exists()  && !uploadDir.isDirectory()) {// 目录存在说明今日已经上传过视频了
			uploadDir.mkdirs();// 创建用户视频目录
		}
			//map.put("status", "失败，已传过");// 保存失败
			//map.put("url", "/");// 路径为空
			
		//} else {
			//
			// String fileExt = "";// 文件后缀
		
			//System.out.println(myFile+"---"+myFileFileName);
			if (myFile != null && myFileFileName != null) {
				String fileExt = myFileFileName
						.substring(myFileFileName.lastIndexOf(".") + 1)
						.trim().toLowerCase();// 获取文件后缀

				String newName = ymd + "_" + userId + "." + fileExt;// 新生成的文件名

				System.out.println(newName);
				//File dirFile = new File(savePath);
				
				File destFile = new File(savePath, newName);
				
//				FileOutputStream fos = null;
//		        FileInputStream fis = null;
//		        try {
//		            // 建立文件输出流
//		            System.out.println(myFile);
//		            fos = new FileOutputStream(savePath+ "\\" + newName);
//		            // 建立文件上传流
//		            fis = new FileInputStream(myFile);
//		            byte[] buffer = new byte[1024];
//		            int len = 0;
//		            while ((len = fis.read(buffer)) > 0) {
//		                fos.write(buffer, 0, len);
//		            }
//		        } catch (Exception e) {
//		            System.out.println("文件上传失败");
//		            e.printStackTrace();
//		        } finally {
//		        	fos.close();
//		        	fis.close();
//		            //close(fos, fis);
//		        }
				
				FileUtils.copyFile(myFile, destFile);// 将文件复制到服务器目录下
				//FileUtil.randomAccessFile(savePath, myFile);
//				BufferedInputStream inputStream=null;
//				inputStream = new BufferedInputStream(new FileInputStream(myFile));
				
				
				
				System.out.println("文件路径"+myFile+destFile);
				map.put("status", "成功");// 保存失败
				map.put("url", "/");// 路径为空
			}else{
				
			}

		
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		//System.out.println("保存路径：" + savePath);
	}
}
