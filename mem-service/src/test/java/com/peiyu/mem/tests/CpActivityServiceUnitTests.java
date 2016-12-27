package com.peiyu.mem.tests;

import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.service.BizService;
import com.peiyu.mem.service.CpActivityService;
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
    private CpActivityService  activityService;
    @Test
    public void testInsertCpActivity(){
        CpActivity activity=new CpActivity();
        activity.setVendorId(1483l);
        activity.setActNo(bizService.getOddNumbers(1483l,"Activity_Code",0));
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
        activityService.insertCpActivity(activity);
    }

    @Test
    public void testInsertCpActivity1(){
        CpActivity activity=new CpActivity();
        activity.setVendorId(1468l);
        activity.setActNo(bizService.getOddNumbers(1468l,"Activity_Codes",0));
        activity.setActName("消费送券002");
        activity.setStartDate(DateUtil.getFormatDate("2016-12-15","yyyy-MM-dd"));
        activity.setEndDate(DateUtil.getFormatDate("2016-12-25","yyyy-MM-dd"));
        activity.setGetLimitType(SysConstants.GETCOUPONLIMITSTATE.UNLIMITED);
        activity.setUseLimitType(SysConstants.USECOUPONLIMITSTATE.UNLIMITED);
        activity.setAllowSimt(0);
        activity.setWeekFlag("1111111");
        activity.setStatus(SysConstants.ACTIVITYSTATUS.CHECKED);
        activity.setApplyScopeType(SysConstants.COUPONAPPLIEDRANGE.CATEGORY);
        activity.setOrganOrStoreCode("10,101,910003");
        activity.setOrganOrStoreName("91动力集团,总部机构,91分公司");
        activity.setUseScopeType(SysConstants.COUPONUSERANGE.ORAGN);
        activity.setDetailName("百岁山,非统配品牌,康师傅,中石化,子公司统配品牌,总公司统配品牌");
        activity.setDetailCode("pp30,10,pp29,pp31,99,999");
        activity.setMemo("备注");
        activity.setCreator("lp");
        activity.setModifyDate(new Date());
        activity.setCreateDate(new Date());
        activity.setDf(0);
        activity.setSendType(SysConstants.CouponSendType.AUTO_PROVIDE);
        activity.setOverMoney(100d);
        activity.setUseOverMoney(200d);
        activityService.insertCpActivity(activity);
    }

    @Test
    public void testInsertCpActivity2(){
        for (int i=0;i<25;i++){
            CpActivity activity=new CpActivity();
            activity.setVendorId(1483l);
            activity.setActNo(bizService.getOddNumbers(1483l,"Activity_Code",0));
            activity.setActName("消费送券0"+i);
            activity.setStartDate(DateUtil.getFormatDate("2016-12-15","yyyy-MM-dd"));
            activity.setEndDate(DateUtil.getFormatDate("2017-12-25","yyyy-MM-dd"));
            activity.setGetLimitType(SysConstants.GETCOUPONLIMITSTATE.UNLIMITED);
            activity.setUseLimitType(SysConstants.USECOUPONLIMITSTATE.UNLIMITED);
            activity.setAllowSimt(0);
            activity.setWeekFlag("1111111");
            activity.setStatus(SysConstants.ACTIVITYSTATUS.CHECKED);
            activity.setApplyScopeType(SysConstants.COUPONAPPLIEDRANGE.CATEGORY);
            activity.setOrganOrStoreCode("10,101,910003");
            activity.setOrganOrStoreName("91动力集团,总部机构,91分公司");
            activity.setUseScopeType(SysConstants.COUPONUSERANGE.ORAGN);
            activity.setDetailName("百岁山,非统配品牌,康师傅,中石化,子公司统配品牌,总公司统配品牌");
            activity.setDetailCode("pp30,10,pp29,pp31,99,999");
            activity.setMemo("备注");
            activity.setCreator("lp");
            activity.setModifyDate(new Date());
            activity.setCreateDate(new Date());
            activity.setDf(0);
            activity.setSendType(SysConstants.CouponSendType.AUTO_PROVIDE);
            activity.setOverMoney(100d);
            activity.setUseOverMoney(200d);
            activityService.insertCpActivity(activity);
        }
    }
}
