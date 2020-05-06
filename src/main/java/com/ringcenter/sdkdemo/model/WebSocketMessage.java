package com.ringcenter.sdkdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Xu Dongdong
 * @date 2020-5-4
 */
@Data
@AllArgsConstructor
public class WebSocketMessage {
    private boolean canWrite;
    private String message;
}
