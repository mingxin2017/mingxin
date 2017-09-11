package com.mx.ssh.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ʱ��ת�����������ص�json�����е����ڸ�ʽת��Ϊָ����ʽyyyy-MM-dd HH:mm:ss��
 * Created by  on 2017/4/24.
 */
public class JsonDateValueProcessorUtil implements JsonValueProcessor {
    private String format ="yyyy-MM-dd HH:mm:ss";

    public JsonDateValueProcessorUtil() {
        super();
    }

    public JsonDateValueProcessorUtil(String format) {
        super();
        this.format = format;
    }

    public Object processArrayValue(Object paramObject,
                                    JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    public Object processObjectValue(String paramString, Object paramObject,
                                     JsonConfig paramJsonConfig) {
        return process(paramObject);
    }


    private Object process(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }
}
