package com.hongjun.akka;

import akka.actor.AbstractActor;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * @author hongjun500
 * @date 2023/2/7 17:37
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: Actor的基类，每次注入时都会创建一个新的实例
 */
@Log4j2
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class BaseActor<T> extends AbstractActor {
// public abstract class BaseActor<T> extends UntypedAbstractActor {
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(getMessageType(), this::onReceiveMsg)
				.build();
	}

	@Override
	public void preStart() {
		log.info("Actor started: {}", self().path());
	}

	@Override
	public void postStop() {
		log.info("Actor stopped: {}", self().path());
	}

	/**
	 * 获取消息类型
	 * @return 消息类型
	 */
	protected abstract Class<T> getMessageType();

	/**
	 * 处理接收到的消息
	 * @param msg 接收到的消息
	 */
	protected abstract void onReceiveMsg(T msg);



}
