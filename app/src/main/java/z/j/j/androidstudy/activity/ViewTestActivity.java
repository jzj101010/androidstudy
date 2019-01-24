package z.j.j.androidstudy.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.utils.EventBusUtil;
import z.j.j.androidstudy.view.MyViewPager;

public class ViewTestActivity extends BaseActivity {

    private int type;
    MyViewPager myViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type=getIntent().getIntExtra("type",1);

        switch (type) {
            case 1:
                setContentView(R.layout.activity_view_test);
        break;
            case 2:
                showMyViewPager();

                break;
        }
        initView();
    }
    private int[] imgId={R.mipmap.ic_launcher,R.mipmap.check_ck,R.mipmap.ic_launcher,R.mipmap.check_ck,R.mipmap.ic_launcher,R.mipmap.check_ck,R.mipmap.ic_launcher};
   private  List<ImageView> imageViews=new ArrayList<>();
    private void showMyViewPager() {
        myViewPager=new MyViewPager(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myViewPager.setLayoutParams(layoutParams);
        for (int i = 0; i <imgId.length ; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(imgId[i]);
            imageViews.add(imageView);
            myViewPager.addView(imageView);
        }



        setContentView(myViewPager);
    }


    private void initView() {

    }
}
