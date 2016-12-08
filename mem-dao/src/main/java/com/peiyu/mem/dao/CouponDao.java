package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.Coupon;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 批量插入优惠券
     * @param coupons
     */
    void insertBatchCoupons(@Param("coupons") List<Coupon> coupons);
}
