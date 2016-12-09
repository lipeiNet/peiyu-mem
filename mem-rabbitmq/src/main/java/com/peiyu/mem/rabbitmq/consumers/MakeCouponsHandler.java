package com.peiyu.mem.rabbitmq.consumers;

import com.google.gson.reflect.TypeToken;
import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.dao.CouponDao;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.rabbitmq.Gson2JsonMessageConverter;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
@Component
public class MakeCouponsHandler implements MessageListener {
    private Logger log = Logger.getLogger(MakeCouponsHandler.class);
    @Autowired
    private CouponDao couponDao;
    private Gson2JsonMessageConverter jsonMessageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            if (message == null || message.getBody() == null) {
                return;
            }
            String data=jsonMessageConverter.fromMessage(message).toString();
            if (StringUtils.isNotBlank(data)){
                List<Coupon> coupons= JsonUtil.g.fromJson(data,new TypeToken<List<Coupon>>(){}.getType());
                couponDao.insertBatchCoupons(coupons);
            }
        } catch (Exception e) {
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
