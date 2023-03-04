package com.hongjun.index.base;

public interface BaseIndexService {

    /**
     * 初始化Elasticsearch索引和映射
     * @param tClass 索引对应的Java类
     * @return 是否成功初始化索引和映射
     */
     boolean initIndexAndMapping(Class<?> tClass);
}
