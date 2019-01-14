package z.j.j.androidstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import z.j.j.androidstudy.utils.Utils;

/**
 * Created by j on 2019/1/14 0014.
 */

public class MyViewGroup extends ViewGroup {


    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("MyViewGroup","MyViewGroup");
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
      // 计算所有子控件需要用到的宽高
        int height = 0;              //记录根容器的高度
        int width = 0;               //记录根容器的宽度
        int count = getChildCount(); //记录容器内的子控件个数
        for (int i=0;i<count;i++) {
            //测量子控件
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //获得子控件的高度和宽度
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            //得到最大宽度，并且累加高度
            height += childHeight;
            width = Math.max(childWidth, width);
        }
       // 设置当前View的宽高
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth: width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight: height);
        Log.e("MyViewGroup","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            //该子控件在父容器的位置  ， 高度是之前所有子控件的高度和开始 ，从上往下排列，就实现了类似Linearlayout布局垂直排列的布局
            child.layout(0+i*100, top, childWidth+i*100, top + childHeight); //以父容器左上角为原点进行布局
            top += childHeight;
        }

        Log.e("MyViewGroup","onLayout");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e("MyViewGroup","draw");
    }
}
