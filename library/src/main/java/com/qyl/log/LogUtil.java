package com.qyl.log;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by qiuyunlong on 16/6/13.
 */
public class LogUtil {

    public static final int VERBOSE = 2;

    public static final int DEBUG = 3;

    public static final int INFO = 4;

    public static final int WARN = 5;

    public static final int ERROR = 6;

    public static final int ASSERT = 7;

    public static String TAG = "LOG";
    public static boolean DEBUGB = BuildConfig.DEBUG;
    private static LogConfig config;

    private LogUtil() {
    }

    public static void init(LogConfig logConfig){
        config = logConfig;
    }

    public static void v(String msg) {
        if (isLoggable(Log.VERBOSE)) {
            String tag = generateTag();
            Log.v(tag, msg);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (isLoggable(Log.VERBOSE)) {
            Log.v(tag, msg);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isLoggable(Log.VERBOSE)) {
            Log.v(tag, msg, tr);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void d(String s) {
        if (isLoggable(Log.DEBUG)) {
            String tag = generateTag();
            Log.d(tag, s);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, s);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (isLoggable(Log.DEBUG)) {
            Log.d(tag, msg);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isLoggable(Log.DEBUG)) {
            Log.d(tag, msg, tr);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void i(String msg) {
        if (isLoggable(Log.INFO)) {
            String tag = generateTag();
            Log.i(tag, msg);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }

        }
    }

    public static void i(String tag, String msg) {
        if (isLoggable(Log.INFO)) {
            Log.i(tag, msg);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void i(String msg, Throwable t) {
        if (isLoggable(Log.INFO)) {
            String tag = generateTag();
            Log.i(tag, msg, t);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isLoggable(Log.INFO)) {
            Log.i(tag, msg, tr);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void w(String msg) {
        if (isLoggable(Log.WARN)) {
            String tag = generateTag();
            Log.w(tag, msg);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (isLoggable(Log.WARN)){
            Log.w(tag, msg);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void w(String tag, Throwable tr) {
        if (isLoggable(Log.WARN)) {
            Log.w(tag, tr);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, parseException(tr));
            }
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (isLoggable(Log.WARN)){
            Log.w(tag, msg, tr);
            if (config.isNeedSaveToFile(tag)) {
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void e(String msg) {
        if (isLoggable(Log.ERROR)) {
            String tag = generateTag();
            Log.e(tag, msg);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (isLoggable(Log.ERROR)){
            Log.e(tag, msg);
            if (config.needSaveToFile){
                FileWriterHelper.getInstance().writeLog(tag, msg);
            }
        }
    }

    public static void e(String msg, Throwable t) {
        if (isLoggable(Log.ERROR)) {
            String tag = generateTag();
            Log.e(tag, msg, t);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
                FileWriterHelper.getInstance().writeLog(tag, parseException(t));
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isLoggable(Log.ERROR)){
            Log.e(tag, msg, tr);
            if (config.isNeedSaveToFile(tag)){
                FileWriterHelper.getInstance().writeLog(tag, msg);
                FileWriterHelper.getInstance().writeLog(tag, parseException(tr));
            }
        }
    }


    public static boolean isLoggable(int level) {
        return level >= config.logLevel;
    }

    public static String parseException(Throwable ex) {
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printStream);
                cause = cause.getCause();
            }
            String exStr = new String(data);
            sb.append(exStr).append("\t");
        } catch (Exception e) {
            // ignore Exception
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                // ignore Exception
            }
        }
        return sb.toString();
    }

    public static String generateTag() {
        String tag;
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String fileName = stackTrace[4].getFileName();
            tag = fileName.substring(0, fileName.length() - 5);

        }catch (Exception e){
            e.printStackTrace();
            tag = TAG;
        }
        if (TextUtils.isEmpty(tag)){
            tag = TAG;
        }

        return tag;
    }

    static final class FileWriterHelper {
        private OutputStream output;
        private File logFile;

        private static FileWriterHelper INSTANCE = null;

        private FileWriterHelper() {
        }

        public static FileWriterHelper getInstance() {
            if (INSTANCE == null){
                INSTANCE = new FileWriterHelper();
            }
            return INSTANCE;
        }

        /**
         *
         * @param dir 打开指定文件夹
         * @param model  指定模块名
         * @return
         */
        private boolean open(String dir, String model){
            try {
                String path = generateDir(dir);
                if (TextUtils.isEmpty(path))
                    return false;
                File logPath = new File(path);
                if (!logPath.exists()) {
                    logPath.mkdirs();
                }

                String logFileName = generateLogFileName(path, model);

                logFile = new File(logFileName);
                if (!logFile.exists())
                    logFile.createNewFile();

                output = new FileOutputStream(logFile, true);
            }catch (IOException e){
                return false;
            }catch (Exception e){
                return false;
            }
            return output != null;
        }

        private String generateDir(String dir) {
            String path;
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return null;
            } else {
                path = Environment.getExternalStorageDirectory().toString() +dir +"/log/";
            }
            return path;
        }

        private String generateLogFileName(String dir,String model) {
            String logFileName;
            String sDateStr = getTimeStr("yyyy-MM-dd");
            if (TextUtils.isEmpty(model)) {
                model = config.defaultTag;
            }
            logFileName = dir + sDateStr + config.prefix + model + config.suffix;
            return logFileName;
        }

        public void close() {
            try {
                if (null != output) {
                    output.close();
                    output = null;
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        public synchronized void writeLog(String model, String str) {
            try {
                close();
                if (output == null) {
                    if (!open(config.dirPath, model)){
                        return;
                    }
                }
                if (null != output) {
                    String content = "[" + getTimeStr("yyyy-MM-dd HH:mm:ss.SSS") + "] " + str + "\r\n";
                    output.write(content.getBytes("UTF-8"));
                    output.flush();
                }
                close();
            } catch (UnsupportedEncodingException ee) {
            } catch (IOException ioe) {
            }
        }

        private String getTimeStr(String format) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Calendar calendarLocal = Calendar.getInstance();
            return dateFormat.format(calendarLocal.getTime());
        }

    }

}
