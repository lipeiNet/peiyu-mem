package com.peiyu.mem.tests;

import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.domian.entity.CpActsubGroup;
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
public class CpActsubGroupServiceUnitTests {
    @Autowired
    private BizService bizService;
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Test
    public void testInsertActsubGroup(){
        CpActsubGroup actsubGroup=new CpActsubGroup();
        actsubGroup.setVendorId(1483l);
        actsubGroup.setActName("消费送券001");
        actsubGroup.setActNo("VD2016121600001");
        actsubGroup.setSubgroupCode(bizService.getOddNumbers(1483l,"ActsubGroup_Codes",0));
        actsubGroup.setCpValue(10d);
        actsubGroup.setCpLimitValue(200d);
        actsubGroup.setCpType("0");
        actsubGroup.setInheritActLimit(1);
        actsubGroup.setStartDate(DateUtil.getFormatDate("2016-12-15","yyyy-MM-dd"));
        actsubGroup.setEndDate(DateUtil.getFormatDate("2016-12-25","yyyy-MM-dd"));
        actsubGroup.setGetLimitType(SysConstants.GETCOUPONLIMITSTATE.UNLIMITED);
        actsubGroup.setUseLimitType(SysConstants.USECOUPONLIMITSTATE.UNLIMITED);
        actsubGroup.setAllowSimt(0);
        actsubGroup.setWeekFlag("1111111");
        actsubGroup.setApplyScopeType(SysConstants.COUPONAPPLIEDRANGE.UNLIMITED);
        actsubGroup.setUseScopeType(SysConstants.COUPONUSERANGE.UNLIMITED);
        actsubGroup.setMemo("备注");
        actsubGroup.setCreator("lp");
        actsubGroup.setModifyDate(new Date());
        actsubGroup.setCreateDate(new Date());
        actsubGroup.setDf(0);
        actsubGroup.setSendType(SysConstants.CouponSendType.AUTO_PROVIDE);
        actsubGroup.setOverMoney(100d);
        actsubGroup.setUseOverMoney(200d);
        actsubGroupDao.insert(actsubGroup);
    }
}
