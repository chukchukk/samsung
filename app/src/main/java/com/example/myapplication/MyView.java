package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {

        super(context);

    }
    long lastTime = System.currentTimeMillis();
    float x=0;

    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        canvas.drawCircle(x, 300, 20, paint);
        long nowTime = System.currentTimeMillis();
        x += 0.01f * (nowTime - lastTime);
        lastTime = nowTime;
        invalidate();

    }
}
