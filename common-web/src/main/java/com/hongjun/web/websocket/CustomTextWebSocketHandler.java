package com.hongjun.web.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongjun500
 * @date 2023/3/28 21:15
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
@Component
public class CustomTextWebSocketHandler extends TextWebSocketHandler {

	private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(@NonNull WebSocketSession webSocketSession) throws Exception {
		webSocketSessionList.add(webSocketSession);
	}


	@Override
	public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
		log.info("websocket 连接关闭");
	}

	@Override
	protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
		// 接收的消息
		String messagePayload = message.getPayload();
		log.info("接收到 websocket 的消息{}", messagePayload);
		webSocketSession.sendMessage(new TextMessage(messagePayload));
	}


}
