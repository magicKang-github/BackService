package com.back.service.system.service;

import com.back.service.system.bean.ViewUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author magicHat
 */
public interface ViewUserService extends IService<ViewUser> {

    Map<String,Long> getWeekData(Integer week);

    Map<String,Long> getWeekDataByPageId(Integer week, Integer pageId);
}
