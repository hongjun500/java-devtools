package com.hongjun.web.autoconfigure.akka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongjun500
 * @date 2023/4/3 13:42
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: akka.actor的配置类
 */
@Data
@ConfigurationProperties(prefix = "akka.actor")
public class AkkaActorProperties {
	/**
	 * 默认开启
	 */
	private boolean enabled = true;

	/**
	 * 创建一个actorSystem的名字，需要保证唯一性
	 */
	private String actorSystemName;
}
