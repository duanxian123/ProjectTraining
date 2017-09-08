package com.linghao.projecttraining.utils;

import android.content.Context;

/**
 * Created by linghao on 2017/6/25.
 * 作用：px和do互相转换
 */

public class DensityUtil {
    /**
     * 根据手机屏幕从dip的单位转化为px（像素）
     */
    public  static int dip2px(Context context,float dpvalue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dpvalue*scale+0.5f);
    }
    /**
     * 根据手机屏幕从px（像素）的单位转化为dip
     */
    public  static int px2dip(Context context,float pxvalue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int) (pxvalue/scale+0.5f);
    }


}
