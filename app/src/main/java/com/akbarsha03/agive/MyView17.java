package com.akbarsha03.agive;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by: akbarsha03 on 6/17/16.
 */
public class MyView17 extends View {

    public static final int PER_ROW = 6;
    public static final int PER_COLUMN = 8;
    private static final String TAG = "Canvas";
    private Path path1, path2, path3, path4;
    private Paint paint1, paint2, paint3, paint4;
    private Random random = new Random();
    private List<Rect> rects = new ArrayList<>();
    private Path p = new Path();
    private boolean isBackgroundDrawn = false;
    private Bitmap bitmap;
    private int bitmap_offsetX;
    private int bitmap_offsetY;
    private int step;
    private int distance;
    private float[] pos;
    private Matrix matrix;
    private float length;
    private PathMeasure pathMeasure;

    public MyView17(Context context) {
        super(context);
        init();
    }

    public MyView17(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView17(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView17(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint4 = new Paint();

        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
        path4 = new Path();

        paint1.setAntiAlias(true);
        paint1.setStrokeWidth(1f);
        paint1.setColor(Color.TRANSPARENT);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeJoin(Paint.Join.ROUND);

        paint3.setAntiAlias(true);
        paint3.setStrokeWidth(3f);
        paint3.setColor(Color.RED);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeJoin(Paint.Join.ROUND);

        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(3f);
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        Log.d(TAG, "init: ");

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rocket);
        bitmap_offsetX = bitmap.getWidth() / 2;
        bitmap_offsetY = bitmap.getHeight() / 2;

        step = 1;
        distance = 0;

        pos = new float[2];

        matrix = new Matrix();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: " + changed + " " + left + " " + top + " " + right + " " + bottom);

        int height = getHeight();
        int width = getWidth();

        int w = width / PER_ROW;
        int h = height / PER_COLUMN;

        rects.clear();
        for (int i = 0; i < PER_ROW; i++) {
            for (int j = 0; j < PER_COLUMN; j++) {
                rects.add(new Rect(i * w, j * h, (i * w) + w, (j * h) + h));
            }
        }

        path1.addCircle(width / 2, height / 2, 20, Path.Direction.CW);
        pathMeasure = new PathMeasure(path1, true);
        length = pathMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        if (!isBackgroundDrawn) {
//            canvas.drawColor(Color.BLACK);
//
//            for (Rect rect : rects) {
//                p.reset();
//                int y = getRandom(rect.top, rect.bottom);
//                int x = getRandom(rect.left, rect.right);
//                int r = getRandom(1, 5);
//                p.addCircle(x, y, r, Path.Direction.CW);
//                canvas.drawPath(p, paint2);
//            }
//            isBackgroundDrawn = true;
//            canvas.drawPath(path1, paint3);
//        }

        canvas.save();
        if (distance < length) {

            matrix.reset();
            pathMeasure.getPosTan(distance, pos, null);
            matrix.postTranslate(pos[0] - bitmap_offsetX, pos[1] - bitmap_offsetY);

            canvas.drawBitmap(bitmap, matrix, null);
            distance = distance + step + 5;
        } else {
            distance = 0;
        }
        canvas.restore();
        invalidate();

    }

    public int getRandom(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

/*
    private void startAnim() {

        ValueAnimator scaleAnim = ValueAnimator.ofInt(1, 5);
        scaleAnim.setDuration(750);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.setStartDelay(0);

        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                random = (int) animation.getAnimatedValue();
                postInvalidate();

            }
        });
        scaleAnim.start();
    }
*/

}
