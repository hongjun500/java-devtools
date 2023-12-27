package com.hongjun.util.convert.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonFastJsonUtilTest {

    @Setter
    @Getter
    class Person {
        String name;
        boolean enable;
    }

    @Test
    void convert() {
        String jsonString = "{\"name\":\"我的盲僧阐释你们的梦\",\"enable\":true}";

        // 将 json 字符串转换成对象
        Person person = CommonFastJsonUtil.fromJson(jsonString, Person.class);

        // 将对象转换成 json 字符串
        jsonString = CommonFastJsonUtil.toJson(person);

        // 将 json 字符串转换成  json  对象
        JSONObject jsonObject = CommonFastJsonUtil.fromJson(jsonString, JSONObject.class);
        String jsonArrString = "[{\"name\":\"hongjun\",\"enable\":true},{\"name\":\"hongjun\",\"enable\":false}]";
        // 将 json 字符串转换成 json 数组
        JSONArray jsonArray = CommonFastJsonUtil.fromJson(jsonArrString, JSONArray.class);

        // 将 json 对象转换成对象
        Person person2 = CommonFastJsonUtil.fromJson(jsonObject, Person.class);

        // 将 json 数组转换成对象数组
        List<Person> persons = CommonFastJsonUtil.fromJson(jsonArray, Person.class);

        // 将对象转换成json对象
        JSONObject jsonObject2 = CommonFastJsonUtil.toJsonObject(person);
        String jsonString1 = CommonFastJsonUtil.toJsonString(jsonObject2);


        // 将对象数组转换成 jso数组
        JSONArray jsonArray2 = CommonFastJsonUtil.toJsonArray(persons);
        String jsonString2 = CommonFastJsonUtil.toJsonString(jsonArray2);
    }



    class PersonTest {
        String name;
        boolean enable;
    }

    @Test
    void convertJson() {
        String jsonString = "{\"name\":\"我的盲僧阐释你们的梦\",\"enable\":true}";
        PersonTest person = JSON.parseObject(jsonString, PersonTest.class);
        assertFalse(person.enable);
        assertNull(person.name);

        Gson gson = new GsonBuilder().create();
        PersonTest person1 = gson.fromJson(jsonString, PersonTest.class);
        assertTrue(person1.enable);
        assertEquals("我的盲僧阐释你们的梦", person1.name);
    }
}