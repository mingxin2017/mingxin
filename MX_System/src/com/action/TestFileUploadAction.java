package com.action;

import java.io.File;

import antlr.StringUtils;

import com.util.FileUtil;

public class TestFileUploadAction {
	//单文件上传后台代码
	public void ajaxAttachUpload() {
	        String path =  "d:\\test\\"+fileFileName;
	        try {
	            File file = this.getFile();
	            FileUtil.randomAccessFile(path, file);
	            //如果文件小与5M的话，分片参数chunk的值是null
	            if(StringUtils.isEmpty(chunk)){
	                outJson("0", "success", "");
	            }else{
	            //chunk 分片索引，下标从0开始
	            //chunks 总分片数
	                if (Integer.valueOf(chunk) == (Integer.valueOf(chunks) - 1)) {
	                    outJson("0", "上传成功", "");
	                } else {
	                    outJson("2", "上传中" + fileFileName + " chunk:" + chunk, "");
	                }
	            }
	        } catch (Exception e) {
	            outJson("3", "上传失败", "");
	        }
	    }
}
