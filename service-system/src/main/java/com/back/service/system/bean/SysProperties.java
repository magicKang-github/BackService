package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 系统属性
 * @auther magicHat
 */
@Data
@TableName(value = "sys_properties")
public class SysProperties {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "sys_key")
    private String skey;
    @TableField(value = "sys_value")
    private String value;
    private String note;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date updatetime;
}
