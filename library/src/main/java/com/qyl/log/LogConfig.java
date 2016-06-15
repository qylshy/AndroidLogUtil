package com.qyl.log;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by qiuyunlong on 16/6/13.
 */
public final class LogConfig {

    public Context mContext;

    public int logLevel;  //日志级别

    public boolean needSaveToFile;  //是否保存到文件 总开关

    public String dirPath;         //保存文件路径

    public String prefix;          //文件名前缀

    public String suffix;          //文件名后缀

    public String defaultTag;      //默认保存模块

    public HashMap<String, Boolean> saveConfigMap;


    private LogConfig(Builder builder) {
        mContext = builder.context;
        logLevel = builder.logLevel;
        needSaveToFile = builder.needSaveToFile;
        dirPath = builder.dirPath;
        prefix = builder.prefix;
        suffix = builder.suffix;
        defaultTag = builder.defaultTag;
        saveConfigMap = builder.saveConfigMap;
    }

    public boolean isNeedSaveToFile(String tag) {
        if (saveConfigMap != null && saveConfigMap.size() > 0){
            if (saveConfigMap.containsKey(tag) && saveConfigMap.get(tag)){
                return true;
            }
        }
        return false;

    }

    public static class Builder {
        private Context context;
        private int logLevel;
        private boolean needSaveToFile;
        private String dirPath;         //保存文件路径
        private String prefix;          //文件名前缀
        private String suffix;          //文件名后缀
        private String defaultTag;      //默认保存模块
        private HashMap<String, Boolean> saveConfigMap;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setLogLevel(int logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder setNeedSaveToFile(boolean needSaveToFile) {
            this.needSaveToFile = needSaveToFile;
            return this;
        }

        public Builder dirPath(String dirPath){
            this.dirPath = dirPath;
            return this;
        }

        public Builder prefix(String prefix){
            this.prefix = prefix;
            return this;
        }

        public Builder suffix(String suffix){
            this.suffix = suffix;
            return this;
        }

        public Builder defaultTag(String tag){
            this.defaultTag = tag;
            return this;
        }

        public Builder saveConfig(HashMap hashMap){
            this.saveConfigMap = hashMap;
            return this;
        }

        public Builder addSaveRule(String tag, boolean needSave){
            if (this.saveConfigMap == null){
                this.saveConfigMap = new HashMap<>();
            }
            this.saveConfigMap.put(tag, needSave);
            return this;
        }

        public LogConfig build(){
            initEmptyFieldsWithDefaultValues();
            return new LogConfig(this);
        }

        private void initEmptyFieldsWithDefaultValues() {
            if (logLevel == 0) {
                logLevel = Log.ASSERT;
            }

            if (TextUtils.isEmpty(dirPath)) {
                dirPath = DefaultConfigFactory.createDirPath(context);
            }

            if (TextUtils.isEmpty(prefix)) {
                prefix = DefaultConfigFactory.createPrefix(context);
            }

            if (TextUtils.isEmpty(suffix)) {
                suffix = DefaultConfigFactory.createSuffix();
            }

            if (TextUtils.isEmpty(defaultTag)) {
                defaultTag = DefaultConfigFactory.createDefaultTag();
            }

            if (saveConfigMap == null){
                saveConfigMap = new HashMap<>();
            }

        }
    }

    public static class DefaultConfigFactory {

        public static final String DEFAULT_TAG = "default";
        public static final String DEFAULT_SUFFIX = ".log";

        public static String createDirPath(Context context) {
            return "/" + getAppName(context);
        }

        public static String getAppName(Context context) {
            PackageManager packageManager = null;
            ApplicationInfo applicationInfo = null;
            try {
                packageManager = context.getApplicationContext().getPackageManager();
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                applicationInfo = null;
            }
            String applicationName =
                    (String) packageManager.getApplicationLabel(applicationInfo);
            return applicationName;
        }

        public static String createPrefix(Context context) {
            return "-" + getAppName(context) + "-" ;
        }

        public static String createSuffix() {
            return DEFAULT_SUFFIX;
        }

        public static String createDefaultTag() {
            return DEFAULT_TAG;
        }

    }
}
