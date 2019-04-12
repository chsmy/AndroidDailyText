package com.chs.androiddailytext.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 * date：2019-04-12 16:10
 * des：
 */
public class MyRecyclerView extends ViewGroup {

    private Recycler mRecycler;
    private Adapter mAdapter;

    /**
     * 当前屏幕上显示的View
     */
    private List<View> mCurrentViewList;

    /**
     * 当前滑动的值
     */
    private int currentY;
    /**
     * 最小滑动距离
     */
    private int touchSlop;
    /**
     * 防止重复布局
     */
    private boolean needRelayout;
    /**
     * 行数
     */
    private int rowCount;
    private int [] heights;
    private int firstRow;
    public Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
        if(adapter!=null){
            mRecycler = new Recycler(adapter.getViewTypeCount());
            firstRow = 0;
            needRelayout = true;
            requestLayout();
        }
    }

    public MyRecyclerView(Context context) {
        this(context,null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        touchSlop = configuration.getScaledTouchSlop();
        needRelayout = true;
        mCurrentViewList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(mAdapter!=null){
            rowCount = mAdapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < rowCount; i++) {
                heights[i] = mAdapter.getHeight(i);
            }
        }
        int totalH = sumArray(heights, 0, heights.length);
        setMeasuredDimension(widthSize,Math.min(heightSize,totalH));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private int sumArray(int array[], int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += array[i];
        }
        return sum;
    }
    private int width;
    private int height;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if(needRelayout&&changed){
            needRelayout = false;
            mCurrentViewList.clear();
            removeAllViews();
            if(mAdapter!=null){
             width = r-l;
             height = b-t;
             int top =0;
                for (int i = 0; i < rowCount&&top<height; i++) {
                    int bottom = heights[i]+top;
                    View view = createView(i,width,heights[i]);
                    view.layout(0,top,width,bottom);
                    mCurrentViewList.add(view);
                    top = bottom;
                }
            }
        }
    }

    private View createView(int row, int width, int height) {
        int itemType= mAdapter.getItemViewType(row);
        View reclyView = mRecycler.get(itemType);
        View view = null;
        if(reclyView==null){
            view = mAdapter.onCreateViewHodler(row,reclyView,this);
            if (view == null) {
                throw new RuntimeException("onCreateViewHolder  必须调用");
            }
        }else {
            view = mAdapter.onBinderViewHodler(row,reclyView,this);
        }
        view.setTag(1234512045, itemType);
        view.measure(MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY)
                ,MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
        addView(view,0 );
        return view;
    }

    interface Adapter{
        View onCreateViewHodler(int position, View convertView, ViewGroup parent);
        View onBinderViewHodler(int position, View convertView, ViewGroup parent);
        int getItemViewType(int row);
        int getViewTypeCount();
        int getCount();
        public int getHeight(int index);
    }

}
