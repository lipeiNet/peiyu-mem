package com.peiyu.mem.manager.tests;

import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.manager.CpActivityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring-manager-test.xml"})
public class CpActivityManagerUnitTests {
    @Autowired
    private CpActivityManager activityManager;

    /**
     * 采用for循环添加数据到数据库（5000条数据大约耗时7600毫秒）
     */
    @Test
    public void insertCpActivityUnitTests() {
       /* long start=System.currentTimeMillis();
        CpActivity cpActivity = new CpActivity();
        cpActivity.setVendorId(1432L);
        List<CpApplyLimitdt> applyLimitdts=new ArrayList<>();
        List<CpUseLimitdt> useLimitdts=new ArrayList<>();
        for (int i=0;i<5000;i++){
            CpApplyLimitdt applyLimitdt=new CpApplyLimitdt();
            applyLimitdt.setId(1l);
            applyLimitdt.setVendorId(1433l);
            applyLimitdt.setCreateDate(new Date());
            applyLimitdt.setDetailName("ASCDFDRFCF");
            applyLimitdt.setOwnRecordCode(""+i);
            applyLimitdt.setDetailCode(""+i);
            applyLimitdt.setModifyDate(new Date());
            applyLimitdt.setMemo("备注");
            applyLimitdt.setApplyScopeType(1);
            applyLimitdts.add(applyLimitdt);
        }
        for (int i=0;i<5000;i++){
            CpUseLimitdt useLimitdt=new CpUseLimitdt();
            useLimitdt.setStoreName("加油站");
            useLimitdt.setCreator("lp");
            useLimitdt.setModifyDate(new Date());
            useLimitdt.setOwnRecordCode("12345678");
            useLimitdt.setOrganName(""+i);
            useLimitdt.setMemo("备注");
            useLimitdt.setOwnRecordType(1);
            useLimitdt.setVendorId(1433l);
            useLimitdt.setCreateDate(new Date());
            useLimitdt.setStoreCode(""+i);
            useLimitdts.add(useLimitdt);
        }
        boolean result = activityManager.insertCpActivity(cpActivity, applyLimitdts, useLimitdts);
        long end=System.currentTimeMillis();
        System.out.println("消耗的总时间："+(end-start)+"毫秒");*/
    }

    /**
     * 采用批量插入（5000条数据大约耗时3000毫秒，无疑比for循环效率更佳）
     */
    @Test
    public void insertCpActivityForBatchUnitTests(){
        long start=System.currentTimeMillis();
        CpActivity cpActivity = new CpActivity();
        cpActivity.setVendorId(1432L);
        List<CpApplyLimitdt> applyLimitdts=new ArrayList<>();
        List<CpUseLimitdt> useLimitdts=new ArrayList<>();
        for (int i=0;i<5000;i++){
            CpApplyLimitdt applyLimitdt=new CpApplyLimitdt();
            applyLimitdt.setId(1l);
            applyLimitdt.setVendorId(1433l);
            applyLimitdt.setCreateDate(new Date());
            applyLimitdt.setDetailName("ASCDFDRFCF");
            applyLimitdt.setOwnRecordCode(""+i);
            applyLimitdt.setDetailCode(""+i);
            applyLimitdt.setModifyDate(new Date());
            applyLimitdt.setMemo("备注");
            applyLimitdt.setApplyScopeType(1);
            applyLimitdts.add(applyLimitdt);
        }
        for (int i=0;i<5000;i++){
            CpUseLimitdt useLimitdt=new CpUseLimitdt();
            useLimitdt.setStoreName("加油站");
            useLimitdt.setCreator("lp");
            useLimitdt.setModifyDate(new Date());
            useLimitdt.setOwnRecordCode("12345678");
            useLimitdt.setOrganName(""+i);
            useLimitdt.setMemo("备注");
            useLimitdt.setOwnRecordType(1);
            useLimitdt.setVendorId(1433l);
            useLimitdt.setCreateDate(new Date());
            useLimitdt.setStoreCode(""+i);
            useLimitdts.add(useLimitdt);
        }
        boolean result = activityManager.insertCpActivity(cpActivity, applyLimitdts, useLimitdts);
        long end=System.currentTimeMillis();
        System.out.println("消耗的总时间："+(end-start)+"毫秒");
    }

}
