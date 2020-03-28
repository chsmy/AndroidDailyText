package com.chs.lib_common_ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

/**
 *  @author chs
 *  date: 2020-01-17 14:34
 *  des:
 */
class BottomNavigationView(context: Context, attrs: AttributeSet) : RadioGroup(context, attrs) {

    private lateinit var onBottomItemClickListener : (Int) -> Unit

    init {
//        initView(context)
    }

//    private fun initView(context: Context) {
//        orientation = HORIZONTAL
//        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
//        addView(getBottomItemView(context,R.drawable.tab_first_selector,"安"))
//        addView(getBottomItemView(context,R.drawable.tab_second_selector,"中"))
//        addView(getBottomItemView(context,R.drawable.tab_four_selector,"我"))
//        addView(getBottomItemView(context,R.drawable.tab_four_selector,"我"))
//
//        for(i in 0 until this.childCount){
//            val view = getChildAt(i)
//            if(i == 0){
//                val v = view as RadioButton
//                v.isChecked = true
//            }
//            view.setOnClickListener {
//                if(::onBottomItemClickListener.isInitialized){
//                    onBottomItemClickListener(i)
//                }
//            }
//        }
//    }


    private fun getBottomItemView(context: Context,resId:Int,text:String): View {
        val radioButton = RadioButton(context)
        radioButton.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0)
        radioButton.buttonDrawable = null
        radioButton.text = text
        radioButton.gravity = Gravity.CENTER
        val layoutParams = LayoutParams(0,LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1.0f
        radioButton.layoutParams = layoutParams
        return radioButton
    }

    fun setOnBottomClickListener(onBottomItemClickListener: (Int) -> Unit){
        this.onBottomItemClickListener = onBottomItemClickListener
    }

}
