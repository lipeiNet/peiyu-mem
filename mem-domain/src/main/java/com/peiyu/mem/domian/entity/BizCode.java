package com.peiyu.mem.domian.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/5.
 * 系统单据号
 */
public class BizCode {
    /**
     * 记录id
     */
    private int id;
    /**
     * 商家id
     */
    private Long vendorId;
    /**
     *业务编码
     */
    private String bizCode;
    /**
     * 业务名称
     */
    private String bizName;
    /**
     *单据标识
     */
    private String bno;
    /**
     * 当前日期
     */
    private Date curDate;
    /**
     *序列号
     */
    private int serialNumber;
    /**
     * 单据顺号长度
     */
    private int lengthValue;
    /**
     * 注释
     */
    private String memo;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改日期
     */
    private Date modifyDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(int lengthValue) {
        this.lengthValue = lengthValue;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
