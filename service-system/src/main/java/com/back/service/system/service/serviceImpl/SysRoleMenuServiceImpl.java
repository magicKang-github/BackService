package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.SysRoleMenuDao;
import com.back.service.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author magicHat
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public void removeByDelIds(Integer rid, Integer[] delIds) {
        sysRoleMenuDao.removeByIds(rid,delIds);
    }

    @Override
    public void insertByIds(Integer rid, Integer[] addIds) {
        sysRoleMenuDao.insertByIds(rid,addIds);
    }
}
