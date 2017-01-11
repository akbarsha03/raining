package com.akbarsha03.agive;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by: akbarsha03 on 1/11/17.
 */

public class FlowerView extends View {

    private Paint paint;
    private List<Path> backgroundPaths;
    private List<Path> foregroundPaths;
    private int centerRadius;
    private Paint centerPaint;
    private Paint alphaPaint;
    private Paint textPaint;
    private List<LeafValue> leafValues;

    public FlowerView(Context context) {
        super(context);
        init(context);
    }

    public FlowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        Context context1 = context;
    }

    private void drawFlower(List<LeafValue> leafValues) {

        paint = new Paint();
        paint.setColor(Color.WHITE);
//        paint.setStrokeWidth(6f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        alphaPaint = new Paint();
        alphaPaint.setColor(Color.WHITE);
//        alphaPaint.setStrokeWidth(6f);
        alphaPaint.setAntiAlias(true);
        alphaPaint.setAlpha(70);
        alphaPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        final int numberOfLeaves = leafValues.size();
        int x = 300;
        int y = 300;
        int radius = 300;
        int currentAngle = 45;
        final int cumulativeAngle = 360 / numberOfLeaves;

        backgroundPaths = new ArrayList<>();

        for (int i = 0; i < numberOfLeaves; i++) {

            Path path = new Path();

            path.moveTo(x, y);
            path.lineTo(getCustomX(x, currentAngle, radius), getCustomY(y, currentAngle, radius));

            currentAngle += cumulativeAngle;
            int midAngle = (currentAngle - cumulativeAngle / 2);

            Log.d("FuckTag", "Mid angles: mid angle =" + midAngle);
            path.quadTo(getCustomX(x, midAngle, radius + 60), getCustomY(y, midAngle, radius + 60),
                    getCustomX(x, currentAngle, radius - (radius / 8)), getCustomY(y, currentAngle, radius - (radius / 8)));
            path.close();

            backgroundPaths.add(path);
        }

        foregroundPaths = new ArrayList<>();

        centerRadius = 100;

        for (int i = 0; i < numberOfLeaves; i++) {

            radius = ((300 - centerRadius) * leafValues.get(i).getPercentage() / 100) + centerRadius;

            Path path = new Path();

            path.moveTo(x, y);
            path.lineTo(getCustomX(x, currentAngle, radius), getCustomY(y, currentAngle, radius));

            currentAngle += cumulativeAngle;
            int midAngle = (currentAngle - cumulativeAngle / 2);

            Log.d("FuckTag", "Mid angles: mid angle =" + midAngle);
            path.quadTo(getCustomX(x, midAngle, radius + 60), getCustomY(y, midAngle, radius + 60),
                    getCustomX(x, currentAngle, radius - (radius / 8)), getCustomY(y, currentAngle, radius - (radius / 8)));
            path.close();

            foregroundPaths.add(path);
        }

        centerPaint = new Paint();
        centerPaint.setColor(Color.WHITE);
        centerPaint.setAntiAlias(true);
        centerPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60f);
        textPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!backgroundPaths.isEmpty())
            for (int i = 0; i < backgroundPaths.size(); i++) {
                paint.setColor(Color.parseColor(leafValues.get(i).getColor()));
                canvas.drawPath(backgroundPaths.get(i), paint);
            }

        if (!foregroundPaths.isEmpty()) {
            for (Path o : foregroundPaths) {
                paint.setColor(getColor());
                canvas.drawPath(o, alphaPaint);
            }

            canvas.drawCircle(300, 300, centerRadius, centerPaint);

            canvas.drawText("Fuck", 300, 300, textPaint);
        }
    }

    private int getColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return Color.rgb(r, g, b);
    }

    public int getCustomX(int x, float angle, float radius) {
        return x + (int) (radius * Math.cos(Math.toRadians(angle)));
    }

    public int getCustomY(int y, float angle, float radius) {
        return y + (int) (radius * Math.sin(Math.toRadians(angle)));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setUpLeaves(List<LeafValue> leafValues) {
        this.leafValues = leafValues;
        drawFlower(leafValues);
        invalidate();
    }
}