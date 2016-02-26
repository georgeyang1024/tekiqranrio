package com.georgeyang.hrqr.math;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.georgeyang.hrqr.util.BitmapUtil;

import java.util.Arrays;

/**
 * 图片腐蚀操作(白点3*3周围带黑色，改点变成黑色)
 * 参考:http://wujeangwei.blog.51cto.com/785319/160874
 * Created by george.yang on 2016/2/26 0026.
 */
public class CorrosionFilter extends ImageMath {
    @Override
   public int[] dealImage(int[] data, int width, int height) {
        int[] ret = new int[data.length];
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                int color = BitmapUtil.getPixel(data,width,height,i,j);
                ret[j*width+i]=color;

//                Log.d("george","reg:" + Arrays.toString(BitmapUtil.getRGB(color)));
                if (BitmapUtil.getRGB(color)[0]==255) {
                    boolean pass = false;
                    for(int m=-1;m<2;m++) {
                        for(int n=-1;n<2;n++) {
                            int piex1= BitmapUtil.getPixel(data,width,height,i+m,j+n);
                            if(BitmapUtil.getRGB(piex1)[0]==0) {
//                                BitmapUtil.setPixel(ret,width,height,i,j,0);
//                                Log.d("george","piex1变黑色:" + (j*width+i));
//                                ret[j*width+i]=0;
                                ret[j*width+i]= Color.rgb(0,0,0);
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
