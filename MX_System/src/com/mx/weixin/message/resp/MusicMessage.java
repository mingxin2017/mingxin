package com.mx.weixin.message.resp;

/**
* 类名: MusicMessage </br>
* 描述: 音乐消息 </br>
* 开发人员：wulm </br>
* 创建时间：  2017.6.2 </br>
* 发布版本：V1.0  </br>
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}