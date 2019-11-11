package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;

//********************
//Для выигрыша необходимо все мины пометить флажком ** (при долгом нажатиее)
//********************

public class MainActivity extends Activity  implements OnClickListener,
        OnLongClickListener {

    public int WIDTH = 9;
    public int HEIGHT = 9;
    private Button[][] cells;
    int mins[][]=new int[9][9];
    int k=0;
    int k1=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();
        generate();
    }


    void generate() {


        int num = 1;
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setText(" " + "");
                num++;
            }
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                mins[i][j]=2;
            }
        }

        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {

                cells[i][j].setBackgroundColor(Color.GRAY);
            }
    }
    void genPole(int tapX,int tapY)
    {

        int minMaxX[]=minMax(tapX);
        int minMaxY[]=minMax(tapY);
        for(int i=0;i<10;i++)
        {
            int x=(int)(Math.random()*8);
            int y=(int)(Math.random()*(8-(minMaxY[1]-minMaxY[0]+1)));
            if(y>=minMaxY[0]) {
                y+=minMaxY[1]-minMaxY[0]+1;
            }
            if(mins[x][y]!=0) {
                mins[x][y] = 0;
            }
            else{
                i--;
            }

        }
       /* for(int i=0;i<9;i++)
        {


            int x=(int)(Math.random()*9)+0;
            int y=(int)(Math.random()*9)+0;
            if(x==tapY&&y==tapX)
                continue;
            if(mins[x][y]==0)
                continue;
            else
                mins[x][y]=0;
        }*/
    }

    @Override
    public boolean onLongClick(View v) {


        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        if (mins[tappedY][tappedX]==0) {
            cells[tappedY][tappedX].setText("**");
            mins[tappedY][tappedX]=1;
            k1++;
        }
        else
        if(mins[tappedY][tappedX]==2)
        {
            cells[tappedY][tappedX].setText("**");
            mins[tappedY][tappedX]=3;
        }
        else
        if(mins[tappedY][tappedX]==1)
        {
            cells[tappedY][tappedX].setText("");
            mins[tappedY][tappedX]=0;
            k1--;
        }
        else
        if (mins[tappedY][tappedX]==3){
            cells[tappedY][tappedX].setText("");
            mins[tappedY][tappedX]=2;
        }
        Log.i("My", ""+k1);
        if(k1==10)
        {
            cells[4][2].setText("W");
            cells[4][3].setText("I");
            cells[4][4].setText("N");

        }
        return true;

    }



    int[] minMax(int tap)
    {
        int[] minMax = new int[2];
        minMax[0] = tap - 1;
        minMax[1] = tap + 1;
        if(tap == 0){
            minMax[0] = 0;
        }
        else if (tap == 8){
            minMax[1] = 8;
        }
        return minMax;
    }

    int proverka(int tapX, int tapY)
    {
        int numMin=0;
        int minX = tapX - 1;
        int maxX = tapX + 1;
        if(tapX == 0){
            minX = 0;
        }
        else if (tapX == 8){
            maxX = 8;
        }
        int minY = tapY - 1;
        int maxY = tapY + 1;
        if(tapY == 0){
            minY = 0;
        }
        else if (tapY == 8){
            maxY = 8;
        }

        for(int i=minX;i<=maxX;i++)
        {
            for(int j=minY;j<=maxY;j++)
            {
                if(i<-1||i>8||j<-1||j>8)
                    continue;
                else
                {
                    if((mins[i][j]==0)||(mins[i][j]==1))
                        numMin++;
                }
            }
        }
        return numMin;
    }

public void open(int tappedY, int tappedX){
    int numMin = proverka(tappedY,tappedX);
    String numMins=String.valueOf(numMin);
    if((cells[tappedY][tappedX].getText()!=numMins)&&(cells[tappedY][tappedX].getText()!="**")){
        cells[tappedY][tappedX].setBackgroundColor(Color.BLUE);
        cells[tappedY][tappedX].setText(numMins);
        if(numMin == 0) {
            int minMaxX[] = minMax(tappedX);
            int minMaxY[] = minMax(tappedY);
            for (int i = minMaxX[0]; i <= minMaxX[1]; i++) {
                for (int j = minMaxY[0]; j <= minMaxY[1]; j++) {
                    open(j, i);
                }
            }
        }
    }
}

    @Override
    public void onClick(View v) {


        Button tappedCell = (Button) v;


        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        k++;
        if(k==1)
            genPole(tappedY,tappedX);

        if(mins[tappedY][tappedX]==0||mins[tappedY][tappedX]==1)
        {
            cells[tappedY][tappedX].setBackgroundColor(Color.RED);
            cells[4][2].setText("L");
            cells[4][3].setText("O");
            cells[4][4].setText("S");
            cells[4][5].setText("S");
            return;
        }
        else
        {
            open(tappedY, tappedX);

        }
    }




    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(HEIGHT);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}

