package com.georgeyang.hrqr.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/2/23 0023.
 */
public class BitmapUtil {

    public static byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap bytes2Bimap(byte[] b){
        if(b.length!=0){
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }


    public static int getPixel(int[] inPixels, int width, int height, int col,
                         int row) {
        if(col < 0 || col >= width)
            col = 0;
        if(row < 0 || row >= height)
            row = 0;
        int index = row * width + col;
        return inPixels[index];
    }

    public static void setPixel(int[] inPixels, int width, int height, int col,
                               int row,int color) {
        if(col < 0 || col >= width)
            col = 0;
        if(row < 0 || row >= height)
            row = 0;
        int index = row * width + col;
        inPixels[index] = color;
    }

    public static int[] getRGB(int pixel) {
        int[] rgb = new int[3];
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        return rgb;
    }


    public static int[] getARGB (int pixel) {
        int[] argb = new int[4];
        argb[0] = pixel>>>24;
        argb[0] = (pixel & 0xff0000) >> 16;
        argb[1] = (pixel & 0xff00) >> 8;
        argb[2] = (pixel & 0xff);
        return argb;
    }



    // 计算像素点(x,y)Y方向上的梯度值
    public static void writeImage(Bitmap img, String imageName) {
        File myCaptureFile = new File( imageName);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            img.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 官网：获取压缩后的图片
     *
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String filepath,
                                                     int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return BitmapFactory.decodeFile(filepath, options);
    }

    /**
     * 计算压缩比例值(改进版 by touch_ping)
     *
     * 原版2>4>8...倍压缩
     * 当前2>3>4...倍压缩
     *
     * @param options
     *            解析图片的配置信息
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度O
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int picheight = options.outHeight;
        final int picwidth = options.outWidth;

        float targetheight = picheight * 1f;
        float targetwidth = picwidth * 1f;
        int inSampleSize = 1;

            while (targetheight  > reqHeight
                    || targetwidth> reqWidth) {
                inSampleSize += 1;
                targetheight = picheight/inSampleSize;
                targetwidth = picwidth/inSampleSize;
        }

        return inSampleSize;
    }

    /**
     * 计算压缩比例值(改进版 by touch_ping)
     *
     * 原版2>4>8...倍压缩
     * 当前2>3>4...倍压缩
     *
     * @param bitmap
     *            解析图片的配置信息
     * @param reqWidth
     *            所需图片压缩尺寸最小宽度O
     * @param reqHeight
     *            所需图片压缩尺寸最小高度
     * @return
     */
    public static int calculateInSampleSize(Bitmap bitmap,
                                            int reqWidth, int reqHeight) {
        final int picheight = bitmap.getHeight();
        final int picwidth = bitmap.getWidth();

        int targetheight = picheight;
        int targetwidth = picwidth;
        int inSampleSize = 1;

        if (targetheight > reqHeight || targetwidth > reqWidth) {
            while (targetheight  >= reqHeight
                    && targetwidth>= reqWidth) {
                inSampleSize += 1;
                targetheight = picheight/inSampleSize;
                targetwidth = picwidth/inSampleSize;
            }
        }

        return inSampleSize;
    }
}
