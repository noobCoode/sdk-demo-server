package com.ringcenter.sdkdemo.controller;

import com.ringcenter.sdkdemo.model.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @author Xu Dongdong
 * @date 2020-5-3
 */
@Controller
public class FileWebsocketController {

    @Autowired
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板

    @MessageMapping("/files/{title}")
    @SendTo("/topic/files/{title}")
    public WebSocketMessage websocket(String content) {
        return new WebSocketMessage(true, content);
    }

    @Scheduled(fixedRate = 1000*5)
    public void whoCanEdit(){
        simpMessageSendingOperations.convertAndSend("/topic/files/who", "我是从服务器来的消息!");
    }
}
