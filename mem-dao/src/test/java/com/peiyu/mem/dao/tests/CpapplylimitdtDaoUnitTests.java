package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CpapplylimitdtDao;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CpapplylimitdtDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private CpapplylimitdtDao applylimitdtDao;
    @Test
    public void testInsert(){
        CpApplyLimitdt applyLimitdt=new CpApplyLimitdt();
        applyLimitdt.setId(1l);
        applyLimitdt.setVendorId(1433l);
        applyLimitdt.setCreateDate(new Date());
        applyLimitdt.setDetailName("121");
        int result=applylimitdtDao.insert(applyLimitdt);
        assertEquals(1,result);
    }
    @Test
    public void testDelete(){
        int result=applylimitdtDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        CpApplyLimitdt applyLimitdt=new CpApplyLimitdt();
        applyLimitdt.setId(1l);
        applyLimitdt.setVendorId(1433l);
        applyLimitdt.setCreateDate(new Date());
        applyLimitdt.setDetailName("1289");
        applyLimitdt.setMemo("qwe");
        int result=applylimitdtDao.update(applyLimitdt);
        assertEquals(1,result);
    }
    @Test
    public void testGet(){
        CpApplyLimitdt except=getExceptApplyLimit();
        CpApplyLimitdt result=applylimitdtDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except, result);
    }

    protected CpApplyLimitdt getExceptApplyLimit(){
        CpApplyLimitdt cpApplyLimitdt=new CpApplyLimitdt();
        cpApplyLimitdt.setId(1l);
        cpApplyLimitdt.setVendorId(1414l);
        cpApplyLimitdt.setOwnRecordCode("VD2016101300002");
        cpApplyLimitdt.setApplyScopeType(4);
        cpApplyLimitdt.setMemo("备注");
        cpApplyLimitdt.setDetailName("冰糖葫芦");
        cpApplyLimitdt.setDetailCode("7782210");
        cpApplyLimitdt.setOwnRecordType(1);
        cpApplyLimitdt.setCreateDate(DateUtil.getFormatDate("2016-10-13","yyyy-MM-dd"));
        cpApplyLimitdt.setModifyDate(DateUtil.getFormatDate("2016-10-13","yyyy-MM-dd"));
        cpApplyLimitdt.setCreator("lp");
        cpApplyLimitdt.setDf(0);
        return cpApplyLimitdt;
    }
}
