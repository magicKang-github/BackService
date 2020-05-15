package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 新闻主体
 * @author magicHat
 */
@Data
@TableName(value = "sys_news")
public class SysNews {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer type;
    private Integer record;
    private String author;
    private String images;
    private String keyword;
    private String summary;
    private String content;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date updatetime;
}
