package com.qf.day02_xutils.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/21.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化xutils的使用内容
        x.Ext.init(this);

        x.Ext.setDebug(true);//是否需要输出日志
    }
}
