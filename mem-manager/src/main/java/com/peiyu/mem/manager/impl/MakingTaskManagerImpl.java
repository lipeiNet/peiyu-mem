package com.peiyu.mem.manager.impl;

import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.dao.CpMakingTaskDao;
import com.peiyu.mem.domian.entity.CpMakingTask;
import com.peiyu.mem.manager.MakingTaskManager;
import com.peiyu.mem.redis.JedisTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service
public class MakingTaskManagerImpl implements MakingTaskManager {
    private Logger log = Logger.getLogger(MakingTaskManagerImpl.class);
    @Autowired
    private JedisTemplate jedisTemplate;
    @Autowired
    private CpMakingTaskDao makingTaskDao;

    @Override
    public boolean isRepeat(CpMakingTask makingTask) {
        String taskCode = String.format("%s_%s", makingTask.getVendorId(), makingTask.getTaskCode());
        if (makingTask.getId() != null && !"".equals(makingTask.getId())) {
            CpMakingTask tempTask = makingTaskDao.get(makingTask.getId());
            if (tempTask != null) {
                jedisTemplate.delete(taskCode);
            }
        }
        if (StringUtils.isNotBlank(jedisTemplate.get(taskCode))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRepeatByMaikingCoupon(Long vendorId, String taskCode) {
        final String mainkingCode = String.format("MACK_COUPON_%s_%s", vendorId, taskCode);
        if (StringUtils.isNotBlank(jedisTemplate.get(mainkingCode))) {
            return true;
        }
        return false;
    }

    @Override
    public void insertCacheByTaskCode(CpMakingTask makingTask) {
        String taskCode = String.format("%s_%s", makingTask.getVendorId(), makingTask.getTaskCode());
        String taskToJson = JsonUtil.objectToJson(makingTask);
        jedisTemplate.set(taskCode, taskToJson);
    }

    @Override
    public void insertCacheByMakingConpon(Long vendorId, String taskCode) {
        final String mainkingCode=String.format("MACK_COUPON_%s_%s",vendorId,taskCode);
        jedisTemplate.set(mainkingCode,"制券中...");
    }

    @Override
    public void deteleCacheByTaskCode(CpMakingTask makingTask) {
        String taskCode = String.format("%s_%s", makingTask.getVendorId(), makingTask.getTaskCode());
        jedisTemplate.delete(taskCode);
    }

    @Override
    public void deleteCacheByMakingConpon(Long vendorId, String taskCode) {
        final String mainkingCode=String.format("MACK_COUPON_%s_%s",vendorId,taskCode);
        jedisTemplate.delete(mainkingCode);
    }

    @Override
    public CpMakingTask getMakingTask(Long vendorId, String taskTask) {
        String taskCode = String.format("%s_%s", vendorId, taskTask);
        String taskJson = jedisTemplate.get(taskCode);
        if (StringUtils.isNotBlank(taskJson)) {
            return JsonUtil.g.fromJson(taskJson, CpMakingTask.class);
        }
        /*if (makingTask.getId() != null) {
            return makingTaskDao.get(makingTask.getId());
        }*/
        return null;
    }
}
