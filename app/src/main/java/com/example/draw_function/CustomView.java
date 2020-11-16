package com.example.draw_function;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


//自定义布局
public class CustomView extends View {

    String Function;

    @Override
    public  void onDraw(Canvas canvas){
        //存储成对出现的参数
        Rect rect=new Rect();
        rect.top=0;
        rect.bottom=getHeight();
        rect.left=0;
        rect.right=getWidth();
        //创建视图
        Draw draw=new Draw(rect);
        draw.Drawall(canvas);

    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}