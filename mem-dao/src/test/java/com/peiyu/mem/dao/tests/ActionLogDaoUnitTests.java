package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.ActionLogDao;
import com.peiyu.mem.domian.entity.ActionLog;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/20.
 */
public class ActionLogDaoUnitTests extends BaseDaoUnitTests{
    @Autowired
    private ActionLogDao actionLogDao;
    @Test
    public void testInsert(){
        ActionLog actionLog=new ActionLog();
        actionLog.setClassName("actionlog");
        actionLog.setCreateDate(new Date());
        actionLog.setMemNo("123");
        actionLog.setMethodName("insert");
        actionLog.setMethodParam("123");
        actionLog.setOperationTime(1000l);
        actionLog.setMethodType(1);
        actionLog.setVendorId(1433l);
        actionLogDao.insert(actionLog);
    }
    @Test
    public void testUpdate(){
        ActionLog actionLog=new ActionLog();
        actionLog.setId(1l);
        actionLog.setClassName("actionlog");
        actionLog.setCreateDate(new Date());
        actionLog.setMemNo("123");
        actionLog.setMethodName("insert");
        actionLog.setMethodParam("123");
        actionLog.setOperationTime(1000l);
        actionLog.setMethodType(1);
        actionLog.setVendorId(1433l);
        assertEquals(1,actionLogDao.update(actionLog));
    }
    @Test
    public void testDelete(){
        long id=1;
        assertEquals(1,actionLogDao.delete(id));
    }
    @Test
    public void testGet(){
        ActionLog except=getActionLog();
        ActionLog result=actionLogDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except,result);

    }
    protected ActionLog getActionLog(){
        ActionLog actionLog=new ActionLog();
        actionLog.setId(1l);
        actionLog.setClassName("actionlog");
        actionLog.setCreateDate(DateUtil.getFormatDate("2016-12-20","yyyy-MM-dd"));
        actionLog.setMemNo("156234");
        actionLog.setParamValue("123");
        actionLog.setMethodName("insert");
        actionLog.setMethodParam("2312");
        actionLog.setOperationTime(1000l);
        actionLog.setMethodType(1);
        actionLog.setVendorId(1433l);
        return actionLog;
    }
}
