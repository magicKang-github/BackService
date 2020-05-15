package com.back.service.system.service;

import com.back.service.system.bean.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author magicHat
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    void removeByDelIds(Integer rid, Integer[] delIds);

    void insertByIds(Integer rid, Integer[] addIds);
}
