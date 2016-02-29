package com.georgeyang.hrqr.math;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.georgeyang.hrqr.util.BitmapUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 图片预处理
 * Created by george.yang on 16/2/29.
 */
public class PreMathFilter {
    public Bitmap dealImage(Bitmap image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, newOpts);
            inputStream.close();

            newOpts.inJustDecodeBounds = false;
            newOpts.inSampleSize = BitmapUtil.calculateInSampleSize(newOpts,400,400);//设置缩放比例
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565

            inputStream = new ByteArrayInputStream(baos.toByteArray());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,newOpts);
            Log.i("test","bitmap:" + bitmap);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
//
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, newOpts);
//
//
//        newOpts.inSampleSize = be;//设置缩放比例
//        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565
//        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        isBm = new ByteArrayInputStream(baos.toByteArray());
//        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);


    }
}
