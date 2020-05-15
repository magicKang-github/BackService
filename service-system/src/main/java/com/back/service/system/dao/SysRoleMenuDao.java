package com.back.service.system.dao;

import com.back.service.system.bean.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author magicHat
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    void removeByIds(@Param("rid") Integer rid, @Param("delIds") Integer[] delIds);

    void insertByIds(@Param("rid") Integer rid, @Param("ids") Integer[] addIds);
}
