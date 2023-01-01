package com.hongjun.response;

import lombok.Data;

/**
 * @author hongjun500
 * @date 2022/12/30 17:38
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 通用的返回类型
 */
@Data
public class CommonReturnType<T> {
	/**
	 * 对应返回处理结果(成功与否)
	 * success
	 * fail
	 */
	private String status;
	/**
	 * 若status=success,则对应data内返回前端需要的json数据
	 * 若status=fail,则对应data内使用通用的错误码格式
	 */
	/**
	 * 返回的数据
	 */
	private T data;

	/**
	 * 定义一个通用的创建方法
	 * @param object 数据
	 * @param status success/fail
	 */
	public static<T> CommonReturnType<T> create(T object, String status){
		CommonReturnType<T> type = new CommonReturnType<>();
		type.setData(object);
		type.setStatus(status);
		return type;
	}


	/**
	 * 定义一个返回成功并且有数据的通用创建方法
	 * @param object 返回数据
	 * @return CommonReturnType
	 */
	public static<T> CommonReturnType<T> create(T object){
		return CommonReturnType.create(object,"success");
	}
	/**
	 * 定义一个返回成功但没有有数据的通用创建方法
	 * @return CommonReturnType
	 */
	public static<T> CommonReturnType<T> create(){
		return CommonReturnType.create(null);
	}


}
