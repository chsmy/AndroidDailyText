package com.chs.androiddailytext.widget.selecttext;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.SparseBooleanArray;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: chs
 * @date: Create in 2020/9/14
 * @description
 */
public class StringManager {

    private List<WordInfo> wordInfos = new ArrayList<>();
    private OnWordClickListener onWordClickListener;
    /**
     * 需要填空的位置
     */
    public static List<String> blank = new ArrayList<>();
    /**
     * 填充过的位置
     */
    public static List<Integer> hasRemoved = new ArrayList<>();
    /**
     * 已经填充的
     */
    private SparseBooleanArray hasFilled = new SparseBooleanArray();

    /**
     * 需要划线的地方
     */
    public static List<String> needLines = new ArrayList<>();

    public StringManager() {

    }


    public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
        this.onWordClickListener = onWordClickListener;
    }

    public SpannableString getResult(String text){
        return getResult(text,null,null,-1);
    }

    public SpannableString getResult(String text, WordInfo wordInfo, Context context, int position){
        SpannableString spannableString = new SpannableString(text);
        if(wordInfos.isEmpty()){
            wordInfos = getWordInfo(text);
        }
        for (int i = 0; i < wordInfos.size(); i++) {
            final WordInfo info = wordInfos.get(i);
            final int finalI = i;
            spannableString.setSpan(new WordClickableSpan(finalI,onWordClickListener,wordInfos),
                    info.getStart(),info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //填空横线
            if(blank.contains(String.valueOf(i))){
                CustomUnderlineSpan customUnderlineSpan = new CustomUnderlineSpan();
                spannableString.setSpan(customUnderlineSpan,info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            //已经填过的
            if(hasRemoved.contains(i)){
                spannableString.setSpan(new CustomUnderlineSpan(R.color.black,R.color.black,
                                3f,true)
                        ,info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            //需要绘制下划线的
            if(needLines.contains(String.valueOf(i))){
                float lineHeight = i%2==0?5f:9f;
                CustomUnderlineSpan customUnderlineSpan = new CustomUnderlineSpan(R.color.colorAccent,R.color.black
                        ,lineHeight,true);
                spannableString.setSpan(customUnderlineSpan,info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        if(!hasRemoved.contains(position)||(hasRemoved.contains(position)&&hasFilled.get(position))){
            if(wordInfo!=null){
                spannableString.setSpan(new BackgroundImageSpan(R.drawable.text_underline_bg,
                                context.getResources().getDrawable(R.drawable.text_underline_bg)),
                        wordInfo.getStart(),wordInfo.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        hasFilled.put(position,true);
        return spannableString;
    }

    /**
     * 获取单词的info
     * @return
     */
    private List<WordInfo> getWordInfo(String text) {
        // 获取分割之后的所有单词
        List<String> words = splitWord(text);
        // 创建WordInfos
        List<WordInfo> result = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            // 获取开始位置
            int start = text.indexOf(word, startIndex);
            int end = start + word.length();
            startIndex = end;
            WordInfo wordInfo = new WordInfo();
            wordInfo.setStart(start);
            wordInfo.setEnd(end);
            wordInfo.setWord(word);
            result.add(wordInfo);
        }
        return result;
    }

    /**
     * 分割单词
     * @return
     */
    private List<String> splitWord(String text) {
        if (TextUtils.isEmpty(text)) {
            return new ArrayList<>();
        }
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z-’]+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            results.add(matcher.group(0));
        }
        return results;
    }

}
