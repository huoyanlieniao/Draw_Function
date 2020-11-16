package com.example.draw_function;

import android.graphics.*;

public class Draw {
    private int MinX;
    private int MaxX;
    private int MinY;
    private int MaxY;


    //手机的实质范围
    private Rect mRect;

    public Draw(int minX, int maxX, int minY, int maxY) {
        MinX = minX;
        MaxX = maxX;
        MinY = minY;
        MaxY = maxY;
    }

    //创建绘图
    public void Drawall(Canvas canvas){
        CreateXY(canvas);
        CreateWange(canvas,20,20);
    }

    public Draw(Rect mRect) {
        //默认横纵各20份
        MinX = -10;
        MaxX = 10;
        MinY = -10;
        MaxY = 10;
        this.mRect = mRect;
    }

    public Draw(int minX, int maxX, int minY, int maxY, Rect mRect) {
        MinX = minX;
        MaxX = maxX;
        MinY = minY;
        MaxY = maxY;
        this.mRect = mRect;
    }

    public int getMinX() {
        return MinX;
    }

    public void setMinX(int minX) {
        MinX = minX;
    }

    public int getMaxX() {
        return MaxX;
    }

    public void setMaxX(int maxX) {
        MaxX = maxX;
    }

    public int getMinY() {
        return MinY;
    }

    public void setMinY(int minY) {
        MinY = minY;
    }

    public int getMaxY() {
        return MaxY;
    }

    public void setMaxY(int maxY) {
        MaxY = maxY;
    }

    public Rect getmRect() {
        return mRect;
    }

    public void setmRect(Rect mRect) {
        this.mRect = mRect;
    }

    //坐标点转换
    public Point PointTrans(Point point){
        Point point1=new Point();
        //转化坐标
        point1.x=TransX(point.x);
        point1.y=TransY(point.y);
        return point1;

    }

    //计算x坐标转为实际坐标
    public int TransX(double x){
        //分成份数
        double fen=mRect.width()/(MaxX-MinX);
        //坐标对应乘以份数，得到相距边框的举例
        double dui=x-MinX;
        dui=dui*fen;
        //最后传递
        return (int) (mRect.left+dui);
    }
    //计算y坐标转为实际坐标
    public int TransY(double y){
        //分成份数
        double fen=mRect.height()/(MaxY-MinY);
        //计算相对边框
        double dui=y-MinY;
        dui=dui*fen;
        //底部边缘的y值
        return (int) (mRect.bottom-dui);
    }

    //坐标轴绘制
    public void CreateXY(Canvas canvas){
        Paint paint=createPaint(Color.RED,3);
        //x轴
        canvas.drawLine(TransX(MinX),TransY(0),TransX(MaxX),TransY(0),paint);
        //Y轴
        canvas.drawLine(TransX(0),TransY(MinY),TransX(0),TransY(MaxY),paint);

    }

    //网格绘制
    public void CreateWange(Canvas canvas,int x,int y){
        Paint paint=createPaint(Color.BLUE,3);
        double wangeX=(MaxX-MinX)/x;
        double wangeY=(MaxY-MinX)/y;
        wangeX=wangeX/2;
        wangeY=wangeY/2;

        for(int i=1;i<wangeX;i++){
            canvas.drawLine(TransX(0+i), TransY(MaxY),TransX(0+i), TransY(MinY), paint);
            canvas.drawLine(TransX(0-i), TransY(MaxY), TransX(0-i),TransY(MinY), paint);
        }
        for(int i=1;i<wangeY;i++){
            canvas.drawLine(TransX(MinX), TransY(0+i),TransX(MaxX), TransY(0+i), paint);
            canvas.drawLine(TransX(MinX), TransY(0-i), TransX(MaxX),TransY(0-i), paint);
        }
    }


    //画笔设置
    public Paint createPaint(int color, int Width){
        //画笔设置
        Paint paint=new Paint();
        //设置抗锯齿，也就是模糊画面
        paint.setAntiAlias(true);
        //设定为绘制轮廓
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(Width);
        return paint;
    }

}