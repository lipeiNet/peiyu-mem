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

}
