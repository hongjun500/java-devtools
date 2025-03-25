package com.hongjun.mongodb.common;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

/**
 * @author hongjun500
 * @date 2025-03-25 21:27
 * @tool ThinkPadX1隐士
 * Created with 2024.1.6.IntelliJ IDEA
 * Description:
 */
public class CommonRepositoryImpl<T, ID> extends SimpleMongoRepository<T, ID> implements CommonRepository<T, ID>{

    private final Class<T> tClass;
    private final MongoTemplate mt;

    public CommonRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoTemplate mt) {
        super(metadata, mt);
        this.tClass = metadata.getJavaType();
        this.mt = mt;
    }

    @Override
    public Class<T> getTClass() {
        return tClass;
    }

    @Override
    public MongoTemplate getMt() {
        return mt;
    }
}
