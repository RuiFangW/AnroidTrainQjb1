package com.example.lenovo2.colorid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * CircleCrossView
 *
 * @author: lenovo
 * @time: 2016/1/23 8:19
 */
public class CircleCrossView extends SurfaceView implements SurfaceHolder.Callback {

    public final static String TAG ="CRICLECROSS";
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private int color;

     public CircleCrossView(Context context) {
        super(context);
        init();
    }

    public CircleCrossView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        holder=getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);
        setKeepScreenOn(true);
        setZOrderOnTop(true);//与照相机共存
        paint=new Paint();
        paint.setAntiAlias(true);
        color=Color.RED;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        refresh();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    /**
     *
     * @param canvas
     */
    private void mydraw( Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
       // paint.setColor(Color.WHITE);
       // canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);//设置坐标系中心
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(10);
        float r=getHeight()/4;
        canvas.drawCircle(0, 0, r, paint);//画圆
        paint.setStrokeWidth(4);
        canvas.drawLine(0, paint.getStrokeWidth()/2, 0, -r, paint);//上线
        canvas.drawLine(- paint.getStrokeWidth()/2,0,r,0,paint);//右线
        canvas.restore();

    }

    /**
     * 逻辑
     */
    private void logic(){

    }

    /**
     * 刷新界面
     */
    public void refresh(){
        canvas=holder.lockCanvas();
        if(null!=canvas){
            mydraw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
        logic();
    }

    public void setColor(int color) {
        this.color = color;
    }
}
