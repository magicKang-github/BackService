package com.back.service.system.service;

import com.back.service.system.bean.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author magicHat
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleByUserID(Integer id);
}
