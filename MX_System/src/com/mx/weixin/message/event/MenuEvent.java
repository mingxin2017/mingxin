package com.mx.weixin.message.event;

/**
* ����: MenuEvent </br>
* ����: �Զ���˵��¼� </br>
* ������Ա�� wulm </br>
* ����ʱ�䣺  2017.6.2 </br>
* �����汾��V1.0  </br>
 */
public class MenuEvent extends BaseEvent {
    // �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}