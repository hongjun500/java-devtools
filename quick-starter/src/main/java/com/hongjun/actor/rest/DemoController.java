package com.hongjun.actor.rest;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import com.google.common.collect.Lists;
import com.hongjun.actor.DemosActor;
import com.hongjun.actor.User;
import com.hongjun.akka.ActorRefBean;
import com.hongjun.response.CommonReturnType;
import com.hongjun.websocket.CustomTextWebSocketHandler;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

	@Autowired
	CustomTextWebSocketHandler customTextWebSocketHandler;

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

	@GetMapping(value = "/err/{fasfd}")
	public CommonReturnType<Object> err(@PathVariable String fasfd, @RequestParam String id) {
		int i = 1/1;
		return CommonReturnType.create(fasfd + id + i);
	}

	@GetMapping(value = "/openai")
	public CommonReturnType<Object> openai() {
		String url = "https://api.openai.com/v1/chat/completions";
		String body = "{\n" +
				"     \"model\": \"text-davinci-003\",\n" +
				"     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\n" +
				"     \"temperature\": 0.7\n" +
				"   }";

		String body2 = "\"model\": \"text-davinci-003\",\n" +
				"  \"prompt\": \"Say this is a test\",\n" +
				"  \"max_tokens\": 7,\n" +
				"  \"temperature\": 0,\n" +
				"  \"top_p\": 1,\n" +
				"  \"n\": 1,\n" +
				"  \"stream\": false,\n" +
				"  \"logprobs\": null,\n" +
				"  \"stop\": \"\\n\"";
		String post = HttpRequest.post(url).
				setHttpProxy("127.0.0.1",10809).
				header("Authorization", "Bearer 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")
				.body(body2)
				.execute().
				body();

		return CommonReturnType.create(post);
	}
}
