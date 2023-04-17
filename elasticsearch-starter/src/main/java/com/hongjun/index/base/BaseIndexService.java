package com.hongjun.index.base;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;

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


    boolean delDoc();

    default Query Query(String fieldName, String param) {
        Criteria criteria = new Criteria(fieldName).is(param);
        return new CriteriaQuery(criteria);
    }

    default Query query() {
        Query query = NativeQuery.builder()
                .withAggregation("id", Aggregation.of(a -> a.terms(ta -> ta.field("last").size(10))))
                .withQuery(q -> q
                        .match(m -> m.field("first").query("sdf1"))
                )
                .withPageable(Pageable.ofSize(10))
                .build();
        return query;
    }
}
