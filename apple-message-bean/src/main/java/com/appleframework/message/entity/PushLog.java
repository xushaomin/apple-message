package com.appleframework.message.entity;

import java.io.Serializable;
import java.util.Date;

public class PushLog implements Serializable {
    private Long id;

    private Integer mode;

    private String tmpGroup;

    private String tmpCode;

    private String receiver;

    private String title;

    private String content;

    private String data;

    private Integer countFail;

    private Integer countOk;

    private Short state;

    private String msgReturn;

    private String msgId;

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

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
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

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
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