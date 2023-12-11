package com.hongjun.quickstart.mongo.springdata.service;

import cn.hutool.core.util.StrUtil;
import com.hongjun.quickstart.mongo.springdata.document.TopSpotifySongs;
import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/11/3 17:16
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: mongoTemplate crud from TopSpotifySongs document
 */

@Service
@RequiredArgsConstructor
public class TopSpotifySongServiceImpl implements TopSpotifySongService {

    private final MongoTemplate mongoTemplate;

    @Override
    public void importData(List<Map<String, String>> maps, Class clazz) {
        if (maps.isEmpty()) {
            return;
        }
        mongoTemplate.insert(maps, clazz);
    }

    @Override
    public List<TopSpotifySongs> listParam(String name) {
        if (StrUtil.isBlank(name)) {
            return mongoTemplate.findAll(TopSpotifySongs.class, "top_spotify_songs");
        }
        Criteria criteria = new Criteria();
        criteria.and("name").is(name);
        System.out.println(criteria.getKey());
        Query query = new Query(criteria);
        return mongoTemplate.find(query, TopSpotifySongs.class, "top_spotify_songs");
    }

    @Override
    public long removeColl(String collName) {
        // 所有数据并删除
        DeleteResult remove = mongoTemplate.remove(new Query().limit(0), collName);
        return remove.getDeletedCount();
    }


}
