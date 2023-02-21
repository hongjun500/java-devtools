package com.hongjun.actor;

import com.hongjun.akka.BaseActor;
import org.springframework.stereotype.Component;

/**
 * @author hongjun500
 * @date 2023/2/20 14:51
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Component
public class DemoActor extends BaseActor<DemoActor> {


	protected DemoActor() {
		super(DemoActor.class);
	}

	@Override
	protected Class<DemoActor> getMessageType() {
		return null;
	}

	@Override
	protected void onReceiveMsg(DemoActor demoActor) {

	}
}
