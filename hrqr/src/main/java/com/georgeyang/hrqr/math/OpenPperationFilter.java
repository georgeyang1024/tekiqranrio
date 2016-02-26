package com.georgeyang.hrqr.math;

import android.graphics.Bitmap;
import android.os.Environment;

import com.georgeyang.hrqr.util.BitmapUtil;

/**
 * 图片开操作(先腐蚀后膨胀的过程)
 * Created by george.yang on 2016/2/26 0026.
 */
public class OpenPperationFilter extends ImageMath {
    @Override
    public int[] dealImage(int[] data, int width, int height) {
        int[] corrosion = new CorrosionFilter().dealImage(data,width,height);
//        Bitmap bm =      Bitmap.createBitmap(corrosion,width,height, Bitmap.Config.RGB_565);
//        BitmapUtil.writeImage(bm, Environment.getExternalStorageDirectory().getAbsolutePath()+"/corrosion_1.png");

        int[] inflation = new InflationFilter().dealImage(corrosion,width,height);
//        Bitmap bm2 =      Bitmap.createBitmap(inflation,width,height, Bitmap.Config.RGB_565);
//        BitmapUtil.writeImage(bm2, Environment.getExternalStorageDirectory().getAbsolutePath()+"/inflation_1.png");
        return inflation;
    }
}
