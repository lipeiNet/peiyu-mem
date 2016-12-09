package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CouponDao;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CouponDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private CouponDao couponDao;
    @Test
    public void testInsert(){
        Coupon coupon=new Coupon();
        coupon.setDf(1);
        coupon.setCreator("zhangsan");
        coupon.setActName("1234");
        coupon.setCpValue(10d);
        int result=couponDao.insert(coupon);
        assertEquals(1,result);
    }
    @Test
    public void testInsertBatchCoupons() {
        Coupon coupon=new Coupon();
        coupon.setVendorId(1461l);
        coupon.setActNo("VD2016090800001");
        coupon.setActName("消费送券");
        coupon.setSubgroupCode("VE2016090800001");
        coupon.setCpCode("123");
        coupon.setCpValue(25d);
        coupon.setStartDate(com.migr.common.util.DateUtil.getFormatDate("2016-09-08","yyyy-MM-dd"));
        coupon.setEndDate(com.migr.common.util.DateUtil.getFormatDate("2016-09-16","yyyy-MM-dd"));
        coupon.setState(1);
        coupon.setMemNo("17876647");
        coupon.setMemCat("黄金会员");
        coupon.setUseValue(10d);
        coupon.setMemo("备注");
        coupon.setCreateDate(com.migr.common.util.DateUtil.getFormatDate("2016-09-08","yyyy-MM-dd"));
        coupon.setModifyDate(com.migr.common.util.DateUtil.getFormatDate("2016-10-08","yyyy-MM-dd"));
        coupon.setCreator("lp");
        coupon.setDf(0);
        List<Coupon> coupons=new ArrayList<>();
        coupons.add(coupon);
        couponDao.insertBatchCoupons(coupons);
    }
    @Test
    public void testDelete(){
        int result=couponDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        Coupon coupon=new Coupon();
        coupon.setDf(1);
        coupon.setCreator("zhangsan");
        coupon.setActName("1234");
        coupon.setCpValue(10d);
        coupon.setId(1l);
        int result=couponDao.update(coupon);
        assertEquals(1,result);
    }
    @Test
    public void testGet(){
        Coupon except=getExceptCoupon();
        Coupon result=couponDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except, result);
    }
    protected Coupon getExceptCoupon(){
        Coupon coupon=new Coupon();
        coupon.setId(1l);
        coupon.setVendorId(1461l);
        coupon.setActNo("VD2016090800001");
        coupon.setActName("消费送券");
        coupon.setSubgroupCode("VE2016090800001");
        coupon.setCpCode("10142851");
        coupon.setCpValue(25d);
        coupon.setStartDate(DateUtil.getFormatDate("2016-09-08","yyyy-MM-dd"));
        coupon.setEndDate(DateUtil.getFormatDate("2016-09-16","yyyy-MM-dd"));
        coupon.setState(1);
        coupon.setMemNo("17876647");
        coupon.setMemCat("黄金会员");
        coupon.setUseValue(10d);
        coupon.setMemo("备注");
        coupon.setCreateDate(DateUtil.getFormatDate("2016-09-08","yyyy-MM-dd"));
        coupon.setModifyDate(DateUtil.getFormatDate("2016-10-08","yyyy-MM-dd"));
        coupon.setCreator("lp");
        coupon.setDf(0);
        return coupon;
    }
}
