package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.SysRoleDao;
import com.back.service.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author magicHat
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> getRoleByUserID(Integer id) {
        return sysRoleDao.getRoleByUserID(id);
    }
}
