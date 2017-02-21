package com.qf.day02_xutils.demo06;

import android.content.Context;

import org.xutils.DbManager;

/**
 * Created by Administrator on 2017/2/21.
 */

public class DbUtils {

    static DbManager.DaoConfig daoConfig;


    public  static DbManager.DaoConfig getDaoConfig(Context context)
    {

        daoConfig = new DbManager.DaoConfig();

        daoConfig.setDbName("person.db")//设置数据库的名称
                  .setDbDir(context.getExternalCacheDir())//设置数据库存放的路径
                  .setDbVersion(1)//设置数据库的版本号
                  .setAllowTransaction(true)//设置数据库是否使用事务
                  .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                      @Override
                      public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                      }
                  })
        ;

        return  daoConfig;
    }

}
