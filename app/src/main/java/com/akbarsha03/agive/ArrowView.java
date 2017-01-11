package com.akbarsha03.agive;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by: akbarsha03 on 12/9/16.
 */
public class ArrowView extends View {

    private Paint paint;
    private Path startPath;
    private int endX;
    private int startX;
    private int startY;
    private int endY;
    private Path endPath;

    public ArrowView(Context context) {
        super(context);
        init();
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        startPath = new Path();
        endPath = new Path();

        startX = 300;
        startY = 100;

        endX = 200;
        endY = 200;

        startPath.moveTo(100, 100);
        startPath.lineTo(startX, startY);

        final int mX = 300;
        final int mY = 100;

        final int eX = 200;
        final int eY = 200;

        endPath.moveTo(200, 0);
        endPath.lineTo(mX, mY);
        endPath.lineTo(eX, eY);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator animator = ValueAnimator.ofInt(0, 100);

                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        int value = (int) animation.getAnimatedValue();

                        startPath.reset();
                        startPath.moveTo(100, 100);

                        final int x = (((endX - startX) / 100) * value) + startX;
                        final int y = (((endY - startY) / 100) * value) + startY;

                        startPath.lineTo(x, y);
                        startPath.close();
                        invalidate();
                    }
                });
                animator.start();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(startPath, paint);
        canvas.drawPath(endPath, paint);
    }
}
