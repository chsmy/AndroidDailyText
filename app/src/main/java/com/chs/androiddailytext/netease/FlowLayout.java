package com.chs.androiddailytext.netease;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author：chs
 * date：2019/4/7
 * des：
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取本身宽度的测量模式和大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //记录当前行每个子view加起来的中宽度
        int currentWidth = 0;
        //如果有多行，记录最宽的那一行的宽度
        int measureWidth = 0;
        //记录子view每一行加起来的高度
        int currentHeight = 0;

        //遍历测量每个子view
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View chileView = getChildAt(i);
            //测量子view的宽高
            measureChild(chileView,widthMeasureSpec,heightMeasureSpec);
            //得到子view的测量数据
            MarginLayoutParams layoutParams = (MarginLayoutParams)chileView.getLayoutParams();
            //子view最终实际占用的宽高需要加上它的margin值
            int childWidth = chileView.getMeasuredWidth()+ layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = chileView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;

            //如果当前的行的子view的宽加起来小于父view的可用宽度
            if(currentWidth<widthSize){
                currentWidth = currentWidth+childWidth;
                currentHeight = Math.max(currentHeight,childHeight);
            }else {
                measureWidth = Math.max(currentWidth,childWidth);
                currentWidth = childWidth;
                currentHeight = currentHeight+childHeight;
            }
        }
        //保存自己的宽高
        setMeasuredDimension(resolveSize(measureWidth,widthMeasureSpec),resolveSize(currentHeight,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //自身的可用宽
        int width = getWidth()-getPaddingLeft()-getPaddingRight();
        //当前行子view的总宽度
        int currentWidth = 0;
        //当前高度
        int currentHeight = 0;

        //根据上面onMeasure中测量的值，遍历布局子view，算出子view的左上右下的值，调用其layout方法
        int childCount = getChildCount();
        for (int j = 0; j < childCount; j++) {
            View chileView = getChildAt(j);
            //得到子view的测量数据
            MarginLayoutParams layoutParams = (MarginLayoutParams)chileView.getLayoutParams();
            //子view最终实际占用的宽高需要加上它的margin值
            int childWidth = chileView.getMeasuredWidth()+ layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = chileView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            //如果如果当前宽度加上下一个子view的宽度小于父view可用的宽度，就加上子view的宽
            //反之，当前宽度重置为下一个子view的宽
            if(currentWidth+childWidth<width){
                currentWidth = currentWidth+childWidth;
            }else {
                currentWidth = childWidth;
                currentHeight = currentHeight + childHeight;
            }
            //计算出子view左 上 右 下 的位置
            int childL = currentWidth - childWidth+layoutParams.leftMargin+layoutParams.rightMargin;
            int childT = currentHeight+layoutParams.topMargin+layoutParams.bottomMargin;
            int childR = childL+chileView.getMeasuredWidth();
            int childB = childT + chileView.getMeasuredHeight();
            //布局
            chileView.layout(childL,childT,childR,childB);

        }
    }
}
