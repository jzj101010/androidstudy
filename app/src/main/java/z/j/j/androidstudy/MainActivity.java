package z.j.j.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxb.easynavigition.view.EasyNavigitionBar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import z.j.j.androidstudy.activity.BaseActivity;
import z.j.j.androidstudy.activity.ViewTestActivity;
import z.j.j.androidstudy.fragment.FirstFragment;
import z.j.j.androidstudy.fragment.FourthFragment;
import z.j.j.androidstudy.fragment.SecondFragment;
import z.j.j.androidstudy.fragment.ThirdFragment;
import z.j.j.androidstudy.utils.EventBusUtil;
import z.j.j.androidstudy.utils.ToastUtils;
import z.j.j.androidstudy.view.TopView;

public class MainActivity extends BaseActivity {
    private String[] tabText = {"首页", "发现", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    //选中时icon
    private int[] selectIcon = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};

    private List<android.support.v4.app.Fragment> fragments = new ArrayList<>();
    private EasyNavigitionBar navigitionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new FourthFragment());
        initView();
    }

    private void initView() {
         //https://github.com/hexingbo/EasyNavigition
        navigitionBar=findViewById(R.id.navigitionBar);
        navigitionBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
//                .mode(EasyNavigitionBar.MODE_ADD)
//                .addIcon(R.mipmap.ic_launcher_round)
//                .addLayoutHeight(100)
                .navigitionHeight(60)
                .canScroll(true)
//                .smoothScroll(true)
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
