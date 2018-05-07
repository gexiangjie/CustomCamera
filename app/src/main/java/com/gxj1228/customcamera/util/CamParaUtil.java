package com.gxj1228.customcamera.util;

import android.hardware.Camera;

import java.util.List;

/**
 * Created by gxj on 2018/2/8.
 */
public class CamParaUtil {

    /**
     * 打开相机
     */
    public static Camera openCamera() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }

    /**
     * Android相机的预览尺寸都是4:3或者16:9，这里遍历所有支持的预览尺寸，得到16:9的最大尺寸，保证成像清晰度
     *
     * @param sizes
     * @return 最佳尺寸
     */
    public static Camera.Size getBestSize(List<Camera.Size> sizes) {
        Camera.Size bestSize = null;
        for (Camera.Size size : sizes) {
            if ((float) size.width / (float) size.height == 16.0f / 9.0f) {
                if (bestSize == null) {
                    bestSize = size;
                } else {
                    if (size.width > bestSize.width) {
                        bestSize = size;
                    }
                }
            }
        }
        return bestSize;
    }
}
