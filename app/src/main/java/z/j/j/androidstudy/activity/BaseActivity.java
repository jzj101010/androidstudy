package z.j.j.androidstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import z.j.j.androidstudy.utils.ActivityManager;

/**
 * Created by j on 2018/11/22 0022.
 */

public class BaseActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }


    /**
     * 判断是否是连续点击了Button
     *
     * @return true    连续点击了Button
     * false   没有连续点击Button
     */
    private long preTime;
    // 默认两次点击的间隔为 1000 毫秒
    public boolean isDoubleClick(){
        return isDoubleClick(1000);
    }
    public boolean isDoubleClick(int delaySecond) {
        long lastTime = System.currentTimeMillis();
        boolean flag = lastTime - preTime < delaySecond ? true : false;
        preTime = lastTime;
        return flag;
    }
}
