package z.j.j.androidstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import z.j.j.androidstudy.activity.BaseActivity;
import z.j.j.androidstudy.utils.ToastUtils;
import z.j.j.androidstudy.view.TopView;

public class MainActivity extends BaseActivity {
    private TopView topView;
    private TopView topview;
    private XRecyclerView mRecyclerView;
    private List list=new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("1");
        list.add("2");
        list.add("3");
        initView();
    }

    private void initView() {
        topview = (TopView) findViewById(R.id.topview);
        topview.getTop_title().setText("XRecyclerView");
        topview.getTop_left().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView=findViewById(R.id.xrecycler);
        final MyAdapter  mAdapter=new MyAdapter();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToas((String) list.get(position)) ;
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        mRecyclerView.setArrowImageView(R.drawable.ic_launcher_background);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.remove(list.size()-1);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLoadMore() {
                list.add(list.size()+1+"");
                mAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

   class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

       public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
           this.onItemClickListener = onItemClickListener;
       }

       OnItemClickListener onItemClickListener;
       @Override
       public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           TextView textView=new TextView(MainActivity.this);
           textView.setTextSize(15);
           textView.setTextColor(0xff3093fe);
           ViewHolder viewHolder = new ViewHolder(textView);
           viewHolder.mTxt=textView;
           return viewHolder;
       }

       @Override
       public void onBindViewHolder(final ViewHolder holder, final int position) {
           holder.mTxt.setText(list.get(position)+"");
           holder.mTxt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(onItemClickListener!=null){
                       onItemClickListener.onItemClick(v,position);
                   }
               }
           });
       }

       @Override
       public int getItemCount() {
           return list.size();
       }

       public class ViewHolder extends RecyclerView.ViewHolder
       {
           public ViewHolder(View arg0)
           {
               super(arg0);
           }
           TextView mTxt;


       }


   }
    public   interface    OnItemClickListener {
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
