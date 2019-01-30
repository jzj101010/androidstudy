package z.j.j.androidstudy.activity;

import android.opengl.Visibility;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.adapt.commonAdapt.CommonAdapter;
import z.j.j.androidstudy.adapt.commonAdapt.ViewHolder;
import z.j.j.androidstudy.bean.Person;
import z.j.j.androidstudy.view.IndexView;
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
            case 4:
                showIndexView();
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


    ListView listView;
    List<Person> personList=new ArrayList<>();
    private void showIndexView() {
        personList.add(new Person("aA风格"));
        personList.add(new Person("B共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("s共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("儿童"));
        personList.add(new Person("x风e格"));
        personList.add(new Person("共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("v儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("共和"));
        personList.add(new Person("e等多个"));
        personList.add(new Person("t儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("u共和"));
        personList.add(new Person("o等多个"));
        personList.add(new Person("共和"));
        personList.add(new Person("e等多个"));
        personList.add(new Person("t儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("u共和"));
        personList.add(new Person("o等多个"));
        personList.add(new Person("共和"));
        personList.add(new Person("e等多个"));
        personList.add(new Person("t儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("u共和"));
        personList.add(new Person("o等多个"));
        personList.add(new Person("儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("儿童"));
        personList.add(new Person("风格"));
        personList.add(new Person("共和"));
        personList.add(new Person("等多个"));
        personList.add(new Person("儿童"));
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPingyin().compareTo(o2.getPingyin());
            }
        });
        setContentView(R.layout.activity_index_view);
       final TextView tv_center=findViewById(R.id.tv_center);
        tv_center.setVisibility(View.GONE);
        tv_center.setBackgroundColor(0xff3093fe);
        tv_center.setTextColor(0xffa4a4a4);
        IndexView indexView=findViewById(R.id.index);
        indexView.setOnSelectIndexListener(new IndexView.OnSelectIndexListener() {
            @Override
            public void selectIndex(String s) {
                tv_center.setText(s);
                tv_center.setVisibility(View.VISIBLE);
                scrollToStr(s);
            }

            @Override
            public void selectCancel() {
                tv_center.setVisibility(View.GONE);
            }
        });
         listView=findViewById(R.id.listview);
        listView.setAdapter(new CommonAdapter<Person>(this,personList,R.layout.indexview_item) {

            @Override
            public void convert(z.j.j.androidstudy.adapt.commonAdapt.ViewHolder holder, Person person, int position) {
                String p = person.getPingyin().substring(0, 1);
                if(position==0){
                    holder.setVisibility(R.id.tv1, View.VISIBLE);
                }else {
                    String p2 = getItem(position - 1).getPingyin().substring(0, 1);
                    if (p .equals(p2) ) {
                        holder.setVisibility(R.id.tv1, View.GONE);
                    }else {
                        holder.setVisibility(R.id.tv1, View.VISIBLE);
                    }
                }
                holder.setText(R.id.tv1,p);
                holder.setText(R.id.tv2,person.getName());
            }
        });
    }

    private void scrollToStr(String s) {
        for (int i = 0; i <personList.size() ; i++) {
            if(s.equals(personList.get(i).getPingyin().substring(0,1))){
                listView.setSelection(i);
               break;
            }
        }
    }

}
