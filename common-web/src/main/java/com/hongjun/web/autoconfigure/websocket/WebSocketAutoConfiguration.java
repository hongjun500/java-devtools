package com.hongjun.web.autoconfigure.websocket;


import com.hongjun.web.websocket.CustomTextWebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @author hongjun500
 * @date 2023/3/28 21:29
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Slf4j
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "common.websocket", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = WebSocketProperties.class)
public class WebSocketAutoConfiguration implements WebSocketConfigurer {

	private final WebSocketProperties configWebSocketProperties;

	private final CustomTextWebSocketHandler customTextWebSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(customTextWebSocketHandler,
						configWebSocketProperties.getPath()).
				// 允许跨域请求
						setAllowedOrigins("*");
		;
	}

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("WebSocket server started!");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		log.info("WebSocket server stopped!");
	}
}
