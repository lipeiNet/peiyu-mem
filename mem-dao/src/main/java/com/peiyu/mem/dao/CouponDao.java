package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
@Repository
public interface CouponDao extends BaseDao<Coupon> {
    /**
     * 根据条件获取优惠券
     * @param serach
     * @return
     */
    List<Coupon> getCouponsBySearch(Coupon serach);
}
