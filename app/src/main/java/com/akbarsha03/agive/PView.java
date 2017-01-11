package com.akbarsha03.agive;

import android.annotation.TargetApi;
import android.content.Context;
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

public class PView extends View {

    private Context context;
    private Paint paint;
    private Path path;

    public PView(Context context) {
        super(context);
        init(context);
    }

    public PView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        path = new Path();
    }

    public void position(int x, int y) {

        path.moveTo(x, y);
        path.lineTo(400, 400);
        path.quadTo(500, 300, 400, 200);
        path.close();

        invalidate();
    }
}
