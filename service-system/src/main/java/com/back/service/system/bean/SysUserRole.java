package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 关联系统用户和角色
 * @author magicHat
 */
@Data
@TableName(value = "sys_user_role")
public class SysUserRole {

    private Integer uid;
    private Integer rid;
}
