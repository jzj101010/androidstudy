package z.j.j.androidstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import z.j.j.androidstudy.R;

/**
 * Created by j on 2019/1/18 0018.
 */

public class MyViewTwo  extends View {
    private Paint mPaint;
    private String mContent;
    private Bitmap bitmap;
    private Bitmap  slidingBitmap;
    private int maxLeft;
    private float marLeft;
    private float moveX;
   private  boolean isMove;
    public MyViewTwo(Context context) {
        super(context);
        init(context);
    }

    public MyViewTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        if (typedArray!=null){
            mContent=typedArray.getString(R.styleable.MyView_content_MyViewStyle);
        }
        init(context);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                moveX=event.getX();
                Log.e("ACTION_DOWN------","----");
                break;

            case MotionEvent.ACTION_MOVE:
                marLeft+=event.getX()-moveX;

                Log.e("ACTION_MOVE------","----");
                if(Math.abs(event.getX()-moveX)>10){
                    isMove=true;
                    if(marLeft<0){
                        marLeft=0;
                    }else if(marLeft>maxLeft){
                        marLeft=maxLeft;
                    }
                    invalidate();
                    moveX=event.getX();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                checkMarleft();
                isMove=false;
                Log.e("ACTION_CANCEL------","----");
                break;
            case MotionEvent.ACTION_UP:
                if(isMove){
                    isMove=false;
                    checkMarleft();
                    return true;
                }
                Log.e("ACTION_UP------","----");

                break;


        }
        boolean b=super.onTouchEvent(event);
        Log.e("super.onTouc",b+"");
        return b;

    }

    private void checkMarleft() {
        if(marLeft<maxLeft/2){
            marLeft=0;
        }else {
            marLeft=maxLeft;
        }
        invalidate();
    }

    private void init(Context context) {
        bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.checkba);
//        slidingBitmap =BitmapFactory.decodeResource(getResources(),R.mipmap.check_ck);
        maxLeft=bitmap.getWidth()/2;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 1);

        slidingBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.check_ck), 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);

        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMove) {
                    if (marLeft > 0) {
                        marLeft = 0;
                    } else {
                        marLeft = maxLeft;
                    }
                    Log.e("onClick------------", "-----------");
                    invalidate();

                }else {
                    isMove=false;
                }
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,mPaint);
        canvas.drawBitmap(slidingBitmap,marLeft,0,mPaint);
    }
}
