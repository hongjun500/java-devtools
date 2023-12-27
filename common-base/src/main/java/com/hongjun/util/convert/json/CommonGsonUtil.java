package com.hongjun.util.convert.json;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class CommonGsonUtil {
    private static final Gson gson = new GsonBuilder().create();

    /**
     * 将 json 字符串转换成泛型对象
     *
     * @param jsonString json字符串
     * @param clazz      对象类型
     * @param <T>        泛型对象
     * @return 对象实例
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    /**
     * 将泛型对象转换成 json 字符串
     *
     * @param object 对象实例
     * @return json字符串
     */
    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }

    /**
     * 将 json 字符串转换成 json 对象
     *
     * @param jsonString json字符串
     * @return json对象
     * @deprecated Use {@link #fromJson(String, Class)} instead
     */
    @Deprecated
    public static JsonObject fromJson(String jsonString) {
        return gson.fromJson(jsonString, JsonObject.class);
    }

    /**
     * 将json字符串转换成json数组
     *
     * @param jsonString json字符串
     * @return json数组
     * @deprecated Use {@link #fromJson(String, Class)} instead
     */
    @Deprecated // Use fromJson instead
    public static JsonArray toJsonArray(String jsonString) {
        return gson.fromJson(jsonString, JsonArray.class);
    }

    /**
     * 将 json 对象转换成泛型对象
     *
     * @param jsonObject json对象
     * @param clazz      对象类型
     * @param <T>        泛型对象
     * @return 对象实例
     */
    public static <T> T fromJson(JsonObject jsonObject, Class<T> clazz) {
        return gson.fromJson(jsonObject, clazz);
    }

    /**
     * 将 json 数组转换成泛型对象数组
     *
     * @param jsonArray json数组
     * @param clazz     对象类型
     * @param <T>       泛型对象
     * @return 对象数组
     */
    public static <T> List<T> fromJson(JsonArray jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            list.add(fromJson(jsonElement.getAsJsonObject(), clazz));
        }
        return list;
    }

    /**
     * 将 json 对象转换成 json 字符串
     *
     * @param jsonObject json 对象
     * @return json 字符串
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
    public static <T> JsonObject toJsonObject(T object) {
        JsonElement jsonElement = gson.toJsonTree(object);
        return jsonElement.getAsJsonObject();
    }

    /**
     * 将对象数组转换成 json 数组
     *
     * @param tList 对象数组
     * @return json数组
     */
    public static <T> JsonArray toJsonArray(List<T> tList) {
        JsonElement jsonElement = gson.toJsonTree(tList);
        return jsonElement.getAsJsonArray();
    }
}
