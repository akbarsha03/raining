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

import java.util.Random;

/**
 * Created by: akbarsha03 on 12/3/16.
 */
public class StarView extends View {

    private Paint paint;
    private Path path;

    public StarView(Context context) {
        super(context);
        init();
    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        path = new Path();
    }

    public void setPosition(float x, float y) {
        path.reset();
        path.addCircle(x, y, 10, Path.Direction.CW);
        invalidate();
    }

    public void setMove(final float x, final float y) {

        Double[] endPoints = getPath(getAngle(), 0, 0);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, getHeight());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                path.reset();
                path.addCircle(x + (int) animation.getAnimatedValue(),
                        y + (int) animation.getAnimatedValue(), 10, Path.Direction.CW);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public Double[] getPath(int angle, int x, int y) {
        final double degree = Math.toRadians(angle);
        return new Double[]{x + Math.sin(degree), y + Math.sin(degree)};
    }

    public int getAngle() {
        return new Random().nextInt(360) + 1;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
