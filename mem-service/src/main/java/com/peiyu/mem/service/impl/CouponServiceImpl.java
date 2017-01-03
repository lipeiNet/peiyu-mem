package com.peiyu.mem.service.impl;

import com.migr.common.util.StringUtils;
import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.commen.VerificationDate;
import com.peiyu.mem.dao.*;
import com.peiyu.mem.domian.entity.*;
import com.peiyu.mem.manager.CouponManager;
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
    public int consumeSendCoupon(List<GoodsForCoupon> goodsForCoupons) {
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
            CpActivity activity = new CpActivity();
            activity.setVendorId(vendorId);
            activity.setSendType(SysConstants.CouponSendType.AUTO_PROVIDE);
            activity.setStatus(SysConstants.ACTIVITYSTATUS.CHECKED);
            activity.setEndDate(new Date());
            List<CpActivity> activities = activityDao.getCpActivityBySearch(activity);
            if (CollectionUtils.isNotEmpty(activities)) {
                Iterator actsIterator = activities.iterator();
                while (actsIterator.hasNext()) {
                    CpActivity act = (CpActivity) actsIterator.next();
                    if (!VerificationDate.isValidMoment(act.getStartTime(), act.getEndTime()) || !VerificationDate.isValidWeek(act.getWeekFlag())) {
                        actsIterator.remove();
                    }
                }
                if (CollectionUtils.isEmpty(activities)) {
                    log.error("vendorId=" + vendorId + "下没有在" + new Date() + "的消费送券活动");
                    return 0;
                }
            }
            List<Coupon> needUpdateCoupons = new ArrayList<>();

            for (GoodsForCoupon goodsForCoupon : goodsForCoupons) {
                List<CpActivity> validActivitys = new ArrayList<>();
                for (CpActivity act : activities) {
                    if (act.getApplyScopeType().equals(SysConstants.COUPONAPPLIEDRANGE.UNLIMITED)) {
                        validActivitys.add(act);
                        continue;
                    }
                    CpApplyLimitdt applyLimitdt = new CpApplyLimitdt();
                    applyLimitdt.setVendorId(act.getVendorId());
                    applyLimitdt.setOwnRecordCode(act.getActNo());
                    applyLimitdt.setOwnRecordType(0);
                    List<CpApplyLimitdt> applyLimitdts = cpapplylimitdtDao.getCpApplyLimitdtsBySearch(applyLimitdt);
                    if (CollectionUtils.isNotEmpty(applyLimitdts)) {
                        for (CpApplyLimitdt apply : applyLimitdts) {
                            switch (act.getApplyScopeType().intValue()) {
                                case SysConstants.COUPONAPPLIEDRANGE.BRAND:
                                    if (StringUtils.isNotBlank(goodsForCoupon.getFirstIcatCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getFirstIcatCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    if (StringUtils.isNotBlank(goodsForCoupon.getSecondIcatCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getSecondIcatCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    if (StringUtils.isNotBlank(goodsForCoupon.getThirdIcatCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getThirdIcatCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    if (StringUtils.isNotBlank(goodsForCoupon.getFourthIcatCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getFourthIcatCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    break;
                                case SysConstants.COUPONAPPLIEDRANGE.CATEGORY:
                                    if (StringUtils.isNotBlank(goodsForCoupon.getFirstBrandCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getFirstBrandCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    if (StringUtils.isNotBlank(goodsForCoupon.getSecondBrandCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getSecondBrandCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    if (StringUtils.isNotBlank(goodsForCoupon.getThirdBrandCode())) {
                                        if (apply.getDetailCode().equals(goodsForCoupon.getThirdBrandCode())) {
                                            validActivitys.add(act);
                                            break;
                                        }
                                    }
                                    break;
                                case SysConstants.COUPONAPPLIEDRANGE.GOOD:
                                    if (apply.getDetailCode().equals(goodsForCoupon.getSkuCode())) {
                                        validActivitys.add(act);
                                        break;
                                    }
                                    break;
                                case SysConstants.COUPONAPPLIEDRANGE.SUPPLIER:
                                    if (apply.getDetailCode().equals(goodsForCoupon.getSupplierCode())) {
                                        validActivitys.add(act);
                                        break;
                                    }
                                    break;
                            }
                        }
                    }
                }
                List<CpActivity> finalActivitys = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(validActivitys)) {
                    for (CpActivity c : validActivitys) {
                        if (c.getUseScopeType().equals(SysConstants.COUPONUSERANGE.UNLIMITED)) {
                            finalActivitys.add(c);
                            continue;
                        }
                        CpUseLimitdt useLimitdt = new CpUseLimitdt();
                        useLimitdt.setVendorId(c.getVendorId());
                        useLimitdt.setOwnRecordCode(c.getActNo());
                        useLimitdt.setOwnRecordType(0);
                        List<CpUseLimitdt> useLimitdts = cpuselimitdtDao.getCpUseLimitdts(useLimitdt);
                        for (CpUseLimitdt uselimit : useLimitdts) {
                            if (uselimit.getUseScopeType().equals(SysConstants.COUPONUSERANGE.ORAGN)) {
                                if (goodsForCoupon.getOrganCode().equals(uselimit.getOrganCode())) {
                                    finalActivitys.add(c);
                                    break;
                                }
                            }
                            if (uselimit.getUseScopeType().equals(SysConstants.COUPONUSERANGE.STORE)) {
                                if (goodsForCoupon.getStoreCode().equals(uselimit.getStoreCode())) {
                                    finalActivitys.add(c);
                                    break;
                                }
                            }
                        }
                    }
                }

                if (CollectionUtils.isNotEmpty(finalActivitys)) {
                    Iterator iterator = finalActivitys.iterator();
                    while (iterator.hasNext()) {
                        Double money = ((CpActivity) iterator.next()).getOverMoney();
                        if (goodsForCoupon.getRealPayMoney() < money) {
                            iterator.remove();
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(finalActivitys)) {
                    for (CpActivity t : finalActivitys) {
                        CpActsubGroup cpActsubGroup = new CpActsubGroup();//获取优惠券组
                        cpActsubGroup.setVendorId(t.getVendorId());
                        cpActsubGroup.setActNo(t.getActNo());
                        cpActsubGroup.setEndDate(new Date());
                        List<CpActsubGroup> actsubGroups = actsubGroupDao.getCpActsubGroupList(cpActsubGroup);
                        if (CollectionUtils.isNotEmpty(actsubGroups)) {
                            for (CpActsubGroup ag : actsubGroups) {
                                Coupon coupon = new Coupon();
                                coupon.setVendorId(ag.getVendorId());
                                coupon.setActNo(ag.getActNo());
                                coupon.setSubgroupCode(ag.getSubgroupCode());
                                coupon.setState(SysConstants.COUPONSTATE.NOGRANT);
                                coupon.setPageIndex(0);
                                coupon.setPageSize(100);
                                String key = String.format("%s_%s_%s", vendorId, t.getActNo(), ag.getSubgroupCode());
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
                                if (CollectionUtils.isNotEmpty(coupons)) {
                                    for (Coupon c : coupons) {
                                        if (!contains(needUpdateCoupons, c)) {
                                            needUpdateCoupons.add(c);
                                            break;
                                        }
                                    }
                                }
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

    /**
     * 异步发券
     *
     * @param coupons
     */
    private void asyncSaveCouponsState(final List<Coupon> coupons, Member member) {
        if (CollectionUtils.isNotEmpty(coupons)) {
            for (Coupon coupon : coupons) {
                coupon.setMemNo(member.getMemNo());
                coupon.setMemCat(member.getMemCatCode());
                coupon.setModifyDate(new Date());
                coupon.setSendCouponDate(new Date());
                coupon.setState(SysConstants.COUPONSTATE.GRANT);
            }
        }
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                couponManager.updateCoupons(coupons);
            }
        });
    }
}
