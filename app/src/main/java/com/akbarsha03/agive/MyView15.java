package com.akbarsha03.agive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created : Akbar Sha Ebrahim on 6/7/2016.
 */
public class MyView15 extends View {
    Paint paint = new Paint();
    private Bitmap bitmap;
    private int bitmap_offsetX;
    private int bitmap_offsetY;
    private int distance;
    private float step;
    private float[] pos;
    private float[] tan;
    private Matrix matrix;
    private boolean isInitialized = false;
    private int length;
    private PathMeasure pathMeasure;
    private Path circle;
    float angle = 5;

    public MyView15(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView15(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView15(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.RED);
        paint.setTextSize(30f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rocket);
        bitmap_offsetX = bitmap.getWidth() / 2;
        bitmap_offsetY = bitmap.getHeight() / 2;

        step = 1;
        distance = 0;

        pos = new float[2];
        tan = new float[2];

        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        float radius = 30;
        float x = (float) (cx + Math.cos(angle * Math.PI / 180F) * radius);
        float y = (float) (cy + Math.sin(angle * Math.PI / 180F) * radius);

        canvas.drawBitmap(bitmap, x, y, null);

        if (angle < 360) {
            angle += 5;
        }else{
            angle=0;
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}

