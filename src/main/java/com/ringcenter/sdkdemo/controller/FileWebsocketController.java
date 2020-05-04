package com.ringcenter.sdkdemo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Xu Dongdong
 * @date 2020-5-3
 */
@Controller
public class FileWebsocketController {

    @MessageMapping("/files/{title}")
    @SendTo("/topic/files/{title}")
    public String websocket(String content) {
        return content;
    }

}
