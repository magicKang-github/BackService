package com.back.service.system.controller;

import com.back.service.system.bean.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysRoleMenuService;
import com.back.service.system.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author magicHat
 */
@RestController
@RequestMapping(value = "sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping(value = "user/{id}")
    public AjaxResult getRoleByUserID(@PathVariable Integer id){
        AjaxResult ajaxResult;
        try{
            List<SysRole> list = sysRoleService.getRoleByUserID(id);
            ajaxResult = AjaxResult.success("成功！", list);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "page")
    public AjaxResult page(@RequestParam(value = "pageNum", defaultValue = "1")Integer currentPage,
                           @RequestParam(value = "pageSize", defaultValue = "6")Integer size,
                           SysRole sysRole){
        AjaxResult ajaxResult;
        try{
            IPage<SysRole> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysRole.getName())){queryWrapper.like(SysRole::getName,sysRole.getName());}
            if (oConvertUtils.isNotEmpty(sysRole.getId())){queryWrapper.eq(SysRole::getId, sysRole.getId());}
            if (oConvertUtils.isNotEmpty(sysRole.getStatus())){queryWrapper.eq(SysRole::getStatus,sysRole.getStatus());}else{queryWrapper.eq(SysRole::getStatus,1); }
            queryWrapper.orderByDesc(SysRole::getUpdatetime);
            IPage<SysRole> iPage = sysRoleService.page(page, queryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping(value = "insert")
    public AjaxResult insert(@RequestBody SysRole sysRole){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysRole.setCreatetime(date);
            sysRole.setUpdatetime(date);
            ajaxResult = sysRoleService.save(sysRole)?AjaxResult.success("添加信息成功！"):AjaxResult.error("添加信息失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PutMapping(value = "modify")
    public AjaxResult modify(@RequestBody SysRole sysRole){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysRole.setUpdatetime(date);
            ajaxResult = sysRoleService.updateById(sysRole)?AjaxResult.success("修改信息成功！"):AjaxResult.error("修改信息失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @DeleteMapping(value = "delete")
    public AjaxResult delete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysRole> sysRoles = sysRoleService.listByIds(Arrays.asList(ids));
            sysRoles.forEach(item -> item.setStatus(false));
            sysRoleService.updateBatchById(sysRoles);
//            ajaxResult = sysRoleService.removeByIds(Arrays.asList(ids))?AjaxResult.success("删除成功！"):AjaxResult.error("删除失败！");
            ajaxResult = AjaxResult.success("已经全部禁用");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping(value = "roleMenu")
    public AjaxResult roleMenu(@RequestBody Map<String,Integer[]> resultMap){
        AjaxResult ajaxResult;
        try{
            Integer rid = resultMap.get("rid")[0];
            Integer[] delIds = resultMap.get("delIds");
            if (delIds.length>0){
                sysRoleMenuService.removeByDelIds(rid,delIds);
            }
            Integer[] addIds = resultMap.get("addIds");
            if (addIds.length>0){
                sysRoleMenuService.insertByIds(rid,addIds);
            }
            ajaxResult = AjaxResult.success("权限修改成功！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
