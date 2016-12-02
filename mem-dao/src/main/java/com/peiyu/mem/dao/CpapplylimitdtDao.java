package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Repository
public interface CpapplylimitdtDao extends BaseDao<CpApplyLimitdt> {


    /**
     * 根据条件获取应用范围
     * @param Search
     * @return
     */
    List<CpApplyLimitdt> getCpApplyLimitdtsBySearch(CpApplyLimitdt Search);
}
