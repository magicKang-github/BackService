package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 统计记录
 * @author magicHat
 */
@Data
@TableName(value = "sys_action")
public class SysAction {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String ip;
    private Integer page;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createtime;
}
