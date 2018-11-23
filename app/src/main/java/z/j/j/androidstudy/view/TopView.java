package z.j.j.androidstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import z.j.j.androidstudy.R;

/**
 * Created by j on 2018/11/22 0022.
 */

public class TopView extends LinearLayout {


    private ImageView top_left;
    private TextView top_title;
    private TextView top_right;

    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.topview, this);
        initView();
    }

    private void initView() {
        top_left=findViewById(R.id.top_left);
        top_title=findViewById(R.id.top_title);
        top_right=findViewById(R.id.top_right);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public ImageView getTop_left() {
        return top_left;
    }

    public void setTop_left(ImageView top_left) {
        this.top_left = top_left;
    }

    public TextView getTop_title() {
        return top_title;
    }

    public void setTop_title(TextView top_title) {
        this.top_title = top_title;
    }

    public TextView getTop_right() {
        return top_right;
    }

    public void setTop_right(TextView top_right) {
        this.top_right = top_right;
    }
}
