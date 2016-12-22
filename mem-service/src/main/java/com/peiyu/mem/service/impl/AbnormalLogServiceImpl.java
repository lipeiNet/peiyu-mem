package com.peiyu.mem.service.impl;

import com.migr.common.util.JsonUtil;
import com.peiyu.mem.domian.entity.AbnormalLog;
import com.peiyu.mem.domian.entity.ActionLog;
import com.peiyu.mem.rabbitmq.produces.MqSenderHandler;
import com.peiyu.mem.service.AbnormalLogService;
import com.peiyu.mem.utils.ParamUtils;
import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/21.
 */
@Service
public class AbnormalLogServiceImpl implements AbnormalLogService {
    @Autowired
    private MqSenderHandler senderHandler;

    @Override
    public void saveAbnormalLog(JoinPoint joinPoint, Exception e) throws ClassNotFoundException, NotFoundException {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder methodParam = new StringBuilder();
        StringBuilder pavamValues = new StringBuilder();
        if (!className.contains("dao")) {
            String classType = joinPoint.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            String[] methodParams;
            methodParams = ParamUtils.getPavamsName(clazz, clazzName, methodName);
            if (methodParams != null && methodParams.length > 0) {
                for (String str : methodParams) {
                    methodParam.append(str + ",");
                }
            }
        }
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                String typeName = obj.getClass().getName();
                if (ParamUtils.isBasicType(typeName)) {
                    pavamValues.append(obj + ",");
                } else {
                    pavamValues.append(ParamUtils.getFieldsValue(obj) + ",");
                }
            }
        }
        AbnormalLog abnormalLog = new AbnormalLog();
        abnormalLog.setVendorId(0l);
        abnormalLog.setMemNo("0");
        abnormalLog.setClassName(className);
        abnormalLog.setMethodName(methodName);
        abnormalLog.setCreateDate(new Date());
        abnormalLog.setMethodParam(methodParam.toString());
        abnormalLog.setParamValue(pavamValues.toString());
        abnormalLog.setAbnormalInfo(e.getStackTrace().toString());
        String data = JsonUtil.g.toJson(abnormalLog);
        senderHandler.sendMessage("spring.abnormalLog.queueKey", data);
    }
}
