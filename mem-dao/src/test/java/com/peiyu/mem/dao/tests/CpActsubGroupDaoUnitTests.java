package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CpActsubGroupDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private CpActsubGroupDao cpActsubGroupDao;
    @Test
    public void insert_validcpActsubGroup_Reture1(){
        CpActsubGroup cpActsubGroup=new CpActsubGroup();
        cpActsubGroup.setVendorId(1422l);
        cpActsubGroup.setActName("消费送券");
        cpActsubGroup.setUseOverMoney(100d);
        cpActsubGroup.setSendType(2);
        cpActsubGroup.setApplyScopeType(1);
        int result=cpActsubGroupDao.insert(cpActsubGroup);
        assertEquals(1,result);
    }
    @Test
    public void delete_idEqual1_return1(){
        Long id=1l;
        int result=cpActsubGroupDao.delete(id);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        CpActsubGroup cpActsubGroup=new CpActsubGroup();
        cpActsubGroup.setActNo("123");
        cpActsubGroup.setActName("消费");
        cpActsubGroup.setId(1l);
        int result=cpActsubGroupDao.update(cpActsubGroup);
        assertEquals(1,result);
    }
    @Test
    public void testSelect(){
        Long id=1l;
        CpActsubGroup expect=getExpectActsubGroup();
        CpActsubGroup result=cpActsubGroupDao.get(id);
        ReflectionAssert.assertReflectionEquals(expect, result);
    }
    private CpActsubGroup getExpectActsubGroup(){
        CpActsubGroup except=new CpActsubGroup();
        except.setId(1l);
        except.setVendorId(1433l);
        except.setActNo("201611301615");
        except.setActName("消费送券");
        except.setSubgroupCode("201200123");
        except.setCpValue(10d);
        except.setInheritActLimit(0);
        except.setStartDate(DateUtil.getFormatDate("2016-11-29","yyyy-MM-dd"));
        except.setEndDate(DateUtil.getFormatDate("2016-12-30","yyyy-MM-dd"));
        except.setGetLimitType(1);
        except.setGetLimitType(1);
        except.setGetLimitQuantity((short)2);
        except.setUseLimitType(0);
        except.setUseLimitQuantity((short)1);
        except.setStartTime("18:24");
        except.setEndTime("18:27");
        except.setWeekFlag("1101010");
        except.setAllowSimt(0);
        except.setApplyScopeType(0);
        except.setUseScopeType(1);
        except.setMemo("备注");
        except.setCreateDate(DateUtil.getFormatDate("2016-11-29","yyyy-MM-dd"));
        except.setModifyDate(DateUtil.getFormatDate("2016-11-29","yyyy-MM-dd"));
        except.setCreator("lp");
        except.setDf(0);
        except.setSendType(0);
        except.setOverMoney(100d);
        except.setUseOverMoney(200d);
        return except;
    }

}
