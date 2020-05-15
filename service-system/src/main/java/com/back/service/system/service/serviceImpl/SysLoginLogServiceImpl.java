package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.SysLoginLogDao;
import com.back.service.system.service.SysLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther magicHat
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogDao, SysLoginLog> implements SysLoginLogService {

    @Resource
    private SysLoginLogDao sysLoginLogDao;

    @Override
    public IPage selectPage(Page<SysLoginLog> page, SysLoginLog sysLoginLog) {
        return sysLoginLogDao.selectPageVo(page, sysLoginLog);
    }
}
