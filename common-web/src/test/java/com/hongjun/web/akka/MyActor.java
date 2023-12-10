package com.hongjun.web.akka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * @author hongjun500
 * @date 2023/11/16 17:21
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class MyActor extends BaseActor<String> {



	@Override
	protected Class<String> getMessageType() {
		return String.class;
	}

	@Override
	protected void onReceiveMsg(String msg) {
		log.info("msg = " + msg);
	}
}
