package com.georgeyang.hrqr.math;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.georgeyang.hrqr.util.BitmapUtil;

/**
 * 图片处理接口
 * Created by Administrator on 2016/2/23 0023.
 */
public abstract class ImageMath {
    public int width=0,height=0;
    public Bitmap dealImage(Bitmap data) {
        this.width = data.getWidth();
        this.height = data.getHeight();

        int[] imageData = new int[width*height];
        data.getPixels(imageData, 0, width, 0, 0, width,height);

        imageData = dealImage(imageData,width,height);

        Bitmap ret = Bitmap.createBitmap(imageData,width,height, Bitmap.Config.RGB_565);
        return ret;
    }
   public byte[] dealImage(byte[] data) {
       Bitmap bitmap = BitmapUtil.bytes2Bimap(data);
       Bitmap ret = dealImage(bitmap);
       return BitmapUtil.bitmap2Bytes(ret);
   }

    public abstract int[] dealImage(int[] data,int width,int height);
}
