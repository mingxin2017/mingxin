package com.weixin.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weixin.pojo.Token;
import com.weixin.task.WeixinGetTokenTimerTask;
import com.weixin.util.WeixinSignUtil;
import com.weixin.util.WeixinUtil;

/**
* ����: MenuManager </br>
* ������ com.souvc.weixin.main
* ����:�˵��������� </br>
* ������Ա�� liuhf </br>
* ����ʱ�䣺  2015-12-1 </br>
* �����汾��V1.0  </br>
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void createMenu() {
    	
    	System.out.println("��̬�����˵�");
    	
        // �������û�Ψһƾ֤
        String appId = WeixinSignUtil.AppID;
        // �������û�Ψһƾ֤��Կ
        String appSecret = WeixinSignUtil.AppSecret;

        // ���ýӿڻ�ȡaccess_token
        Token accessToken = WeixinGetTokenTimerTask.token;

        if (null != accessToken) {
            // ���ýӿڴ����˵�
            int result = WeixinUtil.createMenu(getMenu(), accessToken.getAccessToken());

            // �жϲ˵��������
            if (0 == result){
                log.info("�˵������ɹ���");
                System.out.println("�˵������ɹ���");
            }
            else{
                log.info("�˵�����ʧ�ܣ������룺" + result);
                System.out.println("�˵�����ʧ�ܣ�");
            }
        }
    }

    /**
     * ��װ�˵�����
     * 
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("����Ԥ��");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("������ѯ");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("�ܱ�����");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("��ʷ�ϵĽ���");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn15 = new CommonButton();
        btn14.setName("�������15");
        btn14.setType("click");
        btn14.setKey("15");
        
        CommonButton btn21 = new CommonButton();
        btn21.setName("�����㲥");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("������Ϸ");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("��Ů��̨");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("����ʶ��");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("�������");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q��Ȧ");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("��Ӱ���а�");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("��ĬЦ��");
        btn33.setType("click");
        btn33.setKey("33");

        
        /**
         * ΢�ţ�  mainBtn1,mainBtn2,mainBtn3�ײ�������һ���˵���
         */
        
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("��������1");
        //һ������4���Ӳ˵�
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

        
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("������վ2");
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });

        
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("��������3");
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

        
        /**
         * ��װ�����˵�
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
}