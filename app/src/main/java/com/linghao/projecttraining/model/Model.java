package com.linghao.projecttraining.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by linghao on 2017/9/6.
 * QQ：782695971
 * 作用：
 */

public class Model {
    private static Model model=new Model();
    //线程池
    private ExecutorService excutor= Executors.newCachedThreadPool();
    // 私有化构造
    private Model() {

    }
    //获取单例对象
    public static Model getInstance() {
        return model;
    }
    //获取全局线程池
    public ExecutorService getGlobalThreadPool(){
        return excutor;
    }
}
