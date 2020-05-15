package com.back.service.system.dao;

import com.back.service.system.bean.ViewUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author magicHat
 */
public interface ViewUserDao extends BaseMapper<ViewUser> {

    List<HashMap<String,Object>> getDataByWeek(@Param("week") Integer week);

    List<HashMap<String, Object>> getDataByWeekAndPageId(@Param("week")Integer week,@Param("pageId") Integer pageId);
}
