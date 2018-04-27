package com.amiba.frame.androidframe.base;

import android.app.Activity;

import java.util.Stack;

/**
 * 界面管理类
 * 用单例模式来管理在栈里的Activity
 *
 * @author Linjiaodu
 */
public class MyActivityManager {
    //存放Activity的栈
    private final static Stack<Activity> mActivityStack = new Stack<>();

    private static MyActivityManager instance;


    private MyActivityManager() {

    }

    /**
     * 获取MyActivityManager的对象
     *
     * @return MyActivityManager的对象
     */
    public static MyActivityManager getMyActivityManager() {
        if (instance == null) {
            synchronized (MyActivityManager.class) {
                if (instance == null) {
                    instance = new MyActivityManager();
                }
            }
        }
        return instance;
    }

    public void pop() {
        //获取栈顶Activity
        synchronized (mActivityStack) {
            Activity activity = mActivityStack.lastElement();
            if (activity != null) {
                mActivityStack.pop();
            }
        }
    }

    /**
     * pop出Activity,但是不finish
     *
     * @param activity
     */
    public void pop(Activity activity) {
        //将指定的Activity Finnish
        if (activity != null) {
            synchronized (mActivityStack) {
                mActivityStack.remove(activity);
            }
        }
    }

    /**
     * pop出栈顶，相当于finnish()
     */
    public void popActivity() {
        //获取栈顶Activity
        synchronized (mActivityStack) {
            Activity activity = mActivityStack.lastElement();
            if (activity != null) {
                //如果Activity不为空则Finnish掉
                activity.finish();
                activity = null;
                mActivityStack.pop();
            }
        }
    }

    /**
     * pop出Activity，相当于finnish()
     */
    public void popActivity(Activity activity) {
        //将指定的Activity Finnish
        if (activity != null) {
            activity.finish();
            synchronized (mActivityStack) {
                mActivityStack.remove(activity);
            }
            activity = null;
        }
    }

    /**
     * 添加Activity
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        synchronized (mActivityStack) {
            mActivityStack.add(activity);
        }
    }

    public void popAllActivityExceptOne(Class cls) {
        synchronized (mActivityStack) {
            while (mActivityStack.size() > 1) {
                Activity activity = currentActivity();
                if (activity == null) {
                    mActivityStack.pop();
                    break;
                }
                if (!activity.getClass().equals(cls)) {
                    popActivity(activity);
                }
            }
        }
    }

    public void popAllActivity() {
        synchronized (mActivityStack) {
            while (!mActivityStack.isEmpty()) {
                Activity activity = currentActivity();
                if (activity == null) {
                    mActivityStack.pop();
                    break;
                }
                popActivity();
            }
        }
    }

    public void exit() {
        popAllActivity();
        try {
            System.exit(0);
        } catch (Exception e) {

        }
    }

    public boolean isEmpty() {
        synchronized (mActivityStack) {
            return mActivityStack.isEmpty();
        }
    }

    public Activity currentActivity() {
        synchronized (mActivityStack) {
            return mActivityStack.lastElement();
        }
    }
}
