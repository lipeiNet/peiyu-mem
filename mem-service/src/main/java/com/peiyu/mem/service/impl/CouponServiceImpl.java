package com.peiyu.mem.service.impl;

import com.migr.common.util.StringUtils;
import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.commen.VerificationDate;
import com.peiyu.mem.dao.*;
import com.peiyu.mem.domian.entity.*;
import com.peiyu.mem.manager.CouponManager;
import com.peiyu.mem.manager.impl.CouponActivityCacheManager;
import com.peiyu.mem.service.CouponService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service
public class CouponServiceImpl implements CouponService {
    private Logger log = Logger.getLogger(CouponServiceImpl.class);
    private Map couponMap = new ConcurrentHashMap();
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private CpActivityDao activityDao;
    @Autowired
    private CpapplylimitdtDao cpapplylimitdtDao;
    @Autowired
    private CpuselimitdtDao cpuselimitdtDao;
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponManager couponManager;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private CouponActivityCacheManager activityCacheManager;

    @Override
    public int insertCoupon(Coupon coupon) {
        return 0;
    }

    @Override
    public int deleteCoupon(long id) {
        return 0;
    }

    @Override
    public int updateCoupon(Coupon coupon) {
        return 0;
    }

    @Override
    public int consumeSendCoupon(List<GoodsForCoupon> goodsForCoupons) throws ParseException {
        try {
            if (CollectionUtils.isEmpty(goodsForCoupons)) {
                log.error("商品信息不能为空");
                return 0;
            }
            Long vendorId = goodsForCoupons.get(0).getVendorId();
            String memNo = goodsForCoupons.get(0).getMemNo();
            Member member = memberDao.getMemberByMemNo(vendorId, memNo);
            if (member == null) {
                log.error("不存在vendorId=" + vendorId + ",memNo=" + memNo + "的会员");
                return 0;
            }
            List<CpActivity> activities = getValidActivitys(vendorId);
            if (CollectionUtils.isEmpty(activities)) {
                log.error("vendorId=" + vendorId + "下没有在" + new Date() + "的消费送券活动");
                return 0;
            }
            List<Coupon> needUpdateCoupons = new ArrayList<>();
            for (GoodsForCoupon goodsForCoupon : goodsForCoupons) {
                List<CpActivity> activitiesForApply = getApplyValidActivity(activities, goodsForCoupon);
                List<CpActivity> activitiesForUse = getUseValidActivity(activitiesForApply, goodsForCoupon);
                List<CpActivity> finalValidActivitys = getPassMoneyValidActivity(activitiesForUse, goodsForCoupon);
                if (CollectionUtils.isEmpty(finalValidActivitys)) {
                    continue;
                }
                for (CpActivity t : finalValidActivitys) {
                    List<CpActsubGroup> actsubGroups = getActsubGroups(t.getVendorId(), t.getActNo());
                    if (CollectionUtils.isEmpty(activities)) {
                        continue;
                    }
                    for (CpActsubGroup group : actsubGroups) {
                        List<Coupon> coupons = getCoupons(group.getVendorId(), group.getActNo(), group.getSubgroupCode(), SysConstants.COUPONSTATE.NOGRANT);
                        if (CollectionUtils.isEmpty(coupons)) {
                            continue;
                        }
                        for (Coupon c : coupons) {
                            if (!contains(needUpdateCoupons, c)) {
                                needUpdateCoupons.add(c);
                                break;
                            }
                        }
                    }
                }
            }
            this.asyncSaveCouponsState(needUpdateCoupons, member);
            return needUpdateCoupons.size();
        } catch (Exception e) {
            log.error("消费送券出现异常" + e);
            return -1;
        }
    }

