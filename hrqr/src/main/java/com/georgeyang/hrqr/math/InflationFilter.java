package com.georgeyang.hrqr.math;

import android.graphics.Color;
import android.util.Log;

import com.georgeyang.hrqr.util.BitmapUtil;

/**
 * 膨胀操作(黑点3*3周围带白色，改点变成白色)
 * Created by george.yang on 2016/2/26 0026.
 */
public class InflationFilter extends ImageMath {
    @Override
    public int[] dealImage(int[] data, int width, int height) {
        int[] ret = new int[data.length];
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                int color = BitmapUtil.getPixel(data,width,height,i,j);
//                BitmapUtil.setPixel(ret,width,height,i,j,color);
                ret[j*width+i]=color;

                if (BitmapUtil.getRGB(color)[0]==0) {

                    boolean pass = false;
                    for(int m=-1;m<2;m++) {
                        for(int n=-1;n<2;n++) {
                            int piex1= BitmapUtil.getPixel(data,width,height,i+m,j+n);
//                            Log.i("george","index:" + j*width+i + "pix:" + piex1);
                            if(BitmapUtil.getRGB(piex1)[0]==255) {
//                                BitmapUtil.setPixel(ret,width,height,i,j,255);
                                ret[j*width+i]= Color.rgb(255,255,255);
//                                Log.i("george","index变黑色:" + j*width+i);
                                pass = true;
                                break;
                            }
                        }
                        if (pass) {
                            break;
                        }
                    }
                }
            }
        }
        return ret;
    }
}
