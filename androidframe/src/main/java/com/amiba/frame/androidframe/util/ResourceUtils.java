package com.amiba.frame.androidframe.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wudl on 16/4/26.
 */
public class ResourceUtils {


    public static Drawable getDrawableWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public static int getColorWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }

    public static void setBackground(View layout, Drawable drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            layout.setBackgroundDrawable(drawable);
        } else {
            layout.setBackground(drawable);
        }
    }

    public static int getColorId(Context mContext, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.getResources().getColor(color, mContext.getTheme());
        } else {
            return mContext.getResources().getColor(color);
        }
    }

    /**
     * @param mContext
     * @param textView
     * @param textsize_id 字体大小的resourceId
     */
    public static void setTextSize(Context mContext, TextView textView, int textsize_id) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(textsize_id));
    }
}
