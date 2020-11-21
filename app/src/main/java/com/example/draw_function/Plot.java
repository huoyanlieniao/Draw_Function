package com.example.draw_function;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

//函数绘制
public class Plot {
    //函数表达式
    private String Function;
    //自变量
    private String X;
    //自变量的范围
    private int MinX;
    private int MaxX;
    //颜色
    private int Function_Color;
    //坐标轴
    private Draw draw;

    //默认
    public Plot(Draw draw){
        this.draw=draw;
        Function="";
        //默认xy是-10 10
        MinX=-10;
        MaxX=10;
        int red = Color.RED;
        this.Function_Color= red;
    }

    public Plot(Draw draw,int  color){
        this.draw=draw;
        Function="";
        //默认xy是-10 10
        MinX=-10;
        MaxX=10;
        this.Function_Color=color;
    }

    public Plot(String function, String x, int minX, int maxX, int function_Color, Draw draw) {
        Function = function;
        X = x;
        MinX = minX;
        MaxX = maxX;
        Function_Color = function_Color;
        this.draw = draw;
    }

    public Plot(Draw draw,String Fun,String s){
        MinX=-10;
        MaxX=10;
        this.draw=draw;
        this.Function=Fun;
        this.X=s;
        this.Function_Color=Color.RED;
    }

    public void hua(Canvas canvas)
    {
        if(draw==null){
            return;
        }

        //画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Function_Color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        SpliteStr spliteStr=new SpliteStr(Function);

        double oldx= MinX;
        double oldy=spliteStr.get(MinX);

        double delta = ((double)(MaxX- MinX)) / 100;
        //绘制方程图形，将图形分为100分
        for (int i = 1; i < 100; i++)
        {
            double newx = (MinX + delta * i);
            double newy = spliteStr.get(newx)*10;
            canvas.drawLine(draw.TransX(oldx), draw.TransY(oldy),draw.TransX(newx),draw.TransY(newy),paint);
            oldx=newx;
            oldy=newy;

        }
    }


}