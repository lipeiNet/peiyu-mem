package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CpMakingTaskDao;
import com.peiyu.mem.domian.entity.CpMakingTask;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CpMakingTaskDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private CpMakingTaskDao cpMakingTaskDao;
    @Test
    public void testInsert(){
        CpMakingTask makingTask=new CpMakingTask();
        makingTask.setVendorId(1455l);
        makingTask.setDf(1);
        makingTask.setActName("lingka");
        makingTask.setCreateDate(new Date());
        makingTask.setCouRulesNum(1);
        int result=cpMakingTaskDao.insert(makingTask);
        assertEquals(1,result);
    }
    @Test
    public void testDelete(){
        int result=cpMakingTaskDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        CpMakingTask makingTask=new CpMakingTask();
        makingTask.setVendorId(1455l);
        makingTask.setDf(1);
        makingTask.setActName("ngka");
        makingTask.setCreator("zhangsan");
        makingTask.setCreateDate(new Date());
        makingTask.setCouRulesNum(1);
        makingTask.setId(1l);
        int result=cpMakingTaskDao.update(makingTask);
        assertEquals(1,result);
    }
    @Test
    public void testGet(){
        CpMakingTask except=getExceptMakingTask();
        CpMakingTask result=cpMakingTaskDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except, result);
    }

    public CpMakingTask getExceptMakingTask(){
        CpMakingTask makingTask=new CpMakingTask();
        makingTask.setId(1l);
        makingTask.setVendorId(1458l);
        makingTask.setActNo("VD2016081000004");
        makingTask.setActName("测试手动发优惠券");
        makingTask.setSubgroupCode("VE2016081000007");
        makingTask.setCpValue(10d);
        makingTask.setStartDate(DateUtil.getFormatDate("2016-08-10","yyyy-MM-dd"));
        makingTask.setEndDate(DateUtil.getFormatDate("2016-12-01","yyyy-MM-dd"));
        makingTask.setTaskCode("VF2016081000001");
        makingTask.setGenNoRulePrefix("YLSD");
        makingTask.setGenNoRuleSuffix("1");
        makingTask.setSerialNoStart(1);
        makingTask.setSerialNoLength(5);
        makingTask.setTaskBeginTime(DateUtil.getFormatDate("2016-08-10","yyyy-MM-dd"));
        makingTask.setTaskEndTime(DateUtil.getFormatDate("2016-08-10","yyyy-MM-dd"));
        makingTask.setState(1);
        makingTask.setMemo("备注");
        makingTask.setCreateDate(DateUtil.getFormatDate("2016-08-10","yyyy-MM-dd"));
        makingTask.setCreator("lp");
        makingTask.setModifyDate(DateUtil.getFormatDate("2016-08-10","yyyy-MM-dd"));
        makingTask.setTicNum(1000);
        makingTask.setCouRules(1);
        makingTask.setCouRulesNum(5);
        makingTask.setDf(0);
        return makingTask;
    }
}
