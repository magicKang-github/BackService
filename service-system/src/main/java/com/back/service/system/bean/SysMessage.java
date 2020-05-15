package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 反馈消息
 * @author magicHat
 */
@Data
@TableName(value = "sys_message")
public class SysMessage {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer type;
    private String phone;
    private String email;
    private String title;
    private String message;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date updatetime;
}