    @Override
    public Coupon getCoupon(long id) {
        return null;
    }
    /**
     * 异步发券
     *
     * @param coupons
     */
    private void asyncSaveCouponsState(final List<Coupon> coupons, Member member) {
        if (CollectionUtils.isEmpty(coupons)) {
            return;
        }
        for (Coupon coupon : coupons) {
            coupon.setMemNo(member.getMemNo());
            coupon.setMemCat(member.getMemCatCode());
            coupon.setModifyDate(new Date());
            coupon.setSendCouponDate(new Date());
            coupon.setState(SysConstants.COUPONSTATE.GRANT);
        }
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                couponManager.updateCoupons(coupons);
            }
        });
    }

    /**
     * 获取日期范围内有效的活动
     * @Param vendorId 商家id
     * @return
     */
    private List<CpActivity> getValidActivitys(Long vendorId) throws ParseException {
        List<CpActivity> activities = getActivitys(vendorId);
        if (CollectionUtils.isEmpty(activities)) {
            return null;
        }
        Iterator actsIterator = activities.iterator();
        while (actsIterator.hasNext()) {
            CpActivity act = (CpActivity) actsIterator.next();
            if (!VerificationDate.isValidMoment(act.getStartTime(), act.getEndTime()) || !VerificationDate.isValidWeek(act.getWeekFlag())) {
                actsIterator.remove();
            }
        }
        return activities;
    }

    /**
     * 获取应用范围内有效的活动
     * @param activities
     * @return
     */
    private List<CpActivity> getApplyValidActivity(List<CpActivity> activities,GoodsForCoupon goodsForCoupon) {
        if (CollectionUtils.isEmpty(activities)) {
            return null;
        }
        List<CpActivity> resultActivitys = new ArrayList<>();
        for (CpActivity act : activities) {
            if (act.getApplyScopeType().equals(SysConstants.COUPONAPPLIEDRANGE.UNLIMITED)) {
                resultActivitys.add(act);
                continue;
            }
            List<CpApplyLimitdt> applyLimitdts = getApplyLimits(act.getVendorId(), act.getActNo());
            if (CollectionUtils.isEmpty(applyLimitdts)) {
                continue;
            }
            for (CpApplyLimitdt apply : applyLimitdts) {
                switch (act.getApplyScopeType().intValue()) {
                    case SysConstants.COUPONAPPLIEDRANGE.BRAND:
                        if (StringUtils.isNotBlank(goodsForCoupon.getFirstIcatCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getFirstIcatCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        if (StringUtils.isNotBlank(goodsForCoupon.getSecondIcatCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getSecondIcatCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        if (StringUtils.isNotBlank(goodsForCoupon.getThirdIcatCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getThirdIcatCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        if (StringUtils.isNotBlank(goodsForCoupon.getFourthIcatCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getFourthIcatCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        break;
                    case SysConstants.COUPONAPPLIEDRANGE.CATEGORY:
                        if (StringUtils.isNotBlank(goodsForCoupon.getFirstBrandCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getFirstBrandCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        if (StringUtils.isNotBlank(goodsForCoupon.getSecondBrandCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getSecondBrandCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        if (StringUtils.isNotBlank(goodsForCoupon.getThirdBrandCode())) {
                            if (apply.getDetailCode().equals(goodsForCoupon.getThirdBrandCode())) {
                                resultActivitys.add(act);
                                break;
                            }
                        }
                        break;
                    case SysConstants.COUPONAPPLIEDRANGE.GOOD:
                        if (apply.getDetailCode().equals(goodsForCoupon.getSkuCode())) {
                            resultActivitys.add(act);
                            break;
                        }
                        break;
                    case SysConstants.COUPONAPPLIEDRANGE.SUPPLIER:
                        if (apply.getDetailCode().equals(goodsForCoupon.getSupplierCode())) {
                            resultActivitys.add(act);
                            break;
                        }
                        break;
                }
            }
        }
        return resultActivitys;
    }

    /**
     * 获取使用范围有效活动
     * @param activities
     * @return
     */
    private List<CpActivity> getUseValidActivity(List<CpActivity> activities,GoodsForCoupon goodsForCoupon) {
        if (CollectionUtils.isEmpty(activities)) {
            return null;
        }
        List<CpActivity> resultActivity = new ArrayList<>();
        for (CpActivity c : activities) {
            if (c.getUseScopeType().equals(SysConstants.COUPONUSERANGE.UNLIMITED)) {
                resultActivity.add(c);
                continue;
            }
            List<CpUseLimitdt> useLimitdts = getUseLimits(c.getVendorId(), c.getActNo());
            if (CollectionUtils.isEmpty(useLimitdts)) {
                continue;
            }
            for (CpUseLimitdt uselimit : useLimitdts) {
                if (uselimit.getUseScopeType().equals(SysConstants.COUPONUSERANGE.ORAGN)) {
                    if (goodsForCoupon.getOrganCode().equals(uselimit.getOrganCode())) {
                        resultActivity.add(c);
                        break;
                    }
                }
                if (uselimit.getUseScopeType().equals(SysConstants.COUPONUSERANGE.STORE)) {
                    if (goodsForCoupon.getStoreCode().equals(uselimit.getStoreCode())) {
                        resultActivity.add(c);
                        break;
                    }
                }
            }
        }
        return resultActivity;
    }

    /**
     * 获取超过获取金额的获取
     * @param activities
     * @param goodsForCoupon
     * @return
     */
    private List<CpActivity> getPassMoneyValidActivity(List<CpActivity> activities,GoodsForCoupon goodsForCoupon) {
        if (CollectionUtils.isEmpty(activities)) {
            return null;
        }
        Iterator iterator = activities.iterator();
        while (iterator.hasNext()) {
            Double money = ((CpActivity) iterator.next()).getOverMoney();
            if (goodsForCoupon.getRealPayMoney() < money) {
                iterator.remove();
            }
        }
        return activities;
    }

    /**
     * 获取活动
     * @param vendorId
     * @return
     */
    private List<CpActivity> getActivitys(Long vendorId) {
        CpActivity activity = new CpActivity();
        activity.setVendorId(vendorId);
        activity.setSendType(SysConstants.CouponSendType.AUTO_PROVIDE);
        activity.setStatus(SysConstants.ACTIVITYSTATUS.CHECKED);
        activity.setEndDate(new Date());
        return activityCacheManager.getCpActivityList(activity);
    }
    /**
     * 获取活动下的优惠券组
     * @return
     */
    private List<CpActsubGroup> getActsubGroups(Long vendorId,String actNo) {
        CpActsubGroup cpActsubGroup = new CpActsubGroup();//获取优惠券组
        cpActsubGroup.setVendorId(vendorId);
        cpActsubGroup.setActNo(actNo);
        cpActsubGroup.setEndDate(new Date());
        List<CpActsubGroup> actsubGroups = activityCacheManager.getCpActsubGroupList(cpActsubGroup);
        return actsubGroups;
    }

    /**
     * 获取券组下优惠券
     * @param vendorId
     * @param actNo
     * @param groupNo
     * @param type
     * @return
     */
    private List<Coupon> getCoupons(Long vendorId,String actNo,String groupNo,int type) {
        Coupon coupon = new Coupon();
        coupon.setVendorId(vendorId);
        coupon.setActNo(actNo);
        coupon.setSubgroupCode(groupNo);
        coupon.setState(type);
        coupon.setPageIndex(0);
        coupon.setPageSize(100);
        String key = String.format("%s_%s_%s", vendorId, actNo, groupNo);
        List<Coupon> coupons = new ArrayList<>();
        if (couponMap.get(key) != null) {
            coupons = (List<Coupon>) couponMap.get(key);
        }
        if (couponMap.get(key) == null) {
            coupons = couponDao.getCouponListByPage(coupon);
            if (CollectionUtils.isNotEmpty(coupons)) {
                couponMap.put(key, coupons);
            }
        }
        return coupons;
    }

    /**
     * 获取应用范围限制
     * @param vendorId
     * @param actNo
     * @return
     */
    private List<CpApplyLimitdt> getApplyLimits(Long vendorId,String actNo) {
        CpApplyLimitdt applyLimitdt = new CpApplyLimitdt();
        applyLimitdt.setVendorId(vendorId);
        applyLimitdt.setOwnRecordCode(actNo);
        applyLimitdt.setOwnRecordType(0);
        return activityCacheManager.getCpApplyLimitdtList(applyLimitdt);
    }

    /**
     * 获取使用范围限制
     * @param vendorId
     * @param actNo
     * @return
     */
    private List<CpUseLimitdt> getUseLimits(Long vendorId,String actNo) {
        CpUseLimitdt useLimitdt = new CpUseLimitdt();
        useLimitdt.setVendorId(vendorId);
        useLimitdt.setOwnRecordCode(actNo);
        useLimitdt.setOwnRecordType(0);
        return activityCacheManager.getCpUseLimitList(useLimitdt);
    }

    /**
     * 集合coupons是否包含Coupon
     *
     * @param coupons
     * @param coupon
     * @return
     */
    protected boolean contains(List<Coupon> coupons, Coupon coupon) {
        if (CollectionUtils.isEmpty(coupons)) {
            return false;
        }
        if (coupon == null) {
            for (Coupon c : coupons) {
                if (c == null) {
                    return true;
                }
            }
        }
        for (Coupon cc : coupons) {
            if (coupon.getId().longValue() == cc.getId().longValue()) {
                return true;
            }
        }
        return false;
    }
}
