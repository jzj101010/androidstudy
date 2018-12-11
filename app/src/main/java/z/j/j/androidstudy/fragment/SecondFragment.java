package z.j.j.androidstudy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z.j.j.androidstudy.R;

/**
 * Created by j on 2018/11/26 0026.
 */

public class SecondFragment extends BaseFragment {


    private static final String TAG = SecondFragment.class.getSimpleName();
    private ViewPager vp_view;
    private TextView tv_title;
    private LinearLayout li_point;
    private List<ImageView> imageViews=new ArrayList<>();
    private List<ImageView> points=new ArrayList<>();
    private MyPagerAdapt myPagerAdapt=new MyPagerAdapt();

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    vp_view.setCurrentItem(vp_view.getCurrentItem()+1);
                    handler.sendEmptyMessageDelayed(0,2000);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(activity, R.layout.secondfragment, null);
        initView(view);
        for (int i = 0; i <5 ; i++) {
            ImageView imageView=new ImageView(activity);
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
            ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageViews.add(imageView);

            ImageView point=new ImageView(activity);
            point.setBackgroundResource(R.drawable.pointdrawble);

            point.setEnabled( i==0);
            LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(20,20);
            if(i!=0)
            layoutParams1.setMargins(10,0,0,0);
            point.setLayoutParams(layoutParams1);
            li_point.addView(point);
            points.add(point);

        }

        vp_view.setAdapter(myPagerAdapt);
        vp_view.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%imageViews.size());
        tv_title.setText("1");
        handler.sendEmptyMessageDelayed(0,2000);
        vp_view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int  realPosition=position%imageViews.size();
                for (int i = 0; i <points.size() ; i++) {
                    points.get(i).setEnabled(realPosition==i);
                }
                tv_title.setText(realPosition+1+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                   switch (state){
                       case ViewPager.SCROLL_STATE_IDLE:
                           Log.e(TAG,"SCROLL_STATE_IDLE");
                           break;
                       case ViewPager.SCROLL_STATE_DRAGGING:
                           Log.e(TAG,"SCROLL_STATE_DRAGGING");
                           handler.removeCallbacksAndMessages(null);
                           break;
                       case ViewPager.SCROLL_STATE_SETTLING:
                           Log.e(TAG,"SCROLL_STATE_SETTLING");
                           handler.removeCallbacksAndMessages(null);
                           handler.sendEmptyMessageDelayed(0,2000);
                           break;
                   }
            }
        });
        return view;
    }

    private void initView(View view) {
        vp_view = (ViewPager) view.findViewById(R.id.vp_view);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        li_point = (LinearLayout) view.findViewById(R.id.li_point);
    }


    class MyPagerAdapt extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int  realPosition=position%imageViews.size();
            ImageView imageView=imageViews.get(realPosition);
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case  MotionEvent.ACTION_DOWN:
                            Log.e(TAG,"ACTION_DOWN");
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case  MotionEvent.ACTION_MOVE:
                            Log.e(TAG,"ACTION_MOVE");
                            break;
                        case  MotionEvent.ACTION_CANCEL:
                            Log.e(TAG,"ACTION_CANCEL");

//                            handler.sendEmptyMessageDelayed(0,2000);
                            break;
                        case  MotionEvent.ACTION_UP:
                            Log.e(TAG,"ACTION_UP");
                            handler.sendEmptyMessageDelayed(0,2000);
                            break;
                    }

                    return true;
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
