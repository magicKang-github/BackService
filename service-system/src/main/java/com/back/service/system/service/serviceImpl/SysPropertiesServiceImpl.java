package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.SysProperties;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.SysPropertiesDao;
import com.back.service.system.service.SysPropertiesService;
import org.springframework.stereotype.Service;

/**
 * @auther magicHat
 */
@Service
public class SysPropertiesServiceImpl extends ServiceImpl<SysPropertiesDao, SysProperties> implements SysPropertiesService {
}
