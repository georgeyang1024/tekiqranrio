package com.example.hrqr.hrqrtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.georgeyang.hrqr.math.BinarizationFilter;
import com.georgeyang.hrqr.math.CannyEdgeFilter;
import com.georgeyang.hrqr.math.ClosePperationFilter;
import com.georgeyang.hrqr.math.CorrosionFilter;
import com.georgeyang.hrqr.math.InflationFilter;
import com.georgeyang.hrqr.math.OpenPperationFilter;
import com.georgeyang.hrqr.math.PreMathFilter;
import com.georgeyang.hrqr.math.SobelEdgeDetect;
import com.georgeyang.hrqr.util.BitmapUtil;
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

        Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/a.jpg");
        Bitmap preBitmap = new PreMathFilter().dealImage(bm);
        BitmapUtil.writeImage(preBitmap,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_pre.png");
        Bitmap binarza = new BinarizationFilter().dealImage(preBitmap);
        BitmapUtil.writeImage(binarza,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_binarza.png");
//
//        Bitmap canny =  new CannyEdgeFilter().filter(binarza);
//        BitmapUtil.writeImage(canny,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_canny.png");

        Bitmap canny =  new SobelEdgeDetect(52).dealImage(binarza);
        BitmapUtil.writeImage(canny,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_canny.png");


//        Bitmap close = new ClosePperationFilter().dealImage(canny);
//        Bitmap open1 = new OpenPperationFilter().dealImage(close);
//        Bitmap open2 = new OpenPperationFilter().dealImage(open1);
//        BitmapUtil.writeImage(open2,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_open1.png");
//
//        close = new OpenPperationFilter().dealImage(canny);
//        open1 = new ClosePperationFilter().dealImage(close);
//        open2 = new ClosePperationFilter().dealImage(open1);
//        BitmapUtil.writeImage(open2,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_open2.png");


        Bitmap close = new CorrosionFilter().dealImage(canny);
        BitmapUtil.writeImage(close,Environment.getExternalStorageDirectory().getAbsolutePath()+"/math_open2.png");



//        SobelEdgeDetect sobelEdgeDetect = new SobelEdgeDetect(50);
//        sobelEdgeDetect.readImage(Environment.getExternalStorageDirectory().getAbsolutePath() + "/0.jpg");
//        sobelEdgeDetect.createEdgeImage(Environment.getExternalStorageDirectory().getAbsolutePath() + "/1.png");

//        CannyEdgeFilter filter = new CannyEdgeFilter();
//        Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/0.jpg");
//       Bitmap out = filter.filter(bm);
//        SobelEdgeDetect.writeImage(out,Environment.getExternalStorageDirectory().getAbsolutePath() + "/1.png");


//        Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/B1.png",1000,1000);
//        Bitmap binarza = new BinarizationFilter().dealImage(bitmap);
//
//        BitmapUtil.writeImage(binarza,Environment.getExternalStorageDirectory().getAbsolutePath()+"/binarza_1.png");
//
//        Bitmap close = new ClosePperationFilter().dealImage(binarza);
//        BitmapUtil.writeImage(close,Environment.getExternalStorageDirectory().getAbsolutePath()+"/close_ret.png");
//        Bitmap open1 = new OpenPperationFilter().dealImage(close);
//        BitmapUtil.writeImage(open1,Environment.getExternalStorageDirectory().getAbsolutePath()+"/open1_ret.png");
//        Bitmap open2 = new OpenPperationFilter().dealImage(open1);
//        BitmapUtil.writeImage(open2,Environment.getExternalStorageDirectory().getAbsolutePath()+"/OpenPperationFilter_ret.png");

//        Bitmap open = new OpenPperationFilter().dealImage(binarza);
//        BitmapUtil.writeImage(binarza,Environment.getExternalStorageDirectory().getAbsolutePath()+"/open1_ret.png");




//        Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Corrosion.png",500,500);
//        Bitmap binarza = new BinarizationFilter().dealImage(bitmap);
//        BitmapUtil.writeImage(binarza,Environment.getExternalStorageDirectory().getAbsolutePath()+"/binarza.png");
////        Bitmap ret = new CorrosionFilter().dealImage(binarza);
////        BitmapUtil.writeImage(ret,Environment.getExternalStorageDirectory().getAbsolutePath()+"/Corrosion.png");
//
//
//        Bitmap ret = new InflationFilter().dealImage(binarza);
//        BitmapUtil.writeImage(ret,Environment.getExternalStorageDirectory().getAbsolutePath()+"/InflationFilter.png");

        return null;
    }

    @Override
    public void runInUi(String flag, Object obj, boolean ispublish, float progress) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
    }
}
