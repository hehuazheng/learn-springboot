package com.hzz;

import com.hzz.utils.JsonUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: hezz
 */
public class MyHandler extends TextWebSocketHandler {
    private ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    public MyHandler() {
        AtomicInteger i =  new AtomicInteger(0);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            queue.offer(String.valueOf(i.getAndAdd(1)));
        }, 0,5, TimeUnit.SECONDS);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        Map<String, String> map = JsonUtils.parseObject(payload, HashMap.class);
        System.out.println("接收数据: " + map);
        while (true ) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = queue.poll();
            session.sendMessage(new TextMessage("return " + payload + msg));
        }
    }
}
