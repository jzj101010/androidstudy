package z.j.j.androidstudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.utils.ToastUtils;
import z.j.j.androidstudy.view.dataView.MyCanlender;
import z.j.j.androidstudy.view.dataView.MyGridView;

public class CalenderActivity extends AppCompatActivity {

    int type = 1;
    private MyCanlender myCanlend;
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
                myCanlend.setBuidData(MyCanlender.getBuidDataInstance()
                        .buidDateList(53, new Date(), 54)
                        .buildShowType(MyCanlender.ShowType.SCROLL_V)
                        .buildDefaultShowPos(4).buildOnDaySelectListener(new MyCanlender.OnDaySelectListener() {
                            @Override
                            public void onDaySelectListener(View clickDayView, String year, String mon, String day) {
                                ToastUtils.showToas(year+mon+day);
                                clickDayView.setBackgroundColor(0xff3093fe);
                            }
                        }));
                break;
            case 2:
                myCanlend.setBuidData(MyCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(MyCanlender.ShowType.SCROLL_H)
                        .buildDefaultShowPos(4));
                break;

            case 3:
                myCanlend.setBuidData(MyCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(MyCanlender.ShowType.MOVER_L_R)
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
                myCanlend.setBuidData(MyCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(MyCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4)
                        .buildDayViewInterface(new MyCanlender.CustomDayViewInterface() {
                            @Override
                            public View getDayView(int position, View convertView ,String year,String mon ,String day) {
                                TextView textView = new TextView(CalenderActivity.this);
                                textView.setText("day" + day);
                                textView.setGravity(Gravity.CENTER);
                                textView.setPadding(0, 50, 0, 50);
                                return textView;
                            }
                        }));
            case 5:
                myCanlend.setBuidData(MyCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(MyCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4)
                        .buildMonthViewInterface(new MyCanlender.CustomMonthViewInterface() {
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
                                MyGridView gv = view.findViewById(R.id.gv_calendar);
                                gv.setHorizontalSpacing(4);
                                gv.setAdapter(new MyCanlender.CalendarGridViewAdapter(gvList, CalenderActivity.this, myCanlend.getBuidData().getDayViewInterface(), myCanlend.getBuidData().getOnDaySelectListener()));
                                return view;
                            }
                        }));
                break;
            case 6:
                myCanlend.setBuidData(MyCanlender.getBuidDataInstance()
                        .buidDateList(3, new Date(), 4)
                        .buildShowType(MyCanlender.ShowType.MOVER_L_R)
                        .buildDefaultShowPos(4));
                pre.setVisibility(View.VISIBLE);
                pre.setText("ver");
                next.setVisibility(View.VISIBLE);
                next.setText("hor");
                center.setVisibility(View.VISIBLE);
                center.setText("move_l_r");
                pre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCanlend.getBuidData().buildShowType(MyCanlender.ShowType.SCROLL_V);
                        myCanlend.build();
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCanlend.getBuidData().buildShowType(MyCanlender.ShowType.SCROLL_H);
                        myCanlend.build();
                    }
                });
                center.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myCanlend.getBuidData().buildShowType(MyCanlender.ShowType.MOVER_L_R);
                        myCanlend.build();
                    }
                });
                break;
        }
        myCanlend.build();
    }

    private void initView() {
        myCanlend = (MyCanlender) findViewById(R.id.mycanlend);
        pre = (TextView) findViewById(R.id.pre);
        next = (TextView) findViewById(R.id.next);
        center= (TextView) findViewById(R.id.center);
    }
}
