package com.akbarsha03.agive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: akbarsha03 on 1/9/17.
 */

public class CircleView extends FrameLayout {

    private int centerPoint;
    private Paint center;
    private Path path;
    private Path path2;
    private int angle;
    private Context context;
    private PieView pieView;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        centerPoint = 500;

        pieView = new PieView(context);
        addView(pieView);

    }

    private void init(Context context) {

        this.context = context;

        center = new Paint();
        center.setColor(Color.WHITE);
        center.setStrokeWidth(6f);
        center.setStyle(Paint.Style.FILL);

        path = new Path();
        path.moveTo(300, 300);
        path.lineTo(400, 400);
        path.quadTo(500, 300, 400, 200);
        path.close();

//        path2 = new Path();
//        path2.moveTo(300, 300);
//        path2.quadTo(300, 400, 400, 400);
//        path2.quadTo(500, 400, 500, 300);
//        path2.quadTo(500, 200, 400, 200);
//        path2.quadTo(300, 200, 300, 300);
//        path2.close();
//        RectF rect = new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        addView(pieView);

//        canvas.drawCircle(centerPoint, centerPoint, 80, center);
//        canvas.drawPath(path, center);

//        canvas.save();
//        Matrix matrix = new Matrix();
//        matrix.reset();
//        PathMeasure pathMeasure = new PathMeasure(path, true);
//
//        float[] value = new float[2];
//        float distance = 0;
//
//        float[] value2 = new float[2];
//        pathMeasure.getPosTan(distance, value2, value);
//        matrix.postRotate(180, 100, 200);
//        canvas.drawPath(path, center);
//        canvas.restore();

    }

    public void chartCount(int count) {

        angle = 360 / count;

        List<Path> pathList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Path path = new Path();
            path.moveTo(centerPoint, centerPoint);
//            path.
//            pathList.add(path);
        }
    }
}