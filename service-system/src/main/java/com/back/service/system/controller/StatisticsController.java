package com.back.service.system.controller;

import com.back.service.system.bean.SysAction;
import com.back.service.system.bean.SysActionPage;
import com.back.service.system.bean.ViewUser;
import com.back.service.system.service.SysActionPageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.Constants;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.DateUtils;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysActionService;
import com.back.service.system.service.SysMessageService;
import com.back.service.system.service.ViewUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 统计
 * @auther magicHat
 */
@RestController
@RequestMapping(value = "statistics")
public class StatisticsController {

    @Resource
    private SysActionService sysActionService;
    @Resource
    private SysActionPageService sysActionPageService;
    @Resource
    private SysMessageService sysMessageService;
    @Resource
    private ViewUserService viewUserService;

    @GetMapping("index")
    public AjaxResult indexCount(@RequestParam(defaultValue = "1") Integer week ){
        AjaxResult ajaxResult;
        try{
            Map<String,Object> map = new HashMap<>();
            List<Integer> fontList = new ArrayList<>();
            List<SysActionPage> sysActionPages = sysActionPageService.list(Wrappers.<SysActionPage>lambdaQuery().eq(SysActionPage::getType,1));
            sysActionPages.forEach(item -> fontList.add(item.getId()));
            map.put("fontCount",sysActionService.count(Wrappers.<SysAction>lambdaQuery().in(SysAction::getPage,fontList)));
            fontList.clear();
            List<SysActionPage> sysActionPageList = sysActionPageService.list(Wrappers.<SysActionPage>lambdaQuery().eq(SysActionPage::getType,2));
            sysActionPageList.forEach(item -> fontList.add(item.getId()));
            map.put("weChatCount",sysActionService.count(Wrappers.<SysAction>lambdaQuery().in(SysAction::getPage,fontList)));
            map.put("totalCount",sysActionService.count());
            map.put("messageCount",sysMessageService.count());
            map.put("newUser",viewUserService.count(Wrappers.<ViewUser>lambdaQuery().eq(ViewUser::getStatus,Constants.STATUS_TRUE)));
            map.put("pageTotal",sysActionPageService.count(Wrappers.<SysActionPage>lambdaQuery().eq(SysActionPage::getStatus, Constants.STATUS_TRUE)));
            map.put("week",viewUserService.getWeekData(week));
            map.put("weekWeChatIndex",viewUserService.getWeekDataByPageId(week,10));
            map.put("weekWeChatProduct",viewUserService.getWeekDataByPageId(week,13));
            map.put("weekWeChatLogin",viewUserService.getWeekDataByPageId(week,16));
            map.put("weekWebIndex",viewUserService.getWeekDataByPageId(week,1));
            map.put("weekWebProduct",viewUserService.getWeekDataByPageId(week,7));
            ajaxResult = AjaxResult.success("成功！",map);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping("charts/histogram")
    public AjaxResult getHistogram(@RequestParam(defaultValue = "0") Integer week,@RequestParam Integer pageId){
        AjaxResult ajaxResult;
        try{
            ajaxResult = AjaxResult.success("成功",viewUserService.getWeekDataByPageId(week,pageId));
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping("charts/lineChart/{week}")
    public AjaxResult getLineChart(@PathVariable Integer week, @RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            List<Map<String,Object>> list = new ArrayList<>();
            for (Integer id : ids) {
                Map<String,Object> map = new HashMap<>();
                map.put("pageInfo", sysActionPageService.getById(id));
                map.put("week", viewUserService.getWeekDataByPageId(week,id));
                list.add(map);
            }
            ajaxResult = AjaxResult.success("成功",list);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "page")
    public AjaxResult newsPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                               @RequestParam(value = "startTime", required = false) String startTime,
                               @RequestParam(value = "endTime", required = false) String endTime,
                               SysAction sysAction){
        AjaxResult ajaxResult;
        try{
            IPage<SysAction> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysAction> lambdaQueryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysAction.getId())){ lambdaQueryWrapper.eq(SysAction::getId,sysAction.getId()); }
            if (oConvertUtils.isNotEmpty(sysAction.getName())){lambdaQueryWrapper.like(SysAction::getName,sysAction.getName());}
            if (oConvertUtils.isNotEmpty(sysAction.getPage())){lambdaQueryWrapper.eq(SysAction::getPage,sysAction.getPage());}
            if (oConvertUtils.isNotEmpty(sysAction.getIp())){lambdaQueryWrapper.eq(SysAction::getIp,sysAction.getIp());}
            if (oConvertUtils.isNotEmpty(startTime) && oConvertUtils.isNotEmpty(endTime)) {
                Date startDate = DateUtils.dateTime(DateUtils.YYYY_MM_DD, startTime);
                Date endDate = DateUtils.dateTime(DateUtils.YYYY_MM_DD,endTime);
                lambdaQueryWrapper.between(SysAction::getCreatetime,startDate,endDate);
            }
            lambdaQueryWrapper.orderByDesc(SysAction::getCreatetime);
            IPage<SysAction> iPage = sysActionService.page(page, lambdaQueryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping("type/list")
    public AjaxResult getTypeList(){
        AjaxResult ajaxResult;
        try{
            ajaxResult = AjaxResult.success("成功",sysActionPageService.list());
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }
}
