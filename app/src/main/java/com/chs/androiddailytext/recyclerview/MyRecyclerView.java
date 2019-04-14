package com.chs.androiddailytext.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    private float currentY;
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
    /**
     * y偏移量
     */
    private int scrollY;
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
        //获取系统最小滑动距离
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录下手指按下的位置
                currentY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //当手指的位置大于最小滑动距离的时候拦截事件
                float moveY = currentY - ev.getRawY();
                if(Math.abs(moveY)>touchSlop){
                    intercepted =  true;
                }
            default:
        }
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       if(event.getAction() == MotionEvent.ACTION_MOVE){
          //滑动距离
           int diff = (int) (currentY - event.getRawY());
//           Log.i("diff",diff+"");
          //上滑是正 下滑是负数
           //因为调用系统的scrollBy方法，只是滑动当前的MyRecyclerView容器
           //我们需要在滑动的时候，动态的删除和加入子view，所以重写系统的scrollBy方法
           scrollBy(0,diff);
       }
        return super.onTouchEvent(event);
    }
    private int scrollBounds(int scrollY) {
        //上滑极限值
        if (scrollY > 0) {
            scrollY = Math.min(scrollY,sumArray(heights, firstRow, heights.length-firstRow)-height);
        }else {
            //下滑极限值
            scrollY = Math.max(scrollY, -sumArray(heights, 0, firstRow));
        }
        return scrollY;

    }
    @Override
    public void removeView(View view) {
        super.removeView(view);
        int key= (int) view.getTag(1234512045);
        mRecycler.put(view, key);
    }
    @Override
    public void scrollBy(int x, int y) {
        scrollY+=y;
        scrollY = scrollBounds(scrollY);
        //<1>上滑
        if(scrollY>0){
         //上滑移除最上面的一条
         while (scrollY>heights[firstRow]){
           removeView(mCurrentViewList.remove(0));
           //scrollY的值保持在0到一条item的高度之间
           scrollY -= heights[firstRow];
           firstRow++;
         }
         //<2>上滑加载最下面的一条
        // 当剩下的数据的总高度小于屏幕的高度的时候
         while (getFillHeight() < height){
            int addLast = firstRow + mCurrentViewList.size();
             View view = createView(addLast,width,heights[addLast]);
             //上滑是往mCurrentViewList中添加数据
             mCurrentViewList.add(mCurrentViewList.size(),view);
         }
        }else if(scrollY<0){
            //<3>下滑最上面加载
            //这里判断scrollY<0即可，滑到顶置零
            while (scrollY<0){
                //第一行应该变成firstRow - 1
                int firstAddRow = firstRow - 1;
                View view = createView(firstAddRow, width, heights[firstAddRow]);
                //找到view添加到第一行
                mCurrentViewList.add(0,view);
                firstRow --;
                scrollY += heights[firstRow+1];
            }
            //<4>下滑最下面移除
            while (sumArray(heights, firstRow, mCurrentViewList.size())-scrollY>height){
                removeView(mCurrentViewList.remove(mCurrentViewList.size() - 1));
            }
//            while (sumArray(heights, firstRow, mCurrentViewList.size()) - scrollY - heights[firstRow + mCurrentViewList.size() - 1] >= height) {
//                removeView(mCurrentViewList.remove(mCurrentViewList.size() - 1));
//            }
        }
        //重新布局
        repositionViews();
    }

    private void repositionViews() {
        int left, top, right, bottom, i;
        top =  - scrollY;
        i = firstRow;
        Log.i("mCurrentViewList",(i)+"--i----"+mCurrentViewList.size());
        for (View view : mCurrentViewList) {
            if(i<heights.length){
                bottom = top + heights[i++];
                view.layout(0, top, width, bottom);
                top = bottom;
            }
        }
    }

    private int getFillHeight() {
        return sumArray(heights, firstRow, mCurrentViewList.size()) - scrollY;
    }

    interface Adapter{
        View onCreateViewHodler(int position, View convertView, ViewGroup parent);
        View onBinderViewHodler(int position, View convertView, ViewGroup parent);
        int getItemViewType(int row);
        int getViewTypeCount();
        int getCount();
        int getHeight(int index);
    }

}
