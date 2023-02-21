package com.hongjun.conf;

import akka.actor.ActorSystem;
import com.hongjun.akka.ActorRefBean;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hongjun.akka.SpringExtension.SpringExtProvider;

/**
 * @author hongjun500
 * @date 2023/2/13 15:56
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义配置
 */
@Configuration
public class CommonAutoConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConditionalOnMissingBean
	public ActorSystem actorSystem() {
		ActorSystem actorSystem = ActorSystem.create("ActorSystem");
		SpringExtProvider.get(actorSystem).initialize(applicationContext);
		return actorSystem;
	}

	@Bean
	public Config akkaConfiguration() {
		return ConfigFactory.load();
	}

	@Bean
	public ActorRefBean actorRefBean(ActorSystem actorSystem){
		return new ActorRefBean(actorSystem);
	}
}
