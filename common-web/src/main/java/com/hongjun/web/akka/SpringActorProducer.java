package com.hongjun.web.akka;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @author hongjun500
 * @date 2023/2/7 17:17
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:Spring 依赖注入 Actor 的生产者
 */
public class SpringActorProducer implements IndirectActorProducer {

	final ApplicationContext applicationContext;

	final String actorBeanName;

	public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
		this.applicationContext = applicationContext;
		this.actorBeanName = actorBeanName;
	}

	@Override
	public Actor produce() {
		return applicationContext.getBean(actorBeanName, Actor.class);
	}

	@Override
	public Class<? extends Actor> actorClass() {
		Class<?> actorClass = applicationContext.getType(actorBeanName);
		if (actorClass == null || !Actor.class.isAssignableFrom(actorClass)) {
			throw new IllegalArgumentException("Invalid actor bean: " + actorBeanName);
		}
		return actorClass.asSubclass(Actor.class);
	}

	/*@Override
	public Actor produce() {
		return (Actor) applicationContext.getBean(actorBeanName);
	}

	@Override
	public Class<? extends Actor> actorClass() {
		return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
	}*/
}
