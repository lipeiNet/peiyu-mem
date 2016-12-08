package com.peiyu.mem.manager.impl;

import com.migr.common.util.JsonUtil;
import com.peiyu.mem.dao.BizCodeDao;
import com.peiyu.mem.domian.entity.BizCode;
import com.peiyu.mem.manager.BizCodeManager;
import com.peiyu.mem.redis.JedisTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Administrator on 2016/12/5.
 */
@Service
public class BizCodeManagerImpl implements BizCodeManager {
    private Logger log = Logger.getLogger(BizCodeManagerImpl.class);
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private JedisTemplate jedisTemplate;
    @Autowired
    private BizCodeDao bizCodeDao;

    @Override
    public BizCode getCodeByNo(final Long vendorId, final String bno) {
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(new TransactionCallback<BizCode>() {
            @Override
            public BizCode doInTransaction(TransactionStatus transactionStatus) {
                try {
                    BizCode code;
                    String cacheKey = String.format("%s_%s", vendorId, bno);
                    code = JsonUtil.g.fromJson(jedisTemplate.get(cacheKey), BizCode.class);
                    if (code != null) {
                        return code;
                    }
                    if (code == null && !vendorId.equals(0)) {
                        code=bizCodeDao.getBizcodeByBno(vendorId,bno);
                    }
                    return code;
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    log.error("获取单据号失败：vendorId=" + vendorId + ",bno=" + bno, e);
                    return null;
                }
            }
        });
    }

    @Override
    public boolean updateCacheForBno(BizCode code) {
        if (code == null) {
            log.error("更新缓存失败，code为null");
            return false;
        }
        String cacheKey = String.format("%s_%s", code.getVendorId(), code.getBno());
        try {
            jedisTemplate.set(cacheKey, JsonUtil.objectToJson(code));
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
