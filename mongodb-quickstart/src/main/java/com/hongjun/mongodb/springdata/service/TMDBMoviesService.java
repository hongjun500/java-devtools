package com.hongjun.mongodb.springdata.service;

import com.hongjun.mongodb.connection.MongoConn;
import com.hongjun.mongodb.springdata.document.TMDBMovies;
import com.hongjun.response.CommonPage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/12/12
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: tmdb_movies
 */
public interface TMDBMoviesService {

    void contextLoads();

    /**
     * 转换 csv 数据到 TMDBMovies
     * @param maps
     * @return
     */
    List<TMDBMovies> convertToTMDBMovies(List<Map<String, String>> maps);

    /**
     * 批量保存 TMDBMovies 到文档中
     * @param list TMDBMovies
     * @return
     */
    boolean bulkSaveAll(List<TMDBMovies> list);

    TMDBMovies getById(String id);

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return
     */
    CommonPage<TMDBMovies> listByPage(Integer pageNum, Integer pageSize);

    /**
     * 多个字段进行正则模糊匹配
     * @param title 电影标题
     * @param keywords 电影关键字
     * @return
     */
    List<TMDBMovies> listByTitleOrKeywords(String title, String keywords);

    /**
     * 大于等于查询
     * @param runtimeGte 播放时长
     * @return
     */
    List<TMDBMovies> listByRuntimeGte(Integer runtimeGte);

    /**
     * 小于等于查询
     * @param voteAverageLte 评分
     * @return
     */
    List<TMDBMovies> listByVoteAverageLte(BigDecimal voteAverageLte);

    /**
     * 多个字段联合比较查询
     * @return
     */
    List<TMDBMovies> listByRuntimeGteAndVoteAverageLte(Integer runtimeGte, BigDecimal voteAverageLte);


    /**
     * 删除当前集合
     * @return
     */
    default boolean delCollection() {
        MongoConn mongoConn = new MongoConn();
        mongoConn.getDatabase("kaggle").getCollection("tmdb_movies").drop();
        return true;
    }
}
