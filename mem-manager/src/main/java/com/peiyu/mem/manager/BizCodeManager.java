package com.peiyu.mem.manager;

import com.peiyu.mem.domian.entity.BizCode;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface BizCodeManager {
    /**
     * 获取单号
     * @param vendorId
     * @param bno
     * @return
     */
    BizCode getCodeByNo(final Long vendorId, final String bno);

    /**
     * 通过单据号更新缓存
     * @param code
     * @return
     */
    boolean updateCacheForBno(BizCode code);
}
