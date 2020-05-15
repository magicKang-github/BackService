package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 系统登录日志
 * @author magicHat
 */
@Data
@TableName(value = "sys_login")
public class SysLoginLog {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private Integer cid;
    private String cip;
    private String cname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createtime;
    private SysUser sysUser;
}
