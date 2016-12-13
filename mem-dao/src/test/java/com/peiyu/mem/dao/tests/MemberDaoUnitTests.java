package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.MemberDao;
import com.peiyu.mem.domian.entity.Member;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/13.
 */
public class MemberDaoUnitTests extends BaseDaoUnitTests {
    @Autowired
    private MemberDao memberDao;
    @Test
    public void testInsert(){
        Member member=new Member();
        member.setVendorId(1433l);
        member.setEndDate(new Date());
        member.setCreateDate(new Date());
        member.setBirthday(new Date());
        member.setCatName("金牌会员");
        member.setCityCode("1010");
        member.setHeadImg("123");
        member.setConsumeAmount(10234d);
        member.setMemAddress("中关村");
        member.setMemo("备注");
        member.setMemPwd("123456");
        member.setMemName("shui");
        member.setMemCatCode("10");
        member.setSex(0);
        member.setMemPin("123");
        member.setMobileNo("11223");
        member.setMemPoint(10d);
        member.setMemPointAmount(13d);
        member.setStoreName("2");
        member.setStatus(1);
        int result= memberDao.insert(member);
        assertEquals(1,result);
    }
    @Test
    public void testDelete(){
        int result=memberDao.delete(1l);
        assertEquals(1,result);
    }
    @Test
    public void testUpdate(){
        Member member=new Member();
        member.setId(1l);
        member.setVendorId(1433l);
        member.setEndDate(new Date());
        member.setCreateDate(new Date());
        member.setBirthday(new Date());
        member.setCatName("金牌会员");
        member.setCityCode("1010");
        member.setHeadImg("123");
        member.setConsumeAmount(10234d);
        member.setMemAddress("中关村");
        member.setMemo("备注");
        member.setMemPwd("123456");
        member.setMemName("shui");
        member.setMemCatCode("10");
        member.setSex(0);
        member.setMemPin("123");
        member.setMobileNo("11223");
        member.setMemPoint(10d);
        member.setMemPointAmount(13d);
        member.setStoreName("2");
        member.setStatus(1);
        int result= memberDao.update(member);
        assertEquals(1,result);
    }

    @Test
    public void testGet(){
        Member except=getExpectMember();
        Member result= memberDao.get(1l);
        ReflectionAssert.assertReflectionEquals(except,result);
    }
    protected Member getExpectMember(){
        Member expect=new Member();
        expect.setId(1l);
        expect.setVendorId(1433l);
        expect.setMemNo("134567893");
        expect.setMemPin("12345678901");
        expect.setMemPwd("1234567");
        expect.setOrganCode("100");
        expect.setOrganName("200");
        expect.setStoreCode("300");
        expect.setStoreName("400");
        expect.setMemName("英雄");
        expect.setNickName("大牛");
        expect.setMobileNo("12345687");
        expect.setMemPoint(1d);
        expect.setConsumeAmount(1d);
        expect.setMemPointAmount(1d);
        expect.setStatus(0);
        expect.setSex(0);
        expect.setMemCatCode("10");
        expect.setProvinceCode("1");
        expect.setCityCode("1");
        expect.setRegionCode("1");
        expect.setMemAddress("中关村");
        expect.setBirthday(DateUtil.getFormatDate("1991-12-30","yyyy-MM-dd"));
        expect.setHeadImg("1");
        expect.setDf(0);
        expect.setMemo("备注");
        expect.setStartDate(DateUtil.getFormatDate("2016-11-19","yyyy-MM-dd"));
        expect.setEndDate(DateUtil.getFormatDate("2017-11-19","yyyy-MM-dd"));
        expect.setCreateDate(DateUtil.getFormatDate("2016-11-19","yyyy-MM-dd"));
        expect.setModifyDate(DateUtil.getFormatDate("2016-11-19","yyyy-MM-dd"));
        return expect;
    }
}
