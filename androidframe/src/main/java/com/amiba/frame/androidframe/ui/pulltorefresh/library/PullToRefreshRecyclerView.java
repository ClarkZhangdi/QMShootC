package com.amiba.frame.androidframe.ui.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.amiba.frame.androidframe.R;


/**
 * com.custom.wudl.testpulltorefresh.library
 * Created by wudl on 16/6/6.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    private RecyclerView.OnScrollListener onScrollListener, mOnScrollListener;
    private boolean mLastItemVisible;
    private OnLastItemVisibleListener mOnLastItemVisibleListener;
    private RecyclerView mRecyclerView;


    public PullToRefreshRecyclerView(Context context) {
        super(context);
        initListener();
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initListener();
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
        initListener();
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
        initListener();
    }


    private void initListener() {
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && null != mOnLastItemVisibleListener && mLastItemVisible) {
                    mOnLastItemVisibleListener.onLastItemVisible();
                }

                if (null != mOnScrollListener) {
                    mOnScrollListener.onScrollStateChanged(recyclerView, newState);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                RecyclerViewPositionHelper helper = RecyclerViewPositionHelper.createHelper(recyclerView);
                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = helper.getItemCount();
                int firstVisibleItem = helper.findFirstVisibleItemPosition();
                if (DEBUG) {
                    Log.d(LOG_TAG, "First Visible: " + firstVisibleItem + ". Visible Count: " + visibleItemCount
                            + ". Total Items:" + totalItemCount);
                }
                if (null != mOnLastItemVisibleListener) {
                    mLastItemVisible = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
                }
                if (null != mOnScrollListener) {
                    mOnScrollListener.onScrolled(recyclerView, dx, dy);
                }
            }
        };
        mRefreshableView.addOnScrollListener(onScrollListener);
    }

    public void setmOnScrollListener(RecyclerView.OnScrollListener mOnScrollListener) {
        this.mOnScrollListener = mOnScrollListener;
    }

    public void setmOnLastItemVisibleListener(OnLastItemVisibleListener mOnLastItemVisibleListener) {
        this.mOnLastItemVisibleListener = mOnLastItemVisibleListener;
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        mRecyclerView = new RecyclerView(context, attrs);
        mRecyclerView.setId(R.id.recyclerview);
        return mRecyclerView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return isLastItemVisible();
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    private boolean isFirstItemVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
        if (null == adapter || adapter.getItemCount() == 0) {
            if (DEBUG) {
                Log.d(LOG_TAG, "isFirstItemVisible. Empty View.");
            }
            return true;

        } else {
            int position = ((LinearLayoutManager) mRefreshableView.getLayoutManager()).findFirstVisibleItemPosition();
            if (position <= 1) {
                final View firstVisibleChild = mRefreshableView.getChildAt(0);
                if (firstVisibleChild != null) {
                    return firstVisibleChild.getTop() >= mRefreshableView.getTop();
                }
            }
        }
        return false;
    }

    private boolean isLastItemVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
        if (null == adapter || adapter.getItemCount() == 0) {
            if (DEBUG) {
                Log.d(LOG_TAG, "isLastItemVisible. Empty View.");
            }
            return true;
        } else {
            final int lastItemPosition = adapter.getItemCount() - 1;
            final int lastVisiblePosition = ((LinearLayoutManager) mRefreshableView.getLayoutManager()).findLastVisibleItemPosition();

            if (DEBUG) {
                Log.d(LOG_TAG, "isLastItemVisible. Last Item Position: " + lastItemPosition + " Last Visible Pos: "
                        + lastVisiblePosition);
            }
            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - ((LinearLayoutManager) mRefreshableView.getLayoutManager()).findFirstVisibleItemPosition();
                final View lastVisibleChild = mRefreshableView.getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    return lastVisibleChild.getBottom() <= mRefreshableView.getBottom();
                }
            }
        }
        return false;
    }

}
