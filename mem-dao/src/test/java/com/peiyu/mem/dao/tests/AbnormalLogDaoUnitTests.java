package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.AbnormalLogDao;
import com.peiyu.mem.domian.entity.AbnormalLog;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/20.
 */
public class AbnormalLogDaoUnitTests extends BaseDaoUnitTests{
    @Autowired
    private AbnormalLogDao abnormalLogDao;
    @Test
    public void testInsert(){
        AbnormalLog abnormalLog=new AbnormalLog();
        abnormalLog.setClassName("actionlog");
        abnormalLog.setCreateDate(new Date());
        abnormalLog.setMemNo("123");
        abnormalLog.setMethodName("insert");
        abnormalLog.setMethodParam("123");
        abnormalLog.setOperationTime(1000l);
        abnormalLog.setAbnormalInfo("出现异常");
        abnormalLog.setVendorId(1433l);
        abnormalLogDao.insert(abnormalLog);
    }
    @Test
    public void testDelete(){
        int result=abnormalLogDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        AbnormalLog abnormalLog=new AbnormalLog();
        abnormalLog.setId(1l);
        abnormalLog.setClassName("actionlog");
        abnormalLog.setCreateDate(new Date());
        abnormalLog.setMemNo("123");
        abnormalLog.setMethodName("delete");
        abnormalLog.setMethodParam("123");
        abnormalLog.setOperationTime(1000l);
        abnormalLog.setAbnormalInfo("出现异常1");
        abnormalLog.setVendorId(1433l);
        abnormalLogDao.update(abnormalLog);
    }
   @Test
    public void testGet(){
       AbnormalLog abnormalLog=getAbnormalLog();
       AbnormalLog result=abnormalLogDao.get(1l);
       ReflectionAssert.assertReflectionEquals(abnormalLog,result);
   }
    protected AbnormalLog getAbnormalLog(){
        AbnormalLog abnormalLog=new AbnormalLog();
        abnormalLog.setId(1l);
        abnormalLog.setClassName("actionlog");
        abnormalLog.setCreateDate(DateUtil.getFormatDate("2016-12-20","yyyy-MM-dd"));
        abnormalLog.setMemNo("156234");
        abnormalLog.setParamValue("123");
        abnormalLog.setMethodName("insert");
        abnormalLog.setMethodParam("2312");
        abnormalLog.setOperationTime(1000l);
        abnormalLog.setVendorId(1433l);
        abnormalLog.setAbnormalInfo("异常信息");
        return abnormalLog;
    }
}
