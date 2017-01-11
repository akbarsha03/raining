package com.akbarsha03.agive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created : Akbar Sha Ebrahim on 6/8/2016.
 */
public class MyView16 extends TextView {

    protected final String THUMB_TRANSPARENCY = "#3D000000";
    protected String colorLight = "#4DFFFFFF";
    protected int radius = 10;
    protected int shadowLength = 1;
    private Rect rect = new Rect();
    private Path mainHandPath = new Path();
    private Path circlePath = new Path();
    private Path leftHandPath = new Path();
    private Path rightHandPath = new Path();
    private TextPaint text = new TextPaint();
    private Paint endHandPaintTransparent = new Paint();
    private Paint endHandPaintWhite = new Paint();
    private Paint mainHandPaint = new Paint();
    private Paint leftHandPaint = new Paint();
    private Paint rightHandPaint = new Paint();
    private Paint middlePointPaint = new Paint();
    private Paint shadow = new Paint();
    private List<Path> backup = new ArrayList<>();
    private Paint middlePointTransparent = new Paint();
    private Paint red = new Paint();

    public MyView16(Context context) {
        super(context);
        init();
    }

    public MyView16(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView16(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // Load attributes
        endHandPaintTransparent.setAntiAlias(true);
        endHandPaintTransparent.setStrokeWidth(6f);
        endHandPaintTransparent.setColor(Color.parseColor(colorLight));
        endHandPaintTransparent.setStrokeJoin(Paint.Join.ROUND);

        endHandPaintWhite.setAntiAlias(true);
        endHandPaintWhite.setStrokeWidth(6f);
        endHandPaintWhite.setColor(Color.WHITE);
        endHandPaintWhite.setStrokeJoin(Paint.Join.ROUND);

        mainHandPaint.setAntiAlias(true);
        mainHandPaint.setStrokeWidth(6f);
        mainHandPaint.setColor(Color.TRANSPARENT);
        mainHandPaint.setStyle(Paint.Style.STROKE);

        leftHandPaint.setAntiAlias(true);
        leftHandPaint.setStrokeWidth(6f);
        leftHandPaint.setColor(Color.WHITE);
        leftHandPaint.setStyle(Paint.Style.STROKE);

        rightHandPaint.setAntiAlias(true);
        rightHandPaint.setStrokeWidth(6f);
        rightHandPaint.setColor(Color.parseColor(colorLight));
        rightHandPaint.setStyle(Paint.Style.STROKE);

        middlePointPaint.setAntiAlias(true);
        middlePointPaint.setStrokeWidth(6f);
        middlePointPaint.setColor(Color.DKGRAY);
        middlePointPaint.setStyle(Paint.Style.FILL);

        middlePointTransparent.setAntiAlias(true);
        middlePointTransparent.setStrokeWidth(6f);
        middlePointTransparent.setColor(Color.TRANSPARENT);
        middlePointTransparent.setStyle(Paint.Style.FILL);

        red.setAntiAlias(true);
        red.setStrokeWidth(6f);
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!backup.isEmpty()) {
            for (Path path : backup) {
                path.reset();
            }
        }

        String userValue = String.valueOf(getText());

        String textValue = TextUtils.isEmpty(userValue) ? "0" : userValue;

        int intValue = Integer.parseInt(textValue);

        String str = "  " + textValue + "%  ";

        String progress = "PROGRESS";

        text.setTextSize(getTextSize() - (getTextSize() / 3));
        text.setColor(Color.WHITE);
        text.getTextBounds(progress, 0, progress.length(), rect);

        int height = getHeight() / 2;
        canvas.drawText(progress, 0, height + (rect.height() / 2), text);

        float actualTextWith = getPaint().measureText(str);
        int startOfEndLine = getWidth() - (getWidth() / 10);

        canvas.drawLine(startOfEndLine, height, getWidth(), height,
                intValue == 100 ? endHandPaintWhite : endHandPaintTransparent);

        float leftTextPoint = startOfEndLine - actualTextWith;

        Paint p = getPaint();
        p.setColor(Color.WHITE);

        canvas.drawText(str, leftTextPoint, height + (rect.height() / 3), p);

        int initialMarginGap = rect.width() + 26;

        mainHandPath.moveTo(initialMarginGap, height);
        mainHandPath.lineTo(leftTextPoint, height);

        canvas.drawPath(mainHandPath, mainHandPaint);

        PathMeasure pathMeasure = new PathMeasure(mainHandPath, false);
        float length = pathMeasure.getLength();

        float percentage = Integer.parseInt(textValue);

        float middleMarginGap = (length * (percentage / 100)) + initialMarginGap;

        leftHandPath.moveTo(initialMarginGap, height);
        leftHandPath.lineTo(middleMarginGap, height);

        canvas.drawPath(leftHandPath, leftHandPaint);

        backup.add(leftHandPath);

        rightHandPath.moveTo(middleMarginGap, height);
        rightHandPath.lineTo(leftTextPoint, height);

        canvas.drawPath(rightHandPath, rightHandPaint);

        backup.add(rightHandPath);

        circlePath.addCircle(middleMarginGap, height, radius, Path.Direction.CW);
        canvas.drawPath(circlePath, middlePointPaint);

        backup.add(circlePath);

        shadow.setColor(Color.WHITE);
        shadow.setShadowLayer(radius + shadowLength, 1, 1, Color.parseColor(THUMB_TRANSPARENCY));

        canvas.drawPath(circlePath, shadow);
    }

    /**
     * Set the progress of the view between 0 and 100
     *
     * @param progress default is 0
     */
    public void setProgress(int progress) {
        if (progress > 100) {
            setText(String.valueOf(100));
        } else if (progress < 0) {
            setText(String.valueOf(0));
        } else
            setText(String.valueOf(progress));
    }
}
