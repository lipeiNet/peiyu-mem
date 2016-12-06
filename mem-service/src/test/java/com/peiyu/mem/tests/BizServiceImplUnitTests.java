package com.peiyu.mem.tests;

import com.peiyu.mem.service.BizService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-service-test.xml"})
public class BizServiceImplUnitTests {
    @Autowired
    private BizService bizService;

    @Test
    public void testGetOddNumbers() {
        String result = bizService.getOddNumbers(1483l, "Activity_Code", 0);
        Assert.assertEquals("VD2016120600007", result);
    }
}
