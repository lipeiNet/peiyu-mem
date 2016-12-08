package com.peiyu.mem.domian.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/23.
 * 制券任务
 */
public class CpMakingTask implements Serializable {
    /**
     *记录编号<br/>
     **/
    private Long id;

    /**
     *商家ID<br/>
     **/
    private Long vendorId;

    /**
     *活动编码<br/>
     **/
    private String actNo;

    /**
     *活动名称<br/>
     **/
    private String actName;

    /**
     *活动编码<br/>
     **/
    private String subgroupCode;

    /**
     *面值<br/>
     **/
    private Double cpValue;

    /**
     *有效开始时间（什么时候开始生效）<br/>
     **/
    private Date startDate;

    /**
     *有效结束时间（什么时候开始失效）<br/>
     **/
    private Date endDate;

    /**
     *任务编号<br/>
     **/
    private String taskCode;

    /**
     *编号规则前缀(允许输入数字和转义码 V-活动编号, YMDhms-日期格式化, R-随机编号)<br/>
     **/
    private String genNoRulePrefix;

    /**
     *编号规则前后缀(允许输入数字和转义码 V-活动编号, YMDhms-日期格式化, R-随机编号)<br/>
     **/
    private String genNoRuleSuffix;

    /**
     *任务开始时间<br/>
     **/
    private Date taskBeginTime;

    /**
     *任务结束时间<br/>
     **/
    private Date taskEndTime;

    /**
     *制券任务状态(0-已保存,1-进行中, 2-已完成, 3-已失败)<br/>
     **/
    private Integer state;

    /**
     *备注<br/>
     **/
    private String memo;

    /**
     *创建日期<br/>
     **/
    private Date createDate;

    /**
     *修改日期<br/>
     **/
    private Date modifyDate;

    /**
     *记录创建人用户名<br/>
     **/
    private String creator;

    /**
     *是否已删除1删除 0正常<br/>
     **/
    private Integer df;

    /**
     *制券数量<br/>
     **/
    private double ticNum;

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


    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo == null ? null : actNo.trim();
    }


    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    public String getSubgroupCode() {
        return subgroupCode;
    }

    public void setSubgroupCode(String subgroupCode) {
        this.subgroupCode = subgroupCode == null ? null : subgroupCode.trim();
    }


    public Double getCpValue() {
        return cpValue;
    }

    public void setCpValue(Double cpValue) {
        this.cpValue = cpValue;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public String getGenNoRulePrefix() {
        return genNoRulePrefix;
    }

    public void setGenNoRulePrefix(String genNoRulePrefix) {
        this.genNoRulePrefix = genNoRulePrefix == null ? null : genNoRulePrefix.trim();
    }


    public String getGenNoRuleSuffix() {
        return genNoRuleSuffix;
    }

    public void setGenNoRuleSuffix(String genNoRuleSuffix) {
        this.genNoRuleSuffix = genNoRuleSuffix == null ? null : genNoRuleSuffix.trim();
    }

    public Date getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Date taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }


    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getDf() {
        return df;
    }

    public void setDf(Integer df) {
        this.df = df;
    }

    public double getTicNum() {
        return ticNum;
    }

    public void setTicNum(double ticNum) {
        this.ticNum = ticNum;
    }
}
