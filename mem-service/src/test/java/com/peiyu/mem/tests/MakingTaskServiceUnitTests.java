package com.peiyu.mem.tests;

import com.peiyu.mem.domian.entity.CpMakingTask;
import com.peiyu.mem.manager.MakingTaskManager;
import com.peiyu.mem.service.MakingTaskService;
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
    private MakingTaskService makingTaskService;
    @Autowired
    private MakingTaskManager makingTaskManager;
    @Test
    public void testInsertMakingTask(){
        CpMakingTask makingTask=new CpMakingTask();
        makingTask.setVendorId(1458l);
        makingTask.setActNo("20140123");
        makingTask.setActName("测试优惠券");
        makingTask.setSubgroupCode("123");
        makingTask.setCpValue(10d);
        makingTask.setStartDate(new Date());
        makingTask.setEndDate(new Date());
        makingTask.setTaskCode("1458001");
        makingTask.setGenNoRulePrefix("a");
        makingTask.setGenNoRuleSuffix("b");
        makingTask.setSerialNoLength(3);
        makingTask.setTicNum(10000);
        makingTask.setCouRules(1);
        makingTask.setSerialNoStart(0);
        makingTask.setCouRulesNum(5);
        makingTaskManager.insertCacheByTaskCode(makingTask);
        makingTaskManager.deleteCacheByMakingConpon(makingTask.getVendorId(),makingTask.getTaskCode());
        makingTaskService.insertMakingTask(makingTask);
    }
    @Test
    public void testMakingCoupon() throws InterruptedException {
        makingTaskService.makingCoupon(1458l,"1458001");
        Thread.currentThread().join();
    }
}
