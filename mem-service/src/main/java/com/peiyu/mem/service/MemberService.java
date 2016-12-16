package com.peiyu.mem.service;

import com.peiyu.mem.domian.entity.Member;

/**
 * Created by Administrator on 2016/12/16.
 */
public interface MemberService {
    /**
     * 添加会员
     * @param member
     * @return
     */
    boolean insertMember(Member member);

    /**
     * 根据会员编号获取会员
     * @param vendorId
     * @param memNo
     * @return
     */
    Member  getMemberByMemNo(Long vendorId,String memNo);
}
