package com.peiyu.mem.manager;

import com.peiyu.mem.domian.entity.Coupon;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface CouponManager {
    /**
     * 插入优惠券
     *
     * @param coupons
     * @return
     */
    boolean insertCoupons(List<Coupon> coupons);

    /**
     * 更新优惠券
     *
     * @param coupons
     * @return
     */
    boolean updateCoupons(List<Coupon> coupons);
}
