package com.peiyu.mem.manager;

import com.peiyu.mem.domian.entity.CpMakingTask;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface MakingTaskManager {
    /**
     * 验证是否重复保存制券任务
     * @param makingTask
     * @return
     */
    boolean isRepeat(CpMakingTask makingTask);

    /**
     * 验证是否重复提交制券任务
     * @param vendorId
     * @param taskCode
     * @return
     */
    boolean isRepeatByMaikingCoupon(Long vendorId,String taskCode);

    /**
     * 把制券任务加入缓存中（防止重复提交）
     * @param makingTask
     */
    void insertCacheByTaskCode(CpMakingTask makingTask);

    /**
     * 把正在进行的制券任务加入缓存中（验证重复提交制券任务）
     * @param vendorId
     * @param taskCode
     */
    void insertCacheByMakingConpon(Long vendorId,String taskCode);

    /**
     * 完成保存制券任务后删除缓存
     * @param makingTask
     */
    void deteleCacheByTaskCode(CpMakingTask makingTask);

    /**
     * 完成制券任务后删除缓存
     * @param vendorId
     * @param taskCode
     */
    void deleteCacheByMakingConpon(Long vendorId,String taskCode);

    /**
     * 获取制券任务
     * @param
     * @return
     */
    CpMakingTask getMakingTask(Long vendorId, String taskTask);
}

