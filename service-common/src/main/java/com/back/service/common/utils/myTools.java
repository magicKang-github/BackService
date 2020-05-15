package com.back.service.common.utils;

import java.util.HashSet;
import java.util.List;

/**
 * @author magicHat
 */
public class myTools {

    public static HashSet<Integer> getRepeatInteger(List<Integer> list){
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> repeatSet = new HashSet<>();
        list.forEach(item -> {
            if (set.contains(item)){
                repeatSet.add(item);
            }else{
                set.add(item);
            }
        });
        return repeatSet;
    }
}
