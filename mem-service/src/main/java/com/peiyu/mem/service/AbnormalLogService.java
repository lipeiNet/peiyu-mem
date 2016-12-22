package com.peiyu.mem.service;

import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;

/**
 * Created by Administrator on 2016/12/21.
 */
public interface AbnormalLogService {
    /**
     *记录异常日志
     * @param joinPoint
     */
    void saveAbnormalLog(JoinPoint joinPoint,Exception e) throws ClassNotFoundException, NotFoundException;

}
