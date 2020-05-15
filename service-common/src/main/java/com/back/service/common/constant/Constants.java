package com.back.service.common.constant;

/**
 * 通用常量信息
 * 
 * @author magicHat
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static String IS_ASC = "isAsc";



    /**
     * 查询不到结果的ID值
     */
    public static Integer PRODUCT_NONE_ID = 0;

    /**
     * 状态标志位True
     */
    public static char STATUS_TRUE = '1';

    /**
     * 状态标志位False
     */
    public static char STATUS_FALSE = '0';

    /**
     * 微信小程序首页产品展示数量大小
     */
    public static final long INDEX_PRODUCT_PAGE_SIZE = 6;
    /**
     * 微信小程序首页新闻数量大小
     */
    public static final long INDEX_NEWS_PAGE_SIZE = 4;
    /**
     * 系统属性之微信小程序通知消息标志
     */
    public static String WECHAT_INDEX_NOTICE = "indexNotice";
    /**
     * 系统属性之微信小程序广告图片标志
     */
    public static String WECHAT_INDEX_IMAGE = "indexImage";
    /**
     * 系统属性之地图KEY
     */
    public static String WEB_MAP_KEY = "mapKey-Web";
    /**
     * 系统属性之地图KEY
     */
    public static String WECHAT_MAP_KEY = "mapKey-WeChat";

    /**
     * 系统图片之轮播图标志
     */
    public static String IMAGES_SWIPER = "1";

    /**
     * 系统图片之产品图片标志
     */
    public static String IMAGES_PRODUCT = "2";

    /**
     * 系统图片之微信小程序轮播图标志
     */
    public static String IMAGES_SWIPER_WECHAT = "3";
    /**
     * 系统图片之微信小程序活动图标志
     */
    public static String IMAGES_WECHAT_ACTIVITY = "4";
    /**
     * 系统图片之微信小程序关于我们背景图标志
     */
    public static String IMAGES_WECHAT_ABOUT = "5";
    /**
     * 消息类型：未读消息
     */
    public static Integer MESSAGE_UNREAD = 1;
    /**
     * 消息类型：已读消息
     */
    public static Integer MESSAGE_READED = 2;
    /**
     * 回收站消息
     */
    public static Integer MESSAGE_TRASH = 3;
}
