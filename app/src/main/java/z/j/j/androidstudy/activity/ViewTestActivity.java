package z.j.j.androidstudy.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.utils.EventBusUtil;
import z.j.j.androidstudy.view.MySlideFrameLayout;
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
                showCehuaView();
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

    MyListViewAdapt myListViewAdapt;
    List<String > strings=new ArrayList<>();
    private void showCehuaView() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(layoutParams);
        ListView listView=new ListView(this);
        layout.addView(listView);
        setContentView(layout);

        for (int i = 0; i <20 ; i++) {
            strings.add(""+Math.random());
        }
         myListViewAdapt=new MyListViewAdapt();
        listView.setAdapter(myListViewAdapt);
    }
    public  class  MyListViewAdapt  extends    BaseAdapter{

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                convertView=View.inflate(ViewTestActivity.this,R.layout.list_deleted_item,null);
                viewHolder.tv_content =convertView.findViewById(R.id.tv_content);
                viewHolder.tv_deleted =convertView.findViewById(R.id.tv_deleted);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            ((MySlideFrameLayout) convertView).close();
            viewHolder.tv_content.setText(""+getItem(position));
            viewHolder.tv_deleted.setText("deleted");
            viewHolder.tv_deleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strings.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
    static class ViewHolder{
        TextView tv_content;
        TextView tv_deleted;

    }


}
