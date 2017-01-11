package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/29.
 * 对优惠券活动的dao进行单元测试
 */
public class CpActivityDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private CpActivityDao cpActivityDao;

    @Test
    public void insert_validActivity_Reture1() {
        CpActivity cpActivity = new CpActivity();
        cpActivity.setVendorId(1432L);
        int reslut = cpActivityDao.insert(cpActivity);
        assertEquals(1, reslut);
    }

    @Test
    public void delete_input1_return1() {
        Long id = 1l;
        int reslut = cpActivityDao.delete(id);
        assertEquals(1, reslut);
    }

    @Test
    public void update_falseDelete_return1() {
        CpActivity cpActivity = new CpActivity();
        cpActivity.setId(1l);
        cpActivity.setDf(1);
        cpActivity.setModifyDate(new Date());
        int result = cpActivityDao.update(cpActivity);
        assertEquals(1, result);
    }

    @Test
    public void update_updateSuccess_return1() {
        CpActivity cpActivity = new CpActivity();
        cpActivity.setId(1l);
        cpActivity.setVendorId(1432L);
        cpActivity.setActNo("BCD");
        cpActivity.setModifyDate(new Date());
        int result = cpActivityDao.update(cpActivity);
        assertEquals(1, result);
    }

    @Test
    public void get_inputId1_returnExceptActively() throws ParseException {
        CpActivity expect=getExpectCpActivity();
        CpActivity result=cpActivityDao.get(1l);
        ReflectionAssert.assertReflectionEquals(expect, result);
    }
    @Test
    public void testGetActivity() throws ParseException {
        CpActivity expect=getExpectCpActivity();
        Long vendorId=1433l;
        String actNo="201611301615";
        CpActivity result=cpActivityDao.getActivity(vendorId,actNo);
        ReflectionAssert.assertReflectionEquals(expect, result);
    }

    private CpActivity getExpectCpActivity() throws ParseException {
        CpActivity expectCpAcivity = new CpActivity();
        expectCpAcivity.setId(1l);
        expectCpAcivity.setVendorId(1433l);
        expectCpAcivity.setActNo("201611301615");
        expectCpAcivity.setActName("消费送券");
        expectCpAcivity.setStartDate(DateUtil.getFormatDate("2016-11-29","yyyy-MM-dd"));
        expectCpAcivity.setEndDate(DateUtil.getFormatDate("2016-12-30","yyyy-MM-dd"));
        expectCpAcivity.setGetLimitType(1);
        expectCpAcivity.setGetLimitQuantity(2);
        expectCpAcivity.setUseLimitType(0);
        expectCpAcivity.setUseLimitQuantity(1);
        expectCpAcivity.setStartTime("18:24");
        expectCpAcivity.setEndTime("18:27");
        expectCpAcivity.setWeekFlag("1101010");
        expectCpAcivity.setAllowSimt(0);
        expectCpAcivity.setApplyScopeType(0);
        expectCpAcivity.setUseScopeType(1);
        expectCpAcivity.setStatus(0);
        expectCpAcivity.setMemo("备注");
        expectCpAcivity.setCreateDate(DateUtil.getFormatDate("2016-11-29","yyyy-MM-dd"));
        expectCpAcivity.setModifyDate(DateUtil.getFormatDate("2016-11-29","yyyy-MM-dd"));
        expectCpAcivity.setCreator("lp");
        expectCpAcivity.setDf(0);
        expectCpAcivity.setSendType(0);
        expectCpAcivity.setOverMoney(100d);
        expectCpAcivity.setUseOverMoney(200d);
        return expectCpAcivity;
    }
}
