package com.hongjun.actor;

import com.hongjun.web.akka.BaseActor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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
public class DemosActor extends BaseActor<List<User>> {



	@Override
	protected Class<List<User>> getMessageType() {
		return (Class<List<User>>) (Class) List.class;
	}

	@Override
	protected void onReceiveMsg(List<User> userList) {
		userList.forEach(obj ->{
			log.info("user.id={}", obj.getId());
		});
		log.info("-----------------DemosActor---user李在赣神魔");
	}

}
