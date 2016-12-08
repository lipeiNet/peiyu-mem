package com.peiyu.mem.manager.impl;

import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.dao.CpapplylimitdtDao;
import com.peiyu.mem.dao.CpuselimitdtDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.manager.CpActivityManager;
import com.peiyu.mem.manager.CpActsubGroupManager;
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
 * Created by Administrator on 2016/12/6.
 */
@Service
public class CpActsubGroupManagerImpl implements CpActsubGroupManager {
    private static final Logger log = Logger.getLogger(CpActsubGroupManagerImpl.class);
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Autowired
    private CpuselimitdtDao uselimitdtDao;
    @Autowired
    private CpapplylimitdtDao applylimitdtDao;

    @Override
    public boolean insertCpActsubGroup(final CpActsubGroup actsubGroup, final List<CpApplyLimitdt> applyLimits, final List<CpUseLimitdt> useLimits) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    actsubGroupDao.insert(actsubGroup);
                    if (CollectionUtils.isNotEmpty(applyLimits)) {
                        applylimitdtDao.insertBatchApplylimits(applyLimits);
                    }
                    if (CollectionUtils.isNotEmpty(useLimits)) {
                        uselimitdtDao.insertBatchUselimits(useLimits);
                    }
                    return true;
                } catch (Exception e) {
                    log.error(e.getMessage());
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    @Override
    public boolean updateCpActsubGroup(CpActsubGroup actsubGroup, List<CpApplyLimitdt> applyLimits, List<CpUseLimitdt> useLimits) {
        return false;
    }
}
