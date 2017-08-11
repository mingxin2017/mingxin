package com.mx.ssh.action;

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
	public void ajaxAttachUpload() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		int userId = 10010;

		// File m=(File) request.getAttribute("myFile");
		// System.out.println("------"+m.getAbsolutePath());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 日期年月日
		String ymd = sdf.format(new Date());

		String showPath = "/WeixinPages/common/fileupload/mediaLibrary/vedio/"
				+ userId + "/" + ymd + "/";

		String savePath = ServletActionContext.getServletContext().getRealPath(
				showPath);

		File uploadDir = new File(savePath);

		Map<String, String> map = new HashMap<String, String>();

		System.out.println(this.getMyFile() + "---" + this.getMyFileFileName()
				+ "------" + myFileContentType + "------");
		
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 目录存在说明今日已经上传过视频了
			uploadDir.mkdirs();// 创建用户视频目录
		}
		if (myFile != null && myFileFileName != null) {
			//String fileExt = myFileFileName.substring(myFileFileName.lastIndexOf(".") + 1).trim().toLowerCase();// 获取文件后缀

			String fileExt=myFileContentType.substring(myFileContentType.lastIndexOf("/")+1).trim().toLowerCase();
			String newName = ymd + "_" + userId + "." + fileExt;// 新生成的文件名

			//System.out.println(newName);

			File destFile = new File(savePath, newName);

			FileUtils.copyFile(myFile, destFile);// 将文件复制保存到服务器目录下

			System.out.println("缓存路径:" + myFile);
			System.out.println("文件路径:" + destFile);
			map.put("status", "成功");// 保存失败
			map.put("url", "/");// 路径为空
		} else {

		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		// System.out.println("保存路径：" + savePath);
	}
}
