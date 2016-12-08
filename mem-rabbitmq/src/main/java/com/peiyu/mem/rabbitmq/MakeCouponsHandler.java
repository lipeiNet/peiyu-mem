package com.peiyu.mem.rabbitmq;

import com.google.gson.reflect.TypeToken;
import com.migr.common.util.JsonUtil;
import com.peiyu.mem.dao.CouponDao;
import com.peiyu.mem.domian.entity.Coupon;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class MakeCouponsHandler implements MessageListener {
    private Logger log = Logger.getLogger(MakeCouponsHandler.class);
    @Autowired
    private CouponDao couponDao;

    @Override
    public void onMessage(Message message) {
        try {
            if (message == null || message.getBody() == null) {
                return;
            }
            String body = new String(message.getBody(), "UTF-8");
            List<Coupon> coupons = JsonUtil.g.fromJson(body, new TypeToken<List<Coupon>>() {
            }.getType());
            couponDao.insertBatchCoupons(coupons);
        } catch (Exception e) {
            log.error("消息队列处理制券异常：" + e);
            System.out.println("消息队列处理制券异常：" + e.getMessage());
        }
    }
}
