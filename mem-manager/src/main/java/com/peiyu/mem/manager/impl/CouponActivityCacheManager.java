package com.peiyu.mem.manager.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.dao.CpapplylimitdtDao;
import com.peiyu.mem.dao.CpuselimitdtDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.redis.JedisTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */
@Service
public class CouponActivityCacheManager {
    private Logger log = Logger.getLogger(CouponActivityCacheManager.class);
    @Autowired
    private CpActivityDao activityDao;
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Autowired
    private CpapplylimitdtDao applyLimitdtDao;
    @Autowired
    private CpuselimitdtDao cpuselimitdtDao;
    @Autowired
    private JedisTemplate jedisTemplate;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    abstract class ListGetterHandler<T> {
        /**
         * 从库中加载数据
         *
         * @param search
         * @return
         */
        abstract List<T> getListFromDB(T search);

        /**
         * 从缓存中获取数据如果没有从库中加载，并刷新缓存
         *
         * @param key
         * @param search
         * @return
         */
        public List<T> getListAndSetCache(final String key, T search, Class<T> type) {
            List<T> list = new ArrayList<>();
            String jsonList = jedisTemplate.get(key);
            if (StringUtils.isBlank(jsonList)) {
                final String lockKey = "lock:" + key;
                if (jedisTemplate.hsetNX(lockKey, lockKey, "60") == 1) {//防止并发时候数据库被击穿（表示设置成功）
                    list = getListFromDB(search);
                    final List<T> finalList = list;
                    //异步刷新缓存
                    taskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String json = JsonUtil.g.toJson(finalList);
                                jedisTemplate.set(key, json, 24 * 60 * 60);
                            } catch (Exception e) {
                                log.error("刷新缓存失败", e);
                            } finally {
                                jedisTemplate.hdel(lockKey, lockKey);
                            }
                        }
                    });
                }
            }
            if (StringUtils.isNotBlank(jsonList)) {
                JsonArray array = new JsonParser().parse(jsonList).getAsJsonArray();
                for (final JsonElement elem : array) {
                    list.add(JsonUtil.g.fromJson(elem, type));
                }
                return list;
            }
            return list;
        }
    }

    private ListGetterHandler<CpActivity> activityListGetterHandler = new ListGetterHandler<CpActivity>() {
        @Override
        List<CpActivity> getListFromDB(CpActivity search) {
            return activityDao.getCpActivityBySearch(search);
        }
    };

    private ListGetterHandler<CpActsubGroup> actsubGroupListGetterHandler = new ListGetterHandler<CpActsubGroup>() {
        @Override
        List<CpActsubGroup> getListFromDB(CpActsubGroup search) {
            return actsubGroupDao.getCpActsubGroupList(search);
        }
    };

    private ListGetterHandler<CpApplyLimitdt> applyLimitdtListGetterHandler = new ListGetterHandler<CpApplyLimitdt>() {
        @Override
        List<CpApplyLimitdt> getListFromDB(CpApplyLimitdt search) {
            return applyLimitdtDao.getCpApplyLimitdtsBySearch(search);
        }
    };

    private ListGetterHandler<CpUseLimitdt> useLimitdtListGetterHandler = new ListGetterHandler<CpUseLimitdt>() {
        @Override
        List<CpUseLimitdt> getListFromDB(CpUseLimitdt search) {
            return cpuselimitdtDao.getCpUseLimitdts(search);
        }
    };

    /**
     * 获取当天有效的优惠券活动
     *
     * @param search
     * @return
     */
    public List<CpActivity> getCpActivityList(CpActivity search) {
        String key = String.format("activitys_%s_%s", search.getVendorId(), search.getSendType());
        return activityListGetterHandler.getListAndSetCache(key, search, CpActivity.class);
    }

    /**
     * 获取活动下的有效的优惠券
     *
     * @param search
     * @return
     */
    public List<CpActsubGroup> getCpActsubGroupList(CpActsubGroup search) {
        String key = String.format("actSubGruops_%s_%s", search.getVendorId(), search.getActNo());
        return actsubGroupListGetterHandler.getListAndSetCache(key, search, CpActsubGroup.class);
    }

    /**
     * 获取活动的应用范围限制
     *
     * @param search
     * @return
     */
    public List<CpApplyLimitdt> getCpApplyLimitdtList(CpApplyLimitdt search) {
        String key = String.format("applyLimits_%s_%s", search.getVendorId(), search.getOwnRecordCode());
        return applyLimitdtListGetterHandler.getListAndSetCache(key, search, CpApplyLimitdt.class);
    }

    /**
     * 获取活动使用范围限制
     * @param search
     * @return
     */
    public List<CpUseLimitdt> getCpUseLimitList(CpUseLimitdt search) {
        String key = String.format("applyLimits_%s_%s", search.getVendorId(), search.getOwnRecordCode());
        return useLimitdtListGetterHandler.getListAndSetCache(key, search, CpUseLimitdt.class);
    }
}
