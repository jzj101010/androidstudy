package z.j.j.androidstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.utils.ToastUtils;

/**
 * Created by j on 2018/11/26 0026.
 */

public class FirstFragment extends BaseFragment {


    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.firstfragment,null);

        return view;
    }
}
