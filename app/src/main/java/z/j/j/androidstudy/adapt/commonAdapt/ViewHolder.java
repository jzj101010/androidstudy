package z.j.j.androidstudy.adapt.commonAdapt;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * Created by sulj on 16/4/24.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId,
                      int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View converView,
                                 ViewGroup parent, int layoutId, int position) {
        if (converView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) converView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView textView = (TextView) getView(viewId);
        textView.setText(text);
        return this;
    }

    public TextView getTextView(int viewId) {
        TextView textView = (TextView) getView(viewId);
        return textView;
    }

    public ViewHolder setText(int viewId, int resid) {
        TextView textView = (TextView) getView(viewId);
        textView.setText(resid);
        return this;
    }

    public LinearLayout getLinearLayoutView(int viewId) {
        LinearLayout linearLayout = (LinearLayout) getView(viewId);
        return linearLayout;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    public ImageView setImage(int viewId) {
        ImageView imageView = (ImageView) getView(viewId);
        // imageView.setImageDrawable(drawable);
        return imageView;
    }

    public ViewHolder setBackgroundResource(int viewId, int resid) {
        getView(viewId).setBackgroundResource(resid);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawable) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageResource(drawable);
        return this;
    }

    public ViewHolder onEditorActionListener(int viewId,
                                             OnEditorActionListener l) {
        EditText editText = (EditText) getView(viewId);
        editText.setOnEditorActionListener(l);
        return this;
    }

    public ViewHolder addTextChangedListener(int viewId, TextWatcher watcher) {
        EditText editText = (EditText) getView(viewId);
        editText.addTextChangedListener(watcher);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener l) {
        View view = getView(viewId);
        view.setOnTouchListener(l);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, OnClickListener l) {
        View view = getView(viewId);
        view.setOnClickListener(l);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, OnLongClickListener l) {
        View view = getView(viewId);
        view.setOnLongClickListener(l);
        return this;
    }

    public ViewHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int color) {
        TextView textView = getView(viewId);
        textView.setTextColor(color);
        return this;
    }

    public ViewHolder setBackRoundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setEnble(int id, boolean enble) {
        View view = getView(id);
        view.setEnabled(enble);
        return this;
    }

    public LinearLayout getVisible(int id) {
        LinearLayout view = getView(id);

        return view;
    }
    public TextView textview(int id) {
        TextView view = getView(id);

        return view;
    }
}
