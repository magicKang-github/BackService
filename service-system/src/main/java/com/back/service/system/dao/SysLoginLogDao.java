package com.back.service.system.dao;

import com.back.service.system.bean.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @auther magicHat
 */
public interface SysLoginLogDao extends BaseMapper<SysLoginLog> {

    IPage<SysLoginLog> selectPageVo(Page<SysLoginLog> page, @Param(value = "sysLoginLog") SysLoginLog sysLoginLog);
}
