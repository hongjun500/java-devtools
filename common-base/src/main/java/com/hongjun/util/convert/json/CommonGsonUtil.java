package com.hongjun.util.convert.json;

import com.google.gson.*;

public class CommonGsonUtil {
    private static final Gson gson = new GsonBuilder().create();

    /**
     * 将json字符串转换成对象
     *
     * @param jsonString json字符串
     * @param clazz      对象类型
     * @param <T>        对象泛型
     * @return 对象实例
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    /**
     * 将对象转换成json字符串
     *
     * @param object 对象实例
     * @return json字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将json字符串转换成json对象
     *
     * @param jsonString json字符串
     * @return json对象
     */
    public static JsonObject toJsonObject(String jsonString) {
        return gson.fromJson(jsonString, JsonObject.class);
    }

    /**
     * 将json字符串转换成json数组
     *
     * @param jsonString json字符串
     * @return json数组
     */
    public static JsonArray toJsonArray(String jsonString) {
        return gson.fromJson(jsonString, JsonArray.class);
    }

    /**
     * 将json对象转换成对象
     *
     * @param jsonObject json对象
     * @param clazz      对象类型
     * @param <T>        对象泛型
     * @return 对象实例
     */
    public static <T> T fromJsonObject(JsonObject jsonObject, Class<T> clazz) {
        return gson.fromJson(jsonObject, clazz);
    }

    /**
     * 将json数组转换成对象数组
     *
     * @param jsonArray json数组
     * @param clazz     对象类型
     * @param <T>       对象泛型
     * @return 对象数组
     */
    public static <T> T[] fromJsonArray(JsonArray jsonArray, Class<T[]> clazz) {
        return gson.fromJson(jsonArray, clazz);
    }

    /**
     * 将json对象转换成json字符串
     *
     * @param jsonObject json对象
     * @return json字符串
     */
    public static String toJsonString(JsonObject jsonObject) {
        return gson.toJson(jsonObject);
    }

    /**
     * 将json数组转换成json字符串
     *
     * @param jsonArray json数组
     * @return json字符串
     */
    public static String toJsonString(JsonArray jsonArray) {
        return gson.toJson(jsonArray);
    }

    /**
     * 将对象转换成json对象
     *
     * @param object 对象实例
     * @return json对象
     */
    public static JsonObject toJsonObject(Object object) {
        JsonElement jsonElement = gson.toJsonTree(object);
        return jsonElement.getAsJsonObject();
    }

    /**
     * 将对象数组转换成json数组
     *
     * @param array 对象数组
     * @return json数组
     */
    public static JsonArray toJsonArray(Object[] array) {
        JsonElement jsonElement = gson.toJsonTree(array);
        return jsonElement.getAsJsonArray();
    }
}
