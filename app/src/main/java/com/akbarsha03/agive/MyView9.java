package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.BounceInterpolator;

/**
 * TODO: document your custom view class.
 */
public class MyView9 extends View {
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    private ValueAnimator scaleAnim;
    private Path path;
    private int scaleValue;
    private float pathLength;
    private int step;
    private int distance;
    private float[] pos;
    private Matrix matrix;
    private float[] tan;
    private Bitmap bm;
    private int bm_offsetX;
    private int bm_offsetY;
    private PathMeasure pathMeasure;
    private Bitmap earth;
    private int earth_offsetX;
    private int earth_offsetY;
    private String TAG = "CUSTOM_VIEW";

    public MyView9(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView9(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView9(Context context, AttributeSet attrs, int defStyle) {
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

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_moon);
        bm_offsetX = bm.getWidth() / 2;
        bm_offsetY = bm.getHeight() / 2;

        earth = BitmapFactory.decodeResource(getResources(), R.drawable.ic_earth);
        earth_offsetX = earth.getWidth() / 2;
        earth_offsetY = earth.getHeight() / 2;

        paint.setStrokeWidth(4);
        paint.setColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        paint2.setStrokeWidth(4);
        paint2.setColor(android.graphics.Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);

        path = new Path();
//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(300, 50);
//        path.lineTo(400, 150);
//        path.lineTo(100, 300);
//        path.lineTo(600, 300);
//        path.lineTo(100, 100);


        step = 1;
        distance = 0;
        pos = new float[2];
        tan = new float[2];

        matrix = new Matrix();

        a.recycle();

        Log.d(TAG, "init: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, paint);
        canvas.drawBitmap(earth, getWidth() / 2
                , getHeight() / 2, paint2);
        if (distance < pathLength) {

            matrix.reset();
            pathMeasure.getPosTan(distance, pos, tan);
            matrix.postTranslate(pos[0] - bm_offsetX, pos[1] - bm_offsetY);

            canvas.drawBitmap(bm, matrix, null);
            distance = distance + step + 5;
        } else {
            distance = 0;
        }
        invalidate();
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
        Log.d(TAG, "onLayout: ");
        path.addCircle(getWidth() / 2 + earth_offsetX, getHeight() / 2 + earth_offsetY, 100, Path.Direction.CW);
        path.close();

        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
    }
}
