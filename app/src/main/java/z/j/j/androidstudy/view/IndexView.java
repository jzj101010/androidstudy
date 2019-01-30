package z.j.j.androidstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by j on 2019/1/29 0029.
 */

public class IndexView extends View {

    private int widht;
    private int height;
    private Paint mPaint;

    private int currentPos=-1;

    private String indexStrs[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","y","Z"};


    public IndexView(Context context) {
        super(context);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void  init( ){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);
        mPaint.setColor(0xff000000);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widht=getWidth();
        height=getHeight()/indexStrs.length;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int Y= (int) event.getY();
                int pos=Y/height;
                if(pos!=currentPos&&pos<indexStrs.length&&pos>=0){
                    currentPos=pos;
                    invalidate();
                    if(onSelectIndexListener!=null){
                        onSelectIndexListener.selectIndex(indexStrs[pos]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                currentPos=-1;
                invalidate();
                if(onSelectIndexListener!=null){
                    onSelectIndexListener.selectCancel();
                }
                break;



        }

  return true;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xff3093fe);
        for (int i = 0; i <indexStrs.length ; i++) {
            if(i==currentPos){
                mPaint.setColor(0xffa4a4a4);
            }else {
                mPaint.setColor(0xff000000);
            }
            Rect rect=new Rect();
            mPaint.getTextBounds(indexStrs[i],0,1,rect);
            int textWidht=rect.width();
            int textheight=rect.height();
            canvas.drawText(indexStrs[i],widht/2-textheight/2,i*height+height/2+textheight/2,mPaint);
        }

    }

    OnSelectIndexListener onSelectIndexListener;

    public void setOnSelectIndexListener(OnSelectIndexListener onSelectIndexListener) {
        this.onSelectIndexListener = onSelectIndexListener;
    }

    public  interface OnSelectIndexListener{
        public void selectIndex(String s);
        public void selectCancel();
    }
}
