package com.mx.ssh.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;
import com.mx.ssh.bean.MxUsersData;

public class FileUploadUtil {

	/**
     * 指定位置开始写入文件
     * @param tempFile  输入文件
     * @param outPath  输出文件的路径(路径+文件名)
     * @throws IOException
     */
    public static void randomAccessFile( String outPath,File tempFile) throws IOException{
        RandomAccessFile  raFile = null;
        BufferedInputStream inputStream=null;
        try{
            File dirFile = new File(outPath);
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(dirFile, "rw"); 
            raFile.seek(raFile.length());
            inputStream = new BufferedInputStream(new FileInputStream(tempFile));
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length);
            }
        }catch(Exception e){
            throw new IOException(e.getMessage());
        }finally{
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (raFile != null) {
                    raFile.close();
                }
            }catch(Exception e){
                throw new IOException(e.getMessage());
            }
        }
    }
    
    
    /*public static void UploadImage(MxUsersData userInfo,File file) throws IOException {

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// 获取用户id
		String myspaceId = request.getParameter("myspaceId").toString();// 获取活动空间id
		String base64Img = request.getParameter("img").toString();
		base64Img = base64Img.replace("data:image/jpeg;base64,", "");// 去除base64中无用的文件头
		String savePath = "/WeixinPages/common/uploadImg/myspaceImg/"
				+ myspaceId + "/" + userId + "/";// 保存图片的绝对路径
		String realSavePath = request.getSession().getServletContext()
				.getRealPath(savePath);
		String imgName = ImageMethod.Base64SaveAsImage(base64Img, realSavePath);// 保存图片到系统应用文件夹中

		Map<String, String> map = new HashMap<String, String>();
		String showPath = request.getContextPath() + savePath;
		String imgPath = showPath + imgName;

		MxActivitiesMySpaceMaterial material = new MxActivitiesMySpaceMaterial();
		material.setCreateDate(new Timestamp(System.currentTimeMillis()));
		material.setDescribe("图片描述");
		material.setLoadUrl(imgPath);
		material.setMaterialType(1);// 图片类型为1
		material.setMyspaceId(Integer.parseInt(myspaceId));
		material.setOthers("");
		material.setSubmitUserId(Integer.parseInt(userId));
		boolean saveToSQL = activitiesMySpaceService
				.saveActivitiesMySpaceMaterial(material);

		if (imgName == null || saveToSQL == false) {
			map.put("done", "-1");
			map.put("imgSrc", "/");
			map.put("msg", "图片上传失败了!");
		} else {
			map.put("done", "0");
			map.put("imgSrc", imgPath);// 显示图片的完整相对路径
			map.put("msg", "图片上传成功!");
			System.out.println("用户上传图片至：" + imgPath);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}*/
    
}
