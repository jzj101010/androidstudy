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
//        for (int i=0;i<count;i++) {
//            //测量子控件
//            View child = getChildAt(i);
//            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//        }
        measureChildren(widthMeasureSpec,heightMeasureSpec);
       // 设置当前View的宽高
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth: width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight: height);
        Log.e("MyViewGroup","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentAddWidth = 0;
        int count = getChildCount();
        int width=this.getWidth();
        int currentWi=0;
        int currentRow=0;
        for (int i=0;i<count;i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();

            if(currentWi+childWidth>width){
                if(currentWi>0){
                    currentRow++;
                    currentWi=0;
                }

            }
            int l1=currentWi+20;
            int t1=(childHeight+30)*currentRow+30;
            int r1=l1+childWidth;
            int b1=t1+childHeight;
            child.layout(l1,t1,r1,b1);
            currentWi+=childWidth+20;
        }
        Log.e("MyViewGroup","onLayout");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e("MyViewGroup","draw");
    }
}
