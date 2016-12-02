package com.peiyu.mem.manager.tests;

import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
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

    @Test
    public void insertCpActivityUnitTests() {
        CpActivity cpActivity = new CpActivity();
        cpActivity.setVendorId(1432L);
        CpApplyLimitdt applyLimitdt=new CpApplyLimitdt();
        applyLimitdt.setId(1l);
        applyLimitdt.setVendorId(null);
        applyLimitdt.setCreateDate(null);
        applyLimitdt.setDetailName("1289");
        applyLimitdt.setMemo("qwe");
        List<CpApplyLimitdt> applyLimitdts=new ArrayList<>();
        applyLimitdts.add(applyLimitdt);
        boolean result = activityManager.insertCpActivity(cpActivity, applyLimitdts, null);
    }
}
