package com.ringcenter.sdkdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xu Dongdong
 * @date 2020-5-18
 */
@Component
@ServerEndpoint("/websocket/{title}/{jSessionId}")
@Slf4j
public class WebSocketServer {

    private static final  long RELEASE_TIME = 60000L;

    /**
     * 存放
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, WebSocketServer>> webSocketServerConcurrentHashMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Timer> timerConcurrentHashMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, String> currentSessionWriteHashMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收sid
     */
    private String title = "";

    private String jSessionId = "";


    /**
     * 连接建立成功调用的方法
     **/
    @OnOpen
    public void onOpen(Session session, @PathParam("title") String title, @PathParam("jSessionId") String jSessionId) throws IOException {
        this.session = session;
        //先取出websocketServers，再往里放一个，最后在put到webSocketServerConcurrentHashMap
        ConcurrentHashMap<String, WebSocketServer> webSocketServers = Optional.ofNullable(webSocketServerConcurrentHashMap.get(title)).orElse(new ConcurrentHashMap<>());

        webSocketServers.put(jSessionId, this);

        webSocketServerConcurrentHashMap.put(title, webSocketServers);
        //加计时器
        //获取一个jSessionId的数组
        List<String> jSessionIds = new ArrayList<>();
        webSocketServerConcurrentHashMap.get(title).forEach((s, webSocketServer) -> jSessionIds.add(s));

        //如果只有一个人，直接让他写
        if(jSessionIds.size()==1){
            sendMessage(JSONObject.toJSONString(new MessageInfo(jSessionIds.get(0), null, true)));
        }
        //有多个人的话进入计时器状态
        try {
            timerConcurrentHashMap.get(title).cancel();
        } catch (Exception e) {

        } finally {
            timerConcurrentHashMap.remove(title);

            timerConcurrentHashMap.put(title, new Timer());

            timerConcurrentHashMap.get(title).schedule(new TimerTask() {
                @SneakyThrows
                @Override
                public void run() {
                    log.info("当前" + title + "排队的人有" + jSessionIds);

                    String token = getRandomValueFromList(title, jSessionIds, currentSessionWriteHashMap.get(title));

                    log.info("文档" + title + "轮到" + token + "编写");
                    boastMessage(JSONObject.toJSONString(new MessageInfo(token, null, true)), title);
                }
            }, 0L, RELEASE_TIME);

            log.info("开始编辑文档:" + title + ",当前在线编辑人数为" + webSocketServerConcurrentHashMap.get(title).size());
            this.title = title;
            this.jSessionId = jSessionId;
        }


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        try {
            //移除定时器
            timerConcurrentHashMap.get(title).cancel();
            timerConcurrentHashMap.remove(this.title);
        } catch (Exception e) {

        } finally {
            ConcurrentHashMap<String, WebSocketServer> webSocketServers = webSocketServerConcurrentHashMap.get(this.title);
            webSocketServers.remove(this.jSessionId);
            webSocketServerConcurrentHashMap.put(this.title, webSocketServers);

            log.info(this.title + "文本编辑，有一连接关闭！当前在线编辑人数为" + webSocketServerConcurrentHashMap.get(this.title).size());
        }

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("title") String title) {
        log.info("收到来自窗口" + title + "的信息:" + message);

        boastMessage(message, title);
    }

    private void boastMessage(String msg, String title) {
        ConcurrentHashMap<String, WebSocketServer> webSocketServers = webSocketServerConcurrentHashMap.get(title);
        //向当前编辑组内
        webSocketServers.forEach((s, webSocketServer) -> {
            try {
                webSocketServer.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    @Data
    @AllArgsConstructor
    public static class MessageInfo {
        /**
         * 可以编辑的jSessionId名称
         */
        String jSessionId;
        /**
         * 内容
         */
        String msg;

        /**
         * 判断是否是锁消息
         */
        boolean isLockMsg;
    }

    public String getRandomValueFromList(String title, List<String> list, String currentValue) {
        //是1不用随机
        if (list.size() == 1) {
            currentSessionWriteHashMap.put(title, currentValue);
            return currentValue;
        } else {
            int currentValueIndex = list.indexOf(currentValue);
            int randomIndex = new Random().nextInt(list.size());
            if (currentValueIndex == randomIndex) {
                randomIndex = (randomIndex + 1) % list.size();
            }
            currentSessionWriteHashMap.put(title, list.get(randomIndex));
            return list.get(randomIndex);
        }
    }
}
