package com.peiyu.mem.dao;

import com.peiyu.mem.domian.entity.Member;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/12/13.
 */
public interface MemberDao extends BaseDao<Member> {
    /**
     * 根据商家获取会员信息
     * @param vendorId
     * @param memNo 会员编号
     * @return
     */
    Member getMemberByMemNo(@Param("vendorId") Long vendorId, @Param("memNo") String memNo);
}
