package com.hongjun.mapper.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class BlogArticleDODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1257759+08:00", comments="Source Table: blog_article")
    public static final BlogArticleDO blogArticleDO = new BlogArticleDO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1257759+08:00", comments="Source field: blog_article.id")
    public static final SqlColumn<Integer> id = blogArticleDO.id;

    /**
     * Database Column Remarks:
     *   标签ID
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1257759+08:00", comments="Source field: blog_article.tag_id")
    public static final SqlColumn<Integer> tagId = blogArticleDO.tagId;

    /**
     * Database Column Remarks:
     *   文章标题
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1257759+08:00", comments="Source field: blog_article.title")
    public static final SqlColumn<String> title = blogArticleDO.title;

    /**
     * Database Column Remarks:
     *   简述
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1257759+08:00", comments="Source field: blog_article.desc")
    public static final SqlColumn<String> desc = blogArticleDO.desc;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.created_on")
    public static final SqlColumn<Integer> createdOn = blogArticleDO.createdOn;

    /**
     * Database Column Remarks:
     *   创建人
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.created_by")
    public static final SqlColumn<String> createdBy = blogArticleDO.createdBy;

    /**
     * Database Column Remarks:
     *   修改时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.modified_on")
    public static final SqlColumn<Integer> modifiedOn = blogArticleDO.modifiedOn;

    /**
     * Database Column Remarks:
     *   修改人
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.modified_by")
    public static final SqlColumn<String> modifiedBy = blogArticleDO.modifiedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.deleted_on")
    public static final SqlColumn<Integer> deletedOn = blogArticleDO.deletedOn;

    /**
     * Database Column Remarks:
     *   状态 0为禁用1为启用
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.state")
    public static final SqlColumn<Byte> state = blogArticleDO.state;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1267732+08:00", comments="Source field: blog_article.content")
    public static final SqlColumn<String> content = blogArticleDO.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.1257759+08:00", comments="Source Table: blog_article")
    public static final class BlogArticleDO extends AliasableSqlTable<BlogArticleDO> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> tagId = column("tag_id", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<String> desc = column("desc", JDBCType.VARCHAR);

        public final SqlColumn<Integer> createdOn = column("created_on", JDBCType.INTEGER);

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Integer> modifiedOn = column("modified_on", JDBCType.INTEGER);

        public final SqlColumn<String> modifiedBy = column("modified_by", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deletedOn = column("deleted_on", JDBCType.INTEGER);

        public final SqlColumn<Byte> state = column("state", JDBCType.TINYINT);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public BlogArticleDO() {
            super("blog_article", BlogArticleDO::new);
        }
    }
}