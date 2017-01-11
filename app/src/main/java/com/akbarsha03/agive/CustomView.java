package com.akbarsha03.agive;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by: akbarsha03 on 12/27/16.
 */

public class CustomView extends LinearLayout {

    private final Context context;

    public CustomView(Context context) {
        super(context);
        this.context = context;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public void setImage(@DrawableRes int res) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(res);
        addView(imageView);

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(getChildAt(0), "s", 0, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        View xx = getChildAt(0);
    }
}