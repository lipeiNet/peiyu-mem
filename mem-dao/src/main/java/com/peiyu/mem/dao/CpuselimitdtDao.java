package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.CpUseLimitdt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Repository
public interface CpuselimitdtDao extends BaseDao<CpUseLimitdt> {
    /**
     * 批量添加使用范围限制
     * @param useLimitdts
     * @return
     */
    int insertBatchUselimits(@Param("useLimitdts") List<CpUseLimitdt> useLimitdts);

    /**
     * 批量更新使用范围限制
     * @param useLimitdts
     * @return
     */
    int updateBatchUselimits(@Param("useLimitdts") List<CpUseLimitdt> useLimitdts);
    /**
     * 获取使用范围
     * @param search
     * @return
     */
    List<CpUseLimitdt> getCpUseLimitdts(CpUseLimitdt search);
}
