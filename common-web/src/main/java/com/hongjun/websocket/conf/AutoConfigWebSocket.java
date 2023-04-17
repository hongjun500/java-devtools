package com.hongjun.websocket.conf;/**
 * @author hongjun500
 * @date 2023/3/28 21:29
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

import com.hongjun.websocket.CustomTextWebSocketHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created with 2019.3.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
@Configuration
@EnableWebSocket
@ConditionalOnProperty(prefix = "common.websocket", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = AutoConfigWebSocketProperties.class)
public class AutoConfigWebSocket implements WebSocketConfigurer {

    @Autowired
    private AutoConfigWebSocketProperties configWebSocketProperties;

    @Autowired
    private CustomTextWebSocketHandler customTextWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customTextWebSocketHandler,
                configWebSocketProperties.getPath()).
                // 允许跨域请求
                setAllowedOrigins("*");;
    }


    
   /* @Bean
    public WebSocketHandler customTextWebSocketHandler() {
        return new CustomTextWebSocketHandler();
    }*/

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("WebSocket server started!");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("WebSocket server stopped!");
    }
}
