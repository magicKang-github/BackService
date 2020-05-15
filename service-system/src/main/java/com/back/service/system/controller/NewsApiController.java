package com.back.service.system.controller;

import com.back.service.system.bean.SysNews;
import com.back.service.system.bean.SysNewsType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.Constants;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.oConvertUtils;
import com.back.service.system.service.SysNewsService;
import com.back.service.system.service.SysNewsTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * 新闻管理的路由
 * @author magicHat
 */
@RestController
@RequestMapping("api/news")
public class NewsApiController {

    @Resource
    private SysNewsService sysNewsService;
    @Resource
    private SysNewsTypeService sysNewsTypeService;

    /**
     * 新闻分页查询
     * @param currentPage 当前页面
     * @param size 页面数据大小
     * @param sysNews 查询条件
     * @return 响应结果
     */
    @GetMapping(value = "page")
    public AjaxResult newsPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                               SysNews sysNews){
        AjaxResult ajaxResult;
        try{
            IPage<SysNews> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysNews> lambdaQueryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysNews.getId())){ lambdaQueryWrapper.eq(SysNews::getId,sysNews.getId()); }
            if (oConvertUtils.isNotEmpty(sysNews.getTitle())){lambdaQueryWrapper.like(SysNews::getTitle,sysNews.getTitle());}
            if (oConvertUtils.isNotEmpty(sysNews.getType())){lambdaQueryWrapper.eq(SysNews::getType,sysNews.getType());}
            if (oConvertUtils.isNotEmpty(sysNews.getAuthor())){lambdaQueryWrapper.like(SysNews::getAuthor,sysNews.getAuthor());}
            if (oConvertUtils.isNotEmpty(sysNews.getStatus())){lambdaQueryWrapper.eq(SysNews::getStatus,sysNews.getStatus());}
            else {lambdaQueryWrapper.eq(SysNews::getStatus,Constants.STATUS_TRUE);}
            lambdaQueryWrapper.orderByDesc(SysNews::getUpdatetime);
            IPage<SysNews> iPage = sysNewsService.page(page, lambdaQueryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 添加新闻
     * @param sysNews 添加的新闻数据
     * @return 响应结果
     */
    @PostMapping(value = "insert")
    public AjaxResult newsInsert(@RequestBody SysNews sysNews){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysNews.setStatus(true);
            sysNews.setRecord(0);
            sysNews.setCreatetime(date);
            sysNews.setUpdatetime(date);
            ajaxResult = sysNewsService.save(sysNews)? AjaxResult.success("添加成功！"): AjaxResult.error("添加失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 修改新闻
     * @param sysNews 修改的新闻数据
     * @return 响应结果
     */
    @PutMapping(value = "modify")
    public AjaxResult newsModify(@RequestBody SysNews sysNews){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysNews.setUpdatetime(date);
            ajaxResult = sysNewsService.updateById(sysNews)?AjaxResult.success("修改成功！"):AjaxResult.error("修改失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 删除新闻
     * @param ids 删除的ID集合
     * @return 响应结果
     */
    @DeleteMapping(value = "delete")
    public AjaxResult newsDelete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysNews> list = sysNewsService.listByIds(Arrays.asList(ids));
            list.forEach(item -> item.setStatus(false));
            sysNewsService.updateBatchById(list);
            ajaxResult = AjaxResult.success("删除成功！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 新闻类型分页查询
     * @param currentPage 当前页
     * @param size 页面数据大小
     * @param sysNewsType 查询条件
     * @return 响应结果
     */
    @GetMapping(value = "type/page")
    public AjaxResult newsTypePage(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                                      SysNewsType sysNewsType){
        AjaxResult ajaxResult;
        try{
            IPage<SysNewsType> page = new Page<>(currentPage,size);
            LambdaQueryWrapper<SysNewsType> lambdaQueryWrapper = Wrappers.lambdaQuery();
            if (oConvertUtils.isNotEmpty(sysNewsType.getId())){ lambdaQueryWrapper.eq(SysNewsType::getId,sysNewsType.getId()); }
            if (oConvertUtils.isNotEmpty(sysNewsType.getName())){lambdaQueryWrapper.like(SysNewsType::getName,sysNewsType.getName());}
            if (oConvertUtils.isNotEmpty(sysNewsType.getNote())){lambdaQueryWrapper.like(SysNewsType::getNote,sysNewsType.getNote());}
            if (oConvertUtils.isNotEmpty(sysNewsType.getStatus())){lambdaQueryWrapper.eq(SysNewsType::getStatus,sysNewsType.getStatus());}
            else {lambdaQueryWrapper.eq(SysNewsType::getStatus,Constants.STATUS_TRUE);}
            lambdaQueryWrapper.orderByDesc(SysNewsType::getUpdatetime);
            IPage<SysNewsType> iPage = sysNewsTypeService.page(page, lambdaQueryWrapper);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 获取全部新闻类型
     * @return 响应结果
     */
    @GetMapping(value = "type/list")
    public AjaxResult newsTypeList(){
        AjaxResult ajaxResult;
        try{
            ajaxResult = AjaxResult.success("成功！",
                    sysNewsTypeService.list(Wrappers.<SysNewsType>lambdaQuery().eq(SysNewsType::getStatus,Constants.STATUS_TRUE)));
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 根据编号获取新闻类型
     * @param id 新闻类型编号
     * @return 响应结果
     */
    @GetMapping(value = "type/id/{id}")
    public AjaxResult newsTypeById(@PathVariable Integer id){
        AjaxResult ajaxResult;
        try{
            ajaxResult = AjaxResult.success("成功！",
                    sysNewsTypeService.getOne(Wrappers.<SysNewsType>lambdaQuery().eq(SysNewsType::getId,id).eq(SysNewsType::getStatus, Constants.STATUS_TRUE)));
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 添加新闻类型
     * @param sysNewsType 添加的数据
     * @return 响应结果
     */
    @PostMapping(value = "type/insert")
    public AjaxResult newsTypeInsert(@RequestBody SysNewsType sysNewsType){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysNewsType.setCreatetime(date);
            sysNewsType.setUpdatetime(date);
            ajaxResult = sysNewsTypeService.save(sysNewsType)? AjaxResult.success("添加成功！"): AjaxResult.error("添加失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 修改新闻类型
     * @param sysNewsType 修改的数据
     * @return 响应结果
     */
    @PutMapping(value = "type/modify")
    public AjaxResult newsTypeModify(@RequestBody SysNewsType sysNewsType){
        AjaxResult ajaxResult;
        try{
            Date date = new Date();
            sysNewsType.setUpdatetime(date);
            ajaxResult = sysNewsTypeService.updateById(sysNewsType)?AjaxResult.success("修改成功！"):AjaxResult.error("修改失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 删除新闻类型
     * @param ids ID集合
     * @return 响应结果
     */
    @DeleteMapping(value = "type/delete")
    public AjaxResult newsTypeDelete(@RequestBody Integer[] ids){
        AjaxResult ajaxResult;
        try{
            Collection<SysNewsType> list = sysNewsTypeService.listByIds(Arrays.asList(ids));
            list.forEach(item -> item.setStatus(false));
            sysNewsTypeService.updateBatchById(list);
            ajaxResult = AjaxResult.success("删除成功！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
