package com.hongjun.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

/**
 * @author hongjun500
 * @date 2023/11/21 13:08
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
// 使用 @ServerEndpoint 注解表示此类是一个 WebSocket 端点
// 通过 value 注解，指定 websocket 的路径
@ServerEndpoint(value = "/channel/echo")
@Component
public class EchoChannel {

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoChannel.class);

	private Session session;

	// 收到消息
	@OnMessage
	public void onMessage(String message) throws IOException {

		LOGGER.info("[websocket] 收到消息：id={}，message={}", this.session.getId(), message);

		if (message.equalsIgnoreCase("bye")) {
			// 由服务器主动关闭连接。状态码为 NORMAL_CLOSURE（正常关闭）。
			this.session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Bye"));;
			return;
		}


		this.session.getAsyncRemote().sendText("["+ Instant.now().toEpochMilli() +"] Hello " + message);
	}

	// 连接打开
	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig){
		// 保存 session 到对象
		this.session = session;
		LOGGER.info("[websocket] 新的连接：id={}", this.session.getId());
	}

	// 连接关闭
	@OnClose
	public void onClose(CloseReason closeReason){
		LOGGER.info("[websocket] 连接断开：id={}，reason={}", this.session.getId(),closeReason);
	}

	// 连接异常
	@OnError
	public void onError(Throwable throwable) throws IOException {

		LOGGER.info("[websocket] 连接异常：id={}，throwable={}", this.session.getId(), throwable.getMessage());

		// 关闭连接。状态码为 UNEXPECTED_CONDITION（意料之外的异常）
		this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
	}
}