package com.peiyu.mem.domian.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 * 会员信息记录
 */
public class Member implements Serializable {
    /**
     *记录编号<br/>
     **/
    private Long id;

    /**
     *商家ID<br/>
     **/
    private Long vendorId;

    /**
     *会员号<br/>
     **/
    private String memNo;

    /**
     *会员登录用户名<br/>
     **/
    private String memPin;

    /**
     *会员登录密码<br/>
     **/
    private String memPwd;

    /**
     *会员所属门店<br/>
     **/
    private String storeCode;

    /**
     *会员所属门店名称<br/>
     **/
    private String storeName;

    /**
     *会员所属机构<br/>
     **/
    private String organCode;

    /**
     *会员所属机构名称<br/>
     **/
    private String organName;

    /**
     *会员姓名<br/>
     **/
    private String memName;

    /**
     *会员昵称<br/>
     **/
    private String nickName;

    /**
     *会员手机号<br/>
     **/
    private String mobileNo;

    /**
     * 积分<br/>
     */
    private Double memPoint;

    /**
     * 累计消费<br/>
     */
    private Double consumeAmount;
    /**
     * 累计积分<br/>
     */
    private Double memPointAmount;

    /**
     * 0正常1冻结<br/>
     */
    private Integer status;

    /**
     * 0不需要密码 1表示需要密码<br/>
     */
    private Integer needPwdFlag;

    /**
     *性别 0女 1男<br/>
     **/
    private Integer sex;

    /**
     *会员类别<br/>
     **/
    private String memCatCode;

    /**
     *会员类别名称<br/>
     **/
    private String catName;

    /**
     *省级编码<br/>
     **/
    private String provinceCode;

    /**
     *市级编码<br/>
     **/
    private String cityCode;

    /**
     *地区编码<br/>
     **/
    private String regionCode;

    /**
     *详细地址<br/>
     **/
    private String memAddress;

    /**
     *会员生日<br/>
     **/
    private Date birthday;

    /**
     *头像图片url<br/>
     **/
    private String headImg;
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
     *会员类别名称，只是为了列表显示用，数据库中并没有此字段<br/>
     **/
    private String memCatName;

    /**
     * 会员有效开始时间
     */
    private Date startDate;
    /**
     * 会员有效结束时间
     */
    private Date endDate;

    /**
     *修改密码专用字段(新密码)<br/>
     **/
    private String memPwdNew;

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

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemPin() {
        return memPin;
    }

    public void setMemPin(String memPin) {
        this.memPin = memPin;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getMemPoint() {
        return memPoint;
    }

    public void setMemPoint(Double memPoint) {
        this.memPoint = memPoint;
    }

    public Double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Double getMemPointAmount() {
        return memPointAmount;
    }

    public void setMemPointAmount(Double memPointAmount) {
        this.memPointAmount = memPointAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNeedPwdFlag() {
        return needPwdFlag;
    }

    public void setNeedPwdFlag(Integer needPwdFlag) {
        this.needPwdFlag = needPwdFlag;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMemCatCode() {
        return memCatCode;
    }

    public void setMemCatCode(String memCatCode) {
        this.memCatCode = memCatCode;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getMemAddress() {
        return memAddress;
    }

    public void setMemAddress(String memAddress) {
        this.memAddress = memAddress;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public String getMemCatName() {
        return memCatName;
    }

    public void setMemCatName(String memCatName) {
        this.memCatName = memCatName;
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

    public String getMemPwdNew() {
        return memPwdNew;
    }

    public void setMemPwdNew(String memPwdNew) {
        this.memPwdNew = memPwdNew;
    }
}
