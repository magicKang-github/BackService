package com.back.service.system.controller;

import com.back.service.system.bean.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.AddressUtils;
import com.back.service.common.utils.IpUtils;
import com.back.service.system.service.SysLoginLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @auther magicHat
 */
@RestController
@RequestMapping(value = "sysLoginLog")
public class SysLoginLogController {

    @Resource
    private SysLoginLogService sysLoginLogService;

    /**
     * 记录后台登录信息
     * @param id
     * @param request
     * @return
     */
    @PostMapping(value = "log/{id}")
    public AjaxResult loginLog(@PathVariable Integer id, HttpServletRequest request){
        AjaxResult ajaxResult;
        try{
            SysLoginLog sysLoginLog = new SysLoginLog();
            String ip = IpUtils.getIpAddr(request);
            String address = AddressUtils.getRealAddressByIP(ip);
            if (address.indexOf(",")>0){
                String[] str = address.split(",");
                sysLoginLog.setCname(str[0]);
                sysLoginLog.setCid(Integer.parseInt(str[1]));
            }else{
                sysLoginLog.setCname(address);
                sysLoginLog.setCid(0);
            }
            sysLoginLog.setCip(ip);
            sysLoginLog.setUid(id);
            sysLoginLog.setCreatetime(new Date());
            ajaxResult = sysLoginLogService.save(sysLoginLog)?AjaxResult.success("成功！"):AjaxResult.error("失败！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    @GetMapping(value = "page")
    public AjaxResult page(@RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage,
                           @RequestParam(value = "pageSize", defaultValue = "6") Integer size,
                           SysLoginLog sysLoginLog){
        AjaxResult ajaxResult;
        try{
            Page<SysLoginLog> page = new Page<>(currentPage,size);
            IPage iPage = sysLoginLogService.selectPage(page, sysLoginLog);
            ajaxResult = AjaxResult.success("成功！",iPage);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
