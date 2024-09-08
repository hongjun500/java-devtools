package com.hongjun.web.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author hongjun500
 * @date 2024/07/02 17:19
 * @tool ThinkPadX1隐士
 * Created with 2023.2.5.IntelliJ IDEA
 * Description: spring 上下文工具类
 */
@Component
@SuppressWarnings("NullableProblems")
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 通过 applicationContext 获取 bean
     *
     * @param clazz bean 类型
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过 applicationContext 获取 bean
     *
     * @param name bean 名称
     * @return bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

}
