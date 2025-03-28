package com.hongjun.web.autoconfigure.akka;

import akka.actor.ActorSystem;
import com.hongjun.web.akka.ActorRefBean;
import com.hongjun.web.akka.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * @author hongjun500
 * @date 2023/2/13 15:56
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义 akka-actor 配置
 */
@Slf4j
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(AkkaActorProperties.class)
@ConditionalOnProperty(prefix = "common.akka.actor", name = "enabled", havingValue = "true")
public class AkkaActorAutoConfiguration {

	private final AkkaActorProperties akkaActorProperties;

	private final ApplicationContext applicationContext;

	@Bean
	@ConditionalOnMissingBean
	@Singleton // 单例，保证 actorSystem 的唯一性
	public ActorSystem actorSystem() {
		log.info("---Initial creation ActorSystem, name=[{}]", akkaActorProperties.getActorSystemName());
		ActorSystem actorSystem = ActorSystem.create(Optional.ofNullable(akkaActorProperties.getActorSystemName()).orElse("actorSystem"));
		SpringExtension.SpringExtProvider.get(actorSystem).initialize(applicationContext);
		return actorSystem;
	}

	@Bean
	public Config akkaConfiguration() {
		return ConfigFactory.load();
	}

	@Bean
	@DependsOn("actorSystem")
	public ActorRefBean actorRefBean(ActorSystem actorSystem) {
		return new ActorRefBean(actorSystem);
	}
}
