package com.mvp.util.other;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * Created by 安卓的异常处理
 *   常常由于开发中还有未解决的异常问题，但是我们不能及时重现。
 *   这里我们将捕获的异常信息预留下来
 *   在Applicationzhon中onCreate方法中调用
 */
public class UtilUncaughtExceptionHandler  implements  Thread.UncaughtExceptionHandler{
    private static final String LOGTAG = "DroidUncaughtExceptionHandler";
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;
    private Context mAppContext;
    private static UtilUncaughtExceptionHandler instance;

    private UtilUncaughtExceptionHandler(Context context) {
        mAppContext = context;
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    //单例模式，保证实例唯一性
    public static UtilUncaughtExceptionHandler getInstance(Context context) {
        UtilUncaughtExceptionHandler exceptionHandler = instance;
        if (exceptionHandler == null) {
            synchronized (UtilUncaughtExceptionHandler.class) {
                exceptionHandler = instance;
                if (exceptionHandler == null) {
                    exceptionHandler = new UtilUncaughtExceptionHandler(context);
                    instance = exceptionHandler;
                }
            }
        }
        return exceptionHandler;
    }
    //获取进程
    public static String getProcessName(Context appContext) {
        String currentProcessName = "";
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                currentProcessName = processInfo.processName;
                break;
            }
        }
        return currentProcessName;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String processName =  getProcessName(mAppContext);
        if (mAppContext.getPackageName().equals(processName)) {
			//主进程的异常返回
            Log.i(LOGTAG, "uncaughtException main process");
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        } else {
			//非出进程的异常直接kill掉
            Log.i(LOGTAG, "uncaughtException process name=" + processName);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
