package com.peiyu.mem.rabbitmq.consumers;

import com.google.gson.reflect.TypeToken;
import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.dao.CouponDao;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.rabbitmq.Gson2JsonMessageConverter;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class MakeCouponsHandler2 implements ChannelAwareMessageListener {
    private Logger log = Logger.getLogger(MakeCouponsHandler2.class);
    @Autowired
    private CouponDao couponDao;
    private Gson2JsonMessageConverter jsonMessageConverter;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            channel.basicQos(1);
            if (message == null || message.getBody() == null) {
                return;
            }
            String data = jsonMessageConverter.fromMessage(message).toString();
            if (StringUtils.isNotBlank(data)) {
                List<Coupon> coupons = JsonUtil.g.fromJson(data, new TypeToken<List<Coupon>>() {
                }.getType());
                couponDao.insertBatchCoupons(coupons);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
            log.error("消息队列处理制券异常：" + e);
        }
    }

    public Gson2JsonMessageConverter getJsonMessageConverter() {
        return jsonMessageConverter;
    }

    public void setJsonMessageConverter(Gson2JsonMessageConverter jsonMessageConverter) {
        this.jsonMessageConverter = jsonMessageConverter;
    }
}
