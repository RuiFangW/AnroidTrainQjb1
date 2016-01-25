package com.example.lenovo2.anroidtrainqjb1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author: lenovo
 * @time: 2016/1/22 10:18
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    public final static String TAG="MySurfaceView";
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;
    private Thread thread;
    private float x,y;
    private float speedx,speedy;
    private float r;
    private int color;

    private Vector loca;//位置
    private Vector speed;//速度
    private Vector acc;//加速度

    private float rectx,recty;
    private float rectWidth,rectHeight;
    private float rect1x,rect1y;
    private float rect1Width,rect1Height;
    public MySurfaceView(Context context) {
        super(context);
        init();
        initGame();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initGame();
    }

    /**
     * 初始化
     */
    private void init(){
        holder=getHolder();
        holder.addCallback(this);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);


    }

    private void initGame(){
        x=getWidth()/2;
        y=getHeight()/2;
        speedx=10;
        speedy=10;
        color=Color.YELLOW;
        r=100;

        loca=new Vector(100,100);
        speed=new Vector(10,20);
        acc=new Vector(00.1f,0.005f);

        rectx=getWidth()/2-100;
        recty=getHeight()/2-80;
        rectWidth=200;
        rectHeight=160;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfacecreated");
       // initGame();
        flag=true;
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Log.i(TAG, "surfacedechanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        Log.i(TAG,"surfacedestroyed");
        flag=false;
    }
    /*
    逻辑
     */
    private void logic(){
        //简单移动
        x=x+speedx;
        y=y+speedy;
        if(x>=getWidth()||x<0){
            speedx=-speedx;
        }
        if(y>=getHeight()||y<0){
            speedy=-speedy;
        }
        //向量移动
        speed.limit(10);
        speed.add(acc);
        loca.add(speed);
        if(loca.x>=getWidth()||loca.x<0){
            speed.x=-speed.x;
            acc.x=-acc.x;
        }
        if(loca.y>=getHeight()||loca.y<0){
           speed.y=-speed.y;
            acc.y=-acc.y;
        }
        //判断检测

    }

    private boolean rectAndRect(float rect1x,float rect1y,float rect1Width,float rect1Height,float rect2x,float rect2y,float rect2Width,float rect2Height){
        if(rect1x+rect1Width>rect2x)
        return false;
        else if(rect1x<rect2x+rect2Width)
            return false;
        else if(rect1y+rect1Height>rect2y)
            return false;
        else if(rect1y<rect2y+rect2Height)
            return false;
        else
            return true;
    }
    private void myDraw(Canvas canvas){

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor(color);
        canvas.drawCircle(x, y, r, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rectx,recty,rectx+rectWidth,recty+rectHeight,paint);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(loca.x,loca.y,r,paint);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //color=Color.argb(10,(int)event.getX()/255,(int)event.getY()/255,100);
        //r=new Random().nextInt(10)+50;
       int x=(int)event.getX();
        int y=(int)event.getY();
        int[] colors =new int[]{Color.BLUE,Color.GRAY,Color.YELLOW,Color.GREEN};
        color=colors[new Random().nextInt(colors.length)];
        //color=Color.argb(0,x*y%255, x % 255, y % 255);
        //r=new Random().nextInt(10)+50;

        //引力
        Vector touch=new Vector(x,y);
        acc=Vector.sub(touch,loca);
        acc.normalize();
        acc.mult(15.0f);

        return super.onTouchEvent(event);
    }

    @Override
    public void run() {
        while(flag){
            long start=System.currentTimeMillis();
            canvas= holder.lockCanvas();
            if(null!=canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();
            long end=System.currentTimeMillis();
            if(end-start<50){
                try {
                    Thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
