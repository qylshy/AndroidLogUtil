package com.qyl.logdemo;

import android.app.Application;

import com.qyl.log.LogConfig;
import com.qyl.log.LogUtil;

/**
 * Created by qiuyunlong on 16/6/15.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogConfig logConfig = new LogConfig.Builder(getApplicationContext())
                .setLogLevel(LogUtil.VERBOSE)
                .setNeedSaveToFile(true)
                .addSaveRule("MainActivity", true)
                .addSaveRule("ImageDetailActivity", true)
                .addSaveRule("ImageDetailActivity_", true)
                .build();
        LogUtil.init(logConfig);
    }
}
