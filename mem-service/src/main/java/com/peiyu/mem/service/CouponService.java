package com.peiyu.mem.service;

import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.domian.entity.GoodsForCoupon;

import java.util.List;

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
     * 消费送券(-1表示服务器异常，返回送优惠券的数量)
     * @param goodsForCoupons
     * @return
     */
    int consumeSendCoupon(List<GoodsForCoupon> goodsForCoupons);

    /**
     * 获取优惠券
     * @param id
     * @return
     */
    Coupon getCoupon(long id);
}
