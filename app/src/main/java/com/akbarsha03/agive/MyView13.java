package com.akbarsha03.agive;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;

/**
 * TODO: document your custom view class.
 */
public class MyView13 extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    private Path path2 = new Path();
    private Path path3 = new Path();
    private String TAG = "CUSTOM_VIEW";
    private int firstPoint;
    private int secondPoint;
    private int thirdPoint;
    private int[] bottomThreePoints;
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();

    public MyView13(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView13(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView13(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        Log.d(TAG, "onApplyWindowInsets: ");
        return super.onApplyWindowInsets(insets);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint2.setStrokeWidth(4);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);

        a.recycle();

        Log.d(TAG, "init: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint2);
        canvas.drawPath(path3, paint3);
        Log.d(TAG, "onDraw: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
    }

    @Override
    public void setOnSystemUiVisibilityChangeListener(OnSystemUiVisibilityChangeListener l) {
        super.setOnSystemUiVisibilityChangeListener(l);
        Log.d(TAG, "setOnSystemUiVisibilityChangeListener: ");
    }

    @Override
    public void onWindowSystemUiVisibilityChanged(int visible) {
        super.onWindowSystemUiVisibilityChanged(visible);
        Log.d(TAG, "onWindowSystemUiVisibilityChanged: ");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            return true;
        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int height = getHeight();
        int width = getWidth();

        /**
         * Two points from the top line
         */
        int topMiddle = width / 2;
        int incrementalPoint = topMiddle / 2;
        secondPoint = incrementalPoint;
        thirdPoint = incrementalPoint * 3;

        /**
         * One point from the left line
         */
        int leftMiddle;
        leftMiddle = height / 2;
        firstPoint = leftMiddle;

        /**
         * Three points from the bottom line
         */
        int bottomMiddle;
        bottomMiddle = topMiddle;
//        int bottomIncrementalPoint = bottomMiddle / 4;

        bottomThreePoints = new int[3];
        for (int i = 0; i < 3; i++) {
            bottomThreePoints[i] = width;
        }
        Log.d(TAG, "onLayout: " + firstPoint + " " + secondPoint + " " + thirdPoint);
        for (int bottomThreePoint : bottomThreePoints) {
            Log.d(TAG, "Point: " + bottomThreePoint);
        }

        paint.setShader(new LinearGradient(0, 0, width, height, 0x5B3E3E3E, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        paint2.setShader(new LinearGradient(0, 0, secondPoint, height, 0x5B3E3E3E, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        paint3.setShader(new LinearGradient(0, 0, thirdPoint, height, 0x5B3E3E3E, Color.TRANSPARENT, Shader.TileMode.CLAMP));


        path.moveTo(0, firstPoint);
        path.lineTo(bottomThreePoints[0], height);
        path.lineTo(0, height);
        path.close();

        path2.moveTo(0, firstPoint);
        path2.lineTo(0, 0);
        path2.lineTo(secondPoint, 0);
        path2.lineTo(bottomThreePoints[1], height);
        path2.lineTo(bottomThreePoints[0], height);
        path2.close();

        path3.moveTo(thirdPoint, 0);
        path3.lineTo(bottomThreePoints[2], height);
        path3.lineTo(bottomThreePoints[1], height);
        path3.lineTo(secondPoint, 0);
        path3.close();
    }
}
