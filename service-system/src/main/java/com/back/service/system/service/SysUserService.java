package com.back.service.system.service;

import com.back.service.system.bean.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author magicHat
 */
public interface SysUserService extends IService<SysUser> {

    void removeByDelIds(Integer uid, Integer[] delIds);

    void insertByIds(Integer uid, Integer[] addIds);
}
