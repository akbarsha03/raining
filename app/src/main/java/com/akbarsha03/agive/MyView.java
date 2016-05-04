package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Line> lines2 = new ArrayList<>();
    private int[] scaleValue;
    private int[] scaleValue2;
    private ValueAnimator scaleAnim;
    private ValueAnimator scaleAnim2;

    public MyView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
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

        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(6f);
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeJoin(Paint.Join.ROUND);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }

        if (lines.isEmpty()) {
            int width = getWidth() / 20;
            scaleValue = new int[width];
            scaleValue2 = new int[width];
            int x = 0;
            int y = 50;
            for (int i = 0; i < width; i++) {
                x += 20;
                lines.add(new Line(x, 0, x, y));
            }
            for (int j = 0; j < width; j++) {
                x += 10;
                lines2.add(new Line(x, 0, x, y));
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            canvas.save();
            canvas.translate(0, scaleValue[i]);
            canvas.translate(0, scaleValue2[i]);
            canvas.drawLine(lines.get(i).startX, lines.get(i).startY, lines.get(i).stopX, lines.get(i).stopY, paint);
            canvas.drawLine(lines2.get(i).startX, lines2.get(i).startY, lines2.get(i).stopX, lines2.get(i).stopY, paint2);
            canvas.restore();
            Log.d("LINETAG", "onDraw: " + lines.get(i).stopX + (scaleValue[i] * 100));
        }
//        for (int i = 0; i < lines2.size(); i++) {
//            canvas.save();
//            canvas.restore();
//            Log.d("LINETAG", "onDraw: " + lines.get(i).stopX + (scaleValue[i] * 100));
//        }
    }

    public void startAutomatic() {

        for (int i = 0; i < lines.size(); i++) {

            scaleAnim = ValueAnimator.ofInt(0, getHeight());

            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(-1);
            int randomDelay = new Random().nextInt(750 - 250) + 250;
            scaleAnim.setStartDelay(randomDelay);

            final int finalI = i;
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleValue[finalI] = (int) animation.getAnimatedValue();
                    postInvalidate();

                }
            });
            scaleAnim.start();
        }
        for (int i = 0; i < lines.size(); i++) {

            scaleAnim2 = ValueAnimator.ofInt(0, getHeight());

            scaleAnim2.setDuration(750);
            scaleAnim2.setRepeatCount(-1);
            int randomDelay = new Random().nextInt(750 - 250) + 250;
            scaleAnim2.setStartDelay(randomDelay);

            final int finalI = i;
            scaleAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleValue2[finalI] = (int) animation.getAnimatedValue();
                    postInvalidate();

                }
            });
            scaleAnim2.start();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startAutomatic();
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
