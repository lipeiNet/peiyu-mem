package com.peiyu.mem.service.impl;

import com.peiyu.mem.dao.MemberDao;
import com.peiyu.mem.domian.entity.Member;
import com.peiyu.mem.service.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/16.
 */
@Service
public class MemberServiceImpl implements MemberService {
    private Logger logger = Logger.getLogger(MemberServiceImpl.class);
    @Autowired
    private MemberDao memberDao;

    @Override
    public boolean insertMember(Member member) {
        try {
            memberDao.insert(member);
            return true;
        } catch (Exception e) {
            logger.error("添加会员异常" + e);
        }
        return false;
    }

    @Override
    public Member getMemberByMemNo(Long vendorId, String memNo) {
        try {
            return memberDao.getMemberByMemNo(vendorId, memNo);
        } catch (Exception e) {
            logger.error("根据会员编号获取会员失败" + e);
        }
        return null;
    }
}
