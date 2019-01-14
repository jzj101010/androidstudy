package z.j.j.androidstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import z.j.j.androidstudy.R;

/**
 * Created by j on 2018/11/26 0026.
 */

public class ThirdFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.myview_layout,null);
        return view;
    }
}
