package com.back.service.system.service;

import com.back.service.system.bean.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author magicHat
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> getMenuByMenuID(Integer id);
}
