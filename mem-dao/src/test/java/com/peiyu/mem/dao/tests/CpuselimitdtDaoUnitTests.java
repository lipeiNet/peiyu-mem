package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CpuselimitdtDao;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CpuselimitdtDaoUnitTests extends BaseDaoUnitTests{
    @Autowired
    private CpuselimitdtDao cpuselimitdtDao;

    @Test
    public void testInsert(){
        CpUseLimitdt useLimitdt=new CpUseLimitdt();
        useLimitdt.setDf(1);
        useLimitdt.setVendorId(124l);
        useLimitdt.setMemo("11");
        useLimitdt.setOrganName("zongjigou");
        int result=cpuselimitdtDao.insert(useLimitdt);
        assertEquals(1,result);
    }
    @Test
    public void testDelete(){
        int result=cpuselimitdtDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        CpUseLimitdt useLimitdt=new CpUseLimitdt();
        useLimitdt.setOrganName("1");
        useLimitdt.setMemo("2");
        useLimitdt.setDf(1);
        useLimitdt.setModifyDate(new Date());
        useLimitdt.setOwnRecordCode("3");
        useLimitdt.setId(1l);
        int result=cpuselimitdtDao.update(useLimitdt);
        assertEquals(1,result);
    }
    @Test
    public void testGet(){
        CpUseLimitdt except=getExceptUseLimit();
        CpUseLimitdt result=cpuselimitdtDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except, result);
    }

    protected CpUseLimitdt getExceptUseLimit(){
        CpUseLimitdt except=new CpUseLimitdt();
        except.setId(1l);
        except.setVendorId(1483l);
        except.setOwnRecordCode("VD2016101300001");
        except.setOwnRecordType(1);
        except.setUseScopeType(2);
        except.setOrganCode("");
        except.setOrganName("");
        except.setStoreCode("1011");
        except.setStoreName("总机构");
        except.setMemo("备注");
        except.setCreateDate(DateUtil.getFormatDate("2016-09-10","yyyy-MM-dd"));
        except.setModifyDate(DateUtil.getFormatDate("2016-10-10","yyyy-MM-dd"));
        except.setCreator("lp");
        except.setDf(0);
        return except;
    }
}
