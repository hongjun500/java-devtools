package com.hongjun.mapper;

import static com.hongjun.mapper.support.BlogArticleDODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.hongjun.model.BlogArticleDO;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface BlogArticleDOMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<BlogArticleDO>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    BasicColumn[] selectList = BasicColumn.columnList(id, tagId, title, desc, createdOn, createdBy, modifiedOn, modifiedBy, deletedOn, state, content);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BlogArticleDOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_on", property="createdOn", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="modified_on", property="modifiedOn", jdbcType=JdbcType.INTEGER),
        @Result(column="modified_by", property="modifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted_on", property="deletedOn", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.TINYINT),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BlogArticleDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BlogArticleDOResult")
    Optional<BlogArticleDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    default int insert(BlogArticleDO row) {
        return MyBatis3Utils.insert(this::insert, row, blogArticleDO, c ->
            c.map(id).toProperty("id")
            .map(tagId).toProperty("tagId")
            .map(title).toProperty("title")
            .map(desc).toProperty("desc")
            .map(createdOn).toProperty("createdOn")
            .map(createdBy).toProperty("createdBy")
            .map(modifiedOn).toProperty("modifiedOn")
            .map(modifiedBy).toProperty("modifiedBy")
            .map(deletedOn).toProperty("deletedOn")
            .map(state).toProperty("state")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source Table: blog_article")
    default int insertMultiple(Collection<BlogArticleDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, blogArticleDO, c ->
            c.map(id).toProperty("id")
            .map(tagId).toProperty("tagId")
            .map(title).toProperty("title")
            .map(desc).toProperty("desc")
            .map(createdOn).toProperty("createdOn")
            .map(createdBy).toProperty("createdBy")
            .map(modifiedOn).toProperty("modifiedOn")
            .map(modifiedBy).toProperty("modifiedBy")
            .map(deletedOn).toProperty("deletedOn")
            .map(state).toProperty("state")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default int insertSelective(BlogArticleDO row) {
        return MyBatis3Utils.insert(this::insert, row, blogArticleDO, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(tagId).toPropertyWhenPresent("tagId", row::getTagId)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(desc).toPropertyWhenPresent("desc", row::getDesc)
            .map(createdOn).toPropertyWhenPresent("createdOn", row::getCreatedOn)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(modifiedOn).toPropertyWhenPresent("modifiedOn", row::getModifiedOn)
            .map(modifiedBy).toPropertyWhenPresent("modifiedBy", row::getModifiedBy)
            .map(deletedOn).toPropertyWhenPresent("deletedOn", row::getDeletedOn)
            .map(state).toPropertyWhenPresent("state", row::getState)
            .map(content).toPropertyWhenPresent("content", row::getContent)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default Optional<BlogArticleDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default List<BlogArticleDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default List<BlogArticleDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default Optional<BlogArticleDO> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    static UpdateDSL<UpdateModel> updateAllColumns(BlogArticleDO row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(tagId).equalTo(row::getTagId)
                .set(title).equalTo(row::getTitle)
                .set(desc).equalTo(row::getDesc)
                .set(createdOn).equalTo(row::getCreatedOn)
                .set(createdBy).equalTo(row::getCreatedBy)
                .set(modifiedOn).equalTo(row::getModifiedOn)
                .set(modifiedBy).equalTo(row::getModifiedBy)
                .set(deletedOn).equalTo(row::getDeletedOn)
                .set(state).equalTo(row::getState)
                .set(content).equalTo(row::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BlogArticleDO row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(tagId).equalToWhenPresent(row::getTagId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(desc).equalToWhenPresent(row::getDesc)
                .set(createdOn).equalToWhenPresent(row::getCreatedOn)
                .set(createdBy).equalToWhenPresent(row::getCreatedBy)
                .set(modifiedOn).equalToWhenPresent(row::getModifiedOn)
                .set(modifiedBy).equalToWhenPresent(row::getModifiedBy)
                .set(deletedOn).equalToWhenPresent(row::getDeletedOn)
                .set(state).equalToWhenPresent(row::getState)
                .set(content).equalToWhenPresent(row::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default int updateByPrimaryKey(BlogArticleDO row) {
        return update(c ->
            c.set(tagId).equalTo(row::getTagId)
            .set(title).equalTo(row::getTitle)
            .set(desc).equalTo(row::getDesc)
            .set(createdOn).equalTo(row::getCreatedOn)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(modifiedOn).equalTo(row::getModifiedOn)
            .set(modifiedBy).equalTo(row::getModifiedBy)
            .set(deletedOn).equalTo(row::getDeletedOn)
            .set(state).equalTo(row::getState)
            .set(content).equalTo(row::getContent)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.127772+08:00", comments="Source Table: blog_article")
    default int updateByPrimaryKeySelective(BlogArticleDO row) {
        return update(c ->
            c.set(tagId).equalToWhenPresent(row::getTagId)
            .set(title).equalToWhenPresent(row::getTitle)
            .set(desc).equalToWhenPresent(row::getDesc)
            .set(createdOn).equalToWhenPresent(row::getCreatedOn)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(modifiedOn).equalToWhenPresent(row::getModifiedOn)
            .set(modifiedBy).equalToWhenPresent(row::getModifiedBy)
            .set(deletedOn).equalToWhenPresent(row::getDeletedOn)
            .set(state).equalToWhenPresent(row::getState)
            .set(content).equalToWhenPresent(row::getContent)
            .where(id, isEqualTo(row::getId))
        );
    }
}