package com.peiyu.com.rabbitmq.tests;

import com.migr.common.util.DateUtil;
import com.migr.common.util.JsonUtil;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.rabbitmq.produces.MqSenderHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
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
        coupon.setVendorId(1461l);
        coupon.setActNo("VD2016090800001");
        coupon.setActName("消费送券");
        coupon.setSubgroupCode("VE2016090800001");
        coupon.setCpCode("101851");
        coupon.setCpValue(25d);
        coupon.setStartDate(new Date());
        coupon.setEndDate(new Date());
        coupon.setState(1);
        coupon.setMemNo("17876647");
        coupon.setMemCat("黄金会员");
        coupon.setUseValue(10d);
        coupon.setMemo("备注");
        coupon.setCreateDate(new Date());
        coupon.setModifyDate(new Date());
        coupon.setCreator("lp");
        coupon.setDf(0);
        List<Coupon> coupons=new ArrayList<>();
        coupons.add(coupon);
        String data= JsonUtil.g.toJson(coupons);
        mqSenderHandler.sendMessage("spring.makeCoupons.queueKey",data);
    }
}
