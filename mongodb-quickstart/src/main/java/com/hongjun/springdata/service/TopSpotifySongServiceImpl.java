package com.hongjun.springdata.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.hongjun.springdata.document.TopSpotifySongs;
import com.mongodb.client.result.DeleteResult;
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
 * Description:
 */

@Service
public class TopSpotifySongServiceImpl implements TopSpotifySongService {

    private final MongoTemplate mongoTemplate;

    public TopSpotifySongServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void importData(List<Map<String, String>> maps) {
        if (maps.isEmpty()) {
            return;
        }
        List<TopSpotifySongs> list = Lists.newArrayListWithExpectedSize(maps.size());
        maps.forEach(map -> {
            TopSpotifySongs topSpotifySongs = new TopSpotifySongs();
            map.forEach((key, value) -> {


            });
        });
        mongoTemplate.insert(maps, "top_spotify_songs");
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
