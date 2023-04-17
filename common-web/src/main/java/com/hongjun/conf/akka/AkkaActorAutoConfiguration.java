package com.hongjun.conf.akka;

import akka.actor.ActorSystem;
import com.hongjun.akka.ActorRefBean;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.inject.Singleton;

import java.util.Optional;

import static com.hongjun.akka.SpringExtension.SpringExtProvider;

/**
 * @author hongjun500
 * @date 2023/2/13 15:56
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义akka-actor配置
 */
@Log4j2
@Configuration
@EnableConfigurationProperties({AkkaActorProperties.class})
@ConditionalOnProperty(prefix = "akka.actor",value = "enabled", havingValue = "true")
public class AkkaActorAutoConfiguration {

	@Autowired
	private AkkaActorProperties akkaActorProperties;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConditionalOnMissingBean
	// 单例
	@Singleton
	public ActorSystem actorSystem() {
		log.info("---初始化创建ActorSystem, name=[{}]", akkaActorProperties.getActorSystemName());
		ActorSystem actorSystem = ActorSystem.create(Optional.ofNullable(akkaActorProperties.getActorSystemName()).orElse("actorSystem"));
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
