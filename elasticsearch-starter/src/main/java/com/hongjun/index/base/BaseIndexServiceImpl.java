package com.hongjun.index.base;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.DocumentOperations;
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

    @Autowired
    private DocumentOperations documentOperations;

    @Override
    public synchronized boolean initIndexAndMapping(Class<?> clazz) {
        IndexOperations indexOps = elasticsearchOperations.indexOps(clazz);
        if (indexOps.exists()) {
            Map<String, Object> indexOpsMapping = indexOps.getMapping();
            log.info("索引【{}】的mapping{}，更新索引", indexOps.getIndexCoordinates().getIndexName(), CollUtil.isEmpty(indexOpsMapping)?"为空":"不为空");
            return indexOps.putMapping();
        }
        boolean indexCreated = indexOps.create();
        if (indexCreated) {
            log.info("索引【{}】创建成功, 更新索引", indexOps.getIndexCoordinates().getIndexName());
            return indexOps.putMapping();
        }
        return false;
    }

    @Override
    public <T> void refreshDataToEs(List<T> list, Class<?> clazz) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        List<IndexQuery> queries = new ArrayList<>();
        list.forEach(obj -> queries.add(new IndexQueryBuilder()
                .withObject(obj)
                .build()));
        elasticsearchTemplate.bulkIndex(queries, clazz);
    }

    @Override
    public boolean delIndex(Class<?> clazz) {
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(clazz);
        if (!indexOperations.exists()) {
            log.warn("删除失败，索引不存在");
            return false;
        }
        return indexOperations.delete();
    }

    @Override
    public boolean delDoc() {
        return false;
    }
}
