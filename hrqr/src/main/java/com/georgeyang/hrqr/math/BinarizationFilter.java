package com.georgeyang.hrqr.math;

import android.graphics.Color;
import android.util.Log;

import com.georgeyang.hrqr.util.BitmapUtil;

import java.util.Arrays;

/**
 * 图片二值化
 * Created by george.yang on 2016/2/26 0026.
 */
public class BinarizationFilter extends ImageMath {
    @Override
    public int[] dealImage(int[] data, int width, int height) {
        int[] ret = new int[data.length];
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
               int[] color = BitmapUtil.getRGB(data[j*width+i]);
//                Log.d("george","rgb:" + Arrays.toString(color) + " avg:" + (color[0]+color[1]+color[2])/3f);

                ret[j*width+i]=((color[0]+color[1]+color[2])/3f)>(255f/2)?Color.rgb(255,255,255):0;
//                Log.d("george","ret:" + ret[j*width+i]);
            }
        }
        return ret;
    }
}
