package z.j.j.androidstudy.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by j on 2019/1/28 0028.
 */

public class MySlideFrameLayout extends FrameLayout {


    Scroller scroller;



    private int contentWight;
    private int deletedWight;
    private int contentHeight;
    private TextView tv_content;
    private TextView tv_deleted;

    public MySlideFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MySlideFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        boolean b=false;
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX=    startX=  ev.getX();
                b=false;
                break;
            case MotionEvent.ACTION_MOVE:
                if( Math.abs(ev.getX()-startX)>8){
                    b=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(getScrollX()>0&&getScrollX()<deletedWight){
                    b=true;
                }
                break;
        }
        return b;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_content= (TextView) getChildAt(0);
        tv_deleted= (TextView) getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        contentHeight=getHeight();
        contentWight=getChildAt(0).getWidth();
        deletedWight=getChildAt(1).getWidth();
        getChildAt(0).layout(0,0,contentWight,contentHeight);
        getChildAt(1).layout(contentWight,0,contentWight+deletedWight,contentHeight);
    }



    float startX;
    float startY;
    float downX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=  startX=  event.getX();
                startY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX=event.getX();
                float endY=event.getY();
                float distans=endX-startX;
                if(Math.abs(endY-startY)<=Math.abs(distans)&&Math.abs(distans)>8){
                    getParent().requestDisallowInterceptTouchEvent(true);

                    int scrollToX= (int) (getScrollX()-distans);
                    if(scrollToX>deletedWight) {
                        scrollToX = deletedWight;
                    }
                    if(scrollToX<0){
                        scrollToX=0;
                    }
                    scrollTo((int) scrollToX,0);

                    startX=event.getX();
                }

                break;
            case MotionEvent.ACTION_UP:
                int scro = getScrollX();
                if(scro>deletedWight/2){
                    open();
                }else if(scro<=deletedWight/2) {
                    close();
                }
                break;
        }

            return true;
    }

    public void close(){
        scroller.startScroll(getScrollX(),getScrollY(),0-getScrollX(),0);
        invalidate();
    }
    public void open(){
        scroller.startScroll(getScrollX(),getScrollY(),deletedWight-getScrollX(),0);
        invalidate();
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
           scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }



    }
}
