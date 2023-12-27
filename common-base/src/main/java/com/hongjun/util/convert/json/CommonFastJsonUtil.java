package com.hongjun.util.convert.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;

import java.util.List;

public class CommonFastJsonUtil {

    /**
     * 将 json 字符串转换成泛型对象
     *
     * @param jsonString json字符串
     * @param clazz      对象类型
     * @param <T>        泛型对象
     * @return 对象实例
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 将 json 字符串转换成泛型对象，擦除泛型
     *
     * @param jsonString json字符串
     * @param clazz      对象类型
     * @param <T>        泛型对象
     * @return 对象实例
     */
    public static <T> T fromJson(String jsonString, TypeReference<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 将对象转换成 json 字符串
     *
     * @param object 对象实例
     * @return json 字符串
     */
    public static <T> String toJson(T object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将 json 字符串转换成 json 对象
     *
     * @param jsonString json 字符串
     * @return json 对象
     */
    public static JSONObject toJsonObject(String jsonString) {
        return JSON.parseObject(jsonString, JSONObject.class);
    }

    /**
     * 将 json 字符串转换成 json 数组
     *
     * @param jsonString json 字符串
     * @return json数组
     */
    public static JSONArray toJsonArray(String jsonString) {
        return JSON.parseArray(jsonString);
    }

    /**
     * 将 json 对象转换成泛型对象
     *
     * @param jsonObject json 对象
     * @param clazz      对象类型
     * @param <T>        泛型对象
     * @return 对象实例
     */
    public static <T> T fromJson(JSONObject jsonObject, Class<T> clazz) {
        return JSON.to(clazz, jsonObject);
    }

    /**
     * 将 json 数组转换成泛型对象数组
     *
     * @param jsonArray json 数组
     * @param clazz     对象类型
     * @param <T>       泛型对象
     * @return 泛型对象数组
     */
    public static <T> List<T> fromJson(JSONArray jsonArray, Class<T> clazz) {
        return fromJson(clazz, jsonArray.toJSONString());
    }

    public static <T> List<T> fromJson(Class<T> clazz, String jsonString) {
        return JSON.parseArray(jsonString, clazz);
    }

    /**
     * 将 json 对象转换成 json 字符串
     *
     * @param jsonObject json 对象
     * @return json 字符串
     */
    public static String toJsonString(JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    /**
     * 将 json 数组转换成 json 字符串
     *
     * @param jsonArray json 数组
     * @return json 字符串
     */
    public static String toJsonString(JSONArray jsonArray) {
        return jsonArray.toJSONString();
    }

    /**
     * 将对象转换成 json 对象
     *
     * @param object 对象实例
     * @return json 对象
     */
    public static <T> JSONObject toJsonObject(T object) {
        return (JSONObject) JSON.toJSON(object);
    }

    /**
     * 将泛型对象数组转换成 json 数组
     *
     * @param tList 泛型对象数组
     * @return json 数组
     */
    public static <T> JSONArray toJsonArray(List<T> tList) {
        return (JSONArray) JSON.toJSON(tList);
    }
}
