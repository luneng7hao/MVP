package com.mvp.util.other;

import android.util.Log;

/**
 * 封装Log类,便于统一输出日志,等版本上线之后可关闭日志输出.
 * 可设置level来进行输出
 * VERBOSE:1,DEBUG:2,INFO:3,WARN:4,ERROR:5,NOTHING:6
 * @author 王超勇
 * @version 1.0 2015-5-5
 */ 
public class UtilLog {

	/** 全局TAG */
	private static final String TAG = "nsyh_bl";

	/** 输出verbose级别之上的日志 */
	private static final int VERBOSE = 1;

	/** 输出debug级别之上的日志 */
	private static final int DEBUG = 2;

	/** 输出info级别之上的日志 */
	private static final int INFO = 3;

	/** 输出warn级别之上的日志 */
	private static final int WARN = 4;

	/** 输出error级别之上的日志 */
	private static final int ERROR = 5;
	
	/** 关闭日志输出 */
	@SuppressWarnings("unused")
	private static final int NOTHING = 6;

	public static int LEVEL = VERBOSE;
	
	private UtilLog() {

	}
	
	public static void v(String tag, String msg){
		if(LEVEL <= VERBOSE ){
			Log.v(TAG, tag + "--" + msg);
		}
	}

	public static void d(String tag, String msg){
		if(LEVEL <= DEBUG ){
			Log.d(TAG, tag + "--" + msg);
		}
	}
	
	public static void i(String tag, String msg){
		if(LEVEL <= INFO ){
			Log.i(TAG, tag + "--" + msg);
		}
	}
	
	public static void w(String tag, String msg){
		if(LEVEL <= WARN ){
			Log.w(TAG, tag + "--" + msg);
		}
	}
	
	public static void e(String tag, String msg){
		if(LEVEL <= ERROR ){
			Log.e(TAG, tag + "--" + msg);
		}
	}
	
}
