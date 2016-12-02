package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.CpUseLimitdt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Repository
public interface CpuselimitdtDao extends BaseDao<CpUseLimitdt> {
    /**
     * 获取使用范围
     * @param search
     * @return
     */
    List<CpUseLimitdt> getCpUseLimitdts(CpUseLimitdt search);
}
