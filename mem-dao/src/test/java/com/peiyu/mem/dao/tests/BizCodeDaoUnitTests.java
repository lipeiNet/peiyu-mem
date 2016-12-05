package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.BizCodeDao;
import com.peiyu.mem.domian.entity.BizCode;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/5.
 */
public class BizCodeDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private BizCodeDao bizCodeDao;
    @Test
    public void testInsert(){
        BizCode code=new BizCode();
        code.setSerialNumber(1);
        code.setCurDate(new Date());
        code.setBizName("优惠券");
        code.setCreateDate(new Date());
        code.setModifyDate(new Date());
        int result=bizCodeDao.insert(code);
        assertEquals(1,result);
    }
    @Test
    public void testDelete(){
        int result=bizCodeDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        BizCode code=new BizCode();
        code.setSerialNumber(1);
        code.setCurDate(new Date());
        code.setBizName("优惠券");
        code.setCreateDate(new Date());
        code.setModifyDate(new Date());
        code.setId(1);
        int result=bizCodeDao.update(code);
        assertEquals(1,result);
    }
    @Test
    public void testGet(){
        BizCode except=getExceptBizCode();
        BizCode result=bizCodeDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except, result);
    }

    protected BizCode getExceptBizCode(){
        BizCode code=new BizCode();
        code.setId(1);
        code.setVendorId(1483l);
        code.setBizName("优惠券活动编码");
        code.setBizCode("VD");
        code.setBno("Activity_Codes");
        code.setCurDate(DateUtil.getFormatDate("2016-12-05","yyyy-MM-dd"));
        code.setSerialNumber(1);
        code.setLengthValue(5);
        code.setCreateDate(DateUtil.getFormatDate("2016-07-25","yyyy-MM-dd"));
        code.setModifyDate(DateUtil.getFormatDate("2016-07-25","yyyy-MM-dd"));
        code.setMemo("优惠券活动编码");
        return code;
    }
}
