package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Repository
public interface CpapplylimitdtDao extends BaseDao<CpApplyLimitdt> {

    /**
     * 批量插入应用范围限制
     * @return
     */
    int insertBatchApplylimits(@Param("applyLimitdts") List<CpApplyLimitdt> applyLimitdts);
    /**
     * 批量更新应用范围限制
     * @param applyLimitdts
     * @return
     */
    int updateBatchApplylimits(@Param("applyLimitdts") List<CpApplyLimitdt> applyLimitdts);
    /**
     * 根据条件获取应用范围
     * @param Search
     * @return
     */
    List<CpApplyLimitdt> getCpApplyLimitdtsBySearch(CpApplyLimitdt Search);
}
