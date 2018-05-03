package com.amiba.frame.androidframe.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amiba.frame.androidframe.R;
import com.amiba.frame.androidframe.util.ResourceUtils;
import com.amiba.frame.androidframe.util.log.DebugLog;

import butterknife.ButterKnife;

/**
 * com.amiba.frame.androidframe.base
 * Created by wudl on 17/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private float scale = 1f;//放大后的比例
    public Dialog loadingDialog;
    protected ProgressDialog mDialog;
    private Toast toast;
    protected MyActivityManager activityManager;

    abstract protected int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());//把设置布局文件的操作交给继承的子类
//        绑定当前Activity
        ButterKnife.bind(this);
        activityManager = MyActivityManager.getMyActivityManager();
        activityManager.pushActivity(this);
        ViewGroup contentFrameLayout = findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null) {
            parentView.setFitsSystemWindows(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.popActivity(this);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    /**
     * 加载等待
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @see [类、类#方法、类#成员]
     */
    public void showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 加载等待
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param msg
     * @see [类、类#方法、类#成员]
     */
    public void showDialog(String msg) {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.setMessage(msg);
            mDialog.setCancelable(true);
            mDialog.show();
        }
    }

    protected void showLoadingDialogDrawable(int drawable) {
        showLoadingDialog(getString(R.string.loading), drawable);
    }

    /**
     * 加载中的对话框
     **/
    protected void showLoadingDialog(String loadingText, int drawable) {
        if (loadingDialog == null)
            loadingDialog = new Dialog(this, R.style.loading_dialog);
        //loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = View.inflate(this, R.layout.layout_loading, null);
        TextView tv_loading = view.findViewById(R.id.tv_loading);
        tv_loading.setText(loadingText);
        ProgressBar progressbar = view.findViewById(R.id.progressbar);
        progressbar.setIndeterminateDrawable(ResourceUtils.getDrawableWrapper(this, drawable));
        loadingDialog.setContentView(view);
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.show();
    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected void disLoadDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    // 弹出自定义toast
    public void showToast(String content, int drawableId, int layoutId) {
        LayoutInflater inflater = this.getLayoutInflater();
        View view;
        if (toast == null) {
            toast = new Toast(this);
        }
        if (layoutId > 0) {
            view = inflater.inflate(layoutId, null);
            toast.setView(view);
        }
        toast.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT); // 显示时间长短
        toast.show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void showShortToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    protected void showLongToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    protected void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    /**
     * Debug输出Log日志
     **/
    protected void showLogDebug(String tag, String msg) {
        DebugLog.d(tag, msg);
    }

    /**
     * Error输出Log日志
     **/
    protected void showLogError(String tag, String msg) {
        DebugLog.e(tag, msg);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
//		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
//		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有标题和内容的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message) {
        return new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).show();
    }

    protected void dismissAlertDialog(Activity activity, AlertDialog alertDialog) {
        if (activity != null && !activity.isFinishing()) {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        }
    }

    /**
     * 含有标题、内容、一个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener) {
        return new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .show();
    }

    /**
     * 含有标题、内容、两个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        return new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
    }

    /**
     * 含有标题、内容、图标、两个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message,
                                          int icon, String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        return new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).setIcon(icon)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param mResId
     * @param pResId
     * @param nResId
     * @param type
     * @param view
     * @see [类、类#方法、类#成员]
     */
    public void showAlertDialog(String mResId, String pResId, String nResId, final int type,
                                DialogInterface.OnClickListener onPositiveClickListener,
                                DialogInterface.OnClickListener onNegativeClickListener, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!TextUtils.isEmpty(mResId)) {
            builder.setMessage(mResId);
        }
        if (view != null) {
            builder.setView(view);
        }
        builder.setPositiveButton(pResId, onPositiveClickListener).setNegativeButton(nResId, onNegativeClickListener).create();
        builder.show();
    }

    @Override
    public void onClick(View v) {
//		switch (v.getId()) {
//		default:
//			break;
//		}
    }

    /**
     * 以自身中心为圆心扩放
     *
     * @return
     */
    public Animation scaleOut(long time, float fromX, float toX, float fromY, float toY) {
        Animation animation = new ScaleAnimation(0, scale, 0, scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(time);
        animation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);
        animation.setFillAfter(false);
        return animation;
    }

    /**
     * 以自身中心为圆心收缩
     *
     * @return
     */
    public Animation scaleIn(long time, float fromX, float toX, float fromY, float toY) {
        Animation animation = new ScaleAnimation(scale, 0, scale, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);
        animation.setDuration(time);
        animation.setFillAfter(false);
        return animation;
    }
}