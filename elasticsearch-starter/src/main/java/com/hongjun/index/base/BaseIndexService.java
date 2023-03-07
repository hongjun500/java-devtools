package com.hongjun.index.base;

import java.util.List;

public interface BaseIndexService {

    /**
     * 初始化Elasticsearch索引和映射
     * @param clazz 索引对应的Java类
     * @return 是否成功初始化索引和映射
     */
     boolean initIndexAndMapping(Class<?> clazz);

    /**
     * 刷新数据到Es中
     * @param list 数据集
     * @param clazz 索引对应的Java类
     * @param <T> 泛型数据集
     */
     <T> void refreshDataToEs(List<T> list, Class<?> clazz);

    boolean delIndex(Class<?> clazz);
}
