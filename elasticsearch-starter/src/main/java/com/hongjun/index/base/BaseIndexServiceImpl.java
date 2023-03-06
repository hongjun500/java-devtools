package com.hongjun.index.base;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class BaseIndexServiceImpl implements BaseIndexService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public synchronized boolean initIndexAndMapping(Class<?> tClass) {
        IndexOperations indexOps = elasticsearchOperations.indexOps(tClass);
        if (indexOps.exists()) {
            Map<String, Object> indexOpsMapping = indexOps.getMapping();
            if (CollUtil.isEmpty(indexOpsMapping)) {
                log.info("索引【{}】的mapping为空，进行初始化操作", indexOps.getIndexCoordinates().getIndexName());
                indexOps.createMapping(tClass);
            } else {
                // indexOps.getIndexCoordinates()一定不为null, indexOps.exists()的判断
                log.info("索引【{}】的mapping更新", indexOps.getIndexCoordinates().getIndexName());
                return indexOps.putMapping(tClass);
            }
            return true;
        }
        boolean indexCreated = indexOps.create();
        if (indexCreated) {
            indexOps.createMapping(tClass);
            log.info("索引【{}】及其mapping初始化", indexOps.getIndexCoordinates().getIndexName());
            return true;
        }
        return false;
    }

    @Override
    public <T> void refreshDataToEs(List<T> list, Class<?> clazz) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        List<IndexQuery> queries = new ArrayList<>();
        list.forEach(obj ->{
            queries.add(new IndexQueryBuilder()
                    .withObject(obj)
                    .build());
        });
        elasticsearchOperations.bulkIndex(queries, clazz);
    }
}