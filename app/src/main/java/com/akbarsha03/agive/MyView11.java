package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class MyView11 extends View {
    public static final int Y_200 = 200;
    public static final int X_100 = 100;
    public static final int X_400 = 400;
    public static final int X_500 = 500;
    public static final int Y_000 = 0;
    public static final int X_300 = 300;
    public static final int X_600 = 600;
    Paint paint = new Paint();
    private ValueAnimator scaleAnim;
    private Path path;
    private Path path2;
    private String TAG = "CUSTOM_VIEW";
    private int lenghtOfPath;

    public MyView11(Context context) {
        super(context);
        init(null, Y_000);
    }

    public MyView11(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, Y_000);
    }

    public MyView11(Context context, AttributeSet attrs, int defStyle) {
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
                attrs, R.styleable.MyView, defStyle, Y_000);

        paint.setStrokeWidth(4);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
//        paint.setShader(new LinearGradient(0, 0, 500, 500, Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        path = new Path();
        path2 = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        a.recycle();

        Log.d(TAG, "init: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint);
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
        scaleAnim.setRepeatCount(Y_000);
        scaleAnim.reverse();
        scaleAnim.setInterpolator(new BounceInterpolator());

        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                scaleValue = (int) animation.getAnimatedValue();
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

        path.moveTo(Y_000, X_100);
        path.cubicTo(X_100, Y_200, Y_200, Y_200, X_300, X_100);
        path.cubicTo(X_400, Y_000, X_500, Y_000, X_600, X_100);
        path.cubicTo(X_500, Y_200, X_400, Y_200, X_300, X_100);
        path.cubicTo(Y_200, Y_000, X_100, Y_000, Y_000, X_100);
        path.close();

        path2.moveTo(100, 700);
        path2.lineTo(500, 700);
        path2.cubicTo(500, 700, 600, 800, 500, 900);
        path2.lineTo(100, 900);

        PathMeasure pathMeasure = new PathMeasure(path2, false);
        lenghtOfPath = (int) pathMeasure.getLength();

//        path2.close();

        Log.d(TAG, "onLayout: ");
    }
}
