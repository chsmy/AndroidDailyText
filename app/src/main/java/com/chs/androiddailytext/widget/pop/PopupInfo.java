package com.chs.androiddailytext.widget.pop;

import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;

import com.chs.androiddailytext.widget.pop.animator.PopupAnimation;
import com.chs.androiddailytext.widget.pop.animator.PopupAnimator;
import com.chs.androiddailytext.widget.pop.interfaces.XPopupCallback;


/**
 * Description: Popup的属性封装
 * Create by dance, at 2018/12/8
 */
public class PopupInfo {
    public Boolean isDismissOnBackPressed = true;  //按返回键是否消失
    public Boolean isDismissOnTouchOutside = true; //点击外部消失
    public Boolean autoDismiss = true; //操作完毕后是否自动关闭
    public Boolean hasShadowBg = true; // 是否有半透明的背景
    public View atView = null; // 依附于那个View显示
    public View watchView = null; // 依附于那个View显示
    // 动画执行器，如果不指定，则会根据窗体类型popupType字段生成默认合适的动画执行器
    public PopupAnimation popupAnimation = null;
    public PopupAnimator customAnimator = null;
    public PointF touchPoint = null; // 触摸的点
    public int maxWidth; // 最大宽度
    public int maxHeight; // 最大高度
    public Boolean autoOpenSoftInput = false;//是否自动打开输入法
    public XPopupCallback xPopupCallback;

    public ViewGroup decorView; //每个弹窗所属的DecorView
    public Boolean isMoveUpToKeyboard = true; //是否移动到软键盘上面，默认弹窗会移到软键盘上面
    public PopupPosition popupPosition = null; //弹窗出现在目标的什么位置
    public Boolean hasStatusBarShadow = false;
    public int offsetX, offsetY;//x，y方向的偏移量
    public Boolean enableDrag = true;//是否启用拖拽
    public boolean isCenterHorizontal = false;//是否水平居中
    public boolean isRequestFocus = true; //弹窗是否抢占焦点
    /**
     * 若为true 变为大号字体
     */
    public boolean bigText = false;
    /**
     * 是否有全部的选项，有全部的话，全部和其他相是互斥的
     */
    public boolean hasAllOptions = false;
    /**
     * 是否有子项 （左边一个列表 右边一个列表）
     */
    public boolean hasSubOption = false;
    /**
     * 选择的类型 默认单选
     */
    public SelectType mSelectType = SelectType.RADIO;
//    public boolean isClickThrough = true;//是否点击透传，默认弹背景点击是否拦截的

    public View getAtView() {
        return atView;
    }
    public void setAtView(View atView) {
        this.atView = atView;
//        this.popupType = PopupType.AttachView;
    }

    @Override
    public String toString() {
        return "PopupInfo{" +
                ", isDismissOnBackPressed=" + isDismissOnBackPressed +
                ", isDismissOnTouchOutside=" + isDismissOnTouchOutside +
                ", hasShadowBg=" + hasShadowBg +
                ", atView=" + atView +
                ", popupAnimation=" + popupAnimation +
                ", customAnimator=" + customAnimator +
                ", touchPoint=" + touchPoint +
                ", maxWidth=" + maxWidth +
                ", maxHeight=" + maxHeight +
                '}';
    }
}
