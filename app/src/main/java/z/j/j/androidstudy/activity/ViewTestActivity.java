package z.j.j.androidstudy.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.utils.EventBusUtil;
import z.j.j.androidstudy.view.MyViewPager;

public class ViewTestActivity extends BaseActivity {

    private int type;
    MyViewPager myViewPager;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type", 1);

        switch (type) {
            case 1:
                setContentView(R.layout.activity_view_test);
                break;
            case 2:
                showMyViewPager();

                break;
            case 3:
                showMyViewPager();
                break;

        }
        initView();
    }

    private int[] imgId = {R.mipmap.ic_launcher, R.mipmap.check_ck, R.mipmap.ic_launcher, R.mipmap.check_ck, R.mipmap.ic_launcher, R.mipmap.check_ck, R.mipmap.ic_launcher};
    private List<ImageView> imageViews = new ArrayList<>();

    private void showMyViewPager() {
        myViewPager = new MyViewPager(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myViewPager.setLayoutParams(layoutParams);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        radioGroup.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(radioGroup);
        layout.addView(myViewPager);

        for (int i = 0; i < imgId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgId[i]);
            imageViews.add(imageView);
            myViewPager.addView(imageView);

            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
            }
        }

        myViewPager.setOnPagerScrolledListener(new MyViewPager.OnPagerScrolledListener() {
            @Override
            public void onPagerScrolled(int pagerPos) {
                radioGroup.check(pagerPos);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myViewPager.scrollToPager(checkedId);
            }
        });

        setContentView(layout);
    }


    private void initView() {

    }
}
