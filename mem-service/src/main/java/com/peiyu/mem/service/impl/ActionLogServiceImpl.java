package com.peiyu.mem.service.impl;

import com.migr.common.util.JsonUtil;
import com.migr.common.util.StringUtils;
import com.peiyu.mem.domian.entity.ActionLog;
import com.peiyu.mem.domian.entity.Member;
import com.peiyu.mem.rabbitmq.produces.MqSenderHandler;
import com.peiyu.mem.service.ActionLogService;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service
public class ActionLogServiceImpl implements ActionLogService {
    @Autowired
    private MqSenderHandler senderHandler;

    @Override
    public Object insertActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException {
        if (actionLog(joinPoint,0)){
            return true;
        }
        return false;
    }

    @Override
    public Object deleteActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException {
        if (actionLog(joinPoint,1)){
            return true;
        }
        return false;
    }

    @Override
    public Object updateActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException {
        if (actionLog(joinPoint,2)){
            return true;
        }
        return false;
    }

    @Override
    public Object getActionLog(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NotFoundException {
        if (actionLog(joinPoint, 3)) {
            return new Member();
        }
        return null;
    }


    /**
     * 公共处理操作日志
     * @param joinPoint
     * @param type
     * @return
     */
    protected boolean actionLog(ProceedingJoinPoint joinPoint,int type) throws ClassNotFoundException, NotFoundException {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder methodParam=new StringBuilder();
        StringBuilder pavamValues = new StringBuilder();
        if (!className.contains("dao")) {
            String classType = joinPoint.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            String[] methodParams;
            methodParams = this.getPavamsName(clazz, clazzName, methodName);
            if (methodParams != null && methodParams.length > 0) {
                for (String str : methodParams) {
                    methodParam.append(str+",");
                }
            }
        }
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                String typeName = obj.getClass().getName();
                boolean clazzFlag = true;
                for (String t : types) {
                    if (t.equals(typeName)) {
                        clazzFlag = false;
                        pavamValues.append(obj + ",");
                    }
                }
                if (clazzFlag) {
                    pavamValues.append(this.getFieldsValue(obj) + ",");
                }
            }
        }
        ActionLog actionLog = new ActionLog();
        actionLog.setVendorId(0l);
        actionLog.setMemNo("0");
        actionLog.setClassName(className);
        actionLog.setMethodName(methodName);
        actionLog.setMethodType(type);
        actionLog.setCreateDate(new Date());
        actionLog.setMethodParam(methodParam.toString());
        actionLog.setParamValue(pavamValues.toString());
        long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        actionLog.setOperationTime(end - start);
        String data = JsonUtil.g.toJson(actionLog);
        senderHandler.sendMessage("spring.actionLog.queueKey", data);
        return true;
    }
    /**
     * 获取参数名称
     * @param cls
     * @param clazzName
     * @param methodName
     * @return
     * @throws NotFoundException
     */
    protected String[] getPavamsName(Class cls, String clazzName, String methodName) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            return null;
        }
        String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++){
            paramNames[i] = attr.variableName(i + pos+1); //paramNames即参数名
        }

        return paramNames;
    }

    /**
     * 获取对象的属性值
     * @param obj
     * @return
     */
    public String getFieldsValue(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String typeName = obj.getClass().getName();
        for (String t : types) {
            if (t.equals(typeName))
                return "";
        }
        StringBuilder sb = new StringBuilder();
        if (fields != null && fields.length > 0) {
            sb.append("【");
            for (Field f : fields) {
                f.setAccessible(true);
                for (String str : types) {
                    if (f.getType().getName().equals(str)) {
                        try {
                            sb.append(f.get(obj) + ",");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            sb.append("】");
        }
        return sb.toString();
    }
    private static String[] types = { "java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float" };
}
