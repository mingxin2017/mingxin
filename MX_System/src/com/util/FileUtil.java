package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil {

	/**
     * ָ��λ�ÿ�ʼд���ļ�
     * @param tempFile  �����ļ�
     * @param outPath  ����ļ���·��(·��+�ļ���)
     * @throws IOException
     */
    public static void randomAccessFile( String outPath,File tempFile) throws IOException{
        RandomAccessFile  raFile = null;
        BufferedInputStream inputStream=null;
        try{
            File dirFile = new File(outPath);
            //�Զ�д�ķ�ʽ��Ŀ���ļ�
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
}
