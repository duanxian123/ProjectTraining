package com.linghao.projecttraining.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by linghao on 2017/6/25.
 */
//缓存软件参数

public class CacheUtils {

    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sp=context.getSharedPreferences("linghao",Context.MODE_PRIVATE);

        return sp.getBoolean(key,false);
    }

    /**
     * 保存软件的参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp=context.getSharedPreferences("linghao",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static void putString(Context context, String key, String value) {
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            //保存在/mnt/sdcard/beijingnews
//
//            try {
//
//                ///mnt/sdcard/beijingnews/llkskljskljklsjklsllsl
//                File file = new File(Environment.getExternalStorageDirectory()+"/ProjectTraining/file",key);
//
//                File parentFile =  file.getParentFile();//mnt/sdcard/beijingnews
//                if(!parentFile.exists()){
//                    //创建目录
//                    parentFile.mkdirs();
//                }
//                if(!file.exists()){
//                    file.createNewFile();
//                }
//                //保存文本
//                FileOutputStream fileOutputStream=new FileOutputStream(file);
//                fileOutputStream.write(value.getBytes());
//                fileOutputStream.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogUtil.e("文本缓存失败");
//            }
//        }else {
//            SharedPreferences sp = context.getSharedPreferences("linghao", Context.MODE_PRIVATE);
//            sp.edit().putString(key, value).commit();
      SharedPreferences sp = context.getSharedPreferences("linghao", Context.MODE_PRIVATE);
          sp.edit().putString(key, value).commit();
//        }
    }

    public static String getString(Context context, String key) {
//        String result="";
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            try {
//
//                File file = new File(Environment.getExternalStorageDirectory()+"/ProjectTraining/file",key);
//                if(file.exists()){
//
//                    FileInputStream is = new FileInputStream(file);
//                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
//                    byte []buffer=new byte[1024];
//                    int length;
//                    while((length=is.read(buffer))!=-1){
//                        stream.write(buffer,0,length);
//                    }
//                    is.close();
//                    stream.close();
//                    result=stream.toString();
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogUtil.e("文本获取失败");
//            }
//        }else{
//        SharedPreferences sp=context.getSharedPreferences("linghao",Context.MODE_PRIVATE);
//       result=sp.getString(key,"");}
//        return result;
        SharedPreferences sp=context.getSharedPreferences("linghao",Context.MODE_PRIVATE);
                      return sp.getString(key,"");
    }
    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp=context.getSharedPreferences("linghao",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sp=context.getSharedPreferences("linghao",Context.MODE_PRIVATE);
        return sp.getInt(key,100);
    }
}
