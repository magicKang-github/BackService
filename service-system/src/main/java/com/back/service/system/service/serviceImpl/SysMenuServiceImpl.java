package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.SysMenuDao;
import com.back.service.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author magicHat
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenu> getMenuByMenuID(Integer id) {
        return sysMenuDao.getMenuByMenuID(id);
    }
}
