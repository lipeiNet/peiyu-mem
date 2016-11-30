package com.peiyu.mem.domian.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/23.
 * 会员类别记录
 */
public class MemCategory implements Serializable {
    /**
     *记录编号<br/>
     **/
    private Long id;

    /**
     *商家ID<br/>
     **/
    private Long vendorId;

    /**
     *类别编码<br/>
     **/
    private String catCode;

    /**
     *类别名称<br/>
     **/
    private String catName;

    /**
     *是否积分 1是 0否<br/>
     **/
    private Integer doScore;

    /**
     *优惠方式  0零售价折扣  1会员价折扣  2会员价  3批发价折扣<br/>
     **/
    private Integer onsaleType;

    /**
     *折扣<br/>
     **/
    private Double rate;

    /**
     *是否需要办理条件 1是 0否<br/>
     **/
    private Integer need;

    /**
     *消费金额大于<br/>
     **/
    private Double greaterAmount;

    /**
     *条件拼接标记 0或1且<br/>
     **/
    private Integer combineFlag;

    /**
     *消费数量大于<br/>
     **/
    private Double greaterNum;

    /**
     *是否已删除1删除 0正常<br/>
     **/
    private Integer df;

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
     * 会员卡图片<br/>
     * */
    private String memCardAdd;


    /**
     * 微店注册会员卡默认标识<br/>
     * */

    private Integer memCardDefaultForWd;

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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public Integer getDoScore() {
        return doScore;
    }

    public void setDoScore(Integer doScore) {
        this.doScore = doScore;
    }

    public Integer getOnsaleType() {
        return onsaleType;
    }

    public void setOnsaleType(Integer onsaleType) {
        this.onsaleType = onsaleType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }

    public Double getGreaterAmount() {
        return greaterAmount;
    }

    public void setGreaterAmount(Double greaterAmount) {
        this.greaterAmount = greaterAmount;
    }

    public Integer getCombineFlag() {
        return combineFlag;
    }

    public void setCombineFlag(Integer combineFlag) {
        this.combineFlag = combineFlag;
    }

    public Double getGreaterNum() {
        return greaterNum;
    }

    public void setGreaterNum(Double greaterNum) {
        this.greaterNum = greaterNum;
    }

    public Integer getDf() {
        return df;
    }

    public void setDf(Integer df) {
        this.df = df;
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

    public String getMemCardAdd() {
        return memCardAdd;
    }

    public void setMemCardAdd(String memCardAdd) {
        this.memCardAdd = memCardAdd;
    }

    public Integer getMemCardDefaultForWd() {
        return memCardDefaultForWd;
    }

    public void setMemCardDefaultForWd(Integer memCardDefaultForWd) {
        this.memCardDefaultForWd = memCardDefaultForWd;
    }
}
