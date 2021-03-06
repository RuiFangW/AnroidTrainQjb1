package com.example.lenovo2.colorid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * CameraSurface
 *
 * @author: lenovo
 * @time: 2016/1/23 9:27
 */
public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {
    public final static String TAG=" CameraSurface";
    private SurfaceHolder holder;

    protected Camera camera;//照相机
    private boolean ispreview;
    public CameraSurface(Context context) {
        super(context);
        init();
    }

    public CameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    void init(){
        holder=getHolder();
        holder.addCallback(this);//添加监听器
        ispreview = false;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera=Camera.open();
        if(null!=camera){
            try {
                camera.setPreviewDisplay(holder);//设置预览界面到当前surface中
            } catch (IOException e) {
                e.printStackTrace();
                camera.release();
                camera=null;
            }
            Camera.Parameters parameters=camera.getParameters();
            parameters.setPreviewSize(getWidth(),getHeight());//设置camera预览的尺寸
            camera.setParameters(parameters);
            camera.startPreview();//开始预览
            ispreview=true;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(null!=camera){
            if(ispreview){
                camera.stopPreview();//停止预览
            }
            camera.release();//释放camera
            camera=null;
        }
    }
}
