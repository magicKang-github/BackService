package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.SysUserDao;
import com.back.service.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author magicHat
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public void removeByDelIds(Integer uid, Integer[] delIds) {
        sysUserDao.removeByDelIds(uid,delIds);
    }

    @Override
    public void insertByIds(Integer uid, Integer[] addIds) {
        sysUserDao.insertByIds(uid,addIds);
    }
}
