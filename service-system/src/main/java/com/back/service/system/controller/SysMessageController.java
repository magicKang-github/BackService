package com.back.service.system.controller;

import com.back.service.system.service.SysMessageService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.Constants;
import com.back.service.common.constant.ResultCode;
import com.back.service.system.bean.SysMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息控制器
 * @auther magicHat
 */
@RestController
@RequestMapping(value = "message")
public class SysMessageController {

    @Resource
    private SysMessageService sysMessageService;

    /**
     * 消息页面初始化
     * @return
     */
    @GetMapping(value = "init")
    public AjaxResult getMessageInit(){
        AjaxResult ajaxResult;
        try{
            // TODO 这里可以进行算法优化，通过三次查询数据库很麻烦，可以一次查询出来再处理！
            List<SysMessage> unRead = sysMessageService.list(Wrappers.<SysMessage>lambdaQuery()
                    .eq(SysMessage::getType, Constants.MESSAGE_UNREAD)
                    .eq(SysMessage::getStatus,Constants.STATUS_TRUE)
                    .orderByDesc(SysMessage::getUpdatetime)
            );
            List<SysMessage> readed = sysMessageService.list(Wrappers.<SysMessage>lambdaQuery()
                    .eq(SysMessage::getType, Constants.MESSAGE_READED)
                    .eq(SysMessage::getStatus,Constants.STATUS_TRUE)
                    .orderByDesc(SysMessage::getUpdatetime)
            );
            List<SysMessage> trash = sysMessageService.list(Wrappers.<SysMessage>lambdaQuery()
                    .eq(SysMessage::getType, Constants.MESSAGE_TRASH)
                    .eq(SysMessage::getStatus,Constants.STATUS_TRUE)
                    .orderByDesc(SysMessage::getUpdatetime)
            );
            Map<String,Object> map = new HashMap<>();
            map.put("unread",unRead);
            map.put("readed",readed);
            map.put("trash",trash);
            ajaxResult = AjaxResult.success("成功！",map);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 根据消息ID获取消息内容
     * @param msg_id
     * @return
     */
    @GetMapping("content")
    public AjaxResult getContentByMsgId(@RequestParam Integer msg_id){
        AjaxResult ajaxResult;
        try{
            ajaxResult = AjaxResult.success("获取消息成功！",sysMessageService.getById(msg_id));
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 根据消息ID将消息设置为已读
     * @param msg_id
     * @return
     */
    @GetMapping("has_read")
    public AjaxResult hasRead(@RequestParam Integer msg_id){
        AjaxResult ajaxResult;
        try{
            SysMessage sysMessage = sysMessageService.getById(msg_id);
            sysMessage.setType(Constants.MESSAGE_READED);
            ajaxResult = sysMessageService.updateById(sysMessage)?AjaxResult.success("成功！"):AjaxResult.error("失败，请跟管理员联系！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 根据消息ID将已读消息放至回收站
     * @param msg_id
     * @return
     */
    @GetMapping("remove_readed")
    public AjaxResult removeReaded(@RequestParam Integer msg_id){
        AjaxResult ajaxResult;
        try{
            SysMessage sysMessage = sysMessageService.getById(msg_id);
            sysMessage.setType(Constants.MESSAGE_TRASH);
            ajaxResult = sysMessageService.updateById(sysMessage)?AjaxResult.success("成功！"):AjaxResult.error("失败，请跟管理员联系！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 将回收站消息恢复至已读
     * @param msg_id
     * @return
     */
    @GetMapping("restore")
    public AjaxResult restoreTrash(@RequestParam Integer msg_id){
        AjaxResult ajaxResult;
        try{
            SysMessage sysMessage = sysMessageService.getById(msg_id);
            sysMessage.setType(Constants.MESSAGE_READED);
            ajaxResult = sysMessageService.updateById(sysMessage)?AjaxResult.success("成功！"):AjaxResult.error("失败，请跟管理员联系！");
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }

    /**
     * 获取未读消息的记录数
     * @return
     */
    @GetMapping("count")
    public AjaxResult getUnreadCount(){
        AjaxResult ajaxResult;
        try{
            ajaxResult = AjaxResult.success("成功！",sysMessageService.count(Wrappers.<SysMessage>lambdaQuery().eq(SysMessage::getStatus,Constants.STATUS_TRUE).eq(SysMessage::getType,Constants.MESSAGE_UNREAD)));
        }catch (Exception e){
            ajaxResult = AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }
}
