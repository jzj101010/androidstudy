package z.j.j.androidstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
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


    private ViewPager vp_view;
    private TextView tv_title;
    private LinearLayout li_point;
    private List<ImageView> imageViews=new ArrayList<>();
    private List<ImageView> points=new ArrayList<>();
    private MyPagerAdapt myPagerAdapt=new MyPagerAdapt();


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
        vp_view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <points.size() ; i++) {
                    points.get(i).setEnabled(position==i);
                }
                tv_title.setText(position+1+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
