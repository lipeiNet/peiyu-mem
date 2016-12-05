package com.peiyu.mem.service;

import com.peiyu.mem.domian.entity.CpActsubGroup;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface CpActsubGroupService {
    /**
     *
     * @param actsubGroup
     * @return
     */
    int insertActsubGroup(CpActsubGroup actsubGroup);

    /**
     * 根据主键删除记录
     * @param id
     * @return
     */
    int deleteActsubGroup(long id);

    /**
     * 批量删除优惠券组记录
     * @param ids
     * @return
     */
    int deleteBatchActsubGroup(String ids);

    /**
     * 根据条件更新优惠券组信息
     * @param search
     * @return
     */
    int updateActsubGroup(CpActsubGroup search);

    /**
     * 根据主键获取优惠券组
     * @param id
     * @return
     */
    int getActsubGroup(long id);

}
