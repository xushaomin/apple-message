package com.appleframework.message.entity;

import java.io.Serializable;
import java.util.Date;

public class ThirdAuth implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private Long id;

    private Integer type;

    private String name;

    private String thirdKey;

    private String thirdSecret;

    private String thirdExtend;

    private String thirdClass;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey == null ? null : thirdKey.trim();
    }

    public String getThirdSecret() {
        return thirdSecret;
    }

    public void setThirdSecret(String thirdSecret) {
        this.thirdSecret = thirdSecret == null ? null : thirdSecret.trim();
    }

    public String getThirdExtend() {
        return thirdExtend;
    }

    public void setThirdExtend(String thirdExtend) {
        this.thirdExtend = thirdExtend == null ? null : thirdExtend.trim();
    }

    public String getThirdClass() {
        return thirdClass;
    }

    public void setThirdClass(String thirdClass) {
        this.thirdClass = thirdClass == null ? null : thirdClass.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}