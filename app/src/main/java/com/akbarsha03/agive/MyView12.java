package com.akbarsha03.agive;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * A give
 * <p/>
 * Created by akbarsha03 on 5/12/16 3:11 PM.
 */
public class MyView12 extends View {

    private static Paint paint;
    private int screenW, screenH;
    private float X, Y;
    private Path path;
    private float initialScreenW;
    private float initialX, plusX;
    private float TX;
    private boolean translate;
    private int flash;
    private Context context;

    public MyView12(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView12(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView12(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        paint = new Paint();
        paint.setColor(Color.argb(0xff, 0x99, 0x00, 0x00));
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(7, 0, 0, Color.RED);


        path = new Path();
        TX = 0;
        translate = false;

        flash = 0;

        a.recycle();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenW = w;
        screenH = h;
        X = 0;
        Y = (screenH / 2) + (screenH / 4) + (screenH / 10);

        initialScreenW = screenW;
        initialX = ((screenW / 2) + (screenW / 4));
        plusX = (screenW / 24);

        path.moveTo(X, Y);

    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.save();


        flash += 1;
        if (flash < 10 || (flash > 20 && flash < 30)) {
            paint.setStrokeWidth(16);
            paint.setColor(Color.RED);
            paint.setShadowLayer(12, 0, 0, Color.RED);
        } else {
            paint.setStrokeWidth(10);
            paint.setColor(Color.argb(0xff, 0x99, 0x00, 0x00));
            paint.setShadowLayer(7, 0, 0, Color.RED);
        }

        if (flash == 100) {
            flash = 0;
        }

        path.lineTo(X, Y);
        canvas.translate(-TX, 0);
        if (translate == true) {
            TX += 4;
        }

        if (X < initialX) {
            X += 8;
        } else {
            if (X < initialX + plusX) {
                X += 2;
                Y -= 8;
            } else {
                if (X < initialX + (plusX * 2)) {
                    X += 2;
                    Y += 14;
                } else {
                    if (X < initialX + (plusX * 3)) {
                        X += 2;
                        Y -= 12;
                    } else {
                        if (X < initialX + (plusX * 4)) {
                            X += 2;
                            Y += 6;
                        } else {
                            if (X < initialScreenW) {
                                X += 8;
                            } else {
                                translate = true;
                                initialX = initialX + initialScreenW;
                            }
                        }
                    }
                }
            }

        }

        canvas.drawPath(path, paint);


        //canvas.restore();

        invalidate();
    }

}
