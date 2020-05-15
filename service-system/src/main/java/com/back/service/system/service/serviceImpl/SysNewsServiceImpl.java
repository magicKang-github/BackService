package com.back.service.system.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.bean.SysNews;
import com.back.service.system.dao.SysNewsDao;
import com.back.service.system.service.SysNewsService;
import org.springframework.stereotype.Service;

/**
 * @author magicHat
 */
@Service
public class SysNewsServiceImpl extends ServiceImpl<SysNewsDao, SysNews> implements SysNewsService {
}
