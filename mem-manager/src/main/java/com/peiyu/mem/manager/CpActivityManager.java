package com.peiyu.mem.manager;

import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
public interface CpActivityManager {

    /**
     * 插入一条优惠券活动
     * @param cpActivity
     * @param applyLimits
     * @param useLimits
     * @return
     */
    boolean insertCpActivity(CpActivity cpActivity, List<CpApplyLimitdt> applyLimits, List<CpUseLimitdt> useLimits);
    /**
     * 更新一条优惠券活动
     * @param cpActivity
     * @param applyLimits
     * @param useLimits
     * @return
     */
    boolean updateCpActivity(CpActivity cpActivity,List<CpApplyLimitdt> applyLimits,List<CpUseLimitdt> useLimits);
}
