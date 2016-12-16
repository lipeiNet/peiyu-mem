package com.peiyu.mem.tests;

import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.service.BizService;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-service-test.xml"})
public class CpActivityServiceUnitTests {
    @Autowired
    private BizService bizService;
    @Autowired
    private CpActivityDao activityDao;
    @Test
    public void testInsertCpActivity(){
        CpActivity activity=new CpActivity();
        activity.setVendorId(1483l);
        activity.setActNo(bizService.getOddNumbers(1483l,"Activity_Codes",0));
        activity.setActName("消费送券001");
        activity.setStartDate(DateUtil.getFormatDate("2016-12-15","yyyy-MM-dd"));
        activity.setEndDate(DateUtil.getFormatDate("2016-12-25","yyyy-MM-dd"));
        activity.setGetLimitType(SysConstants.GETCOUPONLIMITSTATE.UNLIMITED);
        activity.setUseLimitType(SysConstants.USECOUPONLIMITSTATE.UNLIMITED);
        activity.setAllowSimt(0);
        activity.setWeekFlag("1111111");
        activity.setStatus(SysConstants.ACTIVITYSTATUS.CHECKED);
        activity.setApplyScopeType(SysConstants.COUPONAPPLIEDRANGE.UNLIMITED);
        activity.setUseScopeType(SysConstants.COUPONUSERANGE.UNLIMITED);
        activity.setMemo("备注");
        activity.setCreator("lp");
        activity.setModifyDate(new Date());
        activity.setCreateDate(new Date());
        activity.setDf(0);
        activity.setSendType(SysConstants.CouponSendType.AUTO_PROVIDE);
        activity.setOverMoney(100d);
        activity.setUseOverMoney(200d);
        activityDao.insert(activity);
    }
}
