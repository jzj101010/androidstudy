package z.j.j.androidstudy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hxb.easynavigition.view.EasyNavigitionBar;
import java.util.ArrayList;
import java.util.List;
import z.j.j.androidstudy.activity.BaseActivity;
import z.j.j.androidstudy.fragment.FirstFragment;
import z.j.j.androidstudy.fragment.FourthFragment;
import z.j.j.androidstudy.fragment.SecondFragment;
import z.j.j.androidstudy.fragment.ThirdFragment;


public class MainActivity extends BaseActivity {
    private String[] tabText = {"RECYCLERV", "VIEWPAGER", "me", "me"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.uncheck, R.mipmap.uncheck, R.mipmap.uncheck, R.mipmap.uncheck};
    //选中时icon
    private int[] selectIcon = {R.mipmap.check, R.mipmap.check, R.mipmap.check, R.mipmap.check};

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
                .mode(EasyNavigitionBar.MODE_ADD)
                .addIcon(R.mipmap.ic_launcher_round)
                .addIconSize(60)
                .addLayoutHeight(100)
                .navigitionHeight(60)
                .canScroll(true)
//                .smoothScroll(true)
                    .iconSize(25)
                   .tabTextSize(10)
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
