package z.j.j.androidstudy.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import z.j.j.androidstudy.activity.BaseActivity;

/**
 * Created by j on 2018/11/26 0026.
 */

public class BaseFragment extends Fragment {
    BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (BaseActivity) context;
    }
}
