package com.hongjun.web.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/3/9 15:24
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: spring 对象拷贝工具
 */
public class CommonBeanUtil extends BeanUtils {
    public static final Map<String, BeanCopier> COPIER_MAP = Maps.newConcurrentMap();

    public static void copyProperties(Object source, Object target) {
        if (source == null) {
            return;
        }
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    public static void copyProperties(Object source, Object target, Converter converter) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, converter);
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            var t = targetClass.getDeclaredConstructor().newInstance();
            copyProperties(source, t);
            return t;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException("Create new instance of " + targetClass + " failed: " + e.getMessage());
        }
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass, Converter converter) {
        try {
            var t = targetClass.getDeclaredConstructor().newInstance();
            copyProperties(source, t, converter);
            return t;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException("Create new instance of " + targetClass + " failed: " + e.getMessage());
        }

    }

    public static <T> List<T> copyList(Object[] source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        List<T> target = Lists.newArrayList();
        for (Object obj : source) {
            target.add(copyProperties(obj, targetClass));
        }
        return target;
    }

    public static List<?> copyList(List<?> source, Class<?> targetClass) {
        // todo Java8
        return source == null ? null : List.copyOf(source)
                .stream()
                .map(s -> copyProperties(s, targetClass))
                .toList();
    }

    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        var beanKey = generateKey(sourceClass, targetClass);
        return COPIER_MAP.computeIfAbsent(beanKey, k -> BeanCopier.create(sourceClass, targetClass, false));
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.getName() + "_" + class2.getName();
    }
}
