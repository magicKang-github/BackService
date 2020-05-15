package com.back.service.system.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.bean.SysMessage;
import com.back.service.system.dao.SysMessageDao;
import com.back.service.system.service.SysMessageService;
import org.springframework.stereotype.Service;

/**
 * @author magicHat
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageDao, SysMessage> implements SysMessageService {
}
