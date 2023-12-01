package com.hongjun.web.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.hongjun.web.akka.SpringExtension.SpringExtProvider;

/**
 * @author hongjun500
 * @date 2023/2/10 17:03
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
public class ActorRefBean {
	private static ActorSystem actorSystem;
	private static final Map<String, ActorRef> actorRefMap = new ConcurrentHashMap<>();

	public ActorRefBean(ActorSystem actorSystem) {
		ActorRefBean.actorSystem = actorSystem;
	}

	public <T> void tell(Class<?> t_class, T msg){
		// 这里的 BeanName 通过 spring 注册 Bean 时默认的命名方式获取
		String beanName = StringUtils.uncapitalize(t_class.getSimpleName());
		Component component = AnnotationUtil.getAnnotation(t_class, Component.class);
		if (component != null && StrUtil.isNotEmpty(component.value())) {
			beanName = component.value();
		}
		ActorRef actorRef = actorRefMap.get(beanName);
		if (actorRef == null) {
			actorRef = initActorRef(beanName);
		}
		if (actorRef == null) {
			return;
		}
		actorRef.tell(msg, ActorRef.noSender());
	}

	private synchronized ActorRef initActorRef(String beanName){
		// double check，确保 actor 不会被重复初始化
		ActorRef actorRef = actorRefMap.get(beanName);
		if(Objects.isNull(actorRef)){
			try {
				actorRef = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props(beanName), beanName);
				actorRefMap.putIfAbsent(beanName, actorRef);
			} catch (Exception e) {
				log.error("initActorRef init error {}", e.getMessage());
			}
		}
		return actorRef;
	}
}
