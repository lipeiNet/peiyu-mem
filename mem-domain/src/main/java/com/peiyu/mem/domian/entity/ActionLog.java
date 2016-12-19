package com.peiyu.mem.domian.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ActionLog {
    /**
     * 操作记录
     */
    private Long id;
    /**
     * 商家id
     */
    private Long vendorId;
    /**
     * 会员编号
     */
    private String memNo;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法类别（0：添加，1删除，2修改，3查询）
     */
    private int methodType;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String methodParam;
    /**
     * 参数值
     */
    private String paramValue;
    /**
     * 方法效率（单位毫秒）
     */
    private Long operationTime;
    /**
     * 操作时间
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParam() {
        return methodParam;
    }

    public void setMethodParam(String methodParam) {
        this.methodParam = methodParam;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
