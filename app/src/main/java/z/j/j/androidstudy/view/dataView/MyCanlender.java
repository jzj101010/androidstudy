package z.j.j.androidstudy.view.dataView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import z.j.j.androidstudy.R;

/**
 * Created by j on 2019/1/16 0016.
 */

public class MyCanlender extends LinearLayout {
    private Context context;

//    private NestedScrollView nestedScrollView;
//    private HorizontalScrollView horizontalScrollview;
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdaptr;
    private MyRecyclerAdapter myRecyclerAdapter;
    private LinearLayout rootView;             //根布局
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//日期格式化

    private BuidData buidData;

    private CustomMonthViewInterface defaltMonthViewImp;
    private  CustomDayViewInterface defaltDayViewImp;

    public MyCanlender(Context context) {
        super(context);
        init(context);
    }

    public MyCanlender(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BuidData getBuidData() {
        return buidData;
    }

    public void setBuidData(BuidData buidData) {
        this.buidData = buidData;
    }

    private void init(Context context) {
        this.context = context;

        recyclerView=new RecyclerView(context);
        viewPager=new ViewPager(context);
        this.addView(viewPager);
        this.addView(recyclerView);
        defaltMonthViewImp=new CustomMonthViewInterface() {
            @Override
            public View getMonthView(String yearStr, String monthStr, List gvList,View view) {
                if(view==null) {
                     view = LayoutInflater.from(MyCanlender.this.context).inflate(R.layout.comm_calendar, null, false);//获取布局，开始初始化
                }
                TextView tv_year = (TextView) view.findViewById(R.id.tv_year);
                tv_year.setVisibility(View.VISIBLE);
                tv_year.setText(yearStr + "年");
                TextView tv_month = (TextView) view.findViewById(R.id.tv_month);
                tv_month.setText(monthStr);
                MyGridView gv = view.findViewById(R.id.gv_calendar);
                gv.setHorizontalSpacing(4);
                gv.setAdapter(new CalendarGridViewAdapter(gvList, MyCanlender.this. context,buidData.dayViewInterface,buidData.onDaySelectListener));
                return view;
            }
        };
        defaltDayViewImp=new CustomDayViewInterface() {


            @Override
            public View getDayView(int position, View convertView, String year, String mon, String day) {
                GrideViewHolder holder;
                if (convertView == null) {
                    holder = new GrideViewHolder();
                    convertView = inflate(MyCanlender.this. context, R.layout.common_calendar_gridview_item, null);
                    holder.tv = (TextView) convertView.findViewById(R.id.tv_calendar);
                    holder.tvDay = (TextView) convertView.findViewById(R.id.tv_calendar_day);
                    convertView.setTag(holder);
                } else {
                    holder = (GrideViewHolder) convertView.getTag();
                }

                holder.tvDay.setText(day);
                if ((position + 1) % 7 == 0 || (position) % 7 == 0) {
                    holder.tvDay.setTextColor(Color.parseColor("#339900"));
                }
                return convertView;
            }
        };
    }


    private View getGridViewAndSetData(Date theInDay ,View view) {
        Calendar cal = Calendar.getInstance();//获取日历实例
        cal.setTime(theInDay);//cal设置为当天的
        cal.set(Calendar.DATE, 1);//cal设置当前day为当前月第一天
        int tempSum = countNeedHowMuchEmpety(cal);//获取当前月第一天为星期几
        int dayNumInMonth = getDayNumInMonth(cal);//获取当前月有多少天
        //显示数据 ｛",",",","月，日"｝
        List gvList = getGvListData(tempSum, dayNumInMonth, cal.get(Calendar.YEAR) + "," + getMonth((cal.get(Calendar.MONTH) + 1)));
        return buidData.monthViewInterface.getMonthView(cal.get(Calendar.YEAR)+"",cal.get(Calendar.MONTH)+1+"", gvList,view);
    }






    public  interface CustomMonthViewInterface{
        /**
         * 返回月份显示view
         * @param yearStr
         * @param monthStr
         * @param gvList
         *  @param view  view适配器复用机制的view
         * @return
         */
        public View getMonthView(String yearStr,String monthStr, List gvList,View view) ;
    }
    public  interface CustomDayViewInterface{
        /**
         * 返回每天显示view
         * convertView 适配器复用机制的view
         **/
        public View getDayView(int position, View convertView,String year,String mon ,String day) ;
    }
    /**
     * 自定义监听接口
     * @author Administrator
     *
     */
    public interface OnDaySelectListener {
        void onDaySelectListener(View clickDayView, String year,String mon ,String day);
    }

    private String getMonth(int month) {
        String mon = "";
        if (month < 10) {
            mon = "0" + month;
        } else {
            mon = "" + month;
        }
        return mon;
    }

    /**
     * 为gridview中添加需要展示的数据，
     *
     * @param tempSum
     * @param dayNumInMonth
     */
    private List getGvListData(int tempSum, int dayNumInMonth, String YM) {
        List gvList = new ArrayList<String>();
        for (int i = 0; i < tempSum; i++) {
            gvList.add(" , , ");
        }
        for (int j = 1; j <= dayNumInMonth; j++) {
            gvList.add(YM + "," + String.valueOf(j));
        }
        return gvList;
    }


    /**
     * 获取当前月第一天在第一个礼拜的第几天，得出第一天是星期几
     *
     * @param cal
     * @return
     */
    private int countNeedHowMuchEmpety(Calendar cal) {
        int firstDayInWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return firstDayInWeek;
    }

    /**
     * 获取当前月的总共天数
     *
     * @param cal
     * @return
     */
    private int getDayNumInMonth(Calendar cal) {
        return cal.getActualMaximum(Calendar.DATE);
    }

    public void build() {
        if (buidData == null || buidData.listDate == null)
            return;

       if(buidData.monthViewInterface==null){
           buidData.monthViewInterface=defaltMonthViewImp;
       }

        if(buidData.dayViewInterface==null){
            buidData.dayViewInterface=defaltDayViewImp;
        }

        try {
            switch (buidData.showType){
                case ShowType.SCROLL_V:
                    setRecyclerView(ShowType.SCROLL_V);
                    break;
                case ShowType.SCROLL_H:
                    setRecyclerView(ShowType.SCROLL_H);
                    break;
                case ShowType.MOVER_L_R:
                    viewPager.setVisibility(VISIBLE);
                    if(myPagerAdaptr==null){
                        myPagerAdaptr=new MyPagerAdapter();
                        viewPager.setAdapter(myPagerAdaptr);
                    }else {
                        myPagerAdaptr.notifyDataSetChanged();
                    }
                    scrollToPos(buidData.defaultShowPos);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setRecyclerView(@ ShowType int scrollV) {
        viewPager.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(scrollV==ShowType.SCROLL_V?OrientationHelper. VERTICAL:OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        if(0<=buidData.defaultShowPos&&buidData.defaultShowPos<buidData.listDate.size()){
            if(myRecyclerAdapter==null){
                myRecyclerAdapter=new MyRecyclerAdapter();
                recyclerView.setAdapter(myRecyclerAdapter);
            }else {
                myRecyclerAdapter.notifyDataSetChanged();
            }
        }
        scrollToPos(buidData.defaultShowPos);
    }

    public void scrollToPos(int pos) {
        if(0<=pos&&pos<buidData.listDate.size()){
            switch (buidData.showType){
                case ShowType.SCROLL_V:
                    recyclerView.smoothScrollToPosition(buidData.defaultShowPos);
                    break;
                case ShowType.SCROLL_H:
                    recyclerView.smoothScrollToPosition(buidData.defaultShowPos);
                    break;
                case ShowType.MOVER_L_R:
                    viewPager.setCurrentItem(buidData.defaultShowPos,true);
                    break;
            }
        }
    }



    public void showPre(){
        if (buidData.showType == ShowType.MOVER_L_R) {
            scrollToPos(viewPager.getCurrentItem()-1);
        }
    }
    public void showNext(){
        if (buidData.showType == ShowType.MOVER_L_R) {
            scrollToPos(viewPager.getCurrentItem()+1);
        }
    }



    /**
     * gridview中adapter的viewholder
     *
     * @author Administrator
     */
    public   class GrideViewHolder {
        TextView tvDay, tv;
    }
   public static class CalendarGridViewAdapter extends BaseAdapter {
        List<String> gvList;//存放天
        Context context;

       private CustomDayViewInterface dayViewInterface;
       private  OnDaySelectListener daySelectListener;

        public CalendarGridViewAdapter(List<String> gvList, Context context,CustomDayViewInterface dayViewInterface,OnDaySelectListener daySelectListener) {
            this.gvList = gvList;
            this.context = context;
            this.dayViewInterface = dayViewInterface;
            this.daySelectListener = daySelectListener;
        }

        @Override
        public int getCount() {
            return gvList.size();
        }

        @Override
        public String getItem(int position) {
            return gvList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final String [] strs=getItem(position).split(",");
            View view = null;
            if(strs.length==3){
                 view=dayViewInterface.getDayView(position,convertView,strs[0],strs[1],strs[2]);
                 view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                           if(daySelectListener!=null){
                               daySelectListener.onDaySelectListener(v,strs[0],strs[1],strs[2]);
                           }
                    }
                });
            }
            return view;

        }
    }




    public static BuidData getBuidDataInstance() {
        return new BuidData();
    }

    public static class BuidData {

        /**
         * 设置 listDate 需要显示月份
         */
        private List<String> listDate;  //传入的显示日期，每个显示月份的一个日期，用于获取月份判断要显示的月份
        private int showType;
        private int defaultShowPos;

        private CustomMonthViewInterface monthViewInterface;
        private CustomDayViewInterface dayViewInterface;
        private OnDaySelectListener onDaySelectListener;
        public List<String> getListDate() {
            return listDate;
        }


        public BuidData buildOnDaySelectListener(OnDaySelectListener onDaySelectListener) {
            this.onDaySelectListener = onDaySelectListener;
            return this;
        }

        public OnDaySelectListener getOnDaySelectListener() {
            return onDaySelectListener;
        }

        public BuidData buildDayViewInterface(CustomDayViewInterface dayViewInterface) {
            this.dayViewInterface = dayViewInterface;
            return this;
        }

        public CustomMonthViewInterface getMonthViewInterface() {
            return monthViewInterface;
        }
        public CustomDayViewInterface getDayViewInterface() {
            return dayViewInterface;
        }
        public BuidData buildMonthViewInterface(CustomMonthViewInterface monthViewInterface) {
            this.monthViewInterface = monthViewInterface;
            return this;
        }

        public int getDefaultShowPos() {
            return defaultShowPos;
        }

        public BuidData buildDefaultShowPos(int defaultShowPos) {
            this.defaultShowPos = defaultShowPos;
            return this;
        }

        public int getShowType() {
            return showType;
        }


        public BuidData buildShowType(@ShowType int showType) {
            this.showType = showType;
            return this;
        }
        public BuidData buidDateList(int agoMonCount, String centerDataStr, int afterMonCount) {
            listDate = getDateList(agoMonCount, centerDataStr, afterMonCount);
            return this;
        }

        public BuidData buidDateList(int agoMonCount, Date centerData, int afterMonCount) {
            if(listDate==null){
                listDate = getDateList(agoMonCount, centerData, afterMonCount);
            }else {
                listDate.clear();
                listDate.addAll(getDateList(agoMonCount, centerData, afterMonCount));
            }

            return this;
        }


        public List<String> getDateList(int agoMonCount, String centerDataStr, int afterMonCount) {
            try {
                return getDateList(agoMonCount, dateFormat.parse(centerDataStr), afterMonCount);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new ArrayList<String>();
        }

        public List<String> getDateList(int agoMonCount, Date centerData, int afterMonCount) {
            List<String> list = new ArrayList<String>();
            Calendar cal = Calendar.getInstance();//获取日历实例
            try {
                cal.setTime(centerData);//cal设置为当天的
                cal.add(Calendar.MONTH, -agoMonCount);
                for (int i = 0; i < agoMonCount; i++) {
                    list.add(dateFormat.format(cal.getTime()));
                    cal.add(Calendar.MONTH, 1);
                }
                list.add(dateFormat.format(cal.getTime()));
                cal.add(Calendar.MONTH, 1);
                for (int i = 0; i < afterMonCount; i++) {
                    list.add(dateFormat.format(cal.getTime()));
                    cal.add(Calendar.MONTH, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }
    }


    @IntDef({ShowType.SCROLL_V,ShowType.SCROLL_H, ShowType.MOVER_L_R})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowType {
        int SCROLL_V = 0;  //滚动
        int SCROLL_H = 1;  //滚动
        int MOVER_L_R = 2; //点击左右
    }



    public class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return buidData.listDate.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = null;
            try {
                 view=getGridViewAndSetData(dateFormat.parse(buidData.listDate.get(position)),null);
            } catch (ParseException e) {
                e.printStackTrace();
                view=new TextView(context);
                ( (TextView) view).setText("数据出错");
            }
            container.addView(view);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public class  MyRecyclerHolder extends  RecyclerView.ViewHolder{
        public ViewGroup viewGroup;
        public MyRecyclerHolder(View itemView) {
            super(itemView);
            this.viewGroup= (ViewGroup) itemView;
        }
    }
    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerHolder>{


        @Override
        public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new LinearLayout(context);

            return new MyRecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(MyRecyclerHolder holder, int position) {

             View  view=   holder.viewGroup.getChildAt(0);
                try {
                    view = getGridViewAndSetData(dateFormat.parse(buidData.listDate.get(position)),view);
                    LinearLayout.LayoutParams layoutParams = new LayoutParams(context.getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
                    if (buidData.showType == ShowType.SCROLL_H) {
                        layoutParams.setMargins(0, 10, 10, 0);
                    }
                    view.setLayoutParams(layoutParams);
                } catch (ParseException e) {
                    e.printStackTrace();
                    view = new TextView(context);
                    ((TextView) view).setText("数据出错");
                }
                if(holder.viewGroup.getChildAt(0)==null) {
                    holder.viewGroup.addView(view);
                }

        }

        @Override
        public int getItemCount() {
            return buidData.listDate.size();
        }
    }

}
