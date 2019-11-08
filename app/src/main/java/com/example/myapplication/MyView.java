package com.example.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class MyView extends View {
    int N = 10; // количество шариков
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    float[] rad=new float[N];
    int [] col=new int[N];

    public MyView(Context context) {
        super(context);
        for (int i = 0; i < N; i++){
            x[i] = (float)(Math.random() * 500);
            y[i] = (float)(Math.random() * 500);
            vx[i] = (float)(Math.random() * 6 - 3);
            vy[i] = (float)(Math.random() * 6 - 2);
            rad[i]=(float)(Math.random() * 20 + 40);
            col[i]=(int)(Math.random() * 15 + 150);

        }
    }

    @Override

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        for (int i = 0; i < N - 1; i++) {

            canvas.drawLine(x[i], y[i], x[i + 1], y[i + 1], paint);

        }
        for (int i = 0; i < N; i++) {
            paint.setColor(Color.rgb(col[i]+23,col[i]+(int)x[i],col[i]-15));
            canvas.drawCircle(x[i], y[i], rad[i], paint);
        }

        for (int i = 0; i < N; i++)
        {
            moveballs(x,i);
            x[i] += vx[i];
            moveballsy(y,i);
            y[i] += vy[i];

        }
        invalidate();
    }
    void moveballs(float []x,int i )
    {
        if (x[i] < 0+rad[i] || x[i] > this.getWidth()-rad[i])
        {
            vx[i] = - vx[i];
          //  rad[i]+=10;

        }

    }
    void moveballsy(float []y,int i )
    {
        if (y[i] < 0+rad[i] || y[i] > this.getHeight()-rad[i])
        {
            vy[i] = - vy[i];
            //rad[i]+=5;

        }

    }
}



