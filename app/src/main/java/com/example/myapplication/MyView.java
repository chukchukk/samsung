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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        int i=0;
        while(i<canvas.getHeight())
        {
            canvas.drawLine(i,0,canvas.getWidth(),canvas.getHeight()-i,paint);
            if(i>canvas.getHeight()/3)
                paint.setColor(Color.GRAY);
            i+=50;
        }
        paint.setColor(Color.BLUE);
        i=0;
        while(i<canvas.getHeight())
        {
            canvas.drawLine(0,i,canvas.getWidth()-i,canvas.getHeight(),paint);
            if(i>canvas.getHeight()/4)
                paint.setColor(Color.RED);
            i+=50;
        }
        paint.setColor(Color.BLACK);
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,250,paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,240,paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("А что, патриотично",canvas.getWidth()/2-220,canvas.getHeight()/2,paint);


    }
}
