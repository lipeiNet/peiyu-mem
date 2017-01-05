package com.peiyu.mem.tests;

import com.peiyu.mem.domian.entity.GoodsForCoupon;
import com.peiyu.mem.service.CouponService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-service-test.xml"})
public class CouponServiceUnitTests {
    @Autowired
    private CouponService couponService;
    @Test
    public void testConsumeSendCoupon() throws ParseException {
        List<GoodsForCoupon> goodsForCoupons = new ArrayList<>();
      /*  for (int i = 0; i < 10; i++) {*/
            GoodsForCoupon goodsForCoupon = new GoodsForCoupon();
            goodsForCoupon.setVendorId(1483l);
            goodsForCoupon.setMemNo("13520452");
            goodsForCoupon.setFirstBrandCode("pp30");
            goodsForCoupon.setSecondBrandCode("2");
            goodsForCoupon.setThirdBrandCode("3");
            goodsForCoupon.setFirstIcatCode("11");
            goodsForCoupon.setSecondIcatCode("12");
            goodsForCoupon.setThirdIcatCode("13");
            goodsForCoupon.setFourthIcatCode("14");
            goodsForCoupon.setMoney(30d);
            goodsForCoupon.setRealPayMoney(300d);
            goodsForCoupon.setSkuCode("21");
            goodsForCoupon.setStoreCode("22");
            goodsForCoupon.setOrganCode("910003");
            goodsForCoupon.setSupplierCode("24");
            goodsForCoupons.add(goodsForCoupon);
       // }
        int result = couponService.consumeSendCoupon(goodsForCoupons);
        System.out.println(result);
    }
}
