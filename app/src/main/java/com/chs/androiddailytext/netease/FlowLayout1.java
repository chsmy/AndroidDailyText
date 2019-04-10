package com.chs.androiddailytext.netease;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chs.androiddailytext.R;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout1 extends RelativeLayout {
    private LayoutInflater mInflater;
    //FlowLayout  的宽度
    private int mWidth;
    private List<String> mTags = new ArrayList<String>();
    private boolean mInitialized;
    public FlowLayout1(Context context) {
        super(context);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
    }

    public FlowLayout1(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewTreeObserver mViewTreeObserber = getViewTreeObserver();
        mViewTreeObserber.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(!mInitialized) {
                    mInitialized = true;
                    drawTags();
                }
            }
        });
    }

    public void addTag(List<String> tags){
        mTags = tags;
        drawTags();
    }

    private void drawTags() {
        removeAllViews();
        float total = 0;
        //现在的位置
        int index = 1;
        //相对起点位置
        int pindex = index;
        for(String item:mTags){
            View tagLayout = (View) mInflater.inflate(R.layout.layout_tag, null);
            tagLayout.setId(index);
            TextView tagView = (TextView) tagLayout.findViewById(R.id.tag_txt);
            //设置标签view显示的文字
            tagView.setText(item);
            tagView.setPadding(15, 5, 15, 5);
            float tagWidth = tagView.getPaint().measureText(item) + 15 * 2;
            LayoutParams tagParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //如果当前行剩余空间可以放下下一个textview,就放到最后放入的textview的右边
            if (total + tagWidth < mWidth) {
                tagParams.addRule(RelativeLayout.ALIGN_TOP, pindex);
                tagParams.addRule(RelativeLayout.RIGHT_OF, index - 1);
                if (index > 1) {
                    tagParams.leftMargin = 15;
                    total += 15;
                }
            //如果当前剩余空间不够，就放到参考位置的下面 相当于每一行的第一个
            }else {
                tagParams.addRule(RelativeLayout.BELOW, pindex);
                tagParams.topMargin = 10;
                total = 0;
                pindex = index;
            }
            //当前行的宽度加上新textview的宽度
            total += tagWidth;
            //添加到相对布局中
            addView(tagLayout, tagParams);
            index++;
        }
    }
}
