package com.peiyu.mem.utils;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/12/22.
 */
public class ParamUtils {
    /**
     * 验证参数类型是否是基本类型
     * @param typeName
     * @return
     */
    public static boolean isBasicType(String typeName) {
        boolean clazzFlag = true;
        for (String t : types) {
            if (t.equals(typeName)) {
                clazzFlag = false;
            }
        }
        return clazzFlag;
    }
    /**
     * 获取参数名称
     * @param cls
     * @param clazzName
     * @param methodName
     * @return
     * @throws NotFoundException
     */
    public static String[] getPavamsName(Class cls, String clazzName, String methodName) throws NotFoundException {
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
    public static String getFieldsValue(Object obj) {
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
