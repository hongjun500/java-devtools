package com.hongjun.util.convert.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

class CommonGsonUtilTest {

    class Person {
        String name;
        boolean enable;
    }


    @Test
    void contextTest() {
        String jsonString = "{\"name\":\"hongjun\",\"enable\":true}";

        // 将 json 字符串转换成对象
        Person person = CommonGsonUtil.fromJson(jsonString, Person.class);

        // 将对象转换成 json 字符串
        jsonString = CommonGsonUtil.toJson(person);

        // 将 json 字符串转换成  json  对象
        JsonObject jsonObject = CommonGsonUtil.toJsonObject(jsonString);
        String jsonArrString = "[{\"name\":\"hongjun\",\"enable\":true},{\"name\":\"hongjun\",\"enable\":false}]";
        // 将 json 字符串转换成 json 数组
        JsonArray jsonArray = CommonGsonUtil.toJsonArray(jsonArrString);

        // 将 json 对象转换成对象
        Person person2 = CommonGsonUtil.fromJsonObject(jsonObject, Person.class);

        // 将 json 数组转换成对象数组
        Person[] persons = CommonGsonUtil.fromJsonArray(jsonArray, Person[].class);

        // 将对象转换成json对象
        JsonObject jsonObject2 = CommonGsonUtil.toJsonObject(person);

        // 将对象数组转换成 jso数组
        JsonArray jsonArray2 = CommonGsonUtil.toJsonArray(persons);
    }

}