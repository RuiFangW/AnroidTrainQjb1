package com.example.lenovo2.anroidtrainqjb1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyView
 *
 * @author: lenovo
 * @time: 2016/1/22 8:21
 */
public class MyView extends View {


    private Paint paint;
    private int x,y;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
    private void init(){
        paint=new Paint();
        paint.setColor(Color.RED);//设置画笔颜色
        paint.setAntiAlias(true);//设置是否抗锯齿
        x=100;
        y=100;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //drawTest(canvas);
       //drawPath(canvas);
        //drawBitmap(canvas);

        canvas.drawLine(0, 0,x,y, paint);
    }
//画路径

    /**
    private void drawPath(Canvas canvas) {
        Path path=new Path();
        path.moveTo(100, 100);
        path.lineTo(100, 300);
        path.lineTo(200, 250);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
        Path path1=new Path();
        path.addCircle(500,500,100,Path.Direction.CW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
        canvas.drawPath(path1,paint);
    }
//画圆
    private void drawTest(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//设置画笔style,只画不填充
        paint.setStrokeWidth(15);//设置画笔宽度
        //画线
        canvas.drawLine(0, 0,500,500, paint);
        //画圆
        canvas.drawCircle(200, 100, 100, paint);
        paint.setStyle(Paint.Style.FILL);//设置画笔style,填充

        paint.setColor(Color.GREEN);
        canvas.drawCircle(200, 100, 100, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 100, 100, paint);
        //矩形
        paint.setColor(Color.BLACK);
        canvas.drawRect(100, 500, 600, 800, paint);
        //圆角矩形，最小5.1
       // canvas.drawRoundRect(100,700,200,800,10,10,paint);
    }
//画图片
    private void drawBitmap(Canvas canvas){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.translate(getWidth() / 2, getHeight() / 2);//设置坐标系原点
        canvas.drawCircle(0,0,20, paint);
        canvas.drawLine(0,-getHeight()/2,0,getHeight()/2,paint);
        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        canvas.save();//加上之后只改变中间这段代码的，否则之后的都改变
        canvas.rotate(90);//顺时针旋转90°
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();//终止

        canvas.drawCircle(200,200,50,paint);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int)event.getX();
        y=(int)event.getY();
        invalidate();
        return super.onTouchEvent(event);
    }
}
