package com.peiyu.mem.manager.impl;

import com.migr.common.util.JsonUtil;
import com.peiyu.mem.dao.CouponDao;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.manager.CouponManager;
import com.peiyu.mem.rabbitmq.produces.MqSenderHandler;
import com.peiyu.mem.utils.ListUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service
public class CouponManagerImpl implements CouponManager {
    private Logger log = Logger.getLogger(CouponManagerImpl.class);
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private MqSenderHandler mqSenderHandler;

    @Override
    public boolean insertCoupons(final List<Coupon> coupons) {
        final long start1 = System.currentTimeMillis();
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        return template.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    if (coupons.size() <= 5000) {
                        couponDao.insertBatchCoupons(coupons);
                    }
                    if (coupons.size() > 5000) {
                        List<List<Coupon>> tempCoupons = ListUtil.splitList(coupons, 5000);
                        int i=0;
                        for (List<Coupon> item : tempCoupons) {
//                            try{
                                if (i==5){
//                                    i++;
                                    throw new Exception("异常");
                                }
                                couponDao.insertBatchCoupons(item);
                                i++;
//                            }catch (Exception e){
//                                String data= JsonUtil.g.toJson(item);
//                                mqSenderHandler.sendMessage("spring.makeCoupons.queueKey",data);
//                                continue;
//                            }
                        }
                    }
                    long end1 = System.currentTimeMillis();
                    log.info("添加10000张优惠券消耗时间：" + (end1 - start1) + "毫秒");
                    return true;
                } catch (Exception e) {
                    log.error("添加优惠券异常：" + e);
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }
}
