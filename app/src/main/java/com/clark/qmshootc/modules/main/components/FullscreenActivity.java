package com.clark.qmshootc.modules.main.components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.clark.qmshootc.R;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;
import com.clark.qmshootc.modules.main.views.viewpager.MainViewPager;
import com.clark.qmshootc.modules.main.views.viewpager.fragment.MainPagerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends BaseActivity {

    @BindView(R.id.id_tv_home_page)
    TextView idTvHomePage;
    @BindView(R.id.id_tv_find_page)
    TextView idTvFindPage;
    @BindView(R.id.id_iv_publish_page)
    ImageView idIvPublishPage;
    @BindView(R.id.id_tv_message_page)
    TextView idTvMessagePage;
    @BindView(R.id.id_tv_my_page)
    TextView idTvMyPage;
    @BindView(R.id.id_mvp_main)
    MainViewPager idMvpMain;


    private TextView currentSelectView = null;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fullscreen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        设置界面不跟随系统状态栏和导航栏改变
        FullScreenUtils.setFitsSystem(this);
//        设置界面全屏模式/显示状态栏
        FullScreenUtils.FullScreen(this);
//        设置presenter
        ButterKnife.bind(this);
        List<Fragment> pagers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MainPagerFragment fragment = new MainPagerFragment();
            fragment.setAppCompatActivity(this);
            pagers.add(fragment);
        }
        idMvpMain.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), pagers));

        init();

    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        setNavigationBarStatus();

    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        /**
         * 返回需要展示的fragment
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        /**
         * 返回需要展示的fragment的数量
         *
         * @return
         */
        @Override
        public int getCount() {
            return list.size();
        }
    }


    /**
     * 根据ViewPager内容显示导航栏选中状态
     */
    private void setNavigationBarStatus() {
        idTvHomePage.setOnClickListener(this);
        idTvFindPage.setOnClickListener(this);
        idIvPublishPage.setOnClickListener(this);
        idTvMessagePage.setOnClickListener(this);
        idTvMyPage.setOnClickListener(this);

//        根据ViewPage的状态来设置导航栏状态
        idMvpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        onClick(idTvHomePage);
                        break;
                    case 1:
                        onClick(idTvFindPage);
                        break;
                    case 2:
                        onClick(idTvMessagePage);
                        break;
                    case 3:
                        onClick(idTvMyPage);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        TextView newClickTextView = (TextView) v;
//        未点击过按钮时
        if (currentSelectView == null) {
            setTextViewSelected(newClickTextView);
            currentSelectView = newClickTextView;
            return;

        }
//        设置新的按钮选中状态
        setTextViewSelected(newClickTextView);
//        取消旧的按钮选中状态
        setTextViewUnselected(currentSelectView);
        currentSelectView = newClickTextView;

        switch (v.getId()) {
            case R.id.id_tv_home_page:
                idMvpMain.setCurrentItem(0);
                break;
            case R.id.id_tv_find_page:
                idMvpMain.setCurrentItem(1);
                break;
            case R.id.id_tv_message_page:
                idMvpMain.setCurrentItem(2);
                break;
            case R.id.id_tv_my_page:
                idMvpMain.setCurrentItem(3);
                break;

        }

    }

    private void setTextViewSelected(TextView textView) {
        textView.setEnabled(false);
        textView.setTextSize(16);
        textView.setSelected(true);
    }

    private void setTextViewUnselected(TextView textView) {
        textView.setEnabled(true);
        textView.setTextSize(14);
        textView.setSelected(false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenUtils.FullScreen(this);
    }


}
