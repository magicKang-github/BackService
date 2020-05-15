package com.back.service.system.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 关联系统角色和系统菜单功能
 * @author magicHat
 */
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu {

    private Integer rid;
    private Integer mid;
}
