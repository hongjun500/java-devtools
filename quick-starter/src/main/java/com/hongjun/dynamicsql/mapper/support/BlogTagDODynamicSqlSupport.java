package com.hongjun.dynamicsql.mapper.support;

import jakarta.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;

public final class BlogTagDODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2236181+08:00", comments="Source Table: blog_tag")
    public static final BlogTagDO blogTagDO = new BlogTagDO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2236181+08:00", comments="Source field: blog_tag.id")
    public static final SqlColumn<Integer> id = blogTagDO.id;

    /**
     * Database Column Remarks:
     *   标签名称
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2236181+08:00", comments="Source field: blog_tag.name")
    public static final SqlColumn<String> name = blogTagDO.name;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2236181+08:00", comments="Source field: blog_tag.created_on")
    public static final SqlColumn<Integer> createdOn = blogTagDO.createdOn;

    /**
     * Database Column Remarks:
     *   创建人
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2236181+08:00", comments="Source field: blog_tag.created_by")
    public static final SqlColumn<String> createdBy = blogTagDO.createdBy;

    /**
     * Database Column Remarks:
     *   修改时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2246185+08:00", comments="Source field: blog_tag.modified_on")
    public static final SqlColumn<Integer> modifiedOn = blogTagDO.modifiedOn;

    /**
     * Database Column Remarks:
     *   修改人
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2246185+08:00", comments="Source field: blog_tag.modified_by")
    public static final SqlColumn<String> modifiedBy = blogTagDO.modifiedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2246185+08:00", comments="Source field: blog_tag.deleted_on")
    public static final SqlColumn<Integer> deletedOn = blogTagDO.deletedOn;

    /**
     * Database Column Remarks:
     *   状态 0为禁用、1为启用
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2246185+08:00", comments="Source field: blog_tag.state")
    public static final SqlColumn<Byte> state = blogTagDO.state;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-18T16:33:53.2236181+08:00", comments="Source Table: blog_tag")
    public static final class BlogTagDO extends AliasableSqlTable<BlogTagDO> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> createdOn = column("created_on", JDBCType.INTEGER);

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Integer> modifiedOn = column("modified_on", JDBCType.INTEGER);

        public final SqlColumn<String> modifiedBy = column("modified_by", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deletedOn = column("deleted_on", JDBCType.INTEGER);

        public final SqlColumn<Byte> state = column("state", JDBCType.TINYINT);

        public BlogTagDO() {
            super("blog_tag", BlogTagDO::new);
        }
    }
}