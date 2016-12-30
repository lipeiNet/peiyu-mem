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
     * 批量插入优惠券
     * @param coupons
     */
    void insertBatchCoupons(@Param("coupons") List<Coupon> coupons);

    /**
     * 批量更新优惠券状态
     * @param coupons
     */
    void updateBatchCouponsState(@Param("coupons") List<Coupon> coupons);
    /**
     * 根据条件获取优惠券
     * @param search
     * @return
     */
    List<Coupon> getCouponsBySearch(Coupon search);

    /**
     * 分页
     * @param search
     * @return
     */
    List<Coupon> getCouponListByPage(Coupon search);


}
