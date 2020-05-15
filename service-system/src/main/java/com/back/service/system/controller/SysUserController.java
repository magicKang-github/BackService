package com.back.service.system.controller;

import com.back.service.system.bean.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author magicHat
 */
@RestController
@RequestMapping(value = "sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping(value = "login")
    public AjaxResult login(@RequestBody Map<String,String> result){
        String userName = result.get("userName");
        String password = result.get("password");
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getName,userName).eq(SysUser::getPassword,password));
        return sysUser==null?AjaxResult.error("登录失败！"):AjaxResult.success("登录成功！",sysUser);
    }

    @GetMapping(value = "page")
    public AjaxResult getUsers(@RequestParam(value = "pageNum", defaultValue = "1")Integer currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "6")Integer size,
                               SysUser sysUser){
        AjaxResult ajaxResult;
        try{
            IPage<SysUser> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysUser.getName())){queryWrapper.like(SysUser::getName,sysUser.getName()); }
            if (oConvertUtils.isNotEmpty(sysUser.getId())){ queryWrapper.eq(SysUser::getId, sysUser.getId()); }
            if (oConvertUtils.isNotEmpty(sysUser.getAccount())){queryWrapper.like(SysUser::getAccount, sysUser.getAccount());}
            if (oConvertUtils.isNotEmpty(sysUser.getStatus())){ queryWrapper.eq(SysUser::getStatus,sysUser.getStatus()); }else{ queryWrapper.eq(SysUser::getStatus,1); }
            queryWrapper.orderByDesc(SysUser::getUpdatetime);
            IPage iPage = sysUserService.page(page, queryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping(value = "insert")
    public AjaxResult insert(@RequestBody SysUser sysUser){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysUser.setCreatetime(date);
            sysUser.setUpdatetime(date);
            ajaxResult = sysUserService.save(sysUser)?AjaxResult.success("添加信息成功！"):AjaxResult.error("添加用户信息失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PutMapping(value = "modify")
    public AjaxResult modify(@RequestBody SysUser sysUser){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysUser.setUpdatetime(date);
            ajaxResult = sysUserService.updateById(sysUser)?AjaxResult.success("修改信息成功！"):AjaxResult.error("修改用户信息失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @DeleteMapping(value = "delete")
    public AjaxResult delete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysUser> sysUsers = sysUserService.listByIds(Arrays.asList(ids));
            sysUsers.forEach(item -> item.setStatus(false));
            sysUserService.updateBatchById(sysUsers);
//            ajaxResult = sysUserService.removeByIds(Arrays.asList(ids))?AjaxResult.success("删除成功！"):AjaxResult.error("删除失败！");
            ajaxResult = AjaxResult.success("已经全部禁用！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping(value = "userRole")
    public AjaxResult userRole(@RequestBody Map<String,Integer[]> resultMap){
        AjaxResult ajaxResult;
        try{
            Integer uid = resultMap.get("uid")[0];
            Integer[] delIds = resultMap.get("delIds");
            if (delIds.length>0){
                sysUserService.removeByDelIds(uid,delIds);
            }
            Integer[] addIds = resultMap.get("addIds");
            if (addIds.length>0){
                sysUserService.insertByIds(uid,addIds);
            }
            ajaxResult = AjaxResult.success("用户角色信息修改成功！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
