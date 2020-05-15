package com.back.service.common.beetlFunction;

/**
 * @author magicHat
 */
public class GetPageNum {

    public static int pageNum(Integer size, Integer total){
        return (int) Math.ceil(total*1.0/size);
    }
}
