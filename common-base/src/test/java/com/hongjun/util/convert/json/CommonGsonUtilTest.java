package com.hongjun.util.convert.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.List;

class CommonGsonUtilTest {

    class Person {
        String name;
        boolean enable;
    }


    @Test
    void convert() {
        String jsonString = "{\"name\":\"hongjun\",\"enable\":true}";

        // 将 json 字符串转换成对象
        Person person = CommonGsonUtil.fromJson(jsonString, Person.class);

        // 将对象转换成 json 字符串
        jsonString = CommonGsonUtil.toJson(person);

        // 将 json 字符串转换成  json  对象
        JsonObject jsonObject = CommonGsonUtil.fromJson(jsonString, JsonObject.class);
        String jsonArrString = "[{\"name\":\"hongjun\",\"enable\":true},{\"name\":\"hongjun\",\"enable\":false}]";
        // 将 json 字符串转换成 json 数组
        JsonArray jsonArray = CommonGsonUtil.fromJson(jsonArrString, JsonArray.class);

        // 将 json 对象转换成对象
        Person person2 = CommonGsonUtil.fromJson(jsonObject, Person.class);

        // 将 json 数组转换成对象数组
        List<Person> persons = CommonGsonUtil.fromJson(jsonArray, Person.class);

        // 将对象转换成json对象
        JsonObject jsonObject2 = CommonGsonUtil.toJsonObject(person);
        String jsonString1 = CommonGsonUtil.toJsonString(jsonObject2);


        // 将对象数组转换成 jso数组
        JsonArray jsonArray2 = CommonGsonUtil.toJsonArray(persons);
        String jsonString2 = CommonGsonUtil.toJsonString(jsonArray2);
    }

}