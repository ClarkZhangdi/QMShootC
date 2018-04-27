package com.amiba.frame.androidframe.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amiba.frame.androidframe.util.PhoneUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * com.android.base.androidframework.ui.view.widget
 * Created by wudl on 16/7/14.
 */
public class DisSwipeViewPager extends ViewPager {

    private boolean isEnableSwipe;//是否左右滑动
    private boolean isTimer = false;//是否计时
    private Activity mActivity; // 上下文
    private List<View> mListViews; // 图片组
    private int mScrollTime = 0;
    private Timer timer;
    private int curIndex = 0;
    private boolean copytag;
    private int size = 0;

    /** 图片宽和高的比例 */
    private float ratio = 1.93f;

    public DisSwipeViewPager(Context context) {
        super(context);
    }

    public DisSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsTimer(boolean isTimer) {
        this.isTimer = isTimer;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*返回true时发生截断,将事件传给当前控件的onTouchEvent,
        否则,传递给子控件的onInterceptTouchEvent
        要实现ViewPager的不可滑动事件,就要发生截断,所以此时当需要不可滑动时,需要返回true去截断
        ViewPager只需要截断左右滑动事件,其他事件不需要截断*/
        /*if (isEnableSwipe) {
            return super.onInterceptTouchEvent(ev);
        }else {
            return true;
        }*/
        return  isEnableSwipe && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        /*当onInterceptTouchEvent和onTouchEvent同时返回true,则当前事件被消费,
        并且后续的事件(如跟着ACTION_DOWN的ACTION_MOVE或者ACTION_UP)直接传递到当前控件的onTouchEvent,不传递给任何其他控件的函数
        同时传给子控件一个ACTION_CANCEL事件
        */
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            startTimer();
        } else {
            stopTimer();
        }
        return isEnableSwipe && super.onTouchEvent(ev);
    }

    public boolean isEnableSwipe() {
        return isEnableSwipe;
    }

    public void setIsEnableSwipe(boolean isEnableSwipe) {
        this.isEnableSwipe = isEnableSwipe;
    }

    /**
     *
     * @param mainActivity
     * @param imgList
     * @param scrollTime 轮播间隔时间
     * @param ll_circle 小圆点布局
     * @param backgroundId 小圆点背景
     */
    public void start(Activity mainActivity, List<View> imgList,
                      int scrollTime, LinearLayout ll_circle, int backgroundId/*,
                      int ovalLayoutItemId, int focusedId, int normalId,boolean copytag*/) {
        mActivity = mainActivity;
        mListViews = imgList;
        mScrollTime = scrollTime;
//        this.copytag = copytag;
        initCircleView(mainActivity,ll_circle,imgList.size(),backgroundId);
        this.setAdapter(new MyPagerAdapter());// 设置适配器
        if (scrollTime != 0 && imgList.size() > 1) {

            startTimer();
        }
        if (mListViews.size() > 1) {
            this.setCurrentItem((Integer.MAX_VALUE / 2)
                    - (Integer.MAX_VALUE / 2) % mListViews.size());// 设置选中为中间/图片为和第0张一样
        }
    }


    private void initCircleView(Context context, final LinearLayout ll_circle, int circleCount, int backgroundId){
        ll_circle.removeAllViews();
        for (int i=0; i<  circleCount; i++){
            View view =new View(context);
            int width = PhoneUtils.convertDIP2PX(context, 10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,width);
            int  interval = PhoneUtils.convertDIP2PX(context,5);
            layoutParams.setMargins(interval,0,0,0);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(backgroundId);
            if (i ==0 ){
                view.setSelected(true);
            }
            ll_circle.addView(view);
            this.addOnPageChangeListener(new OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    for (int i=0; i < ll_circle.getChildCount();i++){
                        if (mListViews != null && mListViews.size() > 0 && i == position % mListViews.size()){
                            ll_circle.getChildAt(i).setSelected(true);
                        }else{
                            ll_circle.getChildAt(i).setSelected(false);
                        }
                    }
                }

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
        if (circleCount <= 1){
            ll_circle.removeAllViews();
        }
    }
    /**
     * 取得当明选中下标
     * @return
     */
    public int getCurIndex() {
        return curIndex;
    }
    /**
     * 停止滚动
     */
    public void stopTimer() {
        if (isTimer && timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 开始滚动
     */
    public void startTimer() {
        stopTimer();
        if (!isTimer) return;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        DisSwipeViewPager.this.setCurrentItem(DisSwipeViewPager.this
                                .getCurrentItem() + 1);
                    }
                });
            }
        }, mScrollTime, mScrollTime);
    }

    // 适配器 //循环设置
    private class MyPagerAdapter extends PagerAdapter {
        public void finishUpdate(View arg0) {
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        public int getCount() {
            if (mListViews.size() == 1) {
                return mListViews.size();
            }
            return Integer.MAX_VALUE;
        }

        public Object instantiateItem(View v, int i) {
            if (mListViews == null || mListViews.size() == 0) return null;
            if (((ViewPager) v).getChildCount() == mListViews.size()) {
                ((ViewPager) v).removeView(mListViews.get(i % mListViews.size()));
            }
            ViewGroup parent = (ViewGroup) mListViews.get(i % mListViews.size()).getParent();
            if(parent != null){
                parent.removeView(mListViews.get(i % mListViews.size()));
            }
            ((ViewPager) v).addView(mListViews.get(i % mListViews.size()));
            return mListViews.get(i % mListViews.size());
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        public Parcelable saveState() {
            return null;
        }

        public void startUpdate(View arg0) {
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 父容器传过来的宽度方向上的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 父容器传过来的高度方向上的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 父容器传过来的宽度的值
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
                - getPaddingRight();
        // 父容器传过来的高度的值
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingLeft()
                - getPaddingRight();

        if (widthMode == MeasureSpec.EXACTLY
                && heightMode != MeasureSpec.EXACTLY && ratio != 0.0f) {
            // 判断条件为，宽度模式为Exactly，也就是填充父窗体或者是指定宽度；
            // 且高度模式不是Exaclty，代表设置的既不是fill_parent也不是具体的值，于是需要具体测量
            // 且图片的宽高比已经赋值完毕，不再是0.0f
            // 表示宽度确定，要测量高度
            height = (int) (width / ratio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                    MeasureSpec.EXACTLY);
        } else if (widthMode != MeasureSpec.EXACTLY
                && heightMode == MeasureSpec.EXACTLY && ratio != 0.0f) {
            // 判断条件跟上面的相反，宽度方向和高度方向的条件互换
            // 表示高度确定，要测量宽度
            width = (int) (height * ratio + 0.5f);

            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
