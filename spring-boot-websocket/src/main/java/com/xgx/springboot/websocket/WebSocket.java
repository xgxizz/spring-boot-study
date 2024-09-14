package com.xgx.springboot.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

// https://chatgpt.com/share/9e15229d-1307-4551-88aa-62fb2b17fc43 分布式场景解决方案
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocket {
	private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();
	private Session session;

	public static void sendMessage(Object message, String userId) {
		WebSocket webSocket = webSocketMap.get(userId);
		if (webSocket != null) {
			try {
				webSocket.session.getBasicRemote().sendText(JSON.toJSONString(message));
				System.out.println("【websocket消息】发送消息成功,用户=" + userId + ",消息内容" + message.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static ConcurrentHashMap<String, WebSocket> getWebSocketMap() {
		return webSocketMap;
	}

	public static void setWebSocketMap(ConcurrentHashMap<String, WebSocket> webSocketMap) {
		WebSocket.webSocketMap = webSocketMap;
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("userId") String userId) {
		this.session = session;
		webSocketMap.put(userId, this);
		sendMessage("CONNECT_SUCCESS", userId);
		System.out.println("【websocket消息】有新的连接,连接id" + userId);
	}

	@OnClose
	public void onClose(@PathParam("userId") String userId) {
		webSocketMap.remove(userId);
		System.out.println("【websocket消息】连接断开,总数:" + webSocketMap.size());
	}

	@OnMessage
	public void onMessage(String message, @PathParam("userId") String userId) {
		if (!message.equals("ping")) {
			System.out.println("【websocket消息】收到客户端发来的消息:" + message);
			sendMessage("收到你的消息: " + message, userId);
		}
	}
}
