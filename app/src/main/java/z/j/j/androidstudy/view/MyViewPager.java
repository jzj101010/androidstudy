package z.j.j.androidstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by j on 2019/1/23 0023.
 */

public class MyViewPager extends ViewGroup {

  private GestureDetector gestureDetector;
    private float startX;
    private int currentPage=0;
    private MyViewPagerScroll myViewPagerScroll=new MyViewPagerScroll();


    public MyViewPager(Context context) {
        super(context);
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private  void init(){


        gestureDetector=new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

//                if(getScrollX()+distanceX>getWidth()*(getChildCount()-1)){
//                    scrollToPager(getChildCount()-1);
//                }else if(getScrollX()+distanceX<0){
//                    scrollToPager();
//                }else
//
                    if(getScrollX()+distanceX<getWidth()*(getChildCount()-1)&&getScrollX()+distanceX>0)
                    scrollBy((int) distanceX,0);

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    private void scrollToPager() {
          if(currentPage<0){
              currentPage=0;
          }
          if(currentPage>getChildCount()-1){
              currentPage=getChildCount()-1;
          }
        myViewPagerScroll.startScroll(getScrollX(),getScrollY(),currentPage*getWidth()-getScrollX(),0);
         invalidate();
          //        scrollTo(currentPage*getWidth(),0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(myViewPagerScroll.computeScroll()){
            scrollTo(myViewPagerScroll.getScrollX(),0);
            invalidate();
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i <getChildCount() ; i++) {
            View view=getChildAt(i);
            view.layout(i*getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                float endX = event.getX();
                if(endX - startX>getWidth()/2){
                    currentPage--;
                }
                if(startX-endX>getWidth()/2){
                    currentPage++;
                }
                scrollToPager();
                break;
        }




       return true;
//        return super.onTouchEvent(event);



    }
}
