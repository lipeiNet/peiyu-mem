package com.peiyu.mem.service;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface BizService {
    /**
     * 获取单号
     * @param vendorId 商家id
     * @param bno 单据标识
     * @param i
     * @return
     */
    String getOddNumbers(Long vendorId,String bno,Integer i);
}
