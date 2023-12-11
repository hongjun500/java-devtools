package com.hongjun.web.akka;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * @author hongjun500
 * @date 2023/2/7 17:14
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

	public static final SpringExtension SpringExtProvider = new SpringExtension();

	@Override
	public SpringExt createExtension(ExtendedActorSystem system) {
		return new SpringExt();
	}


	public static class SpringExt implements Extension {
		private volatile ApplicationContext applicationContext;

		/**
		 * Used to initialize the Spring application context for the extension.
		 * @param applicationContext spring application context
		 */
		public void initialize(ApplicationContext applicationContext) {
			this.applicationContext = applicationContext;
		}

		/**
		 * Create a Props for the specified actorBeanName using the
		 * SpringActorProducer class.
		 *
		 * @param actorBeanName  The name of the actor bean to create Props for
		 * @return a Props that will create the named actor bean using Spring
		 */
		public Props props(String actorBeanName) {
			return Props.create(SpringActorProducer.class,
					applicationContext, actorBeanName);
		}
	}
}
