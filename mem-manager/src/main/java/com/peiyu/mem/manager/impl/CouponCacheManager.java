package com.peiyu.mem.manager.impl;

import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.dao.CouponDao;
import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.redis.JedisTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 * 优惠券缓存处理
 */
@Service
public class CouponCacheManager {
    @Autowired
    private JedisTemplate jedisTemplate;
    @Autowired
    private CpActivityDao activityDao;
    @Autowired
    private CouponDao couponDao;

    /**
     * 从缓存中获取一张未发送的优惠券
     * @param search
     * @return
     */
    public Coupon getOneNoGrantCouponFromCache(Coupon search) {
        String key = String.format("coupon_%s_%s_%s", search.getVendorId(), search.getActNo(),
                search.getSubgroupCode());
        String emptyKey = String.format("empty_%s_%s_%s", search.getVendorId(), search.getActNo(),
                search.getSubgroupCode());
        String json = jedisTemplate.lpop(key);
        if (StringUtils.isNotBlank(json)) {
            return JsonUtil.g.fromJson(json, Coupon.class);
        }
        if (jedisTemplate.exists(emptyKey)) {
            return null;//券空;
        }
        this.refreshCouponCache(search, true);//刷新缓存

        for (int i = 0; i < 5; i++) {//等待3分钟，避免别的线程在刷新导致未获取优惠券
            json = jedisTemplate.lpop(key);
            if (StringUtils.isNotBlank(json)) {
                return JsonUtil.g.fromJson(json, Coupon.class);
            }
            if (jedisTemplate.exists(emptyKey)) {
                return null;
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 刷新缓存
     * @param search
     * @param isAsync 是否异步刷新（true：是，false：否）
     */
    public void refreshCouponCache(final Coupon search, boolean isAsync) {
        final String key = String.format("coupon_%s_%s_%s", search.getVendorId(), search.getActNo(),
                search.getSubgroupCode());
        if (jedisTemplate.exists(key)) {
            return;
        }
        String emptyKey = String.format("empty_%s_%s_%s", search.getVendorId(), search.getActNo(),
                search.getSubgroupCode());
        if (jedisTemplate.exists(emptyKey)) {
            return;
        }
        final String lockKey = String.format("lockKeyCoupon");
        if (jedisTemplate.hsetNX(lockKey, lockKey, "60") == 1) {
            CpActivity activity = activityDao.getActivity(search.getVendorId(), search.getActNo());
            if (activity.getEndDate() != null) {
                if (activity.getEndDate().before(new Date())) {
                    return;
                }
            }
            try {
                int pageIndex = 0;
                search.setPageIndex(pageIndex);
                search.setPageSize(500);
                List<Coupon> coupons = couponDao.getCouponListByPage(search);
                if (CollectionUtils.isEmpty(coupons)) {
                    Long cacheSeconds = activity.getEndDate().getTime() - System.currentTimeMillis();
                    jedisTemplate.set(emptyKey, "empty", cacheSeconds.intValue() / 1000);
                    return;
                }
                String[] strList = new String[coupons.size()];
                for (int i = 0; i < coupons.size(); i++) {
                    strList[i] = JsonUtil.g.toJson(coupons.get(i));
                }
                jedisTemplate.lpush(key, strList);
            } catch (Exception e) {
                System.out.println("刷新缓存失败");
            } finally {
                jedisTemplate.hdel(lockKey,lockKey);
            }
        }
    }
}
