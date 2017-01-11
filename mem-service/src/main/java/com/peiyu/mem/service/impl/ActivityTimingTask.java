package com.peiyu.mem.service.impl;

import com.migr.common.util.JsonUtil;
import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.dao.CpapplylimitdtDao;
import com.peiyu.mem.dao.CpuselimitdtDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.redis.JedisTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 * 优惠券定时任务
 */
@Service
public class ActivityTimingTask {
    @Autowired
    private CpActivityDao activityDao;
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Autowired
    private CpapplylimitdtDao cpapplylimitdtDao;
    @Autowired
    private CpuselimitdtDao cpuselimitdtDao;
    @Autowired
    private JedisTemplate jedisTemplate;

    /**
     * 获取数据代理
     *
     * @param <T>
     */
    abstract class CacheRefreshHandler<T> {
        /**
         * 数据库加载数据
         *
         * @param search
         * @return
         */
        abstract List<T> getListFromDB(T search);

        /**
         * 获取key值
         *
         * @param item
         * @return
         */
        abstract String getKey(T item);

        /**
         * 刷新活动通用方法
         *
         * @param search
         */
        public void refreshCache(T search) {
            List<T> list = getListFromDB(search);
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            String key = getKey(search);
            jedisTemplate.set(key, JsonUtil.g.toJson(search));
        }
    }

    public void TimingRefreshAllCache(){
        try {
            this.refreshActivityCache(new CpActivity());
            this.refreshSubGroupCache(new CpActsubGroup());
            this.refreshCpApplyLimitdt(new CpApplyLimitdt());
            this.refreshCpUseLimitdt(new CpUseLimitdt());
        }catch (Exception e){
            System.out.println("刷新缓存失败");
        }
    }

    /**
     * 刷新当天活动缓存
     *
     * @param search
     */
    protected void refreshActivityCache(CpActivity search) {
        search.setEndDate(new Date());
        search.setStatus(SysConstants.ACTIVITYSTATUS.CHECKED);
        CacheRefreshHandler<CpActivity> refreshHandler = new CacheRefreshHandler<CpActivity>() {
            @Override
            List<CpActivity> getListFromDB(CpActivity search) {
                return activityDao.getCpActivityBySearch(search);
            }

            @Override
            String getKey(CpActivity item) {
                return String.format("");
            }
        };
        refreshHandler.refreshCache(search);
    }

    /**
     * 刷新优惠券组的缓存
     *
     * @param search
     */
    public void refreshSubGroupCache(CpActsubGroup search) {
        CacheRefreshHandler<CpActsubGroup> actsubGroupCacheRefreshHandler = new CacheRefreshHandler<CpActsubGroup>() {
            @Override
            List<CpActsubGroup> getListFromDB(CpActsubGroup search) {
                return actsubGroupDao.getCpActsubGroupList(search);
            }

            @Override
            String getKey(CpActsubGroup item) {
                return String.format("");
            }
        };
        actsubGroupCacheRefreshHandler.refreshCache(search);
    }

    /**
     * 刷新应用范围限制
     * @param search
     */
    public void refreshCpApplyLimitdt(CpApplyLimitdt search){
        CacheRefreshHandler<CpApplyLimitdt> cacheRefreshHandler=new CacheRefreshHandler<CpApplyLimitdt>() {
            @Override
            List<CpApplyLimitdt> getListFromDB(CpApplyLimitdt search) {
                return cpapplylimitdtDao.getCpApplyLimitdtsBySearch(search);
            }

            @Override
            String getKey(CpApplyLimitdt item) {
                return String.format("");
            }
        };
        cacheRefreshHandler.refreshCache(search);
    }

    /**
     * 刷新使用范围限制
     * @param search
     */
    public void refreshCpUseLimitdt(CpUseLimitdt search){
        CacheRefreshHandler<CpUseLimitdt> cacheRefreshHandler=new CacheRefreshHandler<CpUseLimitdt>() {
            @Override
            List<CpUseLimitdt> getListFromDB(CpUseLimitdt search) {
                return cpuselimitdtDao.getCpUseLimitdts(search);
            }

            @Override
            String getKey(CpUseLimitdt item) {
                return String.format("");
            }
        };
        cacheRefreshHandler.refreshCache(search);
    }


}
