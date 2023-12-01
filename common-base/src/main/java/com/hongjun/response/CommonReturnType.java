package com.hongjun.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hongjun500
 * @date 2022/12/30 17:38
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 通用的返回类型
 */
@Data
public class CommonReturnType<T> implements Serializable {
	/**
	 * 对应返回处理结果(成功与否)
	 * success
	 * fail
	 */
	private String status;
	/**
	 * 返回的数据
	 * 若 status=success,则对应 data 内返回前端需要的 json 数据
	 * 若 status=fail,则对应 data 内使用通用的错误码格式
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
	 * 定义一个返回成功但没有数据的通用创建方法
	 * @return CommonReturnType
	 */
	public static<T> CommonReturnType<T> create(){
		return CommonReturnType.create(null);
	}


}
