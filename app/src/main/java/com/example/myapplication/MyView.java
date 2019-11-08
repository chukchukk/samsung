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
    float [] col=new float[N];

    public MyView(Context context) {
        super(context);
        fillRandom(x, 0, 500);
        fillRandom(y, 0, 500);
        fillRandom(vx, -3, 3);
        fillRandom(vy, -2,0);
        fillRandom(rad,40,60);
        fillRandom(col,150,165);
    }

    @Override

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        for (int i = 0; i < N - 1; i++) {

            canvas.drawLine(x[i], y[i], x[i + 1], y[i + 1], paint);

        }
        for (int i = 0; i < N; i++) {
            paint.setColor(Color.rgb((int)col[i]+23,(int)col[i]+50,(int)col[i]-15));
            canvas.drawCircle(x[i], y[i], rad[i], paint);
        }
        for(int i=0;i<N;i++)
        {
            moveballs(x,i);
            add(x, vx);
            moveballsy(y,i);
            add(y, vy);
        }
        invalidate();

    }
    void moveballs(float []x,int i ) {
        if (x[i] < 0 + rad[i] || x[i] > this.getWidth() - rad[i]) {
            vx[i] = -vx[i];
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
    float rand(float min , float max){
        return (float)(Math.random() * (max - min + 1)) + min;
    }
    void fillRandom(float[] array , float min, float max){
        for (int i = 0; i < array.length; i++){
            array[i] = rand (min, max);
        }
    }
    void add(float[] array , float[] values){
        for (int i = 0; i < array.length; i++){
            array[i] += values[i];
        }
    }
}



