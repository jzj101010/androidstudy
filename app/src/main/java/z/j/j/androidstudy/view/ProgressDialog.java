package z.j.j.androidstudy.view;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import z.j.j.androidstudy.R;


/**
 * Created by Administrator on 2017/9/28.
 */

public class ProgressDialog  {

    private Context context;
    public Dialog mDialog;
    private AnimationDrawable animationDrawable = null;

    public ProgressDialog(Context context, String message) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.progress_view, null);

        TextView text = (TextView) view.findViewById(R.id.progress_message);
        text.setText(message);
        ImageView loadingImage = (ImageView) view.findViewById(R.id.progress_view);
        loadingImage.setImageResource(R.drawable.loading_animation);
        animationDrawable = (AnimationDrawable) loadingImage.getDrawable();
        animationDrawable.setOneShot(false);
//        animationDrawable.start();

        mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(view);

        // mDialog.setCanceledOnTouchOutside(false);
    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
            if (animationDrawable != null) {
                animationDrawable.start();
            }
        }
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            if (mDialog.getContext() instanceof Activity) {
                if (((Activity) mDialog.getContext()).isFinishing()) {
                    return;
                }
            }
            mDialog.dismiss();
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
        }
    }

}
