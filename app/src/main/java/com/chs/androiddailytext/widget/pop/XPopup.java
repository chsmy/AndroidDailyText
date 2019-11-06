package com.chs.androiddailytext.widget.pop;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.chs.androiddailytext.widget.pop.interfaces.XPopupCallback;
import com.chs.androiddailytext.widget.pop.view.BasePopupView;


/**
 * @author chs
 * date：2019-06-28 10:50
 * des：
 */
public class XPopup {
    private static int animationDuration = 200;
    private static int shadowBgColor = Color.parseColor("#9F000000");

    public static int getAnimationDuration() {
        return animationDuration;
    }
    public static void setShadowBgColor(int color) {
        shadowBgColor = color;
    }

    public static int getShadowBgColor() {
        return shadowBgColor;
    }


    public static class Builder {
        private final PopupInfo popupInfo = new PopupInfo();
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置弹窗依附的View
         *
         * @param atView
         * @return
         */
        public Builder atView(View atView) {
            this.popupInfo.atView = atView;
            return this;
        }
        /**
         * 是否自动打开输入法，当弹窗包含输入框时很有用，默认为false
         *
         * @param autoOpenSoftInput
         * @return
         */
        public Builder autoOpenSoftInput(Boolean autoOpenSoftInput) {
            this.popupInfo.autoOpenSoftInput = autoOpenSoftInput;
            return this;
        }
        /**
         * 设置弹窗显示和隐藏的回调监听
         *
         * @param xPopupCallback
         * @return
         */
        public Builder setPopupCallback(XPopupCallback xPopupCallback) {
            this.popupInfo.xPopupCallback = xPopupCallback;
            return this;
        }
        public BasePopupView asCustom(BasePopupView popupView) {
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        /**
         * 设置点击弹窗外面是否关闭弹窗，默认为true
         *
         * @param isDismissOnTouchOutside
         * @return
         */
        public Builder dismissOnTouchOutside(Boolean isDismissOnTouchOutside) {
            this.popupInfo.isDismissOnTouchOutside = isDismissOnTouchOutside;
            return this;
        }
        /**
         * 设置单选还是多选 默认单选
         * @param selectType
         * @return
         */
        public Builder selectType(SelectType selectType) {
            this.popupInfo.mSelectType = selectType;
            return this;
        }
        public Builder hasAllOptions(boolean hasOrNo){
            this.popupInfo.hasAllOptions = hasOrNo;
            return this;
        }
        public Builder hasSubOptions(boolean hasOrNo){
            this.popupInfo.hasSubOption = hasOrNo;
            return this;
        }
        public Builder bigText(boolean isBig){
            this.popupInfo.bigText = isBig;
            return this;
        }
    }
}
