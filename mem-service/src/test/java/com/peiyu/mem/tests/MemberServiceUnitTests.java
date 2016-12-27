package com.peiyu.mem.tests;

import com.peiyu.mem.domian.entity.Member;
import com.peiyu.mem.service.MemberService;
import com.peiyu.mem.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-service-test.xml"})
public class MemberServiceUnitTests {
    @Autowired
    private MemberService memberService;
    @Test
    public void  testInsertMember(){
        Member member=new Member();
        member.setVendorId(1483l);
        member.setMemNo("12345");
        member.setMemPin("1324895");
        member.setMemPwd("123456");
        member.setOrganCode("1");
        member.setOrganName("信息大厦");
        member.setStoreCode("2");
        member.setStoreName("信息办事处");
        member.setMemName("张三");
        member.setNickName("赵四");
        member.setMobileNo("1234321244");
        member.setConsumeAmount(10d);
        member.setMemPoint(100d);
        member.setStatus(0);
        member.setSex(1);
        member.setMemCatCode("123");
        member.setMemCatName("金牌会员");
        member.setProvinceCode("001");
        member.setCityCode("002");
        member.setRegionCode("003");
        member.setMemAddress("北京市");
        member.setBirthday(new Date());
        member.setHeadImg("1");
        member.setDf(0);
        member.setCreateDate(new Date());
        member.setModifyDate(new Date());
        member.setStartDate(DateUtil.getFormatDate("2016-12-15","yyyy-MM-dd"));
        member.setEndDate(DateUtil.getFormatDate("2017-12-15","yyyy-MM-dd"));
        memberService.insertMember(member);
    }
    @Test
    public void testGet(){
        memberService.getMemberByMemNo(1433l,"123");
    }
}
