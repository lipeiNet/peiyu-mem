package com.peiyu.mem.manager.impl;

import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.dao.CpapplylimitdtDao;
import com.peiyu.mem.dao.CpuselimitdtDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.manager.CpActivityManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
@Service
public class CpActivityManagerImpl implements CpActivityManager {
    private static final Logger log = Logger.getLogger(CpActivityManagerImpl.class);
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private CpActivityDao activityDao;
    @Autowired
    private CpapplylimitdtDao applyLimitdtDao;
    @Autowired
    private CpuselimitdtDao cpuselimitdtDao;

    @Override
    public boolean insertCpActivity(final CpActivity cpActivity, final List<CpApplyLimitdt> applyLimits, final List<CpUseLimitdt> useLimits) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    activityDao.insert(cpActivity);
                    if (CollectionUtils.isNotEmpty(applyLimits)) {
                        applyLimitdtDao.insertBatchApplylimits(applyLimits);
                    }
                    if (CollectionUtils.isNotEmpty(useLimits)) {
                        cpuselimitdtDao.insertBatchUselimits(useLimits);
                    }
                    return true;
                } catch (Exception e) {
                    log.error("保存优惠券活动失败，异常信息：" + e.getMessage());
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    @Override
    public boolean updateCpActivity(CpActivity cpActivity, List<CpApplyLimitdt> applyLimits, List<CpUseLimitdt> useLimits) {
        return false;
    }

    @Override
    public List<CpActivity> getActivitysForCache(CpActivity searct) {
        String key="";
        return null;
    }
}
