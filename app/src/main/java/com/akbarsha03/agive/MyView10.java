package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.BounceInterpolator;

/**
 * TODO: document your custom view class.
 */
public class MyView10 extends View {
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    private ValueAnimator scaleAnim;
    private Path path;
    private String TAG = "CUSTOM_VIEW";
    private int scaleValue;
    private Paint paint3 = new Paint();
    private RectF rectF1 = new RectF();
    private RectF rectF2 = new RectF();
    private RectF rectF3 = new RectF();
    private RectF rectF4 = new RectF();

    public MyView10(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView10(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView10(Context context, AttributeSet attrs, int defStyle) {
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
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        paint2.setStrokeWidth(4);
        paint2.setColor(android.graphics.Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);

        paint3.setStrokeWidth(4);
        paint3.setColor(android.graphics.Color.RED);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setAntiAlias(true);

        path = new Path();

        a.recycle();

        Log.d(TAG, "init: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rectF1, paint);
        canvas.drawRect(rectF2, paint);
        canvas.drawRect(rectF3, paint);
        canvas.drawRect(rectF4, paint);
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

    public void startAutomatic() {

        scaleAnim = ValueAnimator.ofInt(15, 50);

        scaleAnim.setDuration(750);
        scaleAnim.setRepeatCount(0);
        scaleAnim.reverse();
        scaleAnim.setInterpolator(new BounceInterpolator());

        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleValue = (int) animation.getAnimatedValue();
                postInvalidate();

            }
        });
        scaleAnim.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            startAutomatic();
            postInvalidate();
            return true;
        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        rectF1.left = 0;
        rectF1.top = 0;
        rectF1.right = 200;
        rectF1.bottom = 200;

        rectF2.left = 300;
        rectF2.top = 0;
        rectF2.right = 0;
        rectF2.bottom = 400;

        rectF3.left = 0;
        rectF3.top = 500;
        rectF3.right = 600;
        rectF3.bottom = 0;

        rectF4.left = 200;
        rectF4.top = 200;
        rectF4.right = 0;
        rectF4.bottom = 0;
        Log.d(TAG, "onLayout: ");
    }
}
