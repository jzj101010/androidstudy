package z.j.j.androidstudy.view;

import android.content.Context;
import android.os.SystemClock;

/**
 * Created by j on 2019/1/23 0023.
 */

public class MyViewPagerScroll {

    private  long startX;
    private long distanceX;
    private long distanceY;
    private boolean isFinish=true;
    private long scrollTime;
    private int defaultScrollTime=250;
    private long preTime;
    private long startTime;
    private long scrollX;

    public MyViewPagerScroll(Context context){

    }


    public long getCurrX() {
        return scrollX;
    }


    public void startScroll(int startX, int startY, int distanceX, int distanceY ) {


        startScroll(startX,startY,distanceX,distanceY,defaultScrollTime);
    }


        public void startScroll(int startX, int startY, int distanceX, int distanceY,int scrollTime ){
        this.scrollX=this.startX=startX;
        this.distanceX=distanceX;
        this.distanceY=distanceY;
         preTime=SystemClock.uptimeMillis();
        startTime=0;
        this.scrollTime=scrollTime/4;
        if(this.scrollTime<defaultScrollTime){
            this.scrollTime=defaultScrollTime;
        }
        this.isFinish=false;
    }


    public boolean computeScrollOffset() {
        if (isFinish) {
            return false;
        } else {
            long time = SystemClock.uptimeMillis() - preTime;
            preTime = SystemClock.uptimeMillis();
            long scroDistans = time * distanceX / scrollTime;
            scrollX += scroDistans;
            startTime += time;
            if (startTime >= scrollTime) {
                scrollX=startX+distanceX;
                isFinish = true;
            }
        }
        return true;
    }


}
