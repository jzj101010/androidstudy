package z.j.j.androidstudy.view;

import android.os.SystemClock;

/**
 * Created by j on 2019/1/23 0023.
 */

public class MyViewPagerScroll {


    private int startX;
    private int startY;
    private int distanceX;
    private int distanceY;
    private boolean isFinish;
    private int scrollTime=500;

    private long preTime;

    private int scrollX;


    public int getScrollX() {
        return scrollX;
    }

    public void startScroll(int startX, int startY, int distanceX, int distanceY){
        this.scrollX=  this.startX=startX;
        this.startY=startY;
        this.distanceX=distanceX;
        this.distanceY=distanceY;
        preTime=SystemClock.uptimeMillis();
        this.isFinish=false;
    }


    public boolean computeScroll(){
        if(scrollX==startX+distanceX){
            return false;
        }
        int scrollDistance= (int) ((SystemClock.uptimeMillis()-preTime)*distanceX/scrollTime);
        scrollX+=scrollDistance;

        if(scrollX>startX+distanceX){
            scrollX=startX+distanceX;
        }
        return true;
    }


}
