package com.appleframework.message.entity;

import java.io.Serializable;
import java.util.Date;

public class SmsTemplate implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private Long id;

    private String name;

    private String tmpGroup;

    private String tmpCode;

    private String template;

    private String remark;

    private String thirdAuthId;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTmpGroup() {
        return tmpGroup;
    }

    public void setTmpGroup(String tmpGroup) {
        this.tmpGroup = tmpGroup == null ? null : tmpGroup.trim();
    }

    public String getTmpCode() {
        return tmpCode;
    }

    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode == null ? null : tmpCode.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getThirdAuthId() {
        return thirdAuthId;
    }

    public void setThirdAuthId(String thirdAuthId) {
        this.thirdAuthId = thirdAuthId;
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