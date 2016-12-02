package com.peiyu.mem.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/11/29.
 */
@Repository
public interface BaseDao<T> {
    /**
     * 插入一条信息
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 根据主键删除信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 更新信息
     *
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 根据主键获取信息
     *
     * @param id
     * @return
     */
    T get(Long id);
}
