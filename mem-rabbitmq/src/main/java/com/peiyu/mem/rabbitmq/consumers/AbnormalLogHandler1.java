package com.peiyu.mem.rabbitmq.consumers;

import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.dao.AbnormalLogDao;
import com.peiyu.mem.dao.ActionLogDao;
import com.peiyu.mem.domian.entity.AbnormalLog;
import com.peiyu.mem.domian.entity.ActionLog;
import com.peiyu.mem.rabbitmq.Gson2JsonMessageConverter;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/22.
 */
public class AbnormalLogHandler1 implements ChannelAwareMessageListener {
    private Logger log = Logger.getLogger(AbnormalLogHandler1.class);
    @Autowired
    private AbnormalLogDao abnormalLogDao;
    @Autowired
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
                AbnormalLog abnormalLog = JsonUtil.g.fromJson(data, AbnormalLog.class);
                abnormalLogDao.insert(abnormalLog);
            }
        } catch (Exception e) {
            log.error("操作日志异常" + e);
        }
    }
}
