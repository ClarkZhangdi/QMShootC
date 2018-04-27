/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amiba.frame.androidframe.util.qr_codescan.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.amiba.frame.androidframe.R;
import com.google.zxing.ResultPoint;

import java.util.Collection;
import java.util.HashSet;


/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 *
 */
public class ViewfinderView extends View {
	private static final String TAG = "log";
	private static final long ANIMATION_DELAY = 10L;
	private static final int OPAQUE = 0xFF;

	private int ScreenRate;

	private static final int CORNER_WIDTH = 5;
	private static final int MIDDLE_LINE_WIDTH = 6;

	private static final int MIDDLE_LINE_PADDING = 5;

	private static final int SPEEN_DISTANCE = 5;

	private static float density;
	private static final int TEXT_SIZE = 16;
	private static final int TEXT_PADDING_TOP = 30;

	private Paint paint;

	private int slideTop;

	private int slideBottom;

	private Bitmap resultBitmap;
	private final int maskColor;
	private final int resultColor;

	private final int resultPointColor;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;

	boolean isFirst;
	private int pWidth;
	private int pHeight;

	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		pWidth =  context.getResources().getDisplayMetrics().widthPixels;
		pHeight = context.getResources().getDisplayMetrics().heightPixels;
		density = context.getResources().getDisplayMetrics().density;
		ScreenRate = (int)(15 * density);

		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);
		resultColor = resources.getColor(R.color.result_view);

		resultPointColor = resources.getColor(R.color.possible_result_points);
		possibleResultPoints = new HashSet<ResultPoint>(5);
		postInvalidate();
	}

	public void resetMarginHeight(int marginHeight){
		pHeight = pHeight - marginHeight;
	}

	@Override
	public void onDraw(Canvas canvas) {
		int frameSide = pWidth *2/3;
		int[] startPoint = new int[2];
		startPoint[0] = pWidth*1/6;
		startPoint[1] = (pHeight/2) - (frameSide/2);
		int[] endPoint = new int[2];
		endPoint[0] = pWidth*5/6;
		endPoint[1] = (pHeight/2) + (frameSide/2);
		Rect frame = new Rect(startPoint[0], startPoint[1],endPoint[0], endPoint[1]);
		if (frame == null) {
			return;
		}

		if(!isFirst){
			isFirst = true;
			slideTop = frame.top;
			slideBottom = frame.bottom;
		}

		int width = canvas.getWidth();
		int height = canvas.getHeight();

		paint.setColor(resultBitmap != null ? resultColor : maskColor);

		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
				paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);



		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {

			paint.setColor(getResources().getColor(R.color.main_color));
			canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
					frame.top + CORNER_WIDTH, paint);
			canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
					+ ScreenRate, paint);
			canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
					frame.top + CORNER_WIDTH, paint);
			canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
					+ ScreenRate, paint);
			canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
					+ ScreenRate, frame.bottom, paint);
			canvas.drawRect(frame.left, frame.bottom - ScreenRate,
					frame.left + CORNER_WIDTH, frame.bottom, paint);
			canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
					frame.right, frame.bottom, paint);
			canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
					frame.right, frame.bottom, paint);


			slideTop += SPEEN_DISTANCE;
			if(slideTop >= frame.bottom){
				slideTop = frame.top;
			}
			canvas.drawRect(frame.left + MIDDLE_LINE_PADDING, slideTop - MIDDLE_LINE_WIDTH/2, frame.right - MIDDLE_LINE_PADDING,slideTop + MIDDLE_LINE_WIDTH/2, paint);
			paint.setColor(Color.WHITE);
			paint.setTextSize(TEXT_SIZE * density);
			paint.setTypeface(Typeface.create("System", Typeface.NORMAL));
			paint.setTextAlign(Paint.Align.CENTER);

			Paint mpaint = new Paint();
			mpaint.setColor(Color.TRANSPARENT);
			canvas.drawRect(frame.left , frame.bottom + frame.bottom*1/15,frame.right, frame.bottom + frame.bottom*2/15, mpaint);
			Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
			int baseline = (frame.bottom + frame.bottom*2/15 + frame.bottom + frame.bottom*1/15 - fontMetrics.bottom - fontMetrics.top) / 2;
			canvas.drawText(getResources().getString(R.string.scan_text), frame.centerX(), baseline, paint);



			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 6.0f, paint);
				}
			}
			if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 3.0f, paint);
				}
			}


			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
					frame.right, frame.bottom);

		}
	}

	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 *
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode) {
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}

}
