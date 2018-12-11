package z.j.j.androidstudy;

import android.app.Application;
import android.os.Handler;

/**
 * Created by j on 2018/11/23 0023.
 */

public class MyApp extends Application {
    private static MyApp _app;
    private Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        _app=   this;
        handler=new Handler();
    }

    public static MyApp getInstance() {
        return _app;
    }

    public void postDelayed(Runnable runnable, long delayMillis) {
        handler.postDelayed(runnable, delayMillis);
    }

    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
