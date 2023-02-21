package com.hongjun;

import akka.io.dns.internal.Message;
import com.hongjun.actor.DemoActor;
import com.hongjun.akka.ActorRefBean;
import com.hongjun.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjun500
 * @date 2023/2/21 14:34
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@RestController
@RequestMapping(value = "/actor")
public class DemoController {

	@Autowired
	ActorRefBean actorRefBean;

	@GetMapping(value = "")
	public CommonReturnType<String> actor() {
		User user = new User();
		actorRefBean.tell(DemoActor.class, user);
		return CommonReturnType.create();
	}
}
