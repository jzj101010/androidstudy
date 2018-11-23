package z.j.j.androidstudy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import z.j.j.androidstudy.activity.BaseActivity;

/**
 * Created by j on 2018/11/22 0022.
 */

public class ToastUtils {

    private static String lastToast = "";
    private static long lastToastTime;
    private static Toast toast;
    private static TextView tv;
   private  ToastUtils(){
       throw new UnsupportedOperationException("不能被实例化");
   }
    public static void showToas(final String message){
        final BaseActivity currentActivity=ActivityManager.getInstance().currentActivity();

        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {
                @SuppressLint("ShowToast")
                @Override
                public void run() {
                    if (!TextUtils.isEmpty(message)) {
//                        LogUtils.d(message);
                        long time = System.currentTimeMillis();
                        //static final int LONG_DELAY = 3500; // 3.5 seconds
                        //static final int SHORT_DELAY = 2000; // 2 seconds
                        if (!message.equalsIgnoreCase(lastToast) || Math.abs(time - lastToastTime) > 3500) {
                            if (toast == null) {
                                toast = Toast.makeText(currentActivity, "", Toast.LENGTH_LONG);
                                ViewGroup viewGroup = ((ViewGroup) toast.getView());
                                if (viewGroup.getChildCount() > 0) {
                                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                                        if (viewGroup.getChildAt(i) instanceof TextView) {
                                            tv = (TextView) viewGroup.getChildAt(i);
                                        }
                                    }
                                }
                            }
                            toast.setText(message);
                            if (tv != null) {
                                tv.setCompoundDrawablePadding(0);
                                tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                            }
                            toast.show();
                            lastToast = message;
                            lastToastTime = System.currentTimeMillis();
                        }
                    }
                }
            });
        }
    }
}
