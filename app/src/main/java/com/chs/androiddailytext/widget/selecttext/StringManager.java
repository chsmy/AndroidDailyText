package com.chs.androiddailytext.widget.selecttext;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
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
    private List<CustomUnderlineSpan> spans = new ArrayList<>();
    /**
     * 填充过的位置
     */
    public static List<Integer> hasRemoved = new ArrayList<>();
    /**
     * 已经填充的
     */
    private SparseBooleanArray hasFilled = new SparseBooleanArray();

    public StringManager() {
        blank.add("10");
        blank.add("15");
        blank.add("20");
        blank.add("25");
        blank.add("30");
        blank.add("35");
        blank.add("40");
        blank.add("45");
        blank.add("50");
        blank.add("55");
    }

    public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
        this.onWordClickListener = onWordClickListener;
    }

    public SpannableString getResult(String text){
        SpannableString spannableString = new SpannableString(text);
        if(wordInfos.isEmpty()){
            wordInfos = getWordInfo(text);
        }
        //给每个单词设置点击事件
        for (int i = 0; i < wordInfos.size(); i++) {
            final WordInfo info = wordInfos.get(i);
            final int finalI = i;
            spannableString.setSpan(new WordClickableSpan(finalI,onWordClickListener,wordInfos),
                    info.getStart(),info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(blank.contains(String.valueOf(i))){
                CustomUnderlineSpan customUnderlineSpan = new CustomUnderlineSpan();
                spans.add(customUnderlineSpan);
                spannableString.setSpan(customUnderlineSpan,info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }

    public SpannableString getClickResult(String text, WordInfo wordInfo, Context context, int position){
        SpannableString spannableString = new SpannableString(text);
        if(wordInfos.isEmpty()){
            wordInfos = getWordInfo(text);
        }
        for (int i = 0; i < wordInfos.size(); i++) {
            final WordInfo info = wordInfos.get(i);
            final int finalI = i;
            spannableString.setSpan(new WordClickableSpan(finalI,onWordClickListener,wordInfos),
                    info.getStart(),info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(blank.contains(String.valueOf(i))){
                CustomUnderlineSpan customUnderlineSpan = new CustomUnderlineSpan();
                spans.add(customUnderlineSpan);
                spannableString.setSpan(customUnderlineSpan,info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if(hasRemoved.contains(i)){
                spannableString.setSpan(new UnderlineSpan(),info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        if(!hasRemoved.contains(position)||(hasRemoved.contains(position)&&hasFilled.get(position))){
            spannableString.setSpan(new BackgroundImageSpan(R.drawable.text_underline_bg,
                            context.getResources().getDrawable(R.drawable.text_underline_bg)),
                    wordInfo.getStart(),wordInfo.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
            int start = text.toString().indexOf(word, startIndex);
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
        if (TextUtils.isEmpty(text.toString())) {
            return new ArrayList<String>();
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
