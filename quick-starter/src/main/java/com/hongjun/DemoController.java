package com.hongjun;

import akka.io.dns.internal.Message;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.hongjun.actor.DemoActor;
import com.hongjun.actor.DemosActor;
import com.hongjun.akka.ActorRefBean;
import com.hongjun.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
	@Autowired
	ApplicationContext applicationContext;

	@GetMapping(value = "")
	public CommonReturnType<String> actor() {
		// String demoActor = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "DemoActor");
		String demoActor = StringUtils.uncapitalize("DemoActor");
		User user = new User();
		user.setId(RandomUtil.randomInt());
		actorRefBean.tell(DemosActor.class, user);
		List<User> users = Lists.newArrayList(user);
		actorRefBean.tell(DemosActor.class, users);
		return CommonReturnType.create();
	}
}
