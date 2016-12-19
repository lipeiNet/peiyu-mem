package com.peiyu.mem.service.impl;

import com.migr.common.util.StringUtils;
import com.migr.common.util.bean.BeanUtils;
import com.peiyu.mem.commen.SysConstants;
import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.domian.entity.CpApplyLimitdt;
import com.peiyu.mem.domian.entity.CpUseLimitdt;
import com.peiyu.mem.manager.CpActsubGroupManager;
import com.peiyu.mem.service.CpActsubGroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class CpActsubGroupServiceImpl implements CpActsubGroupService {
    private Logger log=Logger.getLogger(CpActivityServiceImpl.class);
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Autowired
    private CpActsubGroupManager actsubGroupManager;
    @Override
    public int insertActsubGroup(CpActsubGroup actsubGroup) {
        List<CpApplyLimitdt> applyLimitdtList = new ArrayList<>();
        List<CpUseLimitdt> useLimitdtList = new ArrayList<>();
        if (!actsubGroup.getApplyScopeType().equals(SysConstants.COUPONAPPLIEDRANGE.UNLIMITED)) {
            if (StringUtils.isEmpty(actsubGroup.getDetailCode())) {
                log.error("没有选择应用范围");
                return 0;
            }
        }
        if (!actsubGroup.getUseScopeType().equals(SysConstants.COUPONUSERANGE.UNLIMITED)) {
            if (StringUtils.isEmpty(actsubGroup.getOrganOrStoreCode())) {
                log.error("没有选择使用范围");
                return 0;
            }
        }
        String[] splitCodes = actsubGroup.getDetailCode().split(",");
        String[] splitNames = actsubGroup.getDetailName().split(",");
        for (int i = 0; i < splitCodes.length; i++) {
            CpApplyLimitdt applyLimit = new CpApplyLimitdt();
            BeanUtils.copyProperties(actsubGroup, applyLimit);
            applyLimit.setId(null);
            applyLimit.setOwnRecordType(SysConstants.OWNRECORDTYPE.GROUPS);
            applyLimit.setOwnRecordCode(actsubGroup.getSubgroupCode());
            applyLimit.setDetailCode(splitCodes[i]);
            applyLimit.setDetailName(splitNames[i]);
            applyLimitdtList.add(applyLimit);
        }
        String[] codes = actsubGroup.getOrganOrStoreCode().split(",");
        String[] names = actsubGroup.getOrganOrStoreName().split(",");
        for (int i = 0; i < codes.length; i++) {
            CpUseLimitdt useLimit = new CpUseLimitdt();
            BeanUtils.copyProperties(actsubGroup, useLimit);
            useLimit.setId(null);
            useLimit.setOwnRecordCode(actsubGroup.getSubgroupCode());
            useLimit.setOwnRecordType(SysConstants.OWNRECORDTYPE.GROUPS);
            if (actsubGroup.getUseScopeType().equals(SysConstants.COUPONUSERANGE.ORAGN)) {
                useLimit.setOrganCode(codes[i]);
                useLimit.setOrganName(names[i]);
            }
            if (actsubGroup.equals(SysConstants.COUPONUSERANGE.STORE)) {
                useLimit.setStoreCode(codes[i]);
                useLimit.setStoreName(names[i]);
            }
            useLimitdtList.add(useLimit);
        }
        if (actsubGroup.getId() == null) {
            if (actsubGroupManager.insertCpActsubGroup(actsubGroup, applyLimitdtList, useLimitdtList)) {
                return 1;
            }
        }
        if (actsubGroupManager.updateCpActsubGroup(actsubGroup, applyLimitdtList, useLimitdtList)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteActsubGroup(long id) {
        try {
            if (id != 0) {
                return actsubGroupDao.delete(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
        return 0;
    }

    @Override
    public int deleteBatchActsubGroup(String ids) {
        return 0;
    }

    @Override
    public int updateActsubGroup(CpActsubGroup search) {
        return actsubGroupDao.update(search);
    }

    @Override
    public CpActsubGroup getActsubGroup(Long id) {
        return actsubGroupDao.get(id);
    }
}
