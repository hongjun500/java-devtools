package com.hongjun.web.constant;

import lombok.Data;

/**
 * @author hongjun500
 * @date 2022/12/30 13:28
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 常量
 */
@Data
public class CustomConstants {

	/**
	 * 请求 header 中携带的 userId
	 */
	public static final String HEAD_USER_ID = "userId";
	/**
	 * 请求 header 中携带的 userName
	 */
	public static final String HEAD_USER_NAME = "userName";


	/**
	 * 请求 header 中的需要处理的参数集
	 * 如果请求 header 有这些参数那么可以直接放到请求体中
	 * 继承某一个含有以下参数的 DTO,在 POST 请求时可以从 body 里面直接拿到对应需要的参数
	 */
	public static final String[] HEAD_PARAMS = new String[]{HEAD_USER_ID, HEAD_USER_NAME};

	/**
	 * 请求 header 中携带的 token
	 */
	public static final String HEAD_TOKEN = "token";
}
