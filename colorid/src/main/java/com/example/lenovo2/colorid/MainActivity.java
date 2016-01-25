package com.example.lenovo2.colorid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements PreviewSurface.OnColorListener {
    private static final String TAG = "MainActivity";
    private CircleCrossView crossView;
    private PreviewSurface previewSurface; //camera预览视图

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crossView=(CircleCrossView)findViewById(R.id.cross_view);
        previewSurface = (PreviewSurface) findViewById(R.id.preview);
        //设置颜色识别的监听器
        previewSurface.setOnColorListener(this);
    }

    @Override
    public void onColor(int color) {
        crossView.setColor(color);
        crossView.refresh();
    }
}
