package z.j.j.androidstudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import z.j.j.androidstudy.R;
import z.j.j.androidstudy.activity.BaseActivity;
import z.j.j.androidstudy.utils.ToastUtils;

/**
 * Created by j on 2018/11/26 0026.
 */

public class FirstFragment extends BaseFragment {

    XRecyclerView recyclerView;
    View view;
     RecyclerViewAdapt recyclerViewAdapt;
     List list= new ArrayList();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(activity, R.layout.firstfragment,null);
        recyclerView=view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.add("1");
                recyclerViewAdapt.notifyDataSetChanged();
//                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                list.remove(list.size()-1);
                recyclerViewAdapt.notifyDataSetChanged();
//                recyclerView.loadMoreComplete();
            }
        });
        recyclerViewAdapt=new RecyclerViewAdapt(activity,list);
        recyclerView.setAdapter(recyclerViewAdapt);
        return view;
    }


    public class RecyclerViewAdapt extends RecyclerView.Adapter <RecyclerViewAdapt.ViewHolder>{
        Context context;
        List list;

       public RecyclerViewAdapt(Context context, List<Object> list){
           this.context=context;
           this.list=list;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           TextView tv=new TextView(context);
            ViewHolder  viewHolder=new ViewHolder(tv);
            viewHolder.tv=tv;
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(position+"");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class  ViewHolder extends RecyclerView.ViewHolder{

             public  TextView tv;
            public ViewHolder(View itemView) {
                super(itemView);
            }

        }
    }


}
