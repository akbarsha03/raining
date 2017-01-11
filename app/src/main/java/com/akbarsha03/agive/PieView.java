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
import android.view.View;

/**
 * Created by: akbarsha03 on 1/11/17.
 */

public class PieView extends View {

    private Path path;
    private Paint center;
    private Context context;

    public PieView(Context context) {
        super(context);
        init(context);
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
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, center);
    }
}
