package com.example.hrqr.hrqrtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.georgeyang.hrqr.math.CannyEdgeFilter;
import com.georgeyang.hrqr.math.SobelEdgeDetect;
import com.georgeyang.hrqr.util.UiThread;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements UiThread.UIThreadEvent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UiThread.init(this).start(this);
    }

    @Override
    public Object runInThread(String flag, Object obj, UiThread.Publisher publisher) {
//        SobelEdgeDetect sobelEdgeDetect = new SobelEdgeDetect(50);
//        sobelEdgeDetect.readImage(Environment.getExternalStorageDirectory().getAbsolutePath() + "/0.jpg");
//        sobelEdgeDetect.createEdgeImage(Environment.getExternalStorageDirectory().getAbsolutePath() + "/1.png");

        CannyEdgeFilter filter = new CannyEdgeFilter();
        Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/0.jpg");
       Bitmap out = filter.filter(bm);
        SobelEdgeDetect.writeImage(out,Environment.getExternalStorageDirectory().getAbsolutePath() + "/1.png");
        return null;
    }

    @Override
    public void runInUi(String flag, Object obj, boolean ispublish, float progress) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
    }
}
