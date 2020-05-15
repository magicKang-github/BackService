package com.back.service.system.controller;

import com.back.service.system.bean.SysImages;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.Constants;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysImagesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @author magicHat
 */
@RestController
@RequestMapping(value = "api/sysImages")
public class SysImagesController {

    @Resource
    private SysImagesService sysImagesService;

    @GetMapping(value = "page")
    public AjaxResult productGauzePage(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                                       @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                                       SysImages sysImages){
        AjaxResult ajaxResult;
        try{
            IPage<SysImages> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysImages> lambdaQueryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysImages.getId())){ lambdaQueryWrapper.eq(SysImages::getId,sysImages.getId()); }
            if (oConvertUtils.isNotEmpty(sysImages.getName())){lambdaQueryWrapper.like(SysImages::getName,sysImages.getName());}
            // TODO 图片根据类型模糊查找有问题
            if (oConvertUtils.isNotEmpty(sysImages.getType())){lambdaQueryWrapper.like(SysImages::getType,sysImages.getType());}
            if (oConvertUtils.isNotEmpty(sysImages.getStatus())){lambdaQueryWrapper.eq(SysImages::getStatus,sysImages.getStatus());}
            else {lambdaQueryWrapper.eq(SysImages::getStatus, Constants.STATUS_TRUE);}
            lambdaQueryWrapper.orderByDesc(SysImages::getUpdatetime);
            IPage<SysImages> iPage = sysImagesService.page(page, lambdaQueryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping(value = "insert")
    public AjaxResult insert(@RequestBody SysImages sysImages){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysImages.setCreatetime(date);
            sysImages.setUpdatetime(date);
            ajaxResult = sysImagesService.save(sysImages)?AjaxResult.success("添加成功！"):AjaxResult.error("添加失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PutMapping(value = "modify")
    public AjaxResult modify(@RequestBody SysImages sysImages){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysImages.setUpdatetime(date);
            ajaxResult = sysImagesService.updateById(sysImages)?AjaxResult.success("修改成功！"):AjaxResult.error("修改失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @DeleteMapping(value = "delete")
    public AjaxResult delete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysImages> list = sysImagesService.listByIds(Arrays.asList(ids));
            list.forEach(item -> item.setStatus(false));
            sysImagesService.updateBatchById(list);
            ajaxResult = AjaxResult.success("删除成功！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
