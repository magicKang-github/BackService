package com.back.service.system.service;

import com.back.service.system.bean.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @auther magicHat
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    IPage selectPage(Page<SysLoginLog> page, SysLoginLog sysLoginLog);
}
