package com.hongjun.dynamicsql.mapper;

import com.hongjun.dynamicsql.model.BlogArticleDO;
import jakarta.annotation.Generated;
import org.apache.ibatis.annotations.*;
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
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hongjun.dynamicsql.mapper.support.BlogArticleDODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface BlogArticleDOMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<BlogArticleDO>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.773636+08:00", comments="Source Table: blog_article")
    BasicColumn[] selectList = BasicColumn.columnList(id, tagId, title, createdOn, createdBy, modifiedOn, modifiedBy, deletedOn, state, content);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7666349+08:00", comments="Source Table: blog_article")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BlogArticleDOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_on", property="createdOn", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="modified_on", property="modifiedOn", jdbcType=JdbcType.INTEGER),
        @Result(column="modified_by", property="modifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted_on", property="deletedOn", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.TINYINT),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BlogArticleDO> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultType(value = Map.class)
    List<Map<String, Object>> selectManyToMap(SelectStatementProvider selectStatementProvider);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7686369+08:00", comments="Source Table: blog_article")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BlogArticleDOResult")
    Optional<BlogArticleDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7686369+08:00", comments="Source Table: blog_article")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7696366+08:00", comments="Source Table: blog_article")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7696366+08:00", comments="Source Table: blog_article")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7706368+08:00", comments="Source Table: blog_article")
    default int insert(BlogArticleDO row) {
        return MyBatis3Utils.insert(this::insert, row, blogArticleDO, c ->
            c.map(id).toProperty("id")
            .map(tagId).toProperty("tagId")
            .map(title).toProperty("title")
            .map(createdOn).toProperty("createdOn")
            .map(createdBy).toProperty("createdBy")
            .map(modifiedOn).toProperty("modifiedOn")
            .map(modifiedBy).toProperty("modifiedBy")
            .map(deletedOn).toProperty("deletedOn")
            .map(state).toProperty("state")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7726343+08:00", comments="Source Table: blog_article")
    default int insertMultiple(Collection<BlogArticleDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, blogArticleDO, c ->
            c.map(id).toProperty("id")
            .map(tagId).toProperty("tagId")
            .map(title).toProperty("title")
            .map(createdOn).toProperty("createdOn")
            .map(createdBy).toProperty("createdBy")
            .map(modifiedOn).toProperty("modifiedOn")
            .map(modifiedBy).toProperty("modifiedBy")
            .map(deletedOn).toProperty("deletedOn")
            .map(state).toProperty("state")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7726343+08:00", comments="Source Table: blog_article")
    default int insertSelective(BlogArticleDO row) {
        return MyBatis3Utils.insert(this::insert, row, blogArticleDO, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(tagId).toPropertyWhenPresent("tagId", row::getTagId)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(createdOn).toPropertyWhenPresent("createdOn", row::getCreatedOn)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(modifiedOn).toPropertyWhenPresent("modifiedOn", row::getModifiedOn)
            .map(modifiedBy).toPropertyWhenPresent("modifiedBy", row::getModifiedBy)
            .map(deletedOn).toPropertyWhenPresent("deletedOn", row::getDeletedOn)
            .map(state).toPropertyWhenPresent("state", row::getState)
            .map(content).toPropertyWhenPresent("content", row::getContent)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7746346+08:00", comments="Source Table: blog_article")
    default Optional<BlogArticleDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7746346+08:00", comments="Source Table: blog_article")
    default List<BlogArticleDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7746346+08:00", comments="Source Table: blog_article")
    default List<BlogArticleDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7756345+08:00", comments="Source Table: blog_article")
    default Optional<BlogArticleDO> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7756345+08:00", comments="Source Table: blog_article")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, blogArticleDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7756345+08:00", comments="Source Table: blog_article")
    static UpdateDSL<UpdateModel> updateAllColumns(BlogArticleDO row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(tagId).equalTo(row::getTagId)
                .set(title).equalTo(row::getTitle)
                .set(createdOn).equalTo(row::getCreatedOn)
                .set(createdBy).equalTo(row::getCreatedBy)
                .set(modifiedOn).equalTo(row::getModifiedOn)
                .set(modifiedBy).equalTo(row::getModifiedBy)
                .set(deletedOn).equalTo(row::getDeletedOn)
                .set(state).equalTo(row::getState)
                .set(content).equalTo(row::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7756345+08:00", comments="Source Table: blog_article")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BlogArticleDO row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(tagId).equalToWhenPresent(row::getTagId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(createdOn).equalToWhenPresent(row::getCreatedOn)
                .set(createdBy).equalToWhenPresent(row::getCreatedBy)
                .set(modifiedOn).equalToWhenPresent(row::getModifiedOn)
                .set(modifiedBy).equalToWhenPresent(row::getModifiedBy)
                .set(deletedOn).equalToWhenPresent(row::getDeletedOn)
                .set(state).equalToWhenPresent(row::getState)
                .set(content).equalToWhenPresent(row::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7766342+08:00", comments="Source Table: blog_article")
    default int updateByPrimaryKey(BlogArticleDO row) {
        return update(c ->
            c.set(tagId).equalTo(row::getTagId)
            .set(title).equalTo(row::getTitle)
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T13:59:53.7766342+08:00", comments="Source Table: blog_article")
    default int updateByPrimaryKeySelective(BlogArticleDO row) {
        return update(c ->
            c.set(tagId).equalToWhenPresent(row::getTagId)
            .set(title).equalToWhenPresent(row::getTitle)
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