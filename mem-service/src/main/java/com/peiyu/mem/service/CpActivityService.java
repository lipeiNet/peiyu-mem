package com.peiyu.mem.service;

import com.peiyu.mem.domian.entity.CpActivity;

/**
 * Created by Administrator on 2016/12/2.
 */
public interface CpActivityService {
    /**
     * 插入数据
     * @param cpActivity
     * @return 0：插入失败，1：插入成功，2：已存在
     */
    int insertCpActivity(CpActivity cpActivity);
    /**
     * 真删除
     * @param id
     * @return 0：删除成功，1：删除失败
     */
    int deleteCpActivity(long id);

    /**
     * 查询优惠券活动
     * @param id
     * @return
     */
    CpActivity getCpActivity(long id);
}
