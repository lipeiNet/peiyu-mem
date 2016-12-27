package com.peiyu.mem.service.impl;

import com.migr.common.util.StringUtils;
import com.migr.common.util.bean.BeanUtils;
import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpActivityDao;
import com.peiyu.mem.domian.entity.CpActivity;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.manager.CpActivityManager;
import com.peiyu.mem.service.CpActivityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */
@Service
public class CpActivityServiceImpl implements CpActivityService {
    private Logger log=Logger.getLogger(CpActivityServiceImpl.class);
    @Autowired
    private CpActivityDao activityDao;
    @Autowired
    private CpActivityManager activityManager;

    @Override
    public int insertCpActivity(CpActivity cpActivity) {
        if (null == cpActivity.getId()) {
            List<CpActivity> cps = activityDao.getCpActivityBySearch(cpActivity);
            if (CollectionUtils.isNotEmpty(cps)) {
                log.error("请不要重复提交");
                return 2;
            }
        }
        List<CpApplyLimitdt> applyLimits = new ArrayList<>();
        List<CpUseLimitdt> useLimits = new ArrayList<>();
        if (!cpActivity.getApplyScopeType().equals(SysConstants.COUPONAPPLIEDRANGE.UNLIMITED)) {
            if (StringUtils.isEmpty(cpActivity.getDetailCode())) {
                log.error("没有选择应用明细范围");
                return 0;
            }
            String[] splitCodes = cpActivity.getDetailCode().split(",");
            String[] splitNames = cpActivity.getDetailName().split(",");
            for (int i = 0; i < splitCodes.length; i++) {
                CpApplyLimitdt applyLimit = new CpApplyLimitdt();
                BeanUtils.copyProperties(cpActivity, applyLimit);
                applyLimit.setId(null);
                applyLimit.setOwnRecordCode(cpActivity.getActNo());
                applyLimit.setOwnRecordType(SysConstants.OWNRECORDTYPE.ACTIVITY);
                applyLimit.setDetailCode(splitCodes[i]);
                applyLimit.setDetailName(splitNames[i]);
                applyLimits.add(applyLimit);
            }
        }
        if (!cpActivity.getUseScopeType().equals(SysConstants.COUPONUSERANGE.UNLIMITED)) {
            if (StringUtils.isEmpty(cpActivity.getOrganOrStoreCode())) {
                log.error("没有选择使用范围明细");
                return 0;
            }
            String[] splitCodes = cpActivity.getOrganOrStoreCode().split(",");
            String[] splitNames = cpActivity.getOrganOrStoreName().split(",");
            for (int i = 0; i < splitCodes.length; i++) {
                CpUseLimitdt useLimit = new CpUseLimitdt();
                BeanUtils.copyProperties(cpActivity, useLimit);
                useLimit.setId(null);
                useLimit.setOwnRecordCode(cpActivity.getActNo());
                useLimit.setOwnRecordType(SysConstants.OWNRECORDTYPE.ACTIVITY);
                if (cpActivity.getUseScopeType().equals(SysConstants.COUPONUSERANGE.ORAGN)) {
                    useLimit.setOrganCode(splitCodes[i]);
                    useLimit.setOrganName(splitNames[i]);
                }
                if (cpActivity.getUseScopeType().equals(SysConstants.COUPONUSERANGE.STORE)) {
                    useLimit.setStoreCode(splitCodes[i]);
                    useLimit.setStoreName(splitNames[i]);
                }
                useLimits.add(useLimit);
            }
        }
        if (cpActivity.getId() == null || "".equals(cpActivity.getId())) {
            if (activityManager.insertCpActivity(cpActivity, applyLimits, useLimits)) {
                return 1;
            }
        }
        if (activityManager.updateCpActivity(cpActivity, applyLimits, useLimits)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteCpActivity(long id) {
        if (id != 0) {
            return activityDao.delete(id);
        }
        return 0;
    }

    @Override
    public CpActivity getCpActivity(long id) {
        if (id != 0) {
            return activityDao.get(id);
        }
        return null;
    }
}
