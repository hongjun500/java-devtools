package com.hongjun.model;

import jakarta.annotation.Generated;
import java.io.Serializable;

/**
 * Database Table Remarks:
 *   文章标签管理
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table blog_tag
 */
public class BlogTagDO implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0938616+08:00", comments="Source field: blog_tag.id")
    private Integer id;

    /**
     * Database Column Remarks:
     *   标签名称
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0958565+08:00", comments="Source field: blog_tag.name")
    private String name;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.created_on")
    private Integer createdOn;

    /**
     * Database Column Remarks:
     *   创建人
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.created_by")
    private String createdBy;

    /**
     * Database Column Remarks:
     *   修改时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.modified_on")
    private Integer modifiedOn;

    /**
     * Database Column Remarks:
     *   修改人
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.modified_by")
    private String modifiedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source field: blog_tag.deleted_on")
    private Integer deletedOn;

    /**
     * Database Column Remarks:
     *   状态 0为禁用、1为启用
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source field: blog_tag.state")
    private Byte state;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source Table: blog_tag")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0958565+08:00", comments="Source field: blog_tag.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0958565+08:00", comments="Source field: blog_tag.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.created_on")
    public Integer getCreatedOn() {
        return createdOn;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.created_on")
    public void setCreatedOn(Integer createdOn) {
        this.createdOn = createdOn;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.created_by")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.modified_on")
    public Integer getModifiedOn() {
        return modifiedOn;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.modified_on")
    public void setModifiedOn(Integer modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.modified_by")
    public String getModifiedBy() {
        return modifiedBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0968538+08:00", comments="Source field: blog_tag.modified_by")
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source field: blog_tag.deleted_on")
    public Integer getDeletedOn() {
        return deletedOn;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source field: blog_tag.deleted_on")
    public void setDeletedOn(Integer deletedOn) {
        this.deletedOn = deletedOn;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source field: blog_tag.state")
    public Byte getState() {
        return state;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source field: blog_tag.state")
    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-04-17T20:22:53.0978511+08:00", comments="Source Table: blog_tag")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append(", deletedOn=").append(deletedOn);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}