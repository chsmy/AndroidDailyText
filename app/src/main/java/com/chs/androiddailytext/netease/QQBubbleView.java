package com.chs.androiddailytext.netease;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author chs
 * date：2019-03-28 09:55
 * des：
 */
public class QQBubbleView extends View {
    public QQBubbleView(Context context) {
        this(context,null);
    }

    public QQBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }
}
