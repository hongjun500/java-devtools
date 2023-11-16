package com.hongjun.actor;

import com.hongjun.web.akka.BaseActor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author hongjun500
 * @date 2023/2/20 14:51
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
@Component(value = "demo")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DemoActor extends BaseActor<User> {



	@Override
	protected Class<User> getMessageType() {
		return User.class;
	}

	@Override
	protected void onReceiveMsg(User demoActor) {
		log.info("user.id={}", demoActor.getId());
		log.info("-----------------DemoActor---user李在赣神魔");
	}

}
