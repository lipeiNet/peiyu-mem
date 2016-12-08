package com.peiyu.mem.service.impl;

import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpMakingTaskDao;
import com.peiyu.mem.domian.entity.Coupon;
import com.peiyu.mem.domian.entity.CpMakingTask;
import com.peiyu.mem.manager.CouponManager;
import com.peiyu.mem.manager.MakingTaskManager;
import com.peiyu.mem.service.MakingTaskService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service
public class MakingTaskServiceImpl implements MakingTaskService {
    private Logger log=Logger.getLogger(MakingTaskServiceImpl.class);
    @Autowired
    private CpMakingTaskDao makingTaskDao;
    @Autowired
    private MakingTaskManager makingTaskManager;
    @Autowired
    private CouponManager couponManager;

    @Override
    public int insertMakingTask(CpMakingTask makingTask) {
        try {
            if (makingTaskManager.isRepeat(makingTask)) {
                log.error("重复提交");
                return 0;
            }
            if (makingTask.getId()==null||"".equals(makingTask.getId())){
                makingTaskDao.insert(makingTask);
            }
            else{
                makingTaskDao.update(makingTask);
            }
            makingTaskManager.insertCacheByTaskCode(makingTask);
            return 1;
        }catch (Exception e){
            log.error("保存制券任务失败："+e.getMessage());
            return 0;
        }
    }

    @Override
    public int makingCoupon(Long vendorId, String taskCode) {
        try {
            final CpMakingTask tempMakingTask = makingTaskManager.getMakingTask(vendorId, taskCode);
            if (tempMakingTask == null) {
                log.error("没有保存制券任务");
                return 2;
            }
            if (makingTaskManager.isRepeatByMaikingCoupon(vendorId, taskCode)) {
                log.error("制券正在进行，不要重复提交");
                return 3;
            }
            tempMakingTask.setState(SysConstants.CPMAKINGTASTSTATE.UNDERWAY);
            tempMakingTask.setTaskBeginTime(new Date());
            makingTaskDao.update(tempMakingTask);

            long start1=System.currentTimeMillis();
            final List<Coupon> tempCoupons = getTempCoupons(tempMakingTask);
            long start2=System.currentTimeMillis();
            log.info("生产优惠券消费时间："+(start2-start1)+"毫秒");
            if (CollectionUtils.isNotEmpty(tempCoupons)) {
                makingTaskManager.insertCacheByMakingConpon(vendorId, taskCode);
                new Thread() {
                    @Override
                    public void run() {
                        boolean result = couponManager.insertCoupons(tempCoupons);
                        if (result) {
                            tempMakingTask.setState(SysConstants.CPMAKINGTASTSTATE.COMPLETED);
                            tempMakingTask.setTaskEndTime(new Date());
                            makingTaskDao.update(tempMakingTask);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            log.error("异步制券出现异常", e);
            return 0;
        }
        return 1;
    }

    @Override
    public void deleteMakingTask(long id) {
        if (id==0){
            return;
        }
        try {
            makingTaskDao.delete(id);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public int updateMakingTask(CpMakingTask makingTask) {
        try{
            makingTaskDao.update(makingTask);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public CpMakingTask getMakingTask(long id) {
        return makingTaskDao.get(id);
    }

   @Override
    public String getTempCpCode(CpMakingTask makingTask) {
        String f = "%0" + makingTask.getSerialNoLength() + "d";
        String cpCode1;
        String cpCode2;
        String cpCode3;
        if (makingTask.getCouRules() != null && makingTask.getCouRules() == 1) {
            cpCode1 = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + 0)
                    + getRandomNumber(makingTask.getCouRulesNum()) + makingTask.getGenNoRuleSuffix();
            cpCode2 = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + 2)
                    + getRandomNumber(makingTask.getCouRulesNum()) + makingTask.getGenNoRuleSuffix();
            cpCode3 = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + makingTask.getTicNum())
                    + getRandomNumber(makingTask.getCouRulesNum()) + makingTask.getGenNoRuleSuffix();
        }
        cpCode1 = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + 0)
                + makingTask.getGenNoRuleSuffix();
        cpCode2 = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + 2)
                + makingTask.getGenNoRuleSuffix();
        cpCode3 = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + makingTask.getTicNum())
                + makingTask.getGenNoRuleSuffix();
        return cpCode1 + cpCode2 + "..." + cpCode3;
    }

    /**
     * 获取临时的优惠券集合
     * @param makingTask
     * @return
     */
    protected List<Coupon> getTempCoupons(CpMakingTask makingTask) {
        List<Coupon> tempCoupons = new ArrayList<>();
        List<String> cpCodes = new ArrayList<>();
        String f = "%0" + makingTask.getSerialNoLength() + "d";
        for (int i = 0; i < makingTask.getTicNum(); i++) {
            String cpCode = "";
            if (makingTask.getCouRules() != null && makingTask.getCouRules() == 1) {
                cpCode = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + i)
                        + getRandomNumber(makingTask.getCouRulesNum()) + makingTask.getGenNoRuleSuffix();
            }
            if (makingTask.getCouRules() == null || makingTask.getCouRules() != 1) {
                cpCode = makingTask.getGenNoRulePrefix() + String.format(f, makingTask.getSerialNoStart() + i)
                        + makingTask.getGenNoRuleSuffix();
            }
            if (!cpCodes.contains(cpCode)) {
                cpCodes.add(cpCode);
            }
        }
        for (String cpCode : cpCodes) {
            Coupon coupon = new Coupon();
            coupon.setId(null);
            coupon.setCpCode(cpCode);
            coupon.setVendorId(makingTask.getVendorId());
            coupon.setCreateDate(new Date());
            coupon.setActName(makingTask.getActName());
            coupon.setActNo(makingTask.getActNo());
            coupon.setSubgroupCode(makingTask.getSubgroupCode());
            coupon.setState(SysConstants.COUPONSTATE.NOGRANT);
            coupon.setCpValue(makingTask.getCpValue());
            tempCoupons.add(coupon);
        }
        return tempCoupons;
    }
    /**
     * 生产随机数
     * @param length
     * @return
     */
    protected int getRandomNumber(int length){
        try {
            Random random=new Random();
            Double maxNumber=Math.pow(10,length);
            return random.nextInt(maxNumber.intValue());
        }catch (Exception e){
            log.error("生产随机数异常："+e.getMessage());
            return 0;
        }
    }


}
