package com.hongjun.akka;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * @author hongjun500
 * @date 2023/2/7 17:37
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: Actor的基类，每次注入时都会创建一个新的实例
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class BaseActor<T> extends AbstractActor {

	protected final Class<T> messageType;

	protected LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	protected BaseActor(Class<T> messageType) {
		this.messageType = messageType;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(messageType, this::onReceiveMsg)
				.build();
	}

	protected abstract Class<T> getMessageType();
	protected abstract void onReceiveMsg(T msg);

}
