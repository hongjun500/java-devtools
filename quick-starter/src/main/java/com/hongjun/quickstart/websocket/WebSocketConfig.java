package com.hongjun.quickstart.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author hongjun500
 * @date 2023/11/20 17:03
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Configuration
// @EnableWebSocketMessageBroker
public class WebSocketConfig {


	// @Bean
	public ServerEndpointExporter serverEndpointExporter() {

		ServerEndpointExporter exporter = new ServerEndpointExporter();

		// 手动注册 WebSocket 端点 或者 EchoChannel 加上 @Component 注解
		// exporter.setAnnotatedEndpointClasses(EchoChannel.class);

		return exporter;
	}
}
