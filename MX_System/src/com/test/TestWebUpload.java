package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;

public class TestWebUpload {/*
	public void fileUpload(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        try {
            String path = request.getParameter("path");
            path = path != null ? java.net.URLDecoder.decode(path, "utf-8")
                    : "";
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
 
                // �õ����еı�������Ŀǰ��������FileItem
                List<FileItem> fileItems = upload.parseRequest(request);
 
                String id = "";
                String fileName = "";
                // �������1˵���Ƿ�Ƭ����
                int chunks = 1;
                int chunk = 0;
                FileItem tempFileItem = null;
 
                for (FileItem fileItem : fileItems) {
                    if (fileItem.getFieldName().equals("id")) {
                        id = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("name")) {
                        fileName = new String(fileItem.getString().getBytes(
                                "ISO-8859-1"), "UTF-8");
                    } else if (fileItem.getFieldName().equals("chunks")) {
                        chunks = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("chunk")) {
                        chunk = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("file")) {
                        tempFileItem = fileItem;
                    }
                }
 
                // ��ʱĿ¼����������з�Ƭ�ļ�
                String tempFileDir = getTempFilePath()
                        + File.separator + id;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // ��Ƭ����ʱ��ǰ̨���ε����ϴ��ӿڣ�ÿ�ζ����ϴ��ļ���һ���ֵ���̨(Ĭ��ÿƬΪ5M)
                File tempPartFile = new File(parentFileDir, fileName + "_" + chunk
                        + ".part");
                FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(),
                        tempPartFile);
 
                // �Ƿ�ȫ���ϴ����
                // ���з�Ƭ�����ڲ�˵�������ļ��ϴ����
                boolean uploadDone = true;
                for (int i = 0; i < chunks; i++) {
                    File partFile = new File(parentFileDir, fileName + "_" + i
                            + ".part");
                    if (!partFile.exists()) {
                        uploadDone = false;
                    }
                }
                // ���з�Ƭ�ļ����ϴ����
                // �����з�Ƭ�ļ��ϲ���һ���ļ���
                if (uploadDone) {
                    File destTempFile = new File(getTempFilePath(), fileName);
                    for (int i = 0; i < chunks; i++) {
                        File partFile = new File(parentFileDir, fileName + "_"
                                + i + ".part");
 
                        FileOutputStream destTempfos = new FileOutputStream(
                                destTempFile, true);
 
                        FileUtils.copyFile(partFile, destTempfos);
 
                        destTempfos.close();
                    }
                    // �õ� destTempFile �������յ��ļ�
                    // ��ӵ��ļ�ϵͳ���ߴ洢��
                     
                    // ɾ����ʱĿ¼�еķ�Ƭ�ļ�
                    FileUtils.deleteDirectory(parentFileDir);
                    // ɾ����ʱ�ļ�
                    destTempFile.delete();
                     
                    ResponseUtil.responseSuccess(response, null);
                } else {
                    // ��ʱ�ļ�����ʧ��
                    if (chunk == chunks -1) {
                        FileUtils.deleteDirectory(parentFileDir);
                        ResponseUtil.responseFail(response, "500", "�ڲ�����");
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            ResponseUtil.responseFail(response, "500", "�ڲ�����");
        }
    }*/
}
