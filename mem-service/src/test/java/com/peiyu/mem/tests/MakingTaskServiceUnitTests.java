package com.peiyu.mem.tests;

import com.peiyu.mem.domian.entity.CpMakingTask;
import com.peiyu.mem.manager.MakingTaskManager;
import com.peiyu.mem.service.BizService;
import com.peiyu.mem.service.MakingTaskService;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-service-test.xml"})
public class MakingTaskServiceUnitTests {
    @Autowired
    private BizService bizService;
    @Autowired
    private MakingTaskService makingTaskService;
    @Autowired
    private MakingTaskManager makingTaskManager;

    @Test
    public void testInsertMakingTask() {
        CpMakingTask makingTask = new CpMakingTask();
        makingTask.setVendorId(1483l);
        makingTask.setActNo("VD2016122700025");
        makingTask.setActName("消费送券24");
        makingTask.setSubgroupCode("VG2016122700194");
        makingTask.setCpValue(20d);
        makingTask.setStartDate(DateUtil.getFormatDate("2016-12-15", "yyyy-MM-dd"));
        makingTask.setEndDate(DateUtil.getFormatDate("2016-17-25", "yyyy-MM-dd"));
        makingTask.setTaskCode(bizService.getOddNumbers(1483l, "Task_Code", 0));
        makingTask.setGenNoRulePrefix("91power");
        makingTask.setGenNoRuleSuffix("test");
        makingTask.setTicNum(50000);
//        makingTaskManager.insertCacheByTaskCode(makingTask);
//        makingTaskManager.deleteCacheByMakingConpon(makingTask.getVendorId(),makingTask.getTaskCode());
//        makingTaskManager.deteleCacheByTaskCode(makingTask);
        makingTaskService.insertMakingTask(makingTask);
    }


    @Test
    public void testMakingCoupon() throws InterruptedException {
        makingTaskService.makingCoupon(1483l,"VT2016122700014");
        Thread.currentThread().join();
    }
    @Test
    public void testInsertMakingTask1(){
        CpMakingTask makingTask=new CpMakingTask();
        makingTask.setVendorId(1468l);
        makingTask.setActNo("VD2016121900010");
        makingTask.setActName("消费送券002");
        makingTask.setSubgroupCode("VE20161219005");
        makingTask.setCpValue(10d);
        makingTask.setStartDate(DateUtil.getFormatDate("2016-12-15","yyyy-MM-dd"));
        makingTask.setEndDate(DateUtil.getFormatDate("2016-12-25","yyyy-MM-dd"));
        makingTask.setTaskCode(bizService.getOddNumbers(1468l,"Task_Codes",0));
        makingTask.setGenNoRulePrefix("test");
        makingTask.setGenNoRuleSuffix("001");
        makingTask.setTicNum(50000);
//        makingTaskManager.insertCacheByTaskCode(makingTask);
//        makingTaskManager.deleteCacheByMakingConpon(makingTask.getVendorId(),makingTask.getTaskCode());
//        makingTaskManager.deteleCacheByTaskCode(makingTask);
        makingTaskService.insertMakingTask(makingTask);
    }
}
