package com.back.service.system.service.serviceImpl;

import com.back.service.system.bean.ViewUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.service.system.dao.ViewUserDao;
import com.back.service.system.service.ViewUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author magicHat
 */
@Service
public class ViewUserServiceImpl extends ServiceImpl<ViewUserDao, ViewUser> implements ViewUserService {

    @Resource
    private ViewUserDao viewUserDao;

    public Map<String,Long> getWeekData(Integer week){
        List<HashMap<String, Object>> list = viewUserDao.getDataByWeek(week);
        return toolOfWeek(list);
    }

    @Override
    public Map<String, Long> getWeekDataByPageId(Integer week, Integer pageId) {
        List<HashMap<String, Object>> list = viewUserDao.getDataByWeekAndPageId(week,pageId);
        return toolOfWeek(list);
    }

    private Map<String, Long> toolOfWeek(List<HashMap<String, Object>> list){
        Map<String, Long> map = new HashMap<>();
        if (list != null && !list.isEmpty()) {
            for (HashMap<String, Object> map1 : list) {
                String key = null;
                Long value = null;
                for (Map.Entry<String, Object> entry : map1.entrySet()) {
                    if ("week".equals(entry.getKey())) {
                        key = entry.getValue().toString();
                    } else if ("total".equals(entry.getKey())) {
                        value = (Long) entry.getValue();
                    }
                }
                map.put(key, value);
            }
        }
        map.putIfAbsent("Mon", 0L);
        map.putIfAbsent("Tue", 0L);
        map.putIfAbsent("Wed", 0L);
        map.putIfAbsent("Thu", 0L);
        map.putIfAbsent("Fri", 0L);
        map.putIfAbsent("Sat", 0L);
        map.putIfAbsent("Sun", 0L);
        return map;
    }
}
