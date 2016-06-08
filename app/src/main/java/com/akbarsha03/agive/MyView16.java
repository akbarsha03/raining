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
    private Path path = new Path();
    private Path circlePath = new Path();
    private Path path2 = new Path();
    private Path path3 = new Path();

    private TextPaint text = new TextPaint();
    private Paint paint = new Paint();
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();
    private Paint paint4 = new Paint();
    private Paint paint5 = new Paint();
    private Paint shadow = new Paint();

    protected String colorLight = "#4dffffff";
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
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.RED);
        paint.setTextSize(30f);
        paint.setStrokeJoin(Paint.Join.ROUND);

        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(6f);

        paint2.setColor(Color.parseColor(colorLight));
        paint2.setStyle(Paint.Style.STROKE);

        paint3.setAntiAlias(true);
        paint3.setStrokeWidth(6f);
        paint3.setColor(Color.BLUE);
        paint3.setStyle(Paint.Style.STROKE);

        paint4.setAntiAlias(true);
        paint4.setStrokeWidth(6f);
        paint4.setColor(Color.parseColor(colorLight));
        paint4.setStyle(Paint.Style.STROKE);

        paint5.setAntiAlias(true);
        paint5.setStrokeWidth(6f);
        paint5.setColor(Color.DKGRAY);
        paint5.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        String userValue = String.valueOf(getText());

        String textValue = TextUtils.isEmpty(userValue) ? "0" : userValue;

        String str = "  " + textValue + "%  ";

        String progress = "PROGRESS";

        text.setTextSize(getTextSize());
        text.getTextBounds(progress, 0, progress.length(), rect);

        int height = getHeight() / 2;
        canvas.drawText(progress, 0, height + (rect.height() / 2), text);

        float actualTextWith = getPaint().measureText(str);
        int startOfEndLine = getWidth() - (getWidth() / 10);

        canvas.drawLine(startOfEndLine, height, getWidth(), height, paint);

        float leftTextPoint = startOfEndLine - actualTextWith;
        canvas.drawText(str, leftTextPoint, height + (rect.height() / 3), getPaint());

        int initialMarginGap = rect.width() + 26;

        path.moveTo(initialMarginGap, height);
        path.lineTo(leftTextPoint, height);

        canvas.drawPath(path, paint2);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();

        float percentage = Integer.parseInt(textValue);

        float middleMarginGap = (length * (percentage / 100)) + initialMarginGap;

        path2.moveTo(initialMarginGap, height);
        path2.lineTo(middleMarginGap, height);

        canvas.drawPath(path2, paint3);

        path3.moveTo(middleMarginGap, height);
        path3.lineTo(leftTextPoint, height);

        canvas.drawPath(path3, paint4);

        circlePath.addCircle(middleMarginGap, height, radius, Path.Direction.CW);

        canvas.drawPath(circlePath, paint5);

        shadow.setColor(Color.WHITE);
        shadow.setShadowLayer(radius + shadowLength, 1, 1, Color.BLACK);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        canvas.drawPath(circlePath, shadow);
    }
}
