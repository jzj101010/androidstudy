package z.j.j.androidstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import z.j.j.androidstudy.R;


/**
 * Created by j on 2019/1/4 0004.
 */

public class MyView extends View {
    private String mContent;
    private int mBackground;
    private int mSelect;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        if (typedArray!=null){
            mContent=typedArray.getString(R.styleable.MyView_content_MyViewStyle);
            mBackground=typedArray.getColor(R.styleable.MyView_background_MyViewStyle,Color.RED);
        }
        Log.d("viewStyle","content:"+mContent);
        Log.d("viewStyle","background:"+mBackground);
        Log.e("MyView","MyView");

    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("MyView","onLayout");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300,500);
        Log.e("MyView","onMeasure");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e("MyView","draw");
        Paint paint = new Paint();
        canvas.drawColor(mBackground);
        canvas.drawCircle(150, 150, 50, paint);
        canvas.drawCircle(300, 300, 50, paint);
    }
}
