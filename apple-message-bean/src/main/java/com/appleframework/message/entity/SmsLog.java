package com.appleframework.message.entity;

import java.io.Serializable;
import java.util.Date;

public class SmsLog implements Serializable {
    private Long id;

    private String tmpGroup;

    private String tmpCode;

    private String mobile;

    private String data;

    private String content;

    private Integer countFail;

    private Integer countOk;

    private String msgReturn;

    private String msgId;

    private Short state;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCountFail() {
        return countFail;
    }

    public void setCountFail(Integer countFail) {
        this.countFail = countFail;
    }

    public Integer getCountOk() {
        return countOk;
    }

    public void setCountOk(Integer countOk) {
        this.countOk = countOk;
    }

    public String getMsgReturn() {
        return msgReturn;
    }

    public void setMsgReturn(String msgReturn) {
        this.msgReturn = msgReturn == null ? null : msgReturn.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
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