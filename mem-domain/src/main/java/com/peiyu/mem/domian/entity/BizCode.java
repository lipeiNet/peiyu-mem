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


}
