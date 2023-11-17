package com.hongjun.web.akka;

import com.hongjun.web.autoconfigure.akka.AkkaActorAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class ActorRefBeanTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withPropertyValues("akka.actor.enabled=true")
			.withPropertyValues("akka.actor.actorSystemName=common-web-actor-system")
			.withConfiguration(AutoConfigurations.of(AkkaActorAutoConfiguration.class))
			.withBean(MyActor.class);


	@Test
	void tell() {
		assertThat(contextRunner).isNotNull();
		contextRunner.run(context -> {
			assertThat(context).hasSingleBean(ActorRefBean.class);
			assertThat(context).getBean("actorRefBean").isSameAs(context.getBean(ActorRefBean.class));
			ActorRefBean actorRefBean = context.getBean(ActorRefBean.class);
			actorRefBean.tell(MyActor.class, "hello");
		});
	}

}