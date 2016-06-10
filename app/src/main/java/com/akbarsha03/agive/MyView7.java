package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * TODO: document your custom view class.
 */
public class MyView7 extends View {
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;
    Paint paint = new Paint();
    private int scaleValue = 15;
    private ValueAnimator scaleAnim;
    private int centerX;
    private int centerY;
    private Path path;
    private RectF rectF;
    private Path path2;

    public MyView7(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView7(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView7(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        mExampleColor = a.getColor(
                R.styleable.MyView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.MyView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.MyView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.MyView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        paint.setStrokeWidth(4);
        paint.setColor(android.graphics.Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        path = new Path();
        path2 = new Path();

        path.moveTo(100, 100);
        path.quadTo(115, 200, 400, 100);

        path2.moveTo(100, 100);
        path2.lineTo(115, 200);
        path2.lineTo(400, 100);

        rectF = new RectF();
        rectF.left = 100;
        rectF.bottom = 200;
        rectF.right = 200;
        rectF.top = 100;

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint);
    }


    public void startAutomatic() {

        scaleAnim = ValueAnimator.ofInt(15, 50);

        scaleAnim.setDuration(1500);
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

            centerX = (int) event.getX();
            centerY = (int) event.getY();
            startAutomatic();
            postInvalidate();
            return true;
        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    class Line {
        float startX, startY, stopX, stopY;

        public Line(float startX, float startY, float stopX, float stopY) {
            this.startX = startX;
            this.startY = startY;
            this.stopX = stopX;
            this.stopY = stopY;
        }

        public Line(float startX, float startY) { // for convenience
            this(startX, startY, startX, startY);
        }
    }
}
