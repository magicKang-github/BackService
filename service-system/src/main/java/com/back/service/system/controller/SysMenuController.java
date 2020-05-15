package com.back.service.system.controller;

import com.back.service.system.bean.SysMenu;
import com.back.service.system.bean.SysRole;
import com.back.service.system.service.SysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysRoleService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author magicHat
 */
@RestController
@RequestMapping(value = "sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping(value = "role/{id}")
    public AjaxResult getMenuByMenuID(@PathVariable Integer id){
        AjaxResult ajaxResult;
        try{
            List<SysMenu> list = sysMenuService.getMenuByMenuID(id);
            ajaxResult = AjaxResult.success("成功！", list);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "user/{id}")
    public AjaxResult getMenuByUserID(@PathVariable Integer id){
        AjaxResult ajaxResult;
        try{
            List<SysRole> rids = sysRoleService.getRoleByUserID(id);
            List<SysMenu> list = new ArrayList<>();
            rids.forEach(item -> list.addAll(sysMenuService.getMenuByMenuID(item.getId())));
            HashSet<SysMenu> hashSet = new HashSet<>(list);
            list.clear();
            list.addAll(hashSet);
            ajaxResult = AjaxResult.success("成功！", list);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "menus")
    public AjaxResult getMenus(){
        AjaxResult ajaxResult;
        try{
            List<SysMenu> list = sysMenuService.list(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getStatus,1));
            ajaxResult = AjaxResult.success("成功！", list);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "page")
    public AjaxResult page(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                           @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                           SysMenu sysMenu){
        AjaxResult ajaxResult;
        try{
            IPage<SysMenu> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysMenu> queryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysMenu.getName())){queryWrapper.like(SysMenu::getName,sysMenu.getName());}
            if (oConvertUtils.isNotEmpty(sysMenu.getId())){queryWrapper.eq(SysMenu::getId, sysMenu.getId());}
            if (oConvertUtils.isNotEmpty(sysMenu.getStatus())){queryWrapper.eq(SysMenu::getStatus,sysMenu.getStatus());}else{ queryWrapper.eq(SysMenu::getStatus,1); }
            queryWrapper.orderByDesc(SysMenu::getUpdatetime);
            IPage<SysMenu> iPage = sysMenuService.page(page, queryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PostMapping(value = "insert")
    public AjaxResult insert(@RequestBody List<SysMenu> list){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            if (list.size()==2){
                SysMenu sysMenu = list.get(0);
                if(sysMenuService.getOne(Wrappers.<SysMenu>lambdaQuery()
                        .eq(SysMenu::getName,sysMenu.getName())
                        .eq(SysMenu::getPath,sysMenu.getPath())
                        .eq(SysMenu::getComponent,sysMenu.getComponent())
                )!=null){
                    ajaxResult = AjaxResult.error("已经存在一样的父节点！");
                } else {
                    sysMenu.setCreatetime(date);
                    sysMenu.setUpdatetime(date);
                    sysMenuService.save(sysMenu);
                    SysMenu menu = sysMenuService.getOne(Wrappers.<SysMenu>lambdaQuery()
                            .eq(SysMenu::getName, sysMenu.getName())
                            .eq(SysMenu::getPath, sysMenu.getPath())
                            .eq(SysMenu::getComponent, sysMenu.getComponent())
                    );
                    if (menu == null) {
                        ajaxResult = AjaxResult.error("添加父节点信息失败！");
                    } else {
                        SysMenu menuChild = list.get(1);
                        menuChild.setPid(menu.getId());
                        menuChild.setCreatetime(date);
                        menuChild.setUpdatetime(date);
                        ajaxResult = sysMenuService.save(menuChild) ? AjaxResult.success("添加信息成功！") : AjaxResult.error("添加信息失败！");
                    }
                }
            }else if(list.size()==1){
                SysMenu sysMenu = list.get(0);
                sysMenu.setCreatetime(date);
                sysMenu.setUpdatetime(date);
                ajaxResult = sysMenuService.save(sysMenu)?AjaxResult.success("添加信息成功！"):AjaxResult.error("添加信息失败！");
            }else{
                ajaxResult = AjaxResult.error("添加信息失败！不符合数据规则");
            }
        }catch (Exception e){
            ajaxResult =e instanceof TooManyResultsException ? AjaxResult.error(ResultCode.ERROR, "已经存在添加的父节点！") : AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @PutMapping(value = "modify")
    public AjaxResult modify(@RequestBody SysMenu sysMenu){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysMenu.setUpdatetime(date);
            ajaxResult = sysMenuService.updateById(sysMenu)?AjaxResult.success("修改信息成功！"):AjaxResult.error("修改信息失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @DeleteMapping(value = "delete")
    public AjaxResult delete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysMenu> sysMenus = sysMenuService.listByIds(Arrays.asList(ids));
            sysMenus.forEach(item->item.setStatus(false));
            sysMenuService.updateBatchById(sysMenus);
//            ajaxResult = sysMenuService.removeByIds(Arrays.asList(ids))?AjaxResult.success("删除成功！"):AjaxResult.error("删除失败！");
            ajaxResult = AjaxResult.success("已经全部禁用");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
