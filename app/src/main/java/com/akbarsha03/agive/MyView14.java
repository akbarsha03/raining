package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.BounceInterpolator;

/**
 * TODO: document your custom view class.
 */
public class MyView14 extends View {
    Paint paint = new Paint();
    private ValueAnimator scaleAnim;
    private Path path;
    private String TAG = "CUSTOM_VIEW";
    private int scaleValue;
    private PathShape pathShape;
    private Path middlePathLeft;
    private Path middlePathRight;
    private Paint paint1;
    private float[] middlePositionLeft;
    private float[] middlePositionRight;

    public MyView14(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView14(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView14(Context context, AttributeSet attrs, int defStyle) {
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
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        path = new Path();

//        This is for circle
//        path.moveTo(100, 100);
//        path.cubicTo(100, 100, 0, 200, 100, 300);
//        path.cubicTo(100, 300, 200, 400, 300, 300);
//        path.cubicTo(300, 300, 400, 200, 300, 100);
//        path.cubicTo(300, 100, 200, 0, 100, 100);
//        path.close();

        paint1 = new Paint();
        paint1.setStrokeWidth(4);
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);

        middlePathLeft = new Path();
        middlePathLeft.moveTo(300, 100);
        middlePathLeft.lineTo(100, 300);
        PathMeasure pathMeasureLeft = new PathMeasure(middlePathLeft, true);
        middlePositionLeft = new float[2];
        pathMeasureLeft.getPosTan(pathMeasureLeft.getLength() / 2, middlePositionLeft, null);

        middlePathRight = new Path();
        middlePathRight.moveTo(300, 100);
        middlePathRight.lineTo(500, 300);
        PathMeasure pathMeasureRight = new PathMeasure(middlePathRight, true);
        middlePositionRight = new float[2];
        pathMeasureRight.getPosTan(pathMeasureRight.getLength() / 2, middlePositionRight, null);

//        This is for triangle
//        path.moveTo(300, 100);
//        path.cubicTo(300, 100, middlePositionLeft[0], middlePositionLeft[1], 100, 300);
//        path.cubicTo(100, 300, 200, 300, 300, 300);
//        path.cubicTo(300, 300, 400, 300, 500, 300);
//        path.cubicTo(500, 300, middlePositionRight[0], middlePositionRight[1], 300, 100);
//        path.close();

//        pathShape = new PathShape(path, 400, 400);

//        This is for Rectangle
//        path.moveTo(100, 100);
//        path.cubicTo(100, 100, 100, 200, 100, 300);
//        path.cubicTo(100, 300, 200, 300, 300, 300);
//        path.cubicTo(300, 300, 300, 200, 300, 100);
//        path.cubicTo(300, 100, 200, 100, 100, 100);
//        path.close();

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.moveTo(100, 100);
        path.cubicTo(100, 100, 0, 200, 100, 300);
        path.cubicTo(100, 300, 200, 400, 300, 300);
        path.cubicTo(300, 300, 400, 200, 300, 100);
        path.cubicTo(300, 100, 200, 0, 100, 100);
        path.close();

        canvas.drawPath(path, paint);
//        pathShape.draw(canvas, paint);
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
    }
}
