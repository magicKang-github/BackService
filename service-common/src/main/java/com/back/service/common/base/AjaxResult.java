package com.back.service.common.base;

import com.back.service.common.constant.ResultCode;

import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @author magicHat
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AjaxResult()
    {
    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static AjaxResult error()
    {
        return error(ResultCode.ERROR, "操作失败", null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg)
    {
        return error(ResultCode.ERROR, msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg)
    {
        return error(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg 内容
     * @param object 错误数据
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg, Object object)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", object);
        json.put("status", false);
        return json;
    }

    /**
     * 返回成功消息
     * 
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        return success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @param object 成功数据
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object object)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", ResultCode.SUCCESS);
        json.put("data", object);
        json.put("status", true);
        return json;
    }
    
    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return success("操作成功",null);
    }

    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
