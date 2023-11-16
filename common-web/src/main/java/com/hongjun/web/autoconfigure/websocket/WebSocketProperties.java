package com.hongjun.web.autoconfigure.websocket;/**
 * @author hongjun500
 * @date 2023/3/28 21:45
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongjun500
 * @date 2023/3/28 21:45
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description:
 */
@Data
@ConfigurationProperties(prefix = "common.websocket")
public class WebSocketProperties {
    /**
     * 是否开启
     */
    private boolean enabled;
    /**
     * 请求路径
     */
    private String path;
}
