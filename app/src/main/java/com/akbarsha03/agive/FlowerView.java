package com.akbarsha03.agive;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.IntRange;
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
    private Paint percentageTextPaint;
    private List<LeafValue> leafValues;
    private float centerTextSize;
    private int overAllPercentage = 0;
    private int centerX;
    private int centerY;
    private int mainRadius;
    private Paint subTextPaint;
    private int radius;

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
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        alphaPaint = new Paint();
        alphaPaint.setColor(Color.WHITE);
        alphaPaint.setAntiAlias(true);
        alphaPaint.setAlpha(90);
        alphaPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        final int numberOfLeaves = leafValues.size();
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        mainRadius = radius;
        int currentAngle = -45;
        final int cumulativeAngle = 360 / numberOfLeaves;
        int extraCurve = 50;

        if (numberOfLeaves > 4) {
            extraCurve = 0;
        }

        backgroundPaths = new ArrayList<>();

        for (int i = 0; i < numberOfLeaves; i++) {

            Path path = new Path();

            path.moveTo(centerX, centerY);
            path.lineTo(getCustomX(centerX, currentAngle, radius), getCustomY(centerY, currentAngle, radius));

            currentAngle += cumulativeAngle;
            int midAngle = (currentAngle - cumulativeAngle / 2);

            Log.d("FuckTag", "Mid angles: mid angle =" + midAngle);
            path.quadTo(getCustomX(centerX, midAngle, radius + extraCurve), getCustomY(centerY, midAngle, radius + extraCurve),
                    getCustomX(centerX, currentAngle, radius - (radius / 8)), getCustomY(centerY, currentAngle, radius - (radius / 8)));
            path.close();

            backgroundPaths.add(path);
        }

        foregroundPaths = new ArrayList<>();

        for (int i = 0; i < numberOfLeaves; i++) {

            radius = ((mainRadius - centerRadius) * leafValues.get(i).getPercentage() / 100) + centerRadius;

            Path path = new Path();

            path.moveTo(centerX, centerY);
            path.lineTo(getCustomX(centerX, currentAngle, radius), getCustomY(centerY, currentAngle, radius));

            currentAngle += cumulativeAngle;
            int midAngle = (currentAngle - cumulativeAngle / 2);

            Log.d("FuckTag", "Mid angles: mid angle =" + midAngle);
            path.quadTo(getCustomX(centerX, midAngle, radius + extraCurve), getCustomY(centerY, midAngle, radius + extraCurve),
                    getCustomX(centerX, currentAngle, radius - (radius / 8)), getCustomY(centerY, currentAngle, radius - (radius / 8)));
            path.close();

            foregroundPaths.add(path);
        }

        centerPaint = new Paint();
        centerPaint.setColor(Color.WHITE);
        centerPaint.setAntiAlias(true);
        centerPaint.setStyle(Paint.Style.FILL);

        percentageTextPaint = new Paint();
        percentageTextPaint.setColor(Color.parseColor("#FF464646"));
        percentageTextPaint.setAntiAlias(true);
        percentageTextPaint.setTextSize(centerTextSize);
        percentageTextPaint.setStyle(Paint.Style.FILL);

        subTextPaint = new Paint();
        subTextPaint.setColor(Color.parseColor("#FF797979"));
        subTextPaint.setAntiAlias(true);
        subTextPaint.setTextSize(percentageTextPaint.getTextSize() / 2);
        subTextPaint.setStyle(Paint.Style.FILL);
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

            final String text = String.valueOf(overAllPercentage).concat("%");
            final String overall = "Overall";

            float[] textValues = new float[text.length() + 1];
            float[] textValues2 = new float[overall.length() + 1];

            percentageTextPaint.getTextWidths(text, 0, text.length() /*- 1*/, textValues);
            subTextPaint.getTextWidths(overall, 0, overall.length() /*- 1*/, textValues2);

            canvas.drawCircle(centerX, centerY, centerRadius, centerPaint);

            float mainTextLength = 0;
            float subTextLength = 0;

            for (float textValue : textValues) mainTextLength += textValue;
            for (float aTextValues2 : textValues2) subTextLength += aTextValues2;

            canvas.drawText(text, centerX - (mainTextLength / 2), centerY + (percentageTextPaint.getTextSize() / 3), percentageTextPaint);
            canvas.drawText(overall, centerX - (subTextLength / 2), centerY + (percentageTextPaint.getTextSize()), subTextPaint);
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
        drawFlower(leafValues);
    }

    /**
     * Build the flower with following parameters
     *
     * @param leafRadius        pass minimum 400 to get a better view
     * @param centerRadius      pass minimum 100
     * @param centerTextSize    pass minimum 60f
     * @param overAllPercentage pass between 0 and 100
     * @param leafValues        Pass @{@link LeafValue}
     */
    public void setUpLeaves(int leafRadius, int centerRadius, float centerTextSize, @IntRange(from = 0, to = 100) int overAllPercentage, List<LeafValue> leafValues) {
        this.centerRadius = centerRadius;
        this.radius = leafRadius;
        this.centerTextSize = centerTextSize;
        this.overAllPercentage = overAllPercentage;
        this.leafValues = leafValues;
        postInvalidate();
    }
}