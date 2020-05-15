package com.back.service.system.dao;

import com.back.service.system.bean.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author magicHat
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    void removeByDelIds(@Param("uid") Integer uid, @Param("ids") Integer[] delIds);

    void insertByIds(@Param("uid") Integer uid, @Param("ids") Integer[] addIds);
}
