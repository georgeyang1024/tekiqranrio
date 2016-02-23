package com.georgeyang.hrqr.math;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.georgeyang.hrqr.util.BitmapUtil;

/**
 * 边缘提取算法
 * Created by Administrator on 2016/2/23 0023.
 */
public class Canny implements Math{
    @Override
    public byte[] dealImage(byte[] data) {
        return BitmapUtil.bitmap2Bytes(dealImage(BitmapUtil.bytes2Bimap(data)));
    }

    public Bitmap dealImage(Bitmap data) {
        return null;
    }
}
