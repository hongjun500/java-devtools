package com.hongjun.actor;

import com.hongjun.User;
import com.hongjun.akka.ActorRefBean;
import com.hongjun.akka.BaseActor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DemoActor extends BaseActor<DemoActor> {

	private User user;

	@Override
	protected Class<DemoActor> getMessageType() {
		return DemoActor.class;
	}

	@Override
	protected void onReceiveMsg(DemoActor demoActor) {

		log.info("-----------------DemoActor李在赣神魔");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
