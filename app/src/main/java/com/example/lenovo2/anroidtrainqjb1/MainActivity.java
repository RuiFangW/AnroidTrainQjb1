package com.example.lenovo2.anroidtrainqjb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(new MySurfaceView(this));
       // crossView=(CircleCrossView)findViewById(R.id.cross_view);
        //previewSurface=(PreviewSurface)findViewById(R.id.preview_surface);
        //previewSurface.setOnColorListener(this);

    }
    public void onColor(int color){
        //crossView.setColor(color);

    }
}
