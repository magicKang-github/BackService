package com.back.service.system.controller;

import com.back.service.system.bean.SysProperties;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysPropertiesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @auther magicHat
 */
@RestController
@RequestMapping(value = "sysProperty")
public class SysPropertiesController {

    @Resource
    private SysPropertiesService sysPropertiesService;

    @PostMapping(value = "insert")
    public AjaxResult insert(@RequestBody SysProperties sysProperties){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysProperties.setCreatetime(date);
            sysProperties.setUpdatetime(date);
            ajaxResult = sysPropertiesService.save(sysProperties)?AjaxResult.success("添加成功！"):AjaxResult.error("添加失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PutMapping(value = "modify")
    public AjaxResult modify(@RequestBody SysProperties sysProperties){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysProperties.setUpdatetime(date);
            ajaxResult = sysPropertiesService.updateById(sysProperties)?AjaxResult.success("修改成功！"):AjaxResult.error("修改失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @DeleteMapping(value = "delete")
    public AjaxResult delete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysProperties> list = sysPropertiesService.listByIds(Arrays.asList(ids));
            list.forEach(item -> item.setStatus(false));
            sysPropertiesService.updateBatchById(list);
            ajaxResult = AjaxResult.success("删除成功！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "page")
    public AjaxResult page(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                           @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                           SysProperties sysProperties){
        AjaxResult ajaxResult;
        try{
            IPage<SysProperties> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysProperties> lambdaQueryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysProperties.getId())){ lambdaQueryWrapper.eq(SysProperties::getId,sysProperties.getId()); }
            if (oConvertUtils.isNotEmpty(sysProperties.getSkey())){lambdaQueryWrapper.like(SysProperties::getSkey,sysProperties.getSkey());}
            if (oConvertUtils.isNotEmpty(sysProperties.getNote())){lambdaQueryWrapper.like(SysProperties::getNote,sysProperties.getNote());}
            if (oConvertUtils.isNotEmpty(sysProperties.getStatus())){lambdaQueryWrapper.eq(SysProperties::getStatus,sysProperties.getStatus());}else {lambdaQueryWrapper.eq(SysProperties::getStatus,1);}
            lambdaQueryWrapper.orderByDesc(SysProperties::getUpdatetime);
            IPage<SysProperties> iPage = sysPropertiesService.page(page, lambdaQueryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
