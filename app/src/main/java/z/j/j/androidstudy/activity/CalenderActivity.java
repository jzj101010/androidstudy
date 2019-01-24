package z.j.j.androidstudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.utils.ToastUtils;
import z.j.j.androidstudy.view.dateView.ScrollMoveCanlender;
import z.j.j.androidstudy.view.dateView.MGridView;

public class CalenderActivity extends AppCompatActivity {

    int type = 1;
    private ScrollMoveCanlender myCanlend;
    private TextView pre;
    private TextView next;
    private TextView   center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        initView();
        type = getIntent().getIntExtra("type", 1);
         myCanlend = findViewById(R.id.mycanlend);

        switch (type) {
            case 1:
                myCanlend.setBuidData(ScrollMoveCanlender.getBuidDataInstance()
                        .buidDateList(53, new Date(), 54)
                        .buildShowType(ScrollMoveCanlender.ShowType.SCROLL_V)
                        .buildDefaultShowPos(4).buildOnDaySelectListener(new ScrollMoveCanlender.OnDaySelectListener() {
                            @Override
                            public void onDaySelectListener(View clickDayView, String year, String mon, String day) {
                                ToastUtils.showToas(year+mon+day);
                                clickDayView.setBackgroundColor(0xff3093fe);
                            }
                        }));
                break;
            case 2:
                myCanlend.setBuidData(ScrollMoveCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(ScrollMoveCanlender.ShowType.SCROLL_H)
                        .buildDefaultShowPos(4));
                break;

            case 3:
                myCanlend.setBuidData(ScrollMoveCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(ScrollMoveCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4));
                pre.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                pre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCanlend.showPre();
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCanlend.showNext();
                    }
                });


            case 4:
                myCanlend.setBuidData(ScrollMoveCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(ScrollMoveCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4)
                        .buildDayViewInterface(new ScrollMoveCanlender.CustomDayViewInterface() {
                            @Override
                            public View getDayView(int position, View convertView ,String year,String mon ,String day) {
                                TextView textView = new TextView(CalenderActivity.this);
                                textView.setText("day" + day+"zidingyiyiid");
                                textView.setGravity(Gravity.CENTER);
                                textView.setPadding(0, 50, 0, 50);
                                return textView;
                            }
                        }));
                break;
            case 5:
                myCanlend.setBuidData(ScrollMoveCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(ScrollMoveCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4)
                        .buildMonthViewInterface(new ScrollMoveCanlender.CustomMonthViewInterface() {
                            @Override
                            public View getMonthView(String yearStr, String monthStr, List gvList,View view) {

                                if(view==null){
                                    view = LayoutInflater.from(CalenderActivity.this).inflate(R.layout.comm_calendar, null, false);//获取布局，开始初始化
                                }
                                TextView tv_year = (TextView) view.findViewById(R.id.tv_year);
                                tv_year.setVisibility(View.VISIBLE);
                                tv_year.setText(yearStr + "年");
                                tv_year.setTextSize(25);
                                TextView tv_month = (TextView) view.findViewById(R.id.tv_month);
                                tv_month.setText(monthStr);
                                tv_month.setTextColor(0xff3093fe);



                                MGridView gv = view.findViewById(R.id.gv_calendar);
                                gv.setHorizontalSpacing(4);
                                gv.setAdapter(myCanlend.getCalendarGridViewAdapter(gvList));

                                return view;
                            }
                        }));
                break;
            case 6:
                myCanlend.setBuidData(ScrollMoveCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(ScrollMoveCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4));
                showChange();
                break;

            case 7:
                 showInOutCanlender();
                 showChange();
                break;

        }



        myCanlend.build();
    }

    private void showChange() {
        pre.setVisibility(View.VISIBLE);
        pre.setText("ver");
        next.setVisibility(View.VISIBLE);
        next.setText("hor");
        center.setVisibility(View.VISIBLE);
        center.setText("move_l_r");

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanlend.getBuidData().buildShowType(ScrollMoveCanlender.ShowType.SCROLL_V);
                myCanlend.build();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanlend.getBuidData().buildShowType(ScrollMoveCanlender.ShowType.SCROLL_H);
                myCanlend.build();
            }
        });
        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanlend.getBuidData().buildShowType(ScrollMoveCanlender.ShowType.MOVER_L_R);
                myCanlend.build();
            }
        });
    }

    Date inDate;
     Date outDate;
    private void showInOutCanlender() {
        ScrollMoveCanlender.BuidData buidData = ScrollMoveCanlender.getBuidDataInstance()
                .buidDateList(5,new Date(),5)
                .buildDefaultShowPos(5)
                .buildShowType(ScrollMoveCanlender.ShowType.MOVER_L_R)
                .buildDayViewInterface(new ScrollMoveCanlender.CustomDayViewInterface() {
                    @Override
                    public View getDayView(int position, View convertView, String year, String mon, String day) {
                        TextView textView = new TextView(CalenderActivity.this);
                        textView.setText(day);
                        textView.setGravity(Gravity.CENTER);
                        textView.setPadding(0, 50, 0, 50);
                        try {
                            Long aLong=   ScrollMoveCanlender.dateFormat.parse(year+"-"+mon+"-"+day).getTime();
                            if(inDate!=null&&inDate.getTime()==aLong){
                                textView.setText("开始");
                                textView.setBackgroundColor(0xff489614);
                            }
                            if(outDate!=null&&outDate.getTime()==aLong){
                                textView.setText("结束");
                                textView.setBackgroundColor(0xff3093fe);
                            }
                            if(inDate!=null&&outDate!=null){
                                if(inDate.getTime()<aLong&&aLong<outDate.getTime()){
                                    textView.setBackgroundColor(0xff3093fe);
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        return textView;
                    }
                })
                .buildOnDaySelectListener(new ScrollMoveCanlender.OnDaySelectListener() {
                    @Override
                    public void onDaySelectListener(View clickDayView, String year, String mon, String day) {

                        try {
                            ToastUtils.showToas(year+"-"+mon+"-"+day);
                            if(" ".equals(year))return;


                            Date date= ScrollMoveCanlender.dateFormat.parse(year+"-"+mon+"-"+day);
                            Long aLong= date.getTime();
                            boolean isRefresh = false;
                            
                            if(inDate==null){
                                if(outDate==null){
                                    inDate=date;
                                    (  (TextView)clickDayView).setText("开始");
                                    clickDayView.setBackgroundColor(0xff489614);
                                }else{

                                    if(aLong<outDate.getTime()){
                                        isRefresh=true;
                                        inDate=date;
                                    }else if(aLong==outDate.getTime()){
                                        outDate=null;
                                        (  (TextView)clickDayView).setText(day);
                                        clickDayView.setBackgroundColor(0xffffffff);
                                    }else {
                                        isRefresh=true;
                                        outDate=date;
                                    }
                                }
                            }else {
                                if(outDate==null){
                                    if(aLong<inDate.getTime()){
                                        isRefresh=true;
                                        inDate=date;
                                    }else if(aLong==inDate.getTime()){
                                        inDate=null;
                                        (  (TextView)clickDayView).setText(day);
                                        clickDayView.setBackgroundColor(0xffffffff);
                                    }else{
                                        isRefresh=true;
                                        outDate=date;
                                    }
                                }else{
                                    isRefresh=true;
                                        if(aLong<inDate.getTime()){
                                            inDate=date;
                                        }else if(aLong==inDate.getTime()){
                                            inDate=null;
                                        }else if(inDate.getTime()<aLong&&aLong<outDate.getTime()){
                                            inDate=date;
                                        }else if(aLong==outDate.getTime()){
                                            outDate=null;
                                        }else if(aLong>outDate.getTime()){
                                            outDate=date;
                                        }
                                }
                            }
                            if(isRefresh){
                                myCanlend.refresh();
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });

        myCanlend.setBuidData(buidData);
        myCanlend.build();
    }

    private void initView() {
        myCanlend = (ScrollMoveCanlender) findViewById(R.id.mycanlend);
        pre = (TextView) findViewById(R.id.pre);
        next = (TextView) findViewById(R.id.next);
        center= (TextView) findViewById(R.id.center);
    }
}
