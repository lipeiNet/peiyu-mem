package com.peiyu.mem.service;

import com.peiyu.mem.domian.entity.Coupon;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface CouponService {
    /**
     * 添加优惠券
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon);

    /**
     * 删除优惠券
     * @param id
     * @return
     */
    int deleteCoupon(long id);

    /**
     * 更新优惠券
     * @param coupon
     * @return
     */
    int updateCoupon(Coupon coupon);

    /**
     * 获取优惠券
     * @param id
     * @return
     */
    Coupon getCoupon(long id);

}
