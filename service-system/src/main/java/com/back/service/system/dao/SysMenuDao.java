package com.back.service.system.dao;

import com.back.service.system.bean.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author magicHat
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    @Select("select * from sys_menu where id in (select mid from sys_role_menu where rid = #{id}) and status = '1'")
    List<SysMenu> getMenuByMenuID(@Param("id") Integer id);
}
