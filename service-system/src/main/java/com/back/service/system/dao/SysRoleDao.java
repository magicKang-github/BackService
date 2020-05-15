package com.back.service.system.dao;

import com.back.service.system.bean.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author magicHat
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    @Select("select * from sys_role where id in (select rid from sys_user_role where uid = #{uid}) and status = '1'")
    List<SysRole> getRoleByUserID(@Param("uid") Integer id);
}
