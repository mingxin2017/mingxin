package com.action;

import java.io.File;

import antlr.StringUtils;

import com.util.FileUtil;

public class TestFileUploadAction {
	//���ļ��ϴ���̨����
	public void ajaxAttachUpload() {
	        String path =  "d:\\test\\"+fileFileName;
	        try {
	            File file = this.getFile();
	            FileUtil.randomAccessFile(path, file);
	            //����ļ�С��5M�Ļ�����Ƭ����chunk��ֵ��null
	            if(StringUtils.isEmpty(chunk)){
	                outJson("0", "success", "");
	            }else{
	            //chunk ��Ƭ�������±��0��ʼ
	            //chunks �ܷ�Ƭ��
	                if (Integer.valueOf(chunk) == (Integer.valueOf(chunks) - 1)) {
	                    outJson("0", "�ϴ��ɹ�", "");
	                } else {
	                    outJson("2", "�ϴ���" + fileFileName + " chunk:" + chunk, "");
	                }
	            }
	        } catch (Exception e) {
	            outJson("3", "�ϴ�ʧ��", "");
	        }
	    }
}
