package com.hongjun.dynamicsql.mapper;

import com.hongjun.dynamicsql.model.BlogTagDO;
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
import java.util.Optional;

import static com.hongjun.dynamicsql.mapper.support.BlogTagDODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface BlogTagDOMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<BlogTagDO>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2316192+08:00", comments="Source Table: blog_tag")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, createdOn, createdBy, modifiedOn, modifiedBy, deletedOn, state);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2246185+08:00", comments="Source Table: blog_tag")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BlogTagDOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_on", property="createdOn", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="modified_on", property="modifiedOn", jdbcType=JdbcType.INTEGER),
        @Result(column="modified_by", property="modifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted_on", property="deletedOn", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.TINYINT)
    })
    List<BlogTagDO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2266199+08:00", comments="Source Table: blog_tag")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BlogTagDOResult")
    Optional<BlogTagDO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2266199+08:00", comments="Source Table: blog_tag")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, blogTagDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2276192+08:00", comments="Source Table: blog_tag")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, blogTagDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2276192+08:00", comments="Source Table: blog_tag")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2286193+08:00", comments="Source Table: blog_tag")
    default int insert(BlogTagDO row) {
        return MyBatis3Utils.insert(this::insert, row, blogTagDO, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(createdOn).toProperty("createdOn")
            .map(createdBy).toProperty("createdBy")
            .map(modifiedOn).toProperty("modifiedOn")
            .map(modifiedBy).toProperty("modifiedBy")
            .map(deletedOn).toProperty("deletedOn")
            .map(state).toProperty("state")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2296187+08:00", comments="Source Table: blog_tag")
    default int insertMultiple(Collection<BlogTagDO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, blogTagDO, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(createdOn).toProperty("createdOn")
            .map(createdBy).toProperty("createdBy")
            .map(modifiedOn).toProperty("modifiedOn")
            .map(modifiedBy).toProperty("modifiedBy")
            .map(deletedOn).toProperty("deletedOn")
            .map(state).toProperty("state")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2306183+08:00", comments="Source Table: blog_tag")
    default int insertSelective(BlogTagDO row) {
        return MyBatis3Utils.insert(this::insert, row, blogTagDO, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(createdOn).toPropertyWhenPresent("createdOn", row::getCreatedOn)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(modifiedOn).toPropertyWhenPresent("modifiedOn", row::getModifiedOn)
            .map(modifiedBy).toPropertyWhenPresent("modifiedBy", row::getModifiedBy)
            .map(deletedOn).toPropertyWhenPresent("deletedOn", row::getDeletedOn)
            .map(state).toPropertyWhenPresent("state", row::getState)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2316192+08:00", comments="Source Table: blog_tag")
    default Optional<BlogTagDO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, blogTagDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2326189+08:00", comments="Source Table: blog_tag")
    default List<BlogTagDO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, blogTagDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2326189+08:00", comments="Source Table: blog_tag")
    default List<BlogTagDO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, blogTagDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2326189+08:00", comments="Source Table: blog_tag")
    default Optional<BlogTagDO> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2336191+08:00", comments="Source Table: blog_tag")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, blogTagDO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2336191+08:00", comments="Source Table: blog_tag")
    static UpdateDSL<UpdateModel> updateAllColumns(BlogTagDO row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(name).equalTo(row::getName)
                .set(createdOn).equalTo(row::getCreatedOn)
                .set(createdBy).equalTo(row::getCreatedBy)
                .set(modifiedOn).equalTo(row::getModifiedOn)
                .set(modifiedBy).equalTo(row::getModifiedBy)
                .set(deletedOn).equalTo(row::getDeletedOn)
                .set(state).equalTo(row::getState);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2336191+08:00", comments="Source Table: blog_tag")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BlogTagDO row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(name).equalToWhenPresent(row::getName)
                .set(createdOn).equalToWhenPresent(row::getCreatedOn)
                .set(createdBy).equalToWhenPresent(row::getCreatedBy)
                .set(modifiedOn).equalToWhenPresent(row::getModifiedOn)
                .set(modifiedBy).equalToWhenPresent(row::getModifiedBy)
                .set(deletedOn).equalToWhenPresent(row::getDeletedOn)
                .set(state).equalToWhenPresent(row::getState);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.234617+08:00", comments="Source Table: blog_tag")
    default int updateByPrimaryKey(BlogTagDO row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(createdOn).equalTo(row::getCreatedOn)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(modifiedOn).equalTo(row::getModifiedOn)
            .set(modifiedBy).equalTo(row::getModifiedBy)
            .set(deletedOn).equalTo(row::getDeletedOn)
            .set(state).equalTo(row::getState)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.234617+08:00", comments="Source Table: blog_tag")
    default int updateByPrimaryKeySelective(BlogTagDO row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(createdOn).equalToWhenPresent(row::getCreatedOn)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(modifiedOn).equalToWhenPresent(row::getModifiedOn)
            .set(modifiedBy).equalToWhenPresent(row::getModifiedBy)
            .set(deletedOn).equalToWhenPresent(row::getDeletedOn)
            .set(state).equalToWhenPresent(row::getState)
            .where(id, isEqualTo(row::getId))
        );
    }
}