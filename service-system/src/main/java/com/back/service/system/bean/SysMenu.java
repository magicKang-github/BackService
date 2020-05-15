package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 系统菜单
 * @author magicHat
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String title;
    private String icon;
    private String path;
    private Integer pid;
    private String pname;
    private Boolean status;
    private String component;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date updatetime;
}
