package com.mvp.util.other;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Asion on 2018/1/16.
 *  Activity 栈管理器，可用于退出单个activity、退出全部activity等
 */

public class UtilActivityStackManager {

    private Stack<Activity> mActivityStack;

    /**
     * 单例模式
     */
    public static UtilActivityStackManager getInstance() {
        return UtilActivityStackManager.SingletonHolder.instance;
    }
    //私有内部类里定义UtilActivityStackManager 实例
    //final 类型确保实例唯一
    private static class SingletonHolder {
        private static final UtilActivityStackManager instance = new UtilActivityStackManager();
    }
    //定义私有UtilActivityStackManager方法，确保外部不能直接实例化UtilActivityStackManager对象
    private UtilActivityStackManager() {
    }

    /**
     *  将单个Activity加入Activity栈中
     */
    public void addActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.push(activity);
    }
    /**
     * 把栈顶的activity删除
     *
     * @param activity
     */
    public void deleteActivity(Activity activity) {
        if (activity == null || UtilCollection.isEmpty(mActivityStack)) {
            return;
        }
        //pop()会把栈顶的值删除
        mActivityStack.pop();
    }
    /**
     * 返回栈顶的activity，遵循先进后出原则
     *
     * @return
     */
    public Activity topActivity() {
        Activity activity = null;
        if (!UtilCollection.isEmpty(mActivityStack)) {
            activity = mActivityStack.peek();
        }
        return activity;
    }
    /**
     * 获取栈底(第一个压住栈)的activity，遵循先进后出原则
     *
     * @return
     */
    public Activity firstActivity() {
        Activity activity = null;
        if (!UtilCollection.isEmpty(mActivityStack)) {
            activity = mActivityStack.firstElement();
        }
        return activity;
    }

    /**
     * 获取activity的数量
     *
     * @return
     */
    public int getStackSize() {
        if (mActivityStack != null) {
            return mActivityStack.size();
        }
        return 0;
    }
    /**
     *退出当前的activity
    * @param activity
    */
    public void exitActivity(Activity activity) {
        activity.finish();
        mActivityStack.remove(activity);
    }
    /**
     * 退出类型为class的Activity
     * @param cls
     */
    public void exitActivity(Class cls) {
        if (UtilCollection.isEmpty(mActivityStack)) {
            return;
        }

        Iterator<Activity> iterator = mActivityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity == null) {
                continue;
            }
            if (activity.getClass().equals(cls)) {
                exitActivity(activity);
            }
        }
    }

    /**
     * 退出类型为cls且第后一个进栈的Activity
     * @param cls
     */
    public void exitActivityByLastIn(Class cls) {
        if (UtilCollection.isEmpty(mActivityStack)) {
            return;
        }

        int size = mActivityStack.size();
        for (int i = size - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if (activity == null) {
                continue;
            }
            if (activity.getClass().equals(cls)) {
                activity.finish();
                mActivityStack.removeElementAt(mActivityStack.lastIndexOf(activity));
                break;
            }
        }
    }
    /**
     * 退出除当前activity以外的所有Activity
     *
     * @param cls 页面activity的类型
     */
    public void exitAllActivityExceptCurrent(Class cls) {
        Activity activity;
        while (true) {
            activity = firstActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                if (getStackSize() <= 1) {
                    break;
                } else continue;
            }
            exitActivity(activity);
        }
    }
    /**
     * 退出应用程序
     */
    public void exitApplication() {
        while (true) {
            Activity activity = firstActivity();
            if (activity == null) {
                break;
            }
            exitActivity(activity);
        }
    }

}
