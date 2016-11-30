package com.peiyu.mem.dao.tests;

import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.domian.entity.CpActivity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/29.
 * 对优惠券活动的dao进行单元测试
 */
public class CpActivityDaoUnitTests extends BaseDaoUnitTests{
    @Autowired
    private CpActivityDao cpActivityDao;
    @Test
    public void testInsert(){
        CpActivity cpActivity=new CpActivity();
        cpActivity.setVendorId(1433L);
        int reslut=cpActivityDao.insert(cpActivity);
    }
}
