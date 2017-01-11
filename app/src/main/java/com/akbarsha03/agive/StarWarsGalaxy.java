package com.akbarsha03.agive;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by: akbarsha03 on 12/3/16.
 */
public class StarWarsGalaxy extends FrameLayout {

    private StarView child1;

    public StarWarsGalaxy(Context context) {
        super(context);
        init(context);
    }

    public StarWarsGalaxy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StarWarsGalaxy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StarWarsGalaxy(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        child1 = new StarView(context);
        final StarView child2 = new StarView(context);
        child1.setPosition(100, 100);
        child2.setPosition(200, 200);
        addView(child1);
        addView(child2);
        child2.setMove(0, 150);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawChild(canvas, child1, 1000);
    }
}
