package com.peiyu.mem.service;

import javassist.NotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface ActionLogService {
    /**
     * 记录删除类操作日志
     * @param joinPoint
     */
    Object insertActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException;

    /**
     * 记录删除类操作日志
     * @param joinPoint
     * @return
     */
    Object deleteActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException;

    /**
     * 记录修改类操作日志
     * @param joinPoint
     * @return
     */
    Object updateActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException;
    /**
     * 记录查询类操作日志
     * @param joinPoint
     * @return
     */
    Object getActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException;
}
