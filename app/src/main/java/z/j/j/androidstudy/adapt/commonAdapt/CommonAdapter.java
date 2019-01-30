package z.j.j.androidstudy.adapt.commonAdapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sulj on 16/4/24.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mLayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = ViewHolder.get(mContext,convertView,parent, mLayoutId ,position);
        convert(holder, getItem(position),position);
        return holder.getConvertView();
    }


    public void convert(ViewHolder holder, T t){}

    public void convert(ViewHolder holder, T t,int position){
        convert(holder,t);
    }

}
