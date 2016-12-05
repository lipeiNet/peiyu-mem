package com.peiyu.mem.service.impl;

import com.peiyu.mem.dao.CpActsubGroupDao;
import com.peiyu.mem.domian.entity.CpActsubGroup;
import com.peiyu.mem.service.CpActsubGroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class CpActsubGroupServiceImpl implements CpActsubGroupService {
    private Logger log=Logger.getLogger(CpActivityServiceImpl.class);
    @Autowired
    private CpActsubGroupDao actsubGroupDao;
    @Override
    public int insertActsubGroup(CpActsubGroup actsubGroup) {
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
        return 0;
    }

    @Override
    public int getActsubGroup(long id) {
        return 0;
    }
}
