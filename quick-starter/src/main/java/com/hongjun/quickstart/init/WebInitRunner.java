package com.hongjun.quickstart.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author hongjun500
 * @date 2023/1/1 15:52
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description:
 */
@Slf4j
@Component
public class WebInitRunner implements ApplicationRunner {



    @Override
    public void run(ApplicationArguments args) {
        log.info("一个启动应用时的初始化操作");
    }
}
