package com.akbarsha03.agive;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by: akbarsha03 on 6/17/16.
 */
public class MyView18 extends FrameLayout {

    private Context context;

    public MyView18(Context context) {
        super(context);
        this.context = context;
    }

    public MyView18(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyView18(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView18(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {


    }

    @Override
    protected void onDraw(Canvas canvas) {
    }
}