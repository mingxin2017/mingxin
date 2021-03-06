package com.mx.weixin.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mx.weixin.pojo.Token;
import com.mx.weixin.task.WeixinGetTokenTimerTask;
import com.mx.weixin.util.WeixinSignUtil;
import com.mx.weixin.util.WeixinUtil;

/**
* 类名: MenuManager </br>
* 包名： com.souvc.weixin.main
* 描述:菜单管理器类 </br>
* 开发人员： liuhf </br>
* 创建时间：  2015-12-1 </br>
* 发布版本：V1.0  </br>
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void createMenu() {
    	
    	System.out.println("动态创建菜单");
    	
        // 第三方用户唯一凭证
        String appId = WeixinSignUtil.AppID;
        // 第三方用户唯一凭证密钥
        String appSecret = WeixinSignUtil.AppSecret;

        // 调用接口获取access_token
        Token accessToken = WeixinGetTokenTimerTask.token;

        if (null != accessToken) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), accessToken.getAccessToken());

            // 判断菜单创建结果
            if (0 == result){
                log.info("菜单创建成功！");
                System.out.println("菜单创建成功！");
            }
            else{
                log.info("菜单创建失败，错误码：" + result);
                System.out.println("菜单创建失败！错误码："+result);
            }
        }
    }

    /**
     * 组装菜单数据
     * 
     * @return
     */
    private static Menu getMenu() {
    	String baseUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=GOTOURL&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    	
        CommonButton btn11 = new CommonButton();
        btn11.setName("校友会");
        btn11.setType("view");
        btn11.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL",WeixinSignUtil.serverUrl+ "activitiesPublicity/getActivitiesPublicityList.action?type=1"));

        CommonButton btn12 = new CommonButton();
        btn12.setName("毕业季");
        btn12.setType("view");
        btn12.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"activitiesPublicity/getActivitiesPublicityList.action?type=2"));

        CommonButton btn13 = new CommonButton();
        btn13.setName("素质拓展");
        btn13.setType("view");
        btn13.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"activitiesPublicity/getActivitiesPublicityList.action?type=3"));

        CommonButton btn14 = new CommonButton();
        btn14.setName("亲子活动");
        btn14.setType("view");
        btn14.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"activitiesPublicity/getActivitiesPublicityList.action?type=4"));
        
        CommonButton btn15 = new CommonButton();
        btn15.setName("我要报名");
        btn15.setType("click");
        btn15.setKey("15");

        CommonButton btn21 = new CommonButton();
        btn21.setName("校园时报");
        btn21.setType("view");
        btn21.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"weixin/getSchoolNews.action"));

        CommonButton btn22 = new CommonButton();
        btn22.setName("乡村时报");
        btn22.setType("view");
        btn22.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"weixin/getCountryNews.action"));
        
        
        CommonButton btn23 = new CommonButton();
        btn23.setName("小记者申请");
        btn23.setType("view");
        btn23.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"mxReporterBusiness/loadReporterApply.action"));
        //setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb235c46c4c2740a9&redirect_uri=http://d1a7069951.iask.in/MX_System/mxReporterBusiness!loadReporterApply.action&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

        
        
        CommonButton btn31 = new CommonButton();
        btn31.setName("教育培训");   
        btn31.setType("view");   
        btn31.setUrl("http://d1a7069951.iask.in/MX_System/weixin!getNotMyUser.action");

        CommonButton btn32 = new CommonButton();
        btn32.setName("新闻投稿");
        btn32.setType("view");     
        btn32.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"weixin/loadEditNews.action"));
        //btn32.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb235c46c4c2740a9&redirect_uri=http://d1a7069951.iask.in/MX_System/weixin!loadAddNews.action&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
        //btn32.setUrl("http://mingxin.xicp.io/MX_System/weixin!loadEditNews.action");     
        
        CommonButton btn33 = new CommonButton();
        btn33.setName("校园记者");
        btn33.setType("view");
        btn33.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL", WeixinSignUtil.serverUrl+"mxReporterBusiness/loadReporterManage.action"));
        //setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb235c46c4c2740a9&redirect_uri=http://d1a7069951.iask.in/MX_System/mxReporterBusiness!loadReporterManage.action&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

        CommonButton btn34 = new CommonButton(); 
        btn34.setName("活动空间");
        btn34.setType("view");
        btn34.setUrl((baseUrl.replace("APPID",WeixinSignUtil.AppID)).replace("GOTOURL",WeixinSignUtil.serverUrl+ "activitiesMySpace/gotoActivitiesMySpaceList.action"));
        
        CommonButton btn35 = new CommonButton();
        btn35.setName("其他功能");
        btn35.setType("click");
        btn35.setKey("35");
        
        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */
        
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("鸣心活动");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14,btn15 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("鸣心时报");
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 , btn23 });

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("个人中心");
        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32, btn33,btn34,btn35 });

        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
}