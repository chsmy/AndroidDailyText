package com.chs.androiddailytext.widget.selecttext;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;


import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author: chs
 * @date: Create in 2020/9/14
 * @description
 */
public class WordClickableSpan extends ClickableSpan {

    private int clickPosition;
    private OnWordClickListener onWordClickListener;
    private List<WordInfo> wordInfos;

    public WordClickableSpan(int clickPosition, OnWordClickListener onWordClickListener, List<WordInfo> wordInfos) {
        this.clickPosition = clickPosition;
        this.onWordClickListener = onWordClickListener;
        this.wordInfos = wordInfos;
    }

    @Override
    public void onClick(@NonNull View widget) {
       if(wordInfos!=null){
           WordInfo wordInfo = wordInfos.get(clickPosition);
           if(StringManager.blank.remove(String.valueOf(clickPosition))){
               StringManager.hasRemoved.add(clickPosition);
           }
           if(onWordClickListener!=null){
               onWordClickListener.click(wordInfo,clickPosition);
           }
       }
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#000000"));
        //去掉下划线
        ds.setUnderlineText(false);
    }
}
