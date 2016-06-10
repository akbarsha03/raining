package com.akbarsha03.agive;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
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
public class MyView3 extends View {
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;
    Paint paint /*= new Paint()*/;
    Paint paint2 = new Paint();
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Line> lines2 = new ArrayList<>();
    private int scaleValue = 50;
    private ValueAnimator scaleAnim;
    private ValueAnimator scaleAnim2;
    private Path myPath1;
    private PointF mPoint1;
    private PointF mPoint2;
    private int x = 300;
    private int y = 50;
    private boolean reverseScaleAnim = false;
    private ArrayList<Integer> reverseScale = new ArrayList<>();
    private int reverseScaleValue;
    private boolean setLastPosition = false;
    private int temp = 0;
    private int modifyValue;
    private int localValue;
    private int[] scalie;
    private int incrementer = 0;

    public MyView3(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView3(Context context, AttributeSet attrs, int defStyle) {
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

//        paint.setAntiAlias(true);
//        paint.setStrokeWidth(6f);
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//
//        paint2.setAntiAlias(true);
//        paint2.setStrokeWidth(6f);
//        paint2.setColor(Color.RED);
//        paint2.setStyle(Paint.Style.STROKE);
//        paint2.setStrokeJoin(Paint.Join.ROUND);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (paint == null) {
            mPoint1 = new PointF(getWidth() / 1.2F, getHeight() / 1.2F);
            mPoint2 = new PointF(getWidth() / 24, getHeight() / 1.2F);
            myPath1 = new Path();
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLUE);

            int h = getHeight() / 2;

            scalie = new int[h / 10];
            int tem = 10;
            for (int i = 0; i < h / 10; i++) {
                tem = tem + 10;
                scalie[i] = tem;
            }
        }

        myPath1 = drawCurve(canvas, paint, mPoint1, mPoint2);
        canvas.drawPath(myPath1, paint);
    }

    private Path drawCurve(Canvas canvas, Paint paint, PointF mPointa, PointF mPointb) {

        Path myPath = new Path();
//        myPath.setFillType(Path.FillType.INVERSE_WINDING);
//        myPath.moveTo(0, 50);
        if (reverseScaleAnim) {
//            for (int i = reverseScale.size(); i > 0; i--) {
//             else
            myPath.quadTo(getWidth() / 2, reverseScaleValue, getWidth(), 0/*, mPointb.y , mPointb.y */);
//            }
//            reverseScaleAnim = false;
//            reverseScale.clear();
        } else if (setLastPosition) {
//            scaleAnim.end();
            myPath.quadTo(getWidth() / 2, 0, getWidth(), 0/*, mPointb.y , mPointb.y */);
//            setLastPosition = false;
        } else {

            myPath.quadTo(getWidth() / 2, scalie[incrementer], getWidth(), 0/*, mPointb.y , mPointb.y */);
        }
        return myPath;
    }

    public void startAutomatic() {

        scaleAnim = ValueAnimator.ofInt(0, getHeight());

        scaleAnim.setDuration(250);
        scaleAnim.setRepeatCount(-1);
        int randomDelay = new Random().nextInt(750 - 250) + 250;
        scaleAnim.setStartDelay(randomDelay);

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
//            startAutomatic();
            x = (int) event.getX();
            y = (int) event.getY();
            temp = (int) event.getY(); // 98
            Log.d("TAG", "first value " + temp);
            reverseScale.clear();
            setLastPosition = false;
//            postInvalidate();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {

//            localValue = /*-1 * */(int) event.getAxisValue(MotionEvent.AXIS_Y) - localValue;

            if (scalie.length - 1 > incrementer) {
                Log.d("TAG", "if " + scalie.length + " >= " + incrementer);
                ++incrementer;
                Log.d("TAG", "incremented: " + incrementer);


                Log.d("TAG", "LOCAL VALUE: " + incrementer);
//            int newValule = (int) event.getY();
//            int oldValue = (int) event.getY();
//            modifyValue = temp - (int) event.getY(); // 300
////            scaleValue += modifyValue;
//            scaleValue = localValue;
//            localValue = localValue -
                reverseScale.add(incrementer);
//            Log.d("TAG", "onTouchEvent: " + modifyValue * -1);
//            x = (int) event.getX();
//            y = (int) event.getY();
                postInvalidate();
            }
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            incrementer = 0;
            localValue = 0;
            scaleAnim = ValueAnimator.ofInt(reverseScale.get(reverseScale.size() - 1), 0);

            scaleAnim.setDuration(250);
            scaleAnim.setRepeatCount(0);
//            scaleAnim.setRepeatCount(-1);
//            int randomDelay = new Random().nextInt(750 - 250) + 250;
//            scaleAnim.setStartDelay(randomDelay);

            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    reverseScaleValue = (int) animation.getAnimatedValue();
                    postInvalidate();

                }
            });
            scaleAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    reverseScaleAnim = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setLastPosition = true;
                    reverseScaleAnim = false;
//                    animation.setupEndValues();
//                    postInvalidate();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            scaleAnim.start();
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
