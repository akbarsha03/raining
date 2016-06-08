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

/**
 * Created : Akbar Sha Ebrahim on 6/8/2016.
 */
public class MyView16 extends TextView {

    private Rect rect = new Rect();
    private Path mainHandPath = new Path();
    private Path circlePath = new Path();
    private Path leftHandPath = new Path();
    private Path rightHandPath = new Path();

    private TextPaint text = new TextPaint();
    private Paint endHandPaint = new Paint();
    private Paint mainHandPaint = new Paint();
    private Paint leftHandPaint = new Paint();
    private Paint rightHandPaint = new Paint();
    private Paint middlePointPaint = new Paint();
    private Paint shadow = new Paint();

    protected String colorLight = "#4DFFFFFF";
    protected int radius = 10;
    protected int shadowLength = 1;


    public MyView16(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView16(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView16(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        endHandPaint.setAntiAlias(true);
        endHandPaint.setStrokeWidth(6f);
        endHandPaint.setColor(Color.parseColor(colorLight));
        endHandPaint.setTextSize(30f);
        endHandPaint.setStrokeJoin(Paint.Join.ROUND);

        mainHandPaint.setAntiAlias(true);
        mainHandPaint.setStrokeWidth(6f);

        mainHandPaint.setColor(Color.parseColor(colorLight));
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
    }


    @Override
    protected void onDraw(Canvas canvas) {

        String userValue = String.valueOf(getText());

        String textValue = TextUtils.isEmpty(userValue) ? "0" : userValue;

        String str = "  " + textValue + "%  ";

        String progress = "PROGRESS";

        text.setTextSize(getTextSize());
        text.setColor(Color.WHITE);
        text.getTextBounds(progress, 0, progress.length(), rect);

        int height = getHeight() / 2;
        canvas.drawText(progress, 0, height + (rect.height() / 2), text);

        float actualTextWith = getPaint().measureText(str);
        int startOfEndLine = getWidth() - (getWidth() / 10);

        canvas.drawLine(startOfEndLine, height, getWidth(), height, endHandPaint);

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

        rightHandPath.moveTo(middleMarginGap, height);
        rightHandPath.lineTo(leftTextPoint, height);

        canvas.drawPath(rightHandPath, rightHandPaint);

        circlePath.addCircle(middleMarginGap, height, radius, Path.Direction.CW);

        canvas.drawPath(circlePath, middlePointPaint);

        shadow.setColor(Color.WHITE);
        shadow.setShadowLayer(radius + shadowLength, 1, 1, Color.parseColor("#3D000000"));
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        canvas.drawPath(circlePath, shadow);
    }
}
