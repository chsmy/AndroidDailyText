package com.chs.androiddailytext.widget

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.cos
import kotlin.math.sin

/**
 *  @author chs
 *  date：2019-10-10 10:06
 *  des：射箭动画
 */
class ArrowDrawable(
        private var mWidth: Int,
        private var mHeight: Int
) : Drawable() {

    private lateinit var  mBowPath : Path
    private var mPaint = Paint()
    private var mTemPoint = PointF()
    private var mBowLength: Int = 0
    private var mCenterX: Float = 0.0f

    init {
        initPaint()
    }

    private fun initPaint() {
        mPaint.isAntiAlias = true
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeJoin = Paint.Join.ROUND
        mBowLength = mWidth / 4
        mCenterX = (mWidth / 2).toFloat()
        mBowPath = Path()
    }

    override fun draw(canvas: Canvas) {
        updateBowPath(30f)
        mPaint.style = Paint.Style.STROKE
        canvas.drawPath(mBowPath, mPaint)
    }
    /**
     * 根据当前拖动的进度计算出弓的弯曲角度
     */
//    private fun getAngleByProgress(): Float {
//        //当前角度 = 基本角度 + (可用角度 * 滑动进度)
//        return mBaseAngle + if (mProgress <= .5f)
//            0
//        else
//            mUsableAngle * (mProgress - .5f/*对齐(从0%开始)*/) * 2/*两倍追赶*/
//    }
    /**
     * 初始化弓
     * @param currentAngle 弓弯曲的角度
     */
    private fun updateBowPath(currentAngle: Float) {
        val stringPoint = getPointByAngle(currentAngle)
        //起始点的x坐标，直接镜像 结束点的x轴坐标
        val startX = mCenterX * 2 - stringPoint.x
        //起始点的y坐标，也就是结束点的y坐标了
        val startY = stringPoint.y
        //控制点x坐标，直接取宽度的一半，也就是中点了
        val controlX = mCenterX
        //控制点的y坐标，刚好跟两端的y坐标相反，这样的话，线条的中点位置就能保持不变
        val controlY = -stringPoint.y
        //结束点坐标，直接赋值，因为getPointByAngle计算的就是结束点坐标
        val endX = stringPoint.x
        val endY = stringPoint.y

        mBowPath.reset()
        //根据三点坐标画一条二阶贝塞尔曲线
        mBowPath.moveTo(startX, startY)
        mBowPath.quadTo(controlX, controlY, endX, endY)
    }

    //根据角度知道弓的点
    private fun getPointByAngle(angle: Float):PointF {
        //把角度转成圆弧
        var radian = angle * Math.PI / 180
        //半径取弓长的一半
        var radius = mBowLength / 2
        //x轴坐标
        var x = (mCenterX + radius * cos(radian)).toFloat()
        //y轴坐标
        var y = (radius * sin(radian)).toFloat()
        mTemPoint.set(x,y)
        return mTemPoint;
    }

    //告诉外面它的宽和高
    override fun getIntrinsicWidth(): Int = mWidth

    override fun getIntrinsicHeight(): Int = mHeight

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

}