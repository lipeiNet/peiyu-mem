package com.peiyu.mem.manager;

import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface CpActsubGroupManager {
    /**
     * 插入一条优惠券组
     * @param actsubGroup
     * @param applyLimits
     * @param useLimits
     * @return
     */
    boolean insertCpActsubGroup(CpActsubGroup actsubGroup, List<CpApplyLimitdt> applyLimits, List<CpUseLimitdt> useLimits);

    /**
     * 更新优惠券组
     * @param actsubGroup
     * @param applyLimits
     * @param useLimits
     * @return
     */
    boolean updateCpActsubGroup(CpActsubGroup actsubGroup, List<CpApplyLimitdt> applyLimits, List<CpUseLimitdt> useLimits);
}
