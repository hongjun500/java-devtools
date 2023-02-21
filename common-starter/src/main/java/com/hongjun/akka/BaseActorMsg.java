package com.hongjun.akka;

import lombok.Data;

/**
 * @author hongjun500
 * @date 2023/2/7 17:45
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
public class BaseActorMsg<T> {
	private T object;
}
