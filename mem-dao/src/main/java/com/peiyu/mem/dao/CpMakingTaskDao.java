package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.CpMakingTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
@Repository
public interface CpMakingTaskDao extends BaseDao<CpMakingTask> {
    /**
     * 根据制券编码获取制券信息
     * @param vendorId
     * @param taskCode
     * @return
     */
    CpMakingTask getCpMakingTaskByTaskCode(@Param("vendorId") Long vendorId,@Param("taskCode") String taskCode);
    /**
     * 根据条件获取制券的任务
     * @param search
     * @return
     */
    List<CpMakingTask> getCpMakingTasksBySearch(CpMakingTask search);
}
