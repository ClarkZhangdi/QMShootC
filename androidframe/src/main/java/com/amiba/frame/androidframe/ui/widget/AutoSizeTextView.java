package com.amiba.frame.androidframe.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * com.android.base.androidframework.ui.view.widget
 * Created by wudl on 16/11/17.
 */
public class AutoSizeTextView extends TextView {

    // Attributes
    private Paint paint;
    private float textSize;

    public AutoSizeTextView(Context context) {
        super(context);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * Re size the font so the specified text fits in the text box * assuming
     * the text box is the specified width.
     * 在此方法中学习到：getTextSize返回值是以像素(px)为单位的，而setTextSize()是以sp为单位的，
     * 因此要这样设置setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            paint = new Paint();
            paint.set(this.getPaint());
            //获得当前TextView的有效宽度
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float[] widths = new float[text.length()];
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            //所有字符串所占像素宽度
            int textWidths = rect.width();
            textSize = this.getTextSize();//这个返回的单位为px
            while(textWidths > availableWidth) {
                textSize = textSize - 1;
                paint.setTextSize(textSize);//这里传入的单位是px
                textWidths = paint.getTextWidths(text, widths);
            }
        }
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);//这里制定传入的单位是px }
    }

    ;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refitText(getText().toString(), this.getWidth());
    }
}
