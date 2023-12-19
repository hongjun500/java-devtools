package com.hongjun.util.convert.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class CommonFastJsonUtil {
    /**
     * 将json字符串转换成对象
     *
     * @param jsonString json字符串
     * @param clazz      对象类型
     * @param <T>        对象泛型
     * @return 对象实例
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 将对象转换成json字符串
     *
     * @param object 对象实例
     * @return json字符串
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将json字符串转换成json对象
     *
     * @param jsonString json字符串
     * @return json对象
     */
    public static JSONObject toJsonObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    /**
     * 将json字符串转换成json数组
     *
     * @param jsonString json字符串
     * @return json数组
     */
    public static JSONArray toJsonArray(String jsonString) {
        return JSON.parseArray(jsonString);
    }

    /**
     * 将json对象转换成对象
     *
     * @param jsonObject json对象
     * @param clazz      对象类型
     * @param <T>        对象泛型
     * @return 对象实例
     */
    public static <T> T fromJsonObject(JSONObject jsonObject, Class<T> clazz) {
        return JSON.to(clazz, jsonObject);
    }

    /**
     * 将json数组转换成对象数组
     *
     * @param jsonArray json数组
     * @param clazz     对象类型
     * @param <T>       对象泛型
     * @return 对象数组
     */
    public static <T> T[] fromJsonArray(JSONArray jsonArray, Class<T[]> clazz) {
        return JSON.parseObject(jsonArray.toJSONString(), clazz);
    }

    /**
     * 将json数组转换成对象数组
     * @param jsonArrayStr jsonArrayStr
     * @param clazz     对象类型
     * @param <T>       对象泛型
     * @return 对象数组
     */
    public static <T> T[] fromJsonArray(String jsonArrayStr, Class<T[]> clazz) {
        return JSON.parseObject(jsonArrayStr, clazz);
    }

    /**
     * 将json对象转换成json字符串
     *
     * @param jsonObject json对象
     * @return json字符串
     */
    public static String toJsonString(JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    /**
     * 将json数组转换成json字符串
     *
     * @param jsonArray json数组
     * @return json字符串
     */
    public static String toJsonString(JSONArray jsonArray) {
        return jsonArray.toJSONString();
    }

    /**
     * 将对象转换成json对象
     *
     * @param object 对象实例
     * @return json对象
     */
    public static JSONObject toJsonObject(Object object) {
        return (JSONObject) JSON.toJSON(object);
    }

    /**
     * 将对象数组转换成json数组
     *
     * @param array 对象数组
     * @return json数组
     */
    public static JSONArray toJsonArray(Object[] array) {
        return (JSONArray) JSON.toJSON(array);
    }
}
