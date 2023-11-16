package com.hongjun.web.akka;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
