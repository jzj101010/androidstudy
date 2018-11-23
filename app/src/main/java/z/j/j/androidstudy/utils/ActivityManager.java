package z.j.j.androidstudy.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import z.j.j.androidstudy.activity.BaseActivity;

/**
 * Created by j on 2018/11/22 0022.
 */

public class ActivityManager {
   private static  final ActivityManager sInstance=new ActivityManager();
   private static Stack<BaseActivity> activityStack = new Stack<>();
    private ActivityManager(){

    }

    public static ActivityManager getInstance() {
        return sInstance;
    }
    public void closeActivity(Class notCloseActivity) {
        BaseActivity act = null;
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(notCloseActivity)) {
                act = activity;
                continue;
            }
            activity.finish();
        }
        activityStack.clear();
        if (act != null) {
            activityStack.add(act);
        }
    }

    public void closeActivity(Class... notCloseClzs) {
        List<Class> list = null;
        if (notCloseClzs != null && notCloseClzs.length > 0) {
            list = Arrays.asList(notCloseClzs);
        }
        closeActivity(list);
    }

    public void closeActivity(List<Class> notCloseClzs) {
        ArrayList<BaseActivity> act = null;
        for (BaseActivity activity : activityStack) {
            if (notCloseClzs != null) {
                if (notCloseClzs.contains(activity.getClass())) {
                    if (act == null){
                        act = new ArrayList<>();
                    }
                    act.add(activity);
                    continue;
                }
            }
            activity.finish();
        }
        activityStack.clear();
        if (act != null && act.size() > 0) {
            activityStack.addAll(act);
        }
    }


    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    public static BaseActivity getActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public BaseActivity getActivity(String className) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().getSimpleName().equals(className)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(BaseActivity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public BaseActivity currentActivity() {
        try {
            return activityStack.lastElement();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        try {
            BaseActivity activity = activityStack.lastElement();
            finishActivity(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(BaseActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(BaseActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
                break;
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }

    public Stack<BaseActivity> getActivityStack() {
        return activityStack;
    }
}
