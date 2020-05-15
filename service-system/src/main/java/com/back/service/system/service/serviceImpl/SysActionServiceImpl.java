package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysAction;
import com.back.service.system.dao.SysActionDao;
import com.back.service.system.service.SysActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author magicHat
 */
@Service
public class SysActionServiceImpl extends ServiceImpl<SysActionDao, SysAction> implements SysActionService {
}
