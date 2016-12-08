package com.peiyu.com.rabbitmq.tests;

import com.migr.common.util.DateUtil;
import com.migr.common.util.JsonUtil;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.rabbitmq.MqSenderHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-rabbitmq-producer.xml"})
public class MqSenderHandlerUnitTests {
    @Autowired
    private MqSenderHandler mqSenderHandler;
    @Test
    public void testSendMessage(){
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
        List<Coupon> coupons=new ArrayList<>();
        coupons.add(coupon);
        mqSenderHandler.sendMessage("spring.makeCoupons.queueKey",coupons);
    }
}
