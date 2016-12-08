package com.peiyu.mem.service;

import com.migr.common.util.StringUtils;
import com.peiyu.mem.domian.entity.CpMakingTask;

/**
 * Created by Administrator on 2016/12/6.
 * 制作优惠券任务
 */
public interface MakingTaskService {
    /**
     * 保存优惠券任务
     * @param makingTask
     * @return
     */
    int insertMakingTask(CpMakingTask makingTask);

    /**
     * 制券
     * 0：制券失败，1：制券成功，2：没有保存制券任务，3：重复提交制券
     * @param
     */
    int makingCoupon(Long vendorId, String taskTask);

    /**
     * 删除制券任务
     * @param id
     */
    void deleteMakingTask(long id);
    /**
     * 更新优惠券制券任务
     * @param makingTask
     * @return
     */
    int updateMakingTask(CpMakingTask makingTask);

    /**
     * 生产优惠券编码样板
     * @return
     */
    String getTempCpCode(CpMakingTask makingTask);
    /**
     * 获取优惠券任务
     * @param id
     * @return
     */
    CpMakingTask getMakingTask(long id);
}
