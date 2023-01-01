package com.hongjun.constant;

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
	 * 请求的header中携带的userId
	 */
	public static final String HEAD_USER_ID = "userId";
	/**
	 * 请求的header中携带的userName
	 */
	public static final String HEAD_USER_NAME = "userName";


	/**
	 * 请求头中的需要处理的参数集
	 * 如果请求头中有这些参数那么可以直接放到请求体中
	 * 继承某一个含有以下参数的DTO,在post请求时可以从body里面直接拿到对应需要的参数
	 */
	public static final String[] HEAD_PARAMS = new String[]{HEAD_USER_ID, HEAD_USER_NAME};

	/**
	 * 携带的token
	 */
	public static final String HEAD_TOKEN = "token";


}
