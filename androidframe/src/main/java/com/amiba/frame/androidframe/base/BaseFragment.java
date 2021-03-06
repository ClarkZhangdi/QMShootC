package com.amiba.frame.androidframe.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amiba.frame.androidframe.R;
import com.amiba.frame.androidframe.util.ResourceUtils;
import com.amiba.frame.androidframe.util.log.DebugLog;

public class BaseFragment extends Fragment implements OnClickListener,
		ViewPager.OnPageChangeListener{

	private float scale = 1f;//放大后的比例
	protected ProgressDialog mDialog;
	protected PopupWindow popupWindow;
	public Dialog loadingDialog;
	private Toast toast;
	public View fragmentView;
	private void onCreate() {
	}

	/**
	 * 加载等待
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void showDialog(){
		if (mDialog!=null&&!mDialog.isShowing()) {
			mDialog.show();
		}
	}
	/**
	 * 加载等待
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param msg
	 * @see [类、类#方法、类#成员]
	 */
	public void showDialog(String msg){
		if (mDialog!=null&&!mDialog.isShowing()) {
			mDialog.setMessage(msg);
			mDialog.setCancelable(true);
			mDialog.show();
		}
	}
	public void dismissDialog(){
		if (mDialog!=null&&mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}
	// 弹出自定义toast
	public void showToast(String content, int drawableId, int layoutId) {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = null;
		if (toast == null) {
			toast = new Toast(getActivity());
		}
		if (layoutId>0) {
			view = inflater.inflate(layoutId, null);
			toast.setView(view);
		}
		toast.setText(content);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT); // 显示时间长短
		toast.show();
	}
	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		
		try{
			Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		
		try{
			Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 长时间显示Toast提示(来自res) **/
	protected void showLongToast(int resId) {
		try{
			Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_LONG).show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
	}

	/** Debug输出Log日志 **/
	protected void showLogDebug(String tag, String msg) {
		DebugLog.d(tag, msg);
	}

	/** Error输出Log日志 **/
	protected void showLogError(String tag, String msg) {
		DebugLog.e(tag, msg);
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		//		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		//		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** 含有标题和内容的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	protected void dismissAlertDialog(Activity activity, AlertDialog alertDialog) {
		if (activity != null && !activity.isFinishing()) {
			if (alertDialog != null) {
				alertDialog.dismiss();
			}
		}
	}

	/** 含有标题、内容、一个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.show();
		return alertDialog;
	}
	/** 含有标题、内容、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 含有标题、内容、图标、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
                                          int icon, String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param mResId
	 * @param pResId
	 * @param nResId
	 * @param type
	 * @param view
	 * @see [类、类#方法、类#成员]
	 */
	public void showAlertDialog(String mResId, String pResId, String nResId, final int type ,
                                DialogInterface.OnClickListener onPositiveClickListener,
                                DialogInterface.OnClickListener onNegativeClickListener, View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		if (!TextUtils.isEmpty(mResId)) {
			builder.setMessage(mResId);
		}
		if (view!=null) {
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
	 * @param 持续时间
	 * @return 
	 */  
	public Animation scaleOut(long time, float fromX, float toX, float fromY, float toY){
		Animation animation = new ScaleAnimation(0,scale,0,scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		animation.setDuration(time);  
		animation.setInterpolator(getActivity(), android.R.anim.accelerate_decelerate_interpolator);
		animation.setFillAfter(false);  
		return animation;  
	}  
	/** 
	 * 以自身中心为圆心收缩 
	 * @param 持续时间 
	 * @return 
	 */  
	public Animation scaleIn(long time, float fromX, float toX, float fromY, float toY){
		Animation animation = new ScaleAnimation(scale,0,scale,0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		animation.setInterpolator(getActivity(), android.R.anim.accelerate_decelerate_interpolator);
		animation.setDuration(time);  
		animation.setFillAfter(false); 
		return animation;  
	}

	/** 加载中的对话框 **/
	protected void showLoadingDialog(String loadingText) {
		if(loadingDialog == null)
			loadingDialog = new Dialog(getActivity(), R.style.loading_dialog);
		//loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		View view  = View.inflate(getActivity(), R.layout.layout_loading, null);
		TextView tv_loading = (TextView) view.findViewById(R.id.tv_loading);
		tv_loading.setText(loadingText);
		loadingDialog.setContentView(view);
		loadingDialog.setCanceledOnTouchOutside(true);
		loadingDialog.show();
	}

	/** 加载中的对话框 **/
	protected void showLoadingDialog(String loadingText, int drawable) {
		if(loadingDialog == null)
			loadingDialog = new Dialog(getActivity(), R.style.loading_dialog);
		//loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		View view  = View.inflate(getActivity(), R.layout.layout_loading, null);
		TextView tv_loading = (TextView) view.findViewById(R.id.tv_loading);
		tv_loading.setText(loadingText);
		ProgressBar progressbar = (ProgressBar)view.findViewById(R.id.progressbar);
		progressbar.setIndeterminateDrawable(ResourceUtils.getDrawableWrapper(getContext(),drawable));
		loadingDialog.setContentView(view);
		loadingDialog.setCanceledOnTouchOutside(true);
		loadingDialog.show();
	}

	protected void showLoadingDialog(int loadingTextId) {
		showLoadingDialog(getString(loadingTextId));
	}

	protected void showLoadingDialog() {
		showLoadingDialog(getString(R.string.loading));
	}

	protected void showLoadingDialogDrawable(int drawable) {
		showLoadingDialog(getString(R.string.loading),drawable);
	}

	protected void disLoadDialog(){
		if(loadingDialog != null && loadingDialog.isShowing() ){
			loadingDialog.dismiss();
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

}
