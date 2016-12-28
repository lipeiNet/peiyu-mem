package com.peiyu.mem.domian.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by Administrator on 2016/11/29.
 */
public class Coupon implements Serializable{
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
     *组编码，允许多个<br/>
     **/
    private String subgroupCode;
    /**
     * 优惠券编码
     */
    private String cpCode;
    /**
     *面值，允许多个<br/>
     **/
    private Double cpValue;

    /**
     * 生效日期
     */
    private Date startDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 优惠券状态
     */
    private  int state;
    /**
     *关联会员号<br/>
     **/
    private String memNo;
    /**
     * 关联会员名称
     */
    private String memCat;
    /**
     *实际使用金额(可能面值50,单实际只当30使用了)<br/>
     **/
    private Double useValue;

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
     * 发券时间
     */
    private Date sendCouponDate;
    /**
     * 使用券时间
     */
    private Date usedCouponDate;
    /**
     * 作废券时间
     */
    private Date validCouponDate;

    /**
     *创建人<br/>
     **/
    private String creator;
    /**
     *是否已删除 1删除 0正常<br/>
     **/
    private Integer df;
    /**
     * 页索引
     */
    private Integer pageIndex;
    /**
     * 页大小
     */
    private Integer pageSize;
    private static final long serialVersionUID = 1L;

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

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMemCat() {
        return memCat;
    }

    public void setMemCat(String memCat) {
        this.memCat = memCat;
    }

    public Double getUseValue() {
        return useValue;
    }

    public void setUseValue(Double useValue) {
        this.useValue = useValue;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public Date getSendCouponDate() {
        return sendCouponDate;
    }

    public void setSendCouponDate(Date sendCouponDate) {
        this.sendCouponDate = sendCouponDate;
    }

    public Date getUsedCouponDate() {
        return usedCouponDate;
    }

    public void setUsedCouponDate(Date usedCouponDate) {
        this.usedCouponDate = usedCouponDate;
    }

    public Date getValidCouponDate() {
        return validCouponDate;
    }

    public void setValidCouponDate(Date validCouponDate) {
        this.validCouponDate = validCouponDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
