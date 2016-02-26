package com.georgeyang.hrqr.math;

/**
 * 图片闭操作(先膨胀后腐蚀的过程)
 * Created by george.yang on 2016/2/26 0026.
 */
public class ClosePperationFilter extends ImageMath {
    @Override
    public int[] dealImage(int[] data, int width, int height) {
        int[] inflation = new InflationFilter().dealImage(data,width,height);
        int[] corrosion = new CorrosionFilter().dealImage(inflation,width,height);
        return corrosion;
    }
}
