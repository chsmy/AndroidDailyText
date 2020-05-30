package com.chs.app_jetpack.ui.camera;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * author：chs
 * date：2020/5/30
 * des： 自定义图片分析类
 */
public class CustomAnalyzer implements ImageAnalysis.Analyzer {
    @Override
    public void analyze(@NonNull ImageProxy image) {
        //Since format in ImageAnalysis is YUV,image.planes[0]包含屏幕的亮度
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        //从回调中提取数据
        buffer.rewind();
        byte[] dst = new byte[buffer.remaining()];
        buffer.get(dst);
        List<Integer> pix = new ArrayList<>();
        for (int i = 0; i < dst.length; i++) {
            pix.add(Integer.valueOf(dst[i]&0xFF));
        }
        image.close();
    }
}
