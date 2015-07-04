package com.morningsidevc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author float.lu
 */
public class DateTimeUtils {
    //Date类型时间格式化 格式：yyyy-MM-dd HH:mm:ss
    public static String date2StringWizMin(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


    //Date类型时间格式化 格式：yyyy-MM-dd
    public static String date2StringWizOutMin(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    //通用Date类型格式化 后面给改进实现
    public static String date2SmartFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
