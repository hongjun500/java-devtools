package com.hongjun.common.websocket;/**
 * @author hongjun500
 * @date 2023/3/28 22:37
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

import com.hongjun.QuickStarterApplication;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author hongjun500
 * @date 2023/3/28 22:37
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class)
public class TestWebSocket {

    @Autowired
    private CustomTextWebSocketHandler customTextWebSocketHandler;

    @Test
    void testSendMessage() throws IOException {

        log.info("1");
    }
}
