package com.peiyu.mem.manager.tests;

import com.migr.common.util.DateUtil;
import com.peiyu.mem.domian.entity.BizCode;
import com.peiyu.mem.manager.BizCodeManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 * Created by Administrator on 2016/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring-manager-test.xml"})
public class BizCodeManagerUnitTests {
    @Autowired
    private BizCodeManager bizCodeManager;
    @Test
    public void testGetCodeByNo(){
        BizCode except=getExceptBizCode();
        BizCode result= bizCodeManager.getCodeByNo(1483l,"Activity_Code");
        ReflectionAssert.assertLenientEquals(except,result);
    }
    protected BizCode getExceptBizCode(){
        BizCode code=new BizCode();
        code.setId(1);
        code.setVendorId(1483l);
        code.setBizName("优惠券活动编码");
        code.setBizCode("VD");
        code.setBno("Activity_Code");
        code.setCurDate(DateUtil.getFormatDate("2016-12-05","yyyy-MM-dd"));
        code.setSerialNumber(1);
        code.setLengthValue(5);
        code.setCreateDate(DateUtil.getFormatDate("2016-07-25","yyyy-MM-dd"));
        code.setModifyDate(DateUtil.getFormatDate("2016-07-25","yyyy-MM-dd"));
        code.setMemo("优惠券活动编码");
        return code;
    }
}
